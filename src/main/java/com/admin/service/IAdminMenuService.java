package com.admin.service;

import com.admin.entity.AdminMenu;
import com.admin.query.AdminMenuQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import org.basis.framework.page.PageUtils;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 菜单 服务类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
public interface IAdminMenuService extends IService<AdminMenu> {
    /**
    * 分页查询
    * @param
    * @return
    */
    PageUtils queryPage(AdminMenuQuery query);

    /**
    * 添加
    * @param adminMenu
    */
    void addAdminMenu(AdminMenu adminMenu);

    /**
    * 更新
    * @param adminMenu
    */
    void updateAdminMenu(AdminMenu adminMenu);

    /**
    * 删除
    * @param idList
    */
    void deleteAdminMenu(List<Integer> idList);

    /**
    * id查询
    * @param id
    * @return
    */
    AdminMenu findById(Integer id);

    /**
     * 查询角色下的菜单
     * @param roleId
     * @return
     */
    List<AdminMenu> findAllByRole(Integer roleId);

    /**
     * 查询员工菜单
     * @param userId
     * @return
     */
    List<AdminMenu> getUserMenuList(Long userId);

    /**
     * 获取所有菜单列表
     * @param menuIdList
     * @return
     */
    List<AdminMenu> getAllMenuList(List<Long> menuIdList);

    /**
     * 查询列表父 ID
     * @param parentId
     * @param menuIdList
     * @return
     */
    List<AdminMenu> queryListParentId(Integer parentId, List<Long> menuIdList);

    /**
     * 查找非按钮列表
     * @return
     */
    List<AdminMenu> findNotButtonList();
}
