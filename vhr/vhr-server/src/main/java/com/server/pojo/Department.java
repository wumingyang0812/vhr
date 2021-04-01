package com.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author mingyang
 * @since 2021-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Department对象", description="")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @TableField("parentId")
    private Integer parentid;

    @TableField("depPath")
    private String deppath;

    private Boolean enabled;

    @TableField("isParent")
    private Boolean isparent;

    @ApiModelProperty(value="子部门列表")
    @TableField(exist = false)
    private List<Department> children;

    @ApiModelProperty(value = "返回结果")
    @TableField(exist = false)
    private Integer result;
}
