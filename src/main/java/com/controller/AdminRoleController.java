package com.controller;


import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.service.IAdminRoleService;
import com.entity.AdminRole;

import org.basis.framework.page.PageUtils;
import org.basis.framework.utils.R;
import java.util.Arrays;
import java.util.Map;

/**
 * 角色
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Api(tags = "角色")
@RestController
@RequestMapping("/adminRole")
public class AdminRoleController {
    @Autowired
    IAdminRoleService adminRoleService;

    /**
    * 列表
    */
    @ApiOperation(value = "列表查询")
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adminRoleService.queryPage(params);
        return R.ok().put("page", page);
    }
    /**
    * 新增角色信息
    *
    * @param adminRole
    * @return AdminRole
    */
    @ApiOperation("新增角色信息")
    @PostMapping("/add")
    public void addAdminRole(@RequestBody AdminRole adminRole) {
        adminRoleService.addAdminRole(adminRole);
    }
    /**
    * 更新角色信息
    * @param adminRole
    */
    @ApiOperation("更新角色信息")
    @PostMapping("/update")
    public R updateAdminRole(AdminRole adminRole){
        adminRoleService.updateAdminRole(adminRole);
        return R.ok();
    }

    /**
    * 删除角色
    * @param ids
    * @return
    */
    @ApiOperation("删除角色信息")
    @PostMapping("/delete")
    public R deleteAdminRole(@RequestBody Integer[] ids){
        adminRoleService.deleteAdminRole(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 根据id查询
    */
    @ApiOperation("根据id查询")
    @PostMapping("/findById")
    public R findById(Integer id){
        return R.ok().put("data",adminRoleService.findById(id));
    }
}
