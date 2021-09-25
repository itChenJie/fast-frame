package com.controller;


import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.service.IAdminUserRoleService;
import com.entity.AdminUserRole;

import org.basis.framework.page.PageUtils;
import org.basis.framework.utils.R;
import java.util.Arrays;
import java.util.Map;

/**
 * 用户角色关联表
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Api(tags = "用户角色关联表")
@RestController
@RequestMapping("/adminUserRole")
public class AdminUserRoleController {
    @Autowired
    IAdminUserRoleService adminUserRoleService;

    /**
    * 列表
    */
    @ApiOperation(value = "列表查询")
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adminUserRoleService.queryPage(params);
        return R.ok().put("page", page);
    }
    /**
    * 新增用户角色关联表信息
    *
    * @param adminUserRole
    * @return AdminUserRole
    */
    @ApiOperation("新增用户角色关联表信息")
    @PostMapping("/add")
    public void addAdminUserRole(@RequestBody AdminUserRole adminUserRole) {
        adminUserRoleService.addAdminUserRole(adminUserRole);
    }
    /**
    * 更新用户角色关联表信息
    * @param adminUserRole
    */
    @ApiOperation("更新用户角色关联表信息")
    @PostMapping("/update")
    public R updateAdminUserRole(AdminUserRole adminUserRole){
        adminUserRoleService.updateAdminUserRole(adminUserRole);
        return R.ok();
    }

    /**
    * 删除用户角色关联表
    * @param ids
    * @return
    */
    @ApiOperation("删除用户角色关联表信息")
    @PostMapping("/delete")
    public R deleteAdminUserRole(@RequestBody Integer[] ids){
        adminUserRoleService.deleteAdminUserRole(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 根据id查询
    */
    @ApiOperation("根据id根据id查询")
    @PostMapping("/findById")
    public R findById(Integer id){
        return R.ok().put("data",adminUserRoleService.findById(id));
    }
}
