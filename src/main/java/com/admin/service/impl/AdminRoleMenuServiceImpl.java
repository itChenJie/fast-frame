package com.admin.service.impl;

import com.admin.entity.AdminRoleMenu;
import com.admin.dao.AdminRoleMenuMapper;
import com.admin.service.IAdminRoleMenuService;
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
 * 菜单角色关联表 服务实现类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Service(value="adminRoleMenuService")
public class AdminRoleMenuServiceImpl extends ServiceImpl<AdminRoleMenuMapper, AdminRoleMenu>  implements IAdminRoleMenuService  {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminRoleMenu> page = this.page(
        new Query<AdminRoleMenu>().getPage(params),
        new QueryWrapper<AdminRoleMenu>()
            );
        return new PageUtils(page);
    }

    @Override
    public void addAdminRoleMenu(AdminRoleMenu adminRoleMenu) {
        save(adminRoleMenu);
    }

    @Override
    public void updateAdminRoleMenu(AdminRoleMenu adminRoleMenu) {
        updateById(adminRoleMenu);
    }

    @Override
    public void deleteAdminRoleMenu(Collection<? extends Serializable> idList) {
        removeByIds(idList);
    }

    @Override
    public AdminRoleMenu findById(Integer id) {
        return getById(id);
    }

}
