package com.example.mywebsite.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("dictionary")
public class Dictionary {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String category; // e.g., "USER_STATUS", "PRODUCT_TYPE"

    private String itemKey; // the actual key, e.g., "STATUS_ACTIVE", "TYPE_ELECTRONICS"

    private String itemValue; // the display value, e.g., "Active", "Electronics"

    private Integer sortOrder; // optional, for ordering

    private String description; // optional

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
