package com.example.grabby.main_recyclerview;

import com.example.grabby.room.model.Translation;
import com.example.grabby.room.model.Word;

public class ListModel {
    Word word;
    Translation translation;

    public ListModel(Word word) {
        this.word = word;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    public ListModel(Word word, Translation translation) {
        this.word = word;
        this.translation = translation;
    }
}
