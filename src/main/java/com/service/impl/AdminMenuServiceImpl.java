package com.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.entity.AdminMenu;
import com.dao.AdminMenuMapper;
import com.service.IAdminMenuService;
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

/**
 * 菜单 服务实现类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Service(value="adminMenuService")
public class AdminMenuServiceImpl extends ServiceImpl<AdminMenuMapper, AdminMenu>  implements IAdminMenuService  {
    @Autowired
    private AdminMenuMapper adminMenuMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminMenu> page = this.page(
        new Query<AdminMenu>().getPage(params),
        new QueryWrapper<AdminMenu>()
            );
        return new PageUtils(page);
    }

    @Override
    public void addAdminMenu(AdminMenu adminMenu) {
        adminMenu.setPerms(menuPerms(adminMenu));
        save(adminMenu);
    }

    @Override
    public void updateAdminMenu(AdminMenu adminMenu) {
        adminMenu.setPerms(menuPerms(adminMenu));
        updateById(adminMenu);
    }

    @Override
    public void deleteAdminMenu(Collection<? extends Serializable> idList) {
        removeByIds(idList);
    }

    @Override
    public AdminMenu findById(Integer id) {
        return adminMenuMapper.selectById(id);
    }

    @Override
    public List<AdminMenu> findAllByRole(Integer roleId) {
        return adminMenuMapper.findAllByRole(roleId);
    }

    private String menuPerms(AdminMenu adminMenu){
        if (ObjectUtil.isNotEmpty(adminMenu.getPid())){
            List<String> allParentRole = adminMenuMapper.findAllParentRole(adminMenu.getPid());
            if (CollectionUtil.isNotEmpty(allParentRole)){
                allParentRole.add(adminMenu.getRole());
                return CollectionUtil.join(allParentRole, ":");
            }
            return adminMenu.getRole();
        }
        return null;
    }
}
