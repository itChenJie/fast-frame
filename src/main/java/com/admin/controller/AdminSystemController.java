package com.admin.controller;

import com.admin.service.IAdminCaptchaService;
import com.admin.service.IAdminUserService;
import com.admin.vo.LoginVo;
import com.common.annotation.Log;
import com.common.base.AbstractController;
import com.common.base.BaseUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.basis.framework.annotation.IgnoreSecurity;
import org.basis.framework.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/10/28 2:59 下午
 **/
@Log
@RestController
public class AdminSystemController extends AbstractController {
    @Autowired
    IAdminUserService adminUserService;
    @Autowired
    private IAdminCaptchaService captchaService;

    @ApiOperation("登陆")
    @PostMapping("/sys/login")
    @IgnoreSecurity
    public R login(@RequestBody LoginVo loginVo){
//        boolean captcha = captchaService.validate(loginVo.getUuid(), loginVo.getCaptcha());
//        if(!captcha){
//            return R.error("验证码不正确");
//        }
        return adminUserService.login(loginVo.getAccount(), loginVo.getPassword());
    }

    @ApiOperation("验证码")
    @ApiImplicitParam(required = true,name = "uuid",value = "uuid",dataTypeClass = String.class)
    @GetMapping("/captcha.jpg")
    @IgnoreSecurity
    public void captcha(HttpServletResponse response, String uuid)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //获取图片验证码
        BufferedImage image = captchaService.getCaptcha(uuid);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @ApiOperation("退出登陆")
    @PostMapping("/logout")
    public R  logout(HttpServletRequest request){
        adminUserService.logout(BaseUtil.getToken(request));
        return R.ok();
    }
}
