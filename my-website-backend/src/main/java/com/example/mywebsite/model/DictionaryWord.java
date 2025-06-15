package com.example.mywebsite.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "words") // Maps to the 'words' table
public class DictionaryWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private Long id;

    @Column(name = "word_text", nullable = false, unique = true)
    private String wordText;

    // One DictionaryWord can have many WordDefinitions (e.g., noun, verb)
    // CascadeType.ALL: if a DictionaryWord is saved/updated/deleted, so are its definitions.
    // OrphanRemoval=true: if a WordDefinition is removed from this list, it's deleted from DB.
    @OneToMany(mappedBy = "dictionaryWord", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER) // Eager fetch for simplicity in this context
    private List<WordDefinition> definitions = new ArrayList<>();

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

    public List<WordDefinition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<WordDefinition> definitions) {
        this.definitions = definitions;
        // Ensure bidirectional relationship is maintained
        for (WordDefinition definition : definitions) {
            definition.setDictionaryWord(this);
        }
    }

    public void addDefinition(WordDefinition definition) {
        this.definitions.add(definition);
        definition.setDictionaryWord(this);
    }

    public void removeDefinition(WordDefinition definition) {
        this.definitions.remove(definition);
        definition.setDictionaryWord(null);
    }

    // equals, hashCode (based on id or business key like wordText)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictionaryWord that = (DictionaryWord) o;
        return Objects.equals(id, that.id) || (id == null && wordText != null && Objects.equals(wordText, that.wordText));
    }

    @Override
    public int hashCode() {
        // Prefer business key for hashCode if id is null (before persistence)
        return id != null ? Objects.hash(id) : Objects.hash(wordText);
    }

    @Override
    public String toString() {
        return "DictionaryWord{" +
               "id=" + id +
               ", wordText='" + wordText + '\'' +
               '}';
    }
}
