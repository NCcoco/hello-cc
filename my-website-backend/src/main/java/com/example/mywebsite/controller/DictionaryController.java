package com.example.mywebsite.controller;

import com.example.mywebsite.dto.DictionaryCreateRequest;
import com.example.mywebsite.dto.DictionaryUpdateRequest;
import com.example.mywebsite.model.Dictionary;
import com.example.mywebsite.service.DictionaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dictionaries")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping
    public ResponseEntity<List<Dictionary>> getAllDictionaries() {
        List<Dictionary> dictionaries = dictionaryService.getAllDictionaries();
        return ResponseEntity.ok(dictionaries);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Dictionary>> getDictionariesByCategory(@PathVariable String categoryName) {
        List<Dictionary> dictionaries = dictionaryService.findByCategory(categoryName);
        return ResponseEntity.ok(dictionaries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dictionary> getDictionaryById(@PathVariable Long id) {
        Dictionary dictionary = dictionaryService.getDictionaryById(id);
        if (dictionary != null) {
            return ResponseEntity.ok(dictionary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Dictionary> createDictionary(@Valid @RequestBody DictionaryCreateRequest createRequest) {
        Dictionary dictionary = new Dictionary();
        dictionary.setCategory(createRequest.getCategory());
        dictionary.setItemKey(createRequest.getItemKey());
        dictionary.setItemValue(createRequest.getItemValue());
        dictionary.setSortOrder(createRequest.getSortOrder());
        dictionary.setDescription(createRequest.getDescription());

        Dictionary createdDictionary = dictionaryService.createDictionary(dictionary);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDictionary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dictionary> updateDictionary(@PathVariable Long id, @Valid @RequestBody DictionaryUpdateRequest updateRequest) {
        Dictionary dictionaryDetails = new Dictionary();
        // Only set fields if they are present in the request, to allow partial updates
        if (updateRequest.getCategory() != null) {
            dictionaryDetails.setCategory(updateRequest.getCategory());
        }
        if (updateRequest.getItemKey() != null) {
            dictionaryDetails.setItemKey(updateRequest.getItemKey());
        }
        if (updateRequest.getItemValue() != null) {
            dictionaryDetails.setItemValue(updateRequest.getItemValue());
        }
        if (updateRequest.getSortOrder() != null) {
            dictionaryDetails.setSortOrder(updateRequest.getSortOrder());
        }
        if (updateRequest.getDescription() != null) {
            dictionaryDetails.setDescription(updateRequest.getDescription());
        }

        Dictionary updatedDictionary = dictionaryService.updateDictionary(id, dictionaryDetails);
        if (updatedDictionary != null) {
            return ResponseEntity.ok(updatedDictionary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDictionary(@PathVariable Long id) {
        dictionaryService.deleteDictionary(id);
        return ResponseEntity.noContent().build();
    }
}
