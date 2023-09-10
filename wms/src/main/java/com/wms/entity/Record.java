package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
    @TableField("createtime")
    private LocalDateTime createtime;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

   @ApiModelProperty("取货人")
    @TableField("username")
    private String username;

    @ApiModelProperty("操作人")
    @TableField("adminname")
    private String adminname;
}
