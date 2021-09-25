package com.service;

import com.entity.AdminRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.basis.framework.page.PageUtils;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 菜单角色关联表 服务类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
public interface IAdminRoleMenuService extends IService<AdminRoleMenu> {
    /**
    * 分页查询
    * @param params
    * @return
    */
    PageUtils queryPage(Map<String, Object> params);

    /**
    * 添加
    * @param adminRoleMenu
    */
    void addAdminRoleMenu(AdminRoleMenu adminRoleMenu);

    /**
    * 更新
    * @param adminRoleMenu
    */
    void updateAdminRoleMenu(AdminRoleMenu adminRoleMenu);

    /**
    * 删除
    * @param idList
    */
    void deleteAdminRoleMenu(Collection<? extends Serializable> idList);

    /**
    * id查询
    * @param id
    * @return
    */
    AdminRoleMenu findById(Integer id);
}
