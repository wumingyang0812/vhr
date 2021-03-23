package com.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value="Salary对象", description="")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "基本工资")
    @TableField("basicSalary")
    private Integer basicsalary;

    @ApiModelProperty(value = "奖金")
    private Integer bonus;

    @ApiModelProperty(value = "午餐补助")
    @TableField("lunchSalary")
    private Integer lunchsalary;

    @ApiModelProperty(value = "交通补助")
    @TableField("trafficSalary")
    private Integer trafficsalary;

    @ApiModelProperty(value = "应发工资")
    @TableField("allSalary")
    private Integer allsalary;

    @ApiModelProperty(value = "养老金基数")
    @TableField("pensionBase")
    private Integer pensionbase;

    @ApiModelProperty(value = "养老金比率")
    @TableField("pensionPer")
    private Float pensionper;

    @ApiModelProperty(value = "启用时间")
    @TableField("createDate")
    private LocalDateTime createdate;

    @ApiModelProperty(value = "医疗基数")
    @TableField("medicalBase")
    private Integer medicalbase;

    @ApiModelProperty(value = "医疗保险比率")
    @TableField("medicalPer")
    private Float medicalper;

    @ApiModelProperty(value = "公积金基数")
    @TableField("accumulationFundBase")
    private Integer accumulationfundbase;

    @ApiModelProperty(value = "公积金比率")
    @TableField("accumulationFundPer")
    private Float accumulationfundper;

    private String name;


}
