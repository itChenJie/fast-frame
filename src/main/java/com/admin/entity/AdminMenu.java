package com.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.basis.framework.entity.BaseEntity;

import java.io.Serializable;

/**
 * 菜单
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("admin_menu")
@ApiModel(value="AdminMenu对象", description="菜单")
public class AdminMenu  extends BaseEntity implements Serializable {


    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "父级ID 顶级菜单为0")
    private Integer pid;

    @ApiModelProperty(value = "菜单url")
    private String url;

    @ApiModelProperty(value = "授权 (多个用逗号分隔，如：user:list,user:create)")
    private String perms;

    @ApiModelProperty(value = "类型 0：目录 1：菜单 2：按钮")
    private Integer type;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer num;

    @ApiModelProperty(value = "权限")
    private String role;
}
