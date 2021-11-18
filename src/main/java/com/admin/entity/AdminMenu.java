package com.admin.entity;

import com.admin.enums.MenuType;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.common.config.serializer.EnumSerializer;
import com.common.config.serializer.MenuTypeDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.basis.framework.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

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

    @JSONField(serializeUsing = EnumSerializer.class,deserializeUsing = MenuTypeDeserializer.class)
    @ApiModelProperty(value = "类型 0：目录 1：菜单 2：按钮")
    private MenuType type;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer num;

    @ApiModelProperty(value = "权限")
    private String role;

    /**
     * ztree属性
     */
    @TableField(exist=false)
    private Boolean open;

    @TableField(exist=false)
    private List<?> list;
}
