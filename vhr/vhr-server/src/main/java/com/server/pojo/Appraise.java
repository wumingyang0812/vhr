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
@ApiModel(value="Appraise对象", description="")
public class Appraise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer eid;

    @ApiModelProperty(value = "考评日期")
    @TableField("appDate")
    private LocalDate appdate;

    @ApiModelProperty(value = "考评结果")
    @TableField("appResult")
    private String appresult;

    @ApiModelProperty(value = "考评内容")
    @TableField("appContent")
    private String appcontent;

    @ApiModelProperty(value = "备注")
    private String remark;


}
