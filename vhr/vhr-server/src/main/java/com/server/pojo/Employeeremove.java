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
@ApiModel(value="Employeeremove对象", description="")
public class Employeeremove implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer eid;

    @ApiModelProperty(value = "调动后部门")
    @TableField("afterDepId")
    private Integer afterdepid;

    @ApiModelProperty(value = "调动后职位")
    @TableField("afterJobId")
    private Integer afterjobid;

    @ApiModelProperty(value = "调动日期")
    @TableField("removeDate")
    private LocalDate removedate;

    @ApiModelProperty(value = "调动原因")
    private String reason;

    private String remark;


}
