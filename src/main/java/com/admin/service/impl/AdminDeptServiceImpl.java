package com.admin.service.impl;

import com.admin.dao.AdminDeptMapper;
import com.admin.entity.AdminDept;
import com.admin.entity.AdminUser;
import com.admin.query.AdminDeptQuery;
import com.admin.service.IAdminDeptService;
import com.admin.service.IAdminUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.basis.framework.page.PageUtils;
import org.basis.framework.query.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 部门 服务实现类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Service(value="adminDeptService")
public class AdminDeptServiceImpl extends BaseServiceImpl<AdminDeptMapper, AdminDept> implements IAdminDeptService  {
    @Autowired
    IAdminUserService adminUserService;
    @Override
    public PageUtils queryPage(AdminDeptQuery query) {
        IPage<AdminDept> page  = this.getPageList(query);
        return new PageUtils(page);
    }

    @Override
    public void addAdminDept(AdminDept adminDept) { this.create(adminDept); }

    @Override
    public void updateAdminDept(AdminDept adminDept) {
        update(adminDept);
    }

    @Override
    public void deleteAdminDept(Collection<? extends Serializable> idList) {
        deleteByIds(idList);
    }

    @Override
    public AdminDept findById(Integer id) {
        Optional<AdminDept> adminDept = Optional.ofNullable(get(id));
        if (adminDept.isPresent()){
            List<AdminUser> adminUserList = adminUserService.findAdminUserList(new QueryWrapper<AdminUser>().eq("dept_id", id));
            adminDept.get().setAdminUsers(adminUserList);
        }
        return adminDept.orElse(null);
    }

    /**
     * 查询部门树
     *
     * @return
     */
    @Override
    public List<AdminDept> findDeptTreeList(Integer deptId) {
        List<AdminDept> adminDepts = list();
        List<AdminDept> newAdminDept = new ArrayList<AdminDept>();
        for (AdminDept adminDept : adminDepts) {
            if (adminDept.getPid().equals(deptId)){
                adminDept.setNode(deptNodes(adminDept.getDeptId(),adminDepts));
                newAdminDept.add(adminDept);
            }
        }
        return newAdminDept;
    }

    private List<AdminDept> deptNodes(Integer deptId ,List<AdminDept> adminDepts){
        List<AdminDept> list = new ArrayList();
        for (AdminDept adminDept : adminDepts) {
            if (adminDept.getPid().equals(deptId)){
                adminDept.setNode(deptNodes(adminDept.getDeptId(),adminDepts));
                list.add(adminDept);
            }
        }
        return list;
    }

    @Override
    public List<AdminDept> findAll(AdminDeptQuery query) {
        return this.getList(query);
    }
}
