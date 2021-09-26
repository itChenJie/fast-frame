package com.admin.controller;


import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.admin.service.IAdminMenuService;
import com.admin.entity.AdminMenu;

import org.basis.framework.page.PageUtils;
import org.basis.framework.utils.R;
import java.util.Arrays;
import java.util.Map;

/**
 * 菜单
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Api(tags = "菜单")
@RestController
@RequestMapping("/adminMenu")
public class AdminMenuController {
    @Autowired
    IAdminMenuService adminMenuService;

    /**
    * 列表
    */
    @ApiOperation(value = "列表查询")
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adminMenuService.queryPage(params);
        return R.ok().put("page", page);
    }
    /**
    * 新增菜单信息
    *
    * @param adminMenu
    * @return AdminMenu
    */
    @ApiOperation("新增菜单信息")
    @PostMapping("/add")
    public void addAdminMenu(@RequestBody AdminMenu adminMenu) {
        adminMenuService.addAdminMenu(adminMenu);
    }
    /**
    * 更新菜单信息
    * @param adminMenu
    */
    @ApiOperation("更新菜单信息")
    @PostMapping("/update")
    public R updateAdminMenu(AdminMenu adminMenu){
        adminMenuService.updateAdminMenu(adminMenu);
        return R.ok();
    }

    /**
    * 删除菜单
    * @param ids
    * @return
    */
    @ApiOperation("删除菜单信息")
    @PostMapping("/delete")
    public R deleteAdminMenu(@RequestBody Integer[] ids){
        adminMenuService.deleteAdminMenu(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 根据id查询
    */
    @ApiOperation("根据id根据id查询")
    @PostMapping("/findById")
    public R findById(Integer id){
        return R.ok().put("data",adminMenuService.findById(id));
    }
}
