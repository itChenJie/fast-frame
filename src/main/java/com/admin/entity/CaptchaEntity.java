package com.admin.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/10/28 3:10 下午
 **/
@Data
public class CaptchaEntity {
    private String uuid;
    private String code;
}
