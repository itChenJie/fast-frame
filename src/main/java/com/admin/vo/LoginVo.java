package com.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Description 登陆vo实体
 * @Author ChenWenJie
 * @Data 2021/10/28 5:50 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="登陆vo实体", description="登陆vo实体")
public class LoginVo {
    @NotNull(message = "账号不能为空")
    @ApiModelProperty(value = "账号")
    private String account;
    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;
    private String uuid;
    @ApiModelProperty(value = "验证码")
    private String captcha;
}
