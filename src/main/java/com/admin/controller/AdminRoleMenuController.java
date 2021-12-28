package com.admin.controller;


import com.admin.query.AdminRoleMenuQuery;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.admin.service.IAdminRoleMenuService;
import com.admin.entity.AdminRoleMenu;

import org.basis.framework.page.PageUtils;
import org.basis.framework.utils.R;
import java.util.Arrays;
import java.util.Map;

/**
 * 菜单角色关联表
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Api(tags = "菜单角色关联表")
@RestController
@RequestMapping("/adminRoleMenu")
public class AdminRoleMenuController {
    @Autowired
    IAdminRoleMenuService adminRoleMenuService;

    /**
    * 列表
    */
    @ApiOperation(value = "列表查询")
    @RequestMapping("/list")
    public R list(@RequestBody AdminRoleMenuQuery query){
        PageUtils page = adminRoleMenuService.queryPage(query);
        return R.ok().put("page", page);
    }
    /**
    * 新增菜单角色关联表信息
    *
    * @param adminRoleMenu
    * @return AdminRoleMenu
    */
    @ApiOperation("新增菜单角色关联表信息")
    @PostMapping("/add")
    public void addAdminRoleMenu(@RequestBody AdminRoleMenu adminRoleMenu) {
        adminRoleMenuService.addAdminRoleMenu(adminRoleMenu);
    }
    /**
    * 更新菜单角色关联表信息
    * @param adminRoleMenu
    */
    @ApiOperation("更新菜单角色关联表信息")
    @PostMapping("/update")
    public R updateAdminRoleMenu(AdminRoleMenu adminRoleMenu){
        adminRoleMenuService.updateAdminRoleMenu(adminRoleMenu);
        return R.ok();
    }

    /**
    * 删除菜单角色关联表
    * @param ids
    * @return
    */
    @ApiOperation("删除菜单角色关联表信息")
    @PostMapping("/delete")
    public R deleteAdminRoleMenu(@RequestBody Integer[] ids){
        adminRoleMenuService.deleteAdminRoleMenu(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 根据id查询
    */
    @ApiOperation("根据id根据id查询")
    @PostMapping("/findById")
    public R findById(Integer id){
        return R.ok().put("data",adminRoleMenuService.findById(id));
    }
}
