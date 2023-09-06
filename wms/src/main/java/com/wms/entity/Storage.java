package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-09-06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("storage")
@ApiModel(value = "Storage对象", description = "")
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("仓库名")
    @TableField("name")
    private String name;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
}
