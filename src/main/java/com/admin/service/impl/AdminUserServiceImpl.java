package com.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.admin.dao.AdminUserMapper;
import com.admin.entity.AdminDept;
import com.admin.entity.AdminRole;
import com.admin.entity.AdminUser;
import com.admin.enums.UserStatusEnum;
import com.admin.service.IAdminDeptService;
import com.admin.service.IAdminRoleService;
import com.admin.service.IAdminUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.utils.HttpContextUtils;
import com.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.basis.framework.error.RRException;
import org.basis.framework.page.PageUtils;
import org.basis.framework.page.Query;
import org.basis.framework.utils.MD5Util;
import org.basis.framework.utils.R;
import org.basis.framework.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 用户 服务实现类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Service(value="adminUserService")
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser>  implements IAdminUserService  {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IAdminDeptService adminDeptService;
    @Autowired
    private IAdminRoleService adminRoleService;
    //12小时后过期
    private final static int LOGINEXPIRE = 3600 * 3;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminUser> page = this.page(
        new Query<AdminUser>().getPage(params),
        new QueryWrapper<AdminUser>()
            );
        List<AdminUser> records = page.getRecords();
        if (CollectionUtil.isNotEmpty(records)){
            for (AdminUser adminUser : records) {
                if (adminUser.getDeptId()!=null){
                    AdminDept adminDept = adminDeptService.findById(adminUser.getDeptId());
                    adminUser.setDept(adminDept);
                }
            }
        }
        page.setRecords(records);
        return new PageUtils(page);
    }

    @Override
    public void addAdminUser(AdminUser adminUser) {
        List<AdminUser> adminUsers = adminUserMapper.selectList(new QueryWrapper<AdminUser>().eq("account",adminUser.getAccount()));
        if (CollectionUtil.isNotEmpty(adminUsers)){
            throw new RRException("帐户已存在！");
        }
        adminUser.setPassWord(MD5Util.sign(adminUser.getUserName().trim()+adminUser.getPassWord().trim()
                ,adminUser.getPassWord().trim()));
        save(adminUser);
    }

    @Override
    public void updateAdminUser(AdminUser adminUser) {
        updateById(adminUser);
    }

    @Override
    public void deleteAdminUser(Collection<? extends Serializable> idList) {
        removeByIds(idList);
    }

    @Override
    public void updateUserStatus(Long userId, UserStatusEnum userStatus) {
        adminUserMapper.updateStatusByUserId(userId,userStatus.getValue());
    }

    @Override
    public AdminUser findById(Long id) {
        AdminUser adminUser =  adminUserMapper.selectById(id);
        AdminDept adminDept = adminDeptService.findById(adminUser.getDeptId());
        adminUser.setDept(adminDept);
        List<AdminRole> adminRoles = adminRoleService.findAllByUserId(id);
        adminUser.setAdminRoles(adminRoles);
        return adminUser;
    }

    @Override
    public R login(String account, String passWord) {
        Optional<AdminUser> adminUser = adminUserMapper.findByAccount(account).stream().findFirst();
        if (!adminUser.isPresent()){
            return R.error("帐户不存在！");
        }
        String redisKey = MD5Util.sign(String.valueOf(adminUser.get().getUserId()), account);
        String redisValue= redisUtils.get(redisKey);
        redisValue = StringUtils.isEmpty(redisValue)?"0":redisValue;
        String sign = MD5Util.sign(account.trim() + passWord.trim(), passWord);
        if (Integer.valueOf(redisValue)<5 &&!sign.equals(adminUser.get().getPassWord())){
            redisUtils.set(redisKey,Integer.valueOf(redisValue)+1,300);
            return R.error("密码错误！");
        }else if(Integer.valueOf(redisValue)==5){
            redisUtils.set(redisKey,Integer.valueOf(redisValue),300);
            return R.error("账号已被锁5分钟后再操作！");
        }
        String token = TokenGenerator.generateValue();
        redisUtils.set(token,adminUser.get(),LOGINEXPIRE);
        adminUser.get().setLastLoginIp(ServletUtil.getClientIP(HttpContextUtils.getHttpServletRequest()));
        adminUser.get().setLastLoginTime(LocalDateTime.now());
        updateAdminUser(adminUser.get());
        return R.ok().put("token",token);
    }

    @Override
    public R updatePassWord(String account, String oldPassWord, String newPassWord) {
        Optional<AdminUser> adminUser = adminUserMapper.findByAccount(account).stream().findFirst();
        if (!adminUser.isPresent()){
            return R.error("帐户不存在！");
        }
        String sign = MD5Util.sign(account.trim() + oldPassWord.trim(), oldPassWord);
        if (!sign.equals(adminUser.get().getPassWord())){
            return R.error("旧密码不对！");
        }
        adminUser.get().setPassWord(MD5Util.sign(account.trim() + newPassWord.trim(), newPassWord));
        updateById(adminUser.get());
        return R.ok();
    }

    @Override
    public void resetPassWord(Long userId, String passWord) {
        AdminUser adminUser = adminUserMapper.selectById(userId);
        adminUser.setPassWord(MD5Util.sign(adminUser.getAccount().trim() + passWord.trim(), passWord));
        adminUserMapper.updateById(adminUser);
    }

}
