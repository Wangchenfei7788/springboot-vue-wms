package com.wms.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import static com.baomidou.mybatisplus.annotation.FieldFill.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author root
 * @since 2023-09-08
 */
@Getter
@Setter

@Accessors(chain = true)
@TableName("record")
@ApiModel(value = "Record对象", description = "")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("货品id")
    @TableField("good")
    private Integer good;

    @ApiModelProperty("取货人/补货人")
    @TableField("userId")
    private Integer userId;

    @ApiModelProperty("操作人id")
    @TableField("adminId")
    private Integer adminId;

    @ApiModelProperty("数量")
    @TableField("count")
    private Integer count;

    @ApiModelProperty("操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("取货人")
    @TableField("userName")
    private String userName;

    @ApiModelProperty("操作人")
    @TableField("adminname")
    private String adminname;

    @ApiModelProperty("产品")
    @TableField("goodName")
    private String goodName;

    @ApiModelProperty("仓库")
    @TableField("storagename")
    private String storagename;

    @ApiModelProperty("产品分类")
    @TableField("goodtypename")
    private String goodtypename;

    @TableField(exist = false)
    private String action;
}
