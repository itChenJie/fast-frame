package com.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.basis.framework.entity.BaseTimeEntity;
/**
 * 部门
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("admin_dept")
@ApiModel(value="AdminDept对象", description="部门")
public class AdminDept extends BaseTimeEntity  implements Serializable {


    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;

    @ApiModelProperty(value = "父级ID 顶级部门为0")
    private Integer pid;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer num;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "部门类型")
    private Integer type;

    @ApiModelProperty(value = "员工")
    @TableField(exist = false)
    private List<AdminUser> adminUsers;
}
