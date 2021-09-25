package com.controller;


import com.common.annotation.IgnoreSecurity;
import com.entity.AdminUser;
import com.service.IAdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.basis.framework.page.PageUtils;
import org.basis.framework.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Map;

/**
 * 用户
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/adminUser")
@Validated
public class AdminUserController {
    @Autowired
    IAdminUserService adminUserService;

    @ApiOperation(value = "列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "page", value = "page",dataTypeClass=Integer.class),
            @ApiImplicitParam(paramType="query", name = "limit", value = "limit",dataTypeClass=Integer.class)
    })
    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adminUserService.queryPage(params);
        return R.ok().put("page", page);
    }

    @ApiOperation("新增用户信息")
    @PostMapping("/add")
    public void addAdminUser(@RequestBody AdminUser adminUser) {
        adminUserService.addAdminUser(adminUser);
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    public R updateAdminUser(AdminUser adminUser){
        adminUserService.updateAdminUser(adminUser);
        return R.ok();
    }

    @ApiOperation("删除用户信息")
    @PostMapping("/delete")
    public R deleteAdminUser(@RequestBody Long[] ids) throws InterruptedException {
        adminUserService.deleteAdminUser(Arrays.asList(ids));
        return R.ok();
    }


    @ApiOperation("根据id查询")
    @PostMapping("/findById")
    public R findById(Long id){
        return R.ok().put("data",adminUserService.findById(id));
    }

    @ApiOperation("登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true,name = "account",value = "账号",dataTypeClass = String.class),
            @ApiImplicitParam(required = true,name = "passWord",value = "密码",dataTypeClass = String.class)
    })
    @PostMapping("/login")
    @IgnoreSecurity
    public R login(@NotNull(message = "账号不能为空") String account,@NotNull(message = "密码不能为空") String passWord){
        return adminUserService.login(account, passWord);
    }

    @ApiOperation("更新密码")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true,name = "account",value = "账号",dataTypeClass = String.class),
            @ApiImplicitParam(required = true,name = "oldPassWord",value = "旧密码",dataTypeClass = String.class),
            @ApiImplicitParam(required = true,name = "newPassWord",value = "新密码",dataTypeClass = String.class)
    })
    @PostMapping("/updatePassWord")
    public R updatePassWord(@NotNull(message = "账号不能为空") String account,@NotNull(message = "旧密码不能为空") String oldPassWord
            ,@NotNull(message = "新密码不能为空") String newPassWord){
        return adminUserService.updatePassWord(account, oldPassWord, newPassWord);
    }

    @ApiOperation("重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true,name = "userId",value = "员工id",dataTypeClass = Long.class),
            @ApiImplicitParam(required = true,name = "passWord",value = "密码",dataTypeClass = String.class),
    })
    @PostMapping("/resetPassWord")
    public R resetPassWord(Long userId,String passWord){
        adminUserService.resetPassWord(userId,passWord);
        return R.ok();
    }
}
