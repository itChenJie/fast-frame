package com.admin.controller;


import com.admin.entity.AdminDept;
import com.admin.entity.AdminMenu;
import com.admin.enums.MenuType;
import com.admin.query.AdminDeptQuery;
import com.admin.service.IAdminDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.basis.framework.page.PageUtils;
import org.basis.framework.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 部门
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Api(tags = "部门")
@RestController
@RequestMapping("/adminDept")
public class AdminDeptController {
    @Autowired
    IAdminDeptService adminDeptService;

    /**
    * 列表
    */
    @ApiOperation(value = "列表查询")
    @GetMapping("/list")
    public R list(AdminDeptQuery query){
        PageUtils page = adminDeptService.queryPage(query);
        return R.ok().put("page", page);
    }
    /**
    * 新增部门信息
    *
    * @param adminDept
    * @return AdminDept
    */
    @ApiOperation("新增部门信息")
    @PostMapping("/add")
    public R addAdminDept(@RequestBody AdminDept adminDept) {
        adminDeptService.addAdminDept(adminDept);
        return R.ok();
    }
    /**
    * 更新部门信息
    * @param adminDept
    */
    @ApiOperation("更新部门信息")
    @PostMapping("/update")
    public R updateAdminDept(AdminDept adminDept){
        adminDeptService.updateAdminDept(adminDept);
        return R.ok();
    }

    /**
    * 删除部门
    * @param ids
    * @return
    */
    @ApiOperation("删除部门信息")
    @PostMapping("/delete")
    public R deleteAdminDept(@RequestBody Integer[] ids){
        adminDeptService.deleteAdminDept(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 根据id查询
    */
    @ApiOperation("根据id根据id查询")
    @PostMapping("/findById")
    public R findById(Integer id){
        return R.ok().put("data",adminDeptService.findById(id));
    }

    @ApiOperation("选择部门(添加、修改部门)")
    @GetMapping("/tree")
    public R tree(){
        List<AdminDept> adminMenuList = adminDeptService.findDeptTreeList(0);
        return R.ok().put("deptList",adminMenuList);
    }

    @ApiOperation("选择菜单(添加、修改菜单)")
    @GetMapping("/select")
    public R select(){
        AdminDeptQuery query = new AdminDeptQuery();
        List<AdminDept> adminDeptList = adminDeptService.findAll(query);
        return R.ok().put("deptList",adminDeptList);
    }
}
