package com.example.mywebsite.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "word_definitions")
public class WordDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "definition_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Many WordDefinitions can belong to one DictionaryWord
    @JoinColumn(name = "word_id", nullable = false)
    private DictionaryWord dictionaryWord;

    @Column(name = "part_of_speech", nullable = false, length = 50)
    private String partOfSpeech;

    @OneToMany(mappedBy = "wordDefinition", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Meaning> meanings = new ArrayList<>();

    // One WordDefinition can have one set of Inflections
    @OneToOne(mappedBy = "wordDefinition", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Inflections inflections;

    // Using @ElementCollection for a simple list of Strings (example sentences)
    // This will create a separate table by default (e.g., word_definition_general_example_sentences)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "example_sentences_general", joinColumns = @JoinColumn(name = "definition_id"))
    @Column(name = "sentence_text", columnDefinition = "TEXT")
    private List<String> generalExampleSentences = new ArrayList<>();

    // Constructors
    public WordDefinition() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DictionaryWord getDictionaryWord() { return dictionaryWord; }
    public void setDictionaryWord(DictionaryWord dictionaryWord) { this.dictionaryWord = dictionaryWord; }

    public String getPartOfSpeech() { return partOfSpeech; }
    public void setPartOfSpeech(String partOfSpeech) { this.partOfSpeech = partOfSpeech; }

    public List<Meaning> getMeanings() { return meanings; }
    public void setMeanings(List<Meaning> meanings) {
        this.meanings = meanings;
        for(Meaning meaning : meanings) { meaning.setWordDefinition(this); }
    }
    public void addMeaning(Meaning meaning) {
        this.meanings.add(meaning);
        meaning.setWordDefinition(this);
    }

    public Inflections getInflections() { return inflections; }
    public void setInflections(Inflections inflections) {
        this.inflections = inflections;
        if (inflections != null) {
            inflections.setWordDefinition(this);
        }
    }

    public List<String> getGeneralExampleSentences() { return generalExampleSentences; }
    public void setGeneralExampleSentences(List<String> generalExampleSentences) { this.generalExampleSentences = generalExampleSentences; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordDefinition that = (WordDefinition) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "WordDefinition{" + "id=" + id + ", partOfSpeech='" + partOfSpeech + '\'' + '}';
    }
}
