package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 菜单角色关联表
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("admin_role_menu")
@ApiModel(value="AdminRoleMenu对象", description="菜单角色关联表")
public class AdminRoleMenu  implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单id")
    @TableField("menu_id")
    private Long menuId;

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Long roleId;


}
