package com.service;

import com.entity.AdminMenu;
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
    * @param params
    * @return
    */
    PageUtils queryPage(Map<String, Object> params);

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
    void deleteAdminMenu(Collection<? extends Serializable> idList);

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
}
