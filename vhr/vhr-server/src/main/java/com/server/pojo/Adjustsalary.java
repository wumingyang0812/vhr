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
@ApiModel(value="Adjustsalary对象", description="")
public class Adjustsalary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer eid;

    @ApiModelProperty(value = "调薪日期")
    @TableField("asDate")
    private LocalDate asdate;

    @ApiModelProperty(value = "调前薪资")
    @TableField("beforeSalary")
    private Integer beforesalary;

    @ApiModelProperty(value = "调后薪资")
    @TableField("afterSalary")
    private Integer aftersalary;

    @ApiModelProperty(value = "调薪原因")
    private String reason;

    @ApiModelProperty(value = "备注")
    private String remark;


}
