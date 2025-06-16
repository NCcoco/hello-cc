package com.example.mywebsite.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mywebsite.model.Dictionary;
import com.example.mywebsite.repository.DictionaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    public List<Dictionary> getAllDictionaries() {
        return dictionaryRepository.selectList(new QueryWrapper<Dictionary>().orderByAsc("category", "sort_order", "item_key"));
    }

    public List<Dictionary> findByCategory(String category) {
        return dictionaryRepository.selectList(
            new QueryWrapper<Dictionary>()
                .eq("category", category)
                .orderByAsc("sort_order", "item_key")
        );
    }

    public Dictionary getDictionaryById(Long id) {
        return dictionaryRepository.selectById(id);
    }

    @Transactional
    public Dictionary createDictionary(Dictionary dictionary) {
        // MyBatis-Plus will automatically fill createdAt and updatedAt
        dictionaryRepository.insert(dictionary);
        return dictionary;
    }

    @Transactional
    public Dictionary updateDictionary(Long id, Dictionary dictionaryDetails) {
        Dictionary existingDictionary = dictionaryRepository.selectById(id);
        if (existingDictionary == null) {
            return null; // Or throw ResourceNotFoundException
        }

        // Update fields if provided in dictionaryDetails
        if (dictionaryDetails.getCategory() != null) {
            existingDictionary.setCategory(dictionaryDetails.getCategory());
        }
        if (dictionaryDetails.getItemKey() != null) {
            existingDictionary.setItemKey(dictionaryDetails.getItemKey());
        }
        if (dictionaryDetails.getItemValue() != null) {
            existingDictionary.setItemValue(dictionaryDetails.getItemValue());
        }
        if (dictionaryDetails.getSortOrder() != null) {
            existingDictionary.setSortOrder(dictionaryDetails.getSortOrder());
        }
        if (dictionaryDetails.getDescription() != null) {
            existingDictionary.setDescription(dictionaryDetails.getDescription());
        }
        // Note: createdAt should not be updated. updatedAt will be handled by MyMetaObjectHandler.

        dictionaryRepository.updateById(existingDictionary);
        return existingDictionary;
    }

    @Transactional
    public void deleteDictionary(Long id) {
        dictionaryRepository.deleteById(id);
    }
}
