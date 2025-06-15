package com.example.mywebsite.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inflections")
public class Inflections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Or derive from definition_id if truly 1-to-1 without own ID
    @Column(name = "inflection_id")
    private Long id;

    // This establishes a one-to-one relationship from Inflections to WordDefinition
    // The Inflections table's definition_id column is unique.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "definition_id", nullable = false, unique = true)
    private WordDefinition wordDefinition;

    @Column(length = 255)
    private String plural;
    @Column(name = "third_person_singular", length = 255)
    private String thirdPersonSingular;
    @Column(name = "past_tense", length = 255)
    private String pastTense;
    @Column(name = "past_participle", length = 255)
    private String pastParticiple;
    @Column(name = "present_participle", length = 255)
    private String presentParticiple;

    // Constructors
    public Inflections() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public WordDefinition getWordDefinition() { return wordDefinition; }
    public void setWordDefinition(WordDefinition wordDefinition) { this.wordDefinition = wordDefinition; }

    public String getPlural() { return plural; }
    public void setPlural(String plural) { this.plural = plural; }

    public String getThirdPersonSingular() { return thirdPersonSingular; }
    public void setThirdPersonSingular(String thirdPersonSingular) { this.thirdPersonSingular = thirdPersonSingular; }

    public String getPastTense() { return pastTense; }
    public void setPastTense(String pastTense) { this.pastTense = pastTense; }

    public String getPastParticiple() { return pastParticiple; }
    public void setPastParticiple(String pastParticiple) { this.pastParticiple = pastParticiple; }

    public String getPresentParticiple() { return presentParticiple; }
    public void setPresentParticiple(String presentParticiple) { this.presentParticiple = presentParticiple; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inflections that = (Inflections) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Inflections{" + "id=" + id + ", plural='" + plural + '\'' + '}';
    }
}
