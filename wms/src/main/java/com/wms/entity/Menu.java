package com.wms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author root
 * @since 2023-08-31
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("menu")
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @ApiModelProperty("菜单编码")
    @TableField("menuCode")
    private String menuCode;

    @ApiModelProperty("菜单名字")
    @TableField("menuName")
    private String menuName;

    @ApiModelProperty("菜单级别")
    @TableField("menuLevel")
    private String menuLevel;

    @ApiModelProperty("菜单的父code")
    @TableField("menuParentCode")
    private String menuParentCode;

    @ApiModelProperty("点击触发的函数")
    @TableField("menuClick")
    private String menuClick;

    @ApiModelProperty("权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用")
    @TableField("menuRight")
    private String menuRight;

    @TableField("menuComponent")
    private String menuComponent;

    @TableField("menuIcon")
    private String menuIcon;
}
