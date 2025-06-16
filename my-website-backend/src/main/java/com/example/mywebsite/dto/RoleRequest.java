package com.example.mywebsite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleRequest {

    @NotBlank(message = "Role name is required")
    @Size(min = 3, max = 50, message = "Role name must be between 3 and 50 characters")
    @Pattern(regexp = "^ROLE_[A-Z_]+$", message = "Role name must start with 'ROLE_' and contain uppercase letters and underscores only")
    private String name;
}
