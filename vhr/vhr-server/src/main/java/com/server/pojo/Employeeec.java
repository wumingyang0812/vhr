package com.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

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
@ApiModel(value="Employeeec对象", description="")
public class Employeeec implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "员工编号")
    private Integer eid;

    @ApiModelProperty(value = "奖罚日期")
    @TableField("ecDate")
    private LocalDate ecdate;

    @ApiModelProperty(value = "奖罚原因")
    @TableField("ecReason")
    private String ecreason;

    @ApiModelProperty(value = "奖罚分")
    @TableField("ecPoint")
    private Integer ecpoint;

    @ApiModelProperty(value = "奖罚类别，0：奖，1：罚")
    @TableField("ecType")
    private Integer ectype;

    @ApiModelProperty(value = "备注")
    private String remark;


}
