package com.example.mywebsite.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "meanings")
public class Meaning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meaning_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "definition_id", nullable = false)
    private WordDefinition wordDefinition;

    @Column(name = "meaning_text", columnDefinition = "TEXT", nullable = false)
    private String definitionText;

    @Column(name = "example_sentence_for_meaning", columnDefinition = "TEXT")
    private String exampleSentenceForMeaning;

    // Constructors
    public Meaning() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public WordDefinition getWordDefinition() { return wordDefinition; }
    public void setWordDefinition(WordDefinition wordDefinition) { this.wordDefinition = wordDefinition; }

    public String getDefinitionText() { return definitionText; }
    public void setDefinitionText(String definitionText) { this.definitionText = definitionText; }

    public String getExampleSentenceForMeaning() { return exampleSentenceForMeaning; }
    public void setExampleSentenceForMeaning(String exampleSentenceForMeaning) { this.exampleSentenceForMeaning = exampleSentenceForMeaning; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meaning meaning = (Meaning) o;
        return Objects.equals(id, meaning.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Meaning{" + "id=" + id + ", definitionText='" + definitionText + '\'' + '}';
    }
}
