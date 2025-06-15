package com.example.mywebsite.model;

import com.example.mywebsite.util.converter.WordDefinitionListConverter; // Import converter
import jakarta.persistence.*; // JPA annotations
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dictionary_words") // New table name for single-table design
public class DictionaryWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "word_text", nullable = false, unique = true, length = 255)
    private String wordText;

    // This field will be stored as JSON in the database.
    @Convert(converter = WordDefinitionListConverter.class)
    @Column(name = "definitions_json", columnDefinition = "JSON") // Specify column type as JSON for MySQL
    private List<WordDefinitionPojo> definitions = new ArrayList<>();

    // Constructors
    public DictionaryWord() {}

    public DictionaryWord(String wordText) {
        this.wordText = wordText;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWordText() {
        return wordText;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public List<WordDefinitionPojo> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<WordDefinitionPojo> definitions) {
        this.definitions = definitions;
    }

    public void addDefinition(WordDefinitionPojo definition) {
        if (this.definitions == null) {
            this.definitions = new ArrayList<>();
        }
        this.definitions.add(definition);
    }

    // equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictionaryWord that = (DictionaryWord) o;
        if (id != null && that.id != null) {
            return Objects.equals(id, that.id);
        }
        return Objects.equals(wordText, that.wordText);
    }

    @Override
    public int hashCode() {
        // If wordText is unique and non-null, it's a good candidate for hashCode
        // If id is the definitive unique key after persistence, it's better.
        return wordText != null ? Objects.hash(wordText) : Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DictionaryWordEntity{" + // Renamed for clarity if debugging
               "id=" + id +
               ", wordText='" + wordText + '\'' +
               ", definitions count=" + (definitions != null ? definitions.size() : 0) +
               '}';
    }
}
