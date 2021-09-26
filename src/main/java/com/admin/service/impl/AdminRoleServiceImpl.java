package com.admin.service.impl;

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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        return adminRoleMapper.findAllByUserId(userId);
    }
}
