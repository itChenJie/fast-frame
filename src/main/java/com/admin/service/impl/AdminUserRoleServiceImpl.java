package com.admin.service.impl;

import com.admin.dao.AdminUserRoleMapper;
import com.admin.entity.AdminUserRole;
import com.admin.query.AdminUserRoleQuery;
import com.admin.service.IAdminUserRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.basis.framework.page.PageUtils;
import org.basis.framework.query.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * 用户角色关联表 服务实现类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Service(value="adminUserRoleService")
public class AdminUserRoleServiceImpl extends BaseServiceImpl<AdminUserRoleMapper, AdminUserRole> implements IAdminUserRoleService  {

    @Override
    public PageUtils queryPage(AdminUserRoleQuery query) {
        IPage<AdminUserRole> page = this.getPageList(query);
        return new PageUtils(page);
    }

    @Override
    public void addAdminUserRole(AdminUserRole adminUserRole) {
        create(adminUserRole);
    }

    @Override
    public void updateAdminUserRole(AdminUserRole adminUserRole) {
        update(adminUserRole);
    }

    @Override
    public void deleteAdminUserRole(Collection<? extends Serializable> idList) {
        deleteByIds(idList);
    }

    @Override
    public AdminUserRole findById(Integer id) {
        return get(id);
    }
}
