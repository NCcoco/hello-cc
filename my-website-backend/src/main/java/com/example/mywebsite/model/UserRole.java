package com.example.mywebsite.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_roles")
public class UserRole {
    private Long userId;
    private Long roleId;
}
