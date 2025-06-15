package com.example.mywebsite.model;

public class MeaningPojo {
    private String definitionText;
    private String exampleSentenceForMeaning; // Example for this specific meaning

    // Constructors
    public MeaningPojo() {}

    // Getters and Setters
    public String getDefinitionText() {
        return definitionText;
    }

    public void setDefinitionText(String definitionText) {
        this.definitionText = definitionText;
    }

    public String getExampleSentenceForMeaning() {
        return exampleSentenceForMeaning;
    }

    public void setExampleSentenceForMeaning(String exampleSentenceForMeaning) {
        this.exampleSentenceForMeaning = exampleSentenceForMeaning;
    }
}
