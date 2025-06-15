package com.example.mywebsite.repository;

import com.example.mywebsite.model.DictionaryWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DictionaryWordRepository extends JpaRepository<DictionaryWord, Long> {

    /**
     * Finds a dictionary word by its text, ignoring case.
     * This method will fetch the DictionaryWord along with all its eagerly fetched associations
     * as defined in the DictionaryWord entity (definitions, meanings, inflections).
     *
     * @param wordText The word text to search for.
     * @return An Optional containing the DictionaryWord if found, or an empty Optional otherwise.
     */
    Optional<DictionaryWord> findByWordTextIgnoreCase(String wordText);
}
