package com.example.grabby.network.model;

import java.util.List;

public class TranslationModel {
    private String word;
    private List<Phonetics> phonetics;
    private List<MeaningModel> meanings;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Phonetics> getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(List<Phonetics> phonetics) {
        this.phonetics = phonetics;
    }

    public List<MeaningModel> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<MeaningModel> meanings) {
        this.meanings = meanings;
    }
}
