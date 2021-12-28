package com.admin.service.impl;

import com.admin.dao.AdminRoleMenuMapper;
import com.admin.entity.AdminRoleMenu;
import com.admin.query.AdminRoleMenuQuery;
import com.admin.service.IAdminRoleMenuService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.basis.framework.page.PageUtils;
import org.basis.framework.query.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * 菜单角色关联表 服务实现类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Service(value="adminRoleMenuService")
public class AdminRoleMenuServiceImpl extends BaseServiceImpl<AdminRoleMenuMapper, AdminRoleMenu>  implements IAdminRoleMenuService  {

    @Override
    public PageUtils queryPage(AdminRoleMenuQuery query) {
        IPage<AdminRoleMenu> page = this.getPageList(query);
        return new PageUtils(page);
    }

    @Override
    public void addAdminRoleMenu(AdminRoleMenu adminRoleMenu) {
        create(adminRoleMenu);
    }

    @Override
    public void updateAdminRoleMenu(AdminRoleMenu adminRoleMenu) {
        update(adminRoleMenu);
    }

    @Override
    public void deleteAdminRoleMenu(Collection<? extends Serializable> idList) {
        deleteByIds(idList);
    }

    @Override
    public AdminRoleMenu findById(Integer id) {
        return get(id);
    }

}
