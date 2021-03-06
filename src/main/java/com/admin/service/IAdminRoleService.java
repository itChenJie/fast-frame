package com.admin.service;

import com.admin.entity.AdminRole;
import com.admin.query.AdminRoleQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import org.basis.framework.page.PageUtils;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 角色 服务类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
public interface IAdminRoleService extends IService<AdminRole> {
    /**
    * 分页查询
    * @param query
    * @return
    */
    PageUtils queryPage(AdminRoleQuery query);

    /**
    * 添加
    * @param adminRole
    */
    void addAdminRole(AdminRole adminRole);

    /**
    * 更新
    * @param adminRole
    */
    void updateAdminRole(AdminRole adminRole);

    /**
    * 删除
    * @param idList
    */
    void deleteAdminRole(Collection<? extends Serializable> idList);

    /**
    * id查询
    * @param id
    * @return
    */
    AdminRole findById(Integer id);

    List<AdminRole> findAllByUserId(Long userId);
    /**
     * 查询员工id下的角色
     * @param userId
     * @param isMenus
     * @return
     */
    List<AdminRole> findAllByUserId(Long userId,boolean isMenus);

    /**
     * 获取用户权限列表
     * @param userId
     * @return
     */
    Set<String> getUserPermissions(Long userId);
}
