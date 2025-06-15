package com.example.mywebsite.model;

import java.util.ArrayList;
import java.util.List;

public class WordDefinitionPojo {
    private String partOfSpeech;
    private List<MeaningPojo> meanings = new ArrayList<>();
    private InflectionsPojo inflections; // Can be null
    private List<String> generalExampleSentences = new ArrayList<>();

    // Constructors
    public WordDefinitionPojo() {}

    // Getters and Setters
    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<MeaningPojo> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<MeaningPojo> meanings) {
        this.meanings = meanings;
    }

    public void addMeaning(MeaningPojo meaning) {
        this.meanings.add(meaning);
    }

    public InflectionsPojo getInflections() {
        return inflections;
    }

    public void setInflections(InflectionsPojo inflections) {
        this.inflections = inflections;
    }

    public List<String> getGeneralExampleSentences() {
        return generalExampleSentences;
    }

    public void setGeneralExampleSentences(List<String> generalExampleSentences) {
        this.generalExampleSentences = generalExampleSentences;
    }
}
