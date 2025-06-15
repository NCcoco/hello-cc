package com.example.mywebsite.service;

import com.example.mywebsite.model.DictionaryWord;
import com.example.mywebsite.repository.DictionaryWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Optional for read-only methods

import java.util.Optional;

@Service
public class DictionaryService {

    private final DictionaryWordRepository dictionaryWordRepository;

    @Autowired
    public DictionaryService(DictionaryWordRepository dictionaryWordRepository) {
        this.dictionaryWordRepository = dictionaryWordRepository;
    }

    /**
     * Finds a dictionary word by its text, ignoring case.
     * The repository method already handles fetching related entities due to eager loading
     * defined in DictionaryWord entity. If lazy loading was used, @Transactional might be
     * needed here to keep the session open for Hibernate to fetch related entities.
     * For read-only operations, @Transactional(readOnly = true) can be an optimization.
     *
     * @param wordText The word text to search for.
     * @return An Optional containing the DictionaryWord if found, or an empty Optional otherwise.
     */
    @Transactional(readOnly = true) // Good practice for read operations
    public Optional<DictionaryWord> findWord(String wordText) {
        if (wordText == null || wordText.trim().isEmpty()) {
            return Optional.empty();
        }
        return dictionaryWordRepository.findByWordTextIgnoreCase(wordText.trim());
    }

    // Future methods for adding/updating words could be added here, for example:
    /*
    @Transactional
    public DictionaryWord saveWord(DictionaryWord dictionaryWord) {
        // Add any business logic before saving, e.g., validation, normalization
        return dictionaryWordRepository.save(dictionaryWord);
    }
    */
}
