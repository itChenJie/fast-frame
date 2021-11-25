package com.admin.controller;


import com.admin.entity.AdminUser;
import com.admin.enums.UserStatusEnum;
import com.admin.service.IAdminUserService;
import com.common.base.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.basis.framework.annotation.IgnoreSecurity;
import org.basis.framework.annotation.Permissions;
import org.basis.framework.page.PageUtils;
import org.basis.framework.utils.R;
import org.basis.framework.validation.group.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
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
public class AdminUserController extends AbstractController {
    @Autowired
    IAdminUserService adminUserService;

    @ApiOperation(value = "列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "page", value = "page",dataTypeClass=Integer.class),
            @ApiImplicitParam(paramType="query", name = "limit", value = "limit",dataTypeClass=Integer.class)
    })
    @PostMapping("/list")
    @Permissions("user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adminUserService.queryPage(params);
        return R.ok().put("page", page);
    }

    @ApiOperation("新增用户信息")
    @PostMapping("/add")
    public R addAdminUser(@Validated @RequestBody AdminUser adminUser) {
        adminUserService.addAdminUser(adminUser);
        return R.ok();
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    public R updateAdminUser(@Validated(UpdateGroup.class) @RequestBody AdminUser adminUser){
        adminUserService.updateAdminUser(adminUser);
        return R.ok();
    }

    @ApiOperation("删除用户信息")
    @PostMapping("/delete")
    public R deleteAdminUser(@RequestBody Long[] ids){
        adminUserService.deleteAdminUser(Arrays.asList(ids));
        return R.ok();
    }

    @ApiOperation("更新用户状态")
    @PostMapping("/updateUserStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true,name = "userId",value = "员工id",dataTypeClass = Long.class),
            @ApiImplicitParam(required = true,name = "status",value = "状态,0禁用,1正常,2未激活,3离职 ",dataTypeClass = Integer.class)
    })
    public R updateUserStatus(@NotNull(message = "员工id不能为空")Long userId,@NotNull(message = "员工状态不能为空") Integer status){
        adminUserService.updateUserStatus(userId,UserStatusEnum.of(status));
        return R.ok();
    }


    @ApiOperation(value = "根据id查询",response = AdminUser.class)
    @PostMapping("/findById")
    public R findById(Long id){
        return R.ok().put("data",adminUserService.findById(id));
    }

    /**
     * 获取登录的用户信息
     */
    @ApiOperation("获取登录的用户信息")
    @GetMapping("/info")
    public R info(){
        return R.ok().put("user", getUser());
    }


    @ApiOperation("更新密码")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true,name = "oldPassWord",value = "旧密码",dataTypeClass = String.class),
            @ApiImplicitParam(required = true,name = "newPassWord",value = "新密码",dataTypeClass = String.class)
    })
    @PostMapping("/updatePassWord")
    public R updatePassWord(@NotBlank(message = "旧密码不能为空") String oldPassWord,@NotNull(message = "新密码不能为空") String newPassWord){
        return adminUserService.updatePassWord(getUser().getAccount(), oldPassWord, newPassWord);
    }

    @ApiOperation("重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true,name = "userId",value = "员工id",dataTypeClass = Long.class),
            @ApiImplicitParam(required = true,name = "passWord",value = "新密码",dataTypeClass = String.class),
    })
    @IgnoreSecurity
    @PostMapping("/resetPassWord")
    public R resetPassWord(@NotNull(message = "员工id不能为空")Long userId, @NotBlank(message = "新密码不能为空")String passWord){
        adminUserService.resetPassWord(userId,passWord);
        return R.ok();
    }
}
