package com.example.mywebsite.model;

public class InflectionsPojo {
    private String plural;
    private String thirdPersonSingular;
    private String pastTense;
    private String pastParticiple;
    private String presentParticiple;

    // Constructors
    public InflectionsPojo() {}

    // Getters and Setters
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
}
