package com.example.grabby.room.model;

import android.view.SurfaceControl;

import java.util.List;

public class WordTranslation {
    private Word word;

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    public WordTranslation(Word word, List<Translation> translations) {
        this.word = word;
        this.translations = translations;
    }

    private List<Translation> translations;
}
