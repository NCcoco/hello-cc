package com.example.mywebsite.util.converter;

import com.example.mywebsite.model.WordDefinitionPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Converter
public class WordDefinitionListConverter implements AttributeConverter<List<WordDefinitionPojo>, String> {

    private static final Logger logger = LoggerFactory.getLogger(WordDefinitionListConverter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<WordDefinitionPojo> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null; // Or "[]" if you prefer an empty JSON array string
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            logger.error("Error converting List<WordDefinitionPojo> to JSON string", e);
            // Depending on policy, you might throw a runtime exception or return null/empty
            throw new IllegalArgumentException("Error converting attribute to JSON", e);
        }
    }

    @Override
    public List<WordDefinitionPojo> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<WordDefinitionPojo>>() {});
        } catch (IOException e) {
            logger.error("Error converting JSON string to List<WordDefinitionPojo>", e);
            // Depending on policy, you might throw a runtime exception or return empty list
            throw new IllegalArgumentException("Error converting DB data to attribute", e);
        }
    }
}
