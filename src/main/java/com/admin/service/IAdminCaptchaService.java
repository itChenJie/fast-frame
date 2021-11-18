package com.admin.service;

import java.awt.image.BufferedImage;

/**
 * @Description 接口
 * @Author ChenWenJie
 * @Data 2021/10/28 3:04 下午
 */
public interface IAdminCaptchaService {

    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}
