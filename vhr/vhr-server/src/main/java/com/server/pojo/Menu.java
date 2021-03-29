package com.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    @TableField("iconCls")
    private String iconcls;

    @TableField("keepAlive")
    private Boolean keepalive;

    @TableField("requireAuth")
    private Boolean requireauth;

    @TableField("parentId")
    private Integer parentid;

    private Boolean enabled;

    // @TableField(exist = false)注解用于是否为数据库表字段，默认为 true。
    @TableField(exist = false)
    private List<Role> roles;

    @ApiModelProperty(value = "子菜单")
    @TableField(exist = false)
    private List<Menu> children;


}
