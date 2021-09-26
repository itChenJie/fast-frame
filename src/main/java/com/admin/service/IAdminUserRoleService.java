package com.admin.service;

import com.admin.entity.AdminUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.basis.framework.page.PageUtils;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 用户角色关联表 服务类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
public interface IAdminUserRoleService extends IService<AdminUserRole> {
    /**
    * 分页查询
    * @param params
    * @return
    */
    PageUtils queryPage(Map<String, Object> params);

    /**
    * 添加
    * @param adminUserRole
    */
    void addAdminUserRole(AdminUserRole adminUserRole);

    /**
    * 更新
    * @param adminUserRole
    */
    void updateAdminUserRole(AdminUserRole adminUserRole);

    /**
    * 删除
    * @param idList
    */
    void deleteAdminUserRole(Collection<? extends Serializable> idList);

    /**
    * id查询
    * @param id
    * @return
    */
    AdminUserRole findById(Integer id);
}
