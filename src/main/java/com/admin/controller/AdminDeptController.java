package com.admin.controller;


import com.admin.entity.AdminMenu;
import com.admin.enums.MenuType;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.admin.service.IAdminDeptService;
import com.admin.entity.AdminDept;

import org.basis.framework.page.PageUtils;
import org.basis.framework.utils.R;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adminDeptService.queryPage(params);
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
    public void addAdminDept(@RequestBody AdminDept adminDept) {
        adminDeptService.addAdminDept(adminDept);
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
    @GetMapping("/select")
    public R select(){
        List<AdminDept> adminMenuList = adminDeptService.findDeptTreeList();
//        AdminMenu menu = new AdminMenu();
//        menu.setMenuId(0);
//        menu.setName("全公司");
//        menu.setPid(-1);
//        menu.setType(MenuType.MENU);
//        menu.setOpen(true);
//        adminMenuList.add(menu);
        return R.ok().put("deptList",adminMenuList);
    }
}
