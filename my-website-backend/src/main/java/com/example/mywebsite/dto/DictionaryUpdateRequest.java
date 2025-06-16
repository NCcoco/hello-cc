package com.example.mywebsite.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DictionaryUpdateRequest {

    @Size(max = 255, message = "Category cannot exceed 255 characters")
    private String category;

    @Size(max = 255, message = "Item key cannot exceed 255 characters")
    private String itemKey;

    @Size(max = 255, message = "Item value cannot exceed 255 characters")
    private String itemValue;

    private Integer sortOrder;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;
}
