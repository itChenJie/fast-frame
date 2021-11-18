package com.admin.controller;


import com.admin.entity.AdminMenu;
import com.admin.enums.MenuType;
import com.admin.service.IAdminMenuService;
import com.admin.service.IAdminRoleService;
import com.common.base.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.basis.framework.page.PageUtils;
import org.basis.framework.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Api(tags = "菜单")
@RestController
@RequestMapping("/adminMenu")
public class AdminMenuController extends AbstractController {
    @Autowired
    IAdminMenuService adminMenuService;
    @Autowired
    IAdminRoleService adminRoleService;
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
    public R addAdminMenu(@RequestBody AdminMenu adminMenu) {
        adminMenuService.addAdminMenu(adminMenu);
        return R.ok();
    }
    /**
    * 更新菜单信息
    * @param adminMenu
    */
    @ApiOperation("更新菜单信息")
    @PostMapping("/update")
    public R updateAdminMenu(@RequestBody AdminMenu adminMenu){
        adminMenuService.updateAdminMenu(adminMenu);
        return R.ok();
    }

    /**
    * 删除菜单
    * @param ids
    * @return
    */
    @ApiOperation("删除菜单信息")
    @GetMapping("/delete")
    public R deleteAdminMenu(Integer[] ids){
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

    @ApiOperation("导航栏菜单")
    @GetMapping("/nav")
    public R nav(){
        List<AdminMenu> menuList = adminMenuService.getUserMenuList(getUserId());
        Set<String> permissions = adminRoleService.getUserPermissions(getUserId());
        return R.ok().put("menuList",menuList).put("permissions",permissions);
    }

    @ApiOperation("选择菜单(添加、修改菜单)")
    @GetMapping("/select")
    public R select(){
        List<AdminMenu> adminMenuList = adminMenuService.findNotButtonList();
        AdminMenu menu = new AdminMenu();
        menu.setMenuId(0);
        menu.setName("一级菜单");
        menu.setPid(-1);
        menu.setType(MenuType.MENU);
        menu.setOpen(true);
        adminMenuList.add(menu);
        return R.ok().put("menuList",adminMenuList);
    }
}
