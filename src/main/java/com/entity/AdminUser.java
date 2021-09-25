package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.basis.framework.entity.BaseEntity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("admin_user")
@ApiModel(value="AdminUser对象", description="用户")
public class AdminUser  extends BaseEntity implements Serializable {


    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    @ApiModelProperty(value = "帐户")
    @NotNull
    private String account;

    @ApiModelProperty(value = "密码",required = true)
    @NotNull
    @TableField("pass_word")
    private String passWord;

    @ApiModelProperty(value = "状态,0禁用,1正常,2未激活,3离职")
    private Integer status;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "用户姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "性别 1 男 0女")
    private Integer sex;

    @ApiModelProperty(value = "部门id")
    @TableField("dept_id")
    private Integer deptId;

    @ApiModelProperty(value = "岗位")
    private String post;

    @ApiModelProperty(value = "头像")
    private String img;

    @ApiModelProperty(value = "最后登陆时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "最后登录IP")
    @TableField("last_login_ip")
    private String lastLoginIp;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "部门")
    @TableField(exist = false)
    private AdminDept dept;

    @ApiModelProperty(value = "角色")
    @TableField(exist = false)
    private List<AdminRole> adminRoles;

    @Override
    public String toString() {
        return "AdminUser{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", passWord='" + passWord + '\'' +
                ", status=" + status +
                ", mobile='" + mobile + '\'' +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", deptId=" + deptId +
                ", post='" + post + '\'' +
                ", img='" + img + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", email='" + email + '\'' +
                ", dept=" + dept +
                ", adminRoles=" + adminRoles +
                '}';
    }
}
