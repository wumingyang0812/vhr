package com.server.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="MailSendLog对象", description="")
public class MailSendLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("msgId")
    private String msgid;

    @TableField("empId")
    private Integer empid;

    @ApiModelProperty(value = "0发送中，1发送成功，2发送失败")
    private Integer status;

    @TableField("routeKey")
    private String routekey;

    private String exchange;

    @ApiModelProperty(value = "重试次数")
    private Integer count;

    @ApiModelProperty(value = "第一次重试时间")
    @TableField("tryTime")
    private LocalDate trytime;

    @TableField("createTime")
    private LocalDate createtime;

    @TableField("updateTime")
    private LocalDate updatetime;


}
