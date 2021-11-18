package com.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.admin.entity.AdminMenu;
import com.admin.dao.AdminMenuMapper;
import com.admin.enums.MenuType;
import com.admin.service.IAdminMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.basis.framework.page.PageUtils;
import org.basis.framework.page.Query;
import java.io.Serializable;
import java.util.*;

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
    public void deleteAdminMenu(List<Integer> idList) {
        for (int i = 0; i < idList.size(); i++) {
            // 联动删除下级菜单
            List<AdminMenu> adminMenus = adminMenuMapper.queryListParentId(idList.get(i));
            for (AdminMenu adminMenu : adminMenus) {
                deleteAdminMenu(Collections.singletonList(adminMenu.getMenuId()));
            }
           removeById(idList.get(i));
        }
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
        if (ObjectUtil.isNotEmpty(adminMenu.getPid()) && adminMenu.getPid()!=0){
            List<String> allParentRole = adminMenuMapper.findAllParentRole(adminMenu.getPid());
            if (CollectionUtil.isNotEmpty(allParentRole)){
                allParentRole.add(adminMenu.getRole());
                return CollectionUtil.join(allParentRole, ":");
            }
            return adminMenu.getRole();
        }else {
            return adminMenu.getRole();
        }
    }

    /**
     * 查询员工菜单
     * @param userId
     * @return
     */
    @Override
    public List<AdminMenu> getUserMenuList(Long userId) {

        List<Long> userMenuIds = adminMenuMapper.findUserMenuIds(userId);
        return getAllMenuList(userMenuIds);
    }

    /**
     * 获取所有菜单列表
     */
    @Override
    public List<AdminMenu> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<AdminMenu> menuList = queryListParentId(0, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 查询列表父 ID
     * @param parentId
     * @param menuIdList
     * @return
     */
    @Override
    public List<AdminMenu> queryListParentId(Integer parentId, List<Long> menuIdList) {
        List<AdminMenu> menuList = adminMenuMapper.queryListParentId(parentId);
        if (CollectionUtil.isEmpty(menuList)){
            return menuList;
        }

        List<AdminMenu> userMenuList = new ArrayList<>();
        for(AdminMenu menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 查找非按钮列表
     *
     * @return
     */
    @Override
    public List<AdminMenu> findNotButtonList() {
        return adminMenuMapper.findNotButtonList();
    }

    /**
     * 递归
     * @param menuList
     * @param menuIdList
     */
    private List<AdminMenu> getMenuTreeList(List<AdminMenu> menuList, List<Long> menuIdList) {
        List<AdminMenu> adminMenus = new ArrayList<>();
        for (AdminMenu adminMenu : menuList) {
            if (adminMenu.getType().equals(MenuType.CATALOG)){
                adminMenu.setList(getMenuTreeList(queryListParentId(adminMenu.getMenuId(),menuIdList),menuIdList));
            }
            adminMenus.add(adminMenu);
        }
        return adminMenus;
    }
}
