package com.service.impl;

import com.entity.AdminDept;
import com.dao.AdminDeptMapper;
import com.entity.AdminUser;
import com.service.IAdminDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.basis.framework.page.PageUtils;
import org.basis.framework.page.Query;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 部门 服务实现类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Service(value="adminDeptService")
public class AdminDeptServiceImpl extends ServiceImpl<AdminDeptMapper, AdminDept>  implements IAdminDeptService  {
    @Autowired
    IAdminUserService adminUserService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminDept> page = this.page(
        new Query<AdminDept>().getPage(params),
        new QueryWrapper<AdminDept>()
            );
        return new PageUtils(page);
    }

    @Override
    public void addAdminDept(AdminDept adminDept) {
        save(adminDept);
    }

    @Override
    public void updateAdminDept(AdminDept adminDept) {
        updateById(adminDept);
    }

    @Override
    public void deleteAdminDept(Collection<? extends Serializable> idList) {
        removeByIds(idList);
    }

    @Override
    public AdminDept findById(Integer id) {
        Optional<AdminDept> adminDept = Optional.ofNullable(getById(id));
        if (adminDept.isPresent()){
            List<AdminUser> adminUserList = adminUserService.findAdminUserList(new QueryWrapper<AdminUser>().eq("dept_id", id));
            adminDept.get().setAdminUsers(adminUserList);
        }
        return adminDept.orElse(null);
    }
}
