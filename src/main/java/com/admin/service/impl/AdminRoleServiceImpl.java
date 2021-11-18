package com.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.admin.entity.AdminMenu;
import com.admin.entity.AdminRole;
import com.admin.dao.AdminRoleMapper;
import com.admin.service.IAdminMenuService;
import com.admin.service.IAdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.basis.framework.page.PageUtils;
import org.basis.framework.page.Query;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色 服务实现类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Service(value="adminRoleService")
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole>  implements IAdminRoleService  {
    @Autowired
    AdminRoleMapper adminRoleMapper;
    @Autowired
    IAdminMenuService adminMenuService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminRole> page = this.page(
        new Query<AdminRole>().getPage(params),
        new QueryWrapper<AdminRole>()
            );
        return new PageUtils(page);
    }

    @Override
    public void addAdminRole(AdminRole adminRole) {
        save(adminRole);
    }

    @Override
    public void updateAdminRole(AdminRole adminRole) {
        updateById(adminRole);
    }

    @Override
    public void deleteAdminRole(Collection<? extends Serializable> idList) {
        removeByIds(idList);
    }

    @Override
    public AdminRole findById(Integer id) {
        Optional<AdminRole> adminRole = Optional.ofNullable(getById(id));
        if (adminRole.isPresent()){
            List<AdminMenu> adminMenuList = adminMenuService.findAllByRole(id);
            adminRole.get().setAdminMenus(adminMenuList);
        }
        return adminRole.orElse(null);
    }

    @Override
    public List<AdminRole> findAllByUserId(Long userId) {
        return findAllByUserId(userId,false);
    }

    /**
     * 查询员工id下的角色
     * @param userId
     * @param isMenus  是否查询菜单
     * @return
     */
    @Override
    public List<AdminRole>  findAllByUserId(Long userId,boolean isMenus) {
        List<AdminRole> adminRoles = adminRoleMapper.findAllByUserId(userId);
        if (CollectionUtil.isNotEmpty(adminRoles)){
            for (AdminRole adminRole : adminRoles) {
                if (isMenus){
                    adminRole.setAdminMenus(adminMenuService.findAllByRole(adminRole.getRoleId()));
                }
            }
        }
        return adminRoles;
    }

    /**
     * 获取用户权限列表
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getUserPermissions(Long userId) {
        List<AdminRole> adminRoles = findAllByUserId(userId,true);
        Set<AdminMenu> menuSet = new HashSet<>();
        for (AdminRole adminRole : adminRoles) {
            menuSet.addAll(adminRole.getAdminMenus());
        }
        Set<String> permissions = menuSet.stream().map(item -> item.getPerms()).collect(Collectors.toSet());
        return permissions;
    }

}
