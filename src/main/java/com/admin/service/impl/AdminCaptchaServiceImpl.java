package com.admin.service.impl;

import com.admin.entity.CaptchaEntity;
import com.admin.service.IAdminCaptchaService;
import com.google.code.kaptcha.Producer;
import com.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.basis.framework.error.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/10/28 3:04 下午
 **/
@Service
public class AdminCaptchaServiceImpl implements IAdminCaptchaService {
    @Autowired
    private Producer producer;
    @Autowired
    private RedisUtils redisUtils;

    private final String prefix = "captcha";

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isBlank(uuid)){
            throw new RRException("uuid不能为空");
        }
        //生成文字验证码
        String code = producer.createText();
        CaptchaEntity captchaEntity = new CaptchaEntity();
        captchaEntity.setUuid(uuid);
        captchaEntity.setCode(code);
        redisUtils.set(prefix+uuid,captchaEntity,5*60);
        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        CaptchaEntity captchaEntity = redisUtils.get(prefix + uuid, CaptchaEntity.class);
        if(captchaEntity == null){
            return false;
        }
        //删除验证码
        redisUtils.delete(prefix + uuid);
        if(captchaEntity.getCode().equalsIgnoreCase(code)){
            return true;
        }
        return false;
    }
}
