package com.service.impl;

import com.entity.AdminUserRole;
import com.dao.AdminUserRoleMapper;
import com.service.IAdminUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.basis.framework.page.PageUtils;
import org.basis.framework.page.Query;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 用户角色关联表 服务实现类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Service(value="adminUserRoleService")
public class AdminUserRoleServiceImpl extends ServiceImpl<AdminUserRoleMapper, AdminUserRole>  implements IAdminUserRoleService  {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminUserRole> page = this.page(
        new Query<AdminUserRole>().getPage(params),
        new QueryWrapper<AdminUserRole>()
            );
        return new PageUtils(page);
    }

    @Override
    public void addAdminUserRole(AdminUserRole adminUserRole) {
        save(adminUserRole);
    }

    @Override
    public void updateAdminUserRole(AdminUserRole adminUserRole) {
        updateById(adminUserRole);
    }

    @Override
    public void deleteAdminUserRole(Collection<? extends Serializable> idList) {
        removeByIds(idList);
    }

    @Override
    public AdminUserRole findById(Integer id) {
        return getById(id);
    }
}
