package com.admin.service;

import com.admin.entity.AdminDept;
import com.admin.query.AdminDeptQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import org.basis.framework.page.PageUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 部门 服务类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
public interface IAdminDeptService extends IService<AdminDept> {
    /**
    * 分页查询
    * @param query
    * @return
    */
    PageUtils queryPage(AdminDeptQuery query);

    /**
    * 添加
    * @param adminDept
    */
    void addAdminDept(AdminDept adminDept);

    /**
    * 更新
    * @param adminDept
    */
    void updateAdminDept(AdminDept adminDept);

    /**
    * 删除
    * @param idList
    */
    void deleteAdminDept(Collection<? extends Serializable> idList);

    /**
    * id查询
    * @param id
    * @return
    */
    AdminDept findById(Integer id);

    /**
     * 查询部门树
     * @return
     */
    List<AdminDept> findDeptTreeList(Integer deptId);

    /**
     * 查询所有
     * @param query
     * @return
     */
    List<AdminDept> findAll(AdminDeptQuery query);
}
