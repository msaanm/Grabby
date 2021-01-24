package com.example.grabby.room;

import android.content.Context;

import com.example.grabby.room.model.Translation;
import com.example.grabby.room.model.Word;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class WordTranslationRepository implements Repository{
    private WordsDB wordsDB;

    public WordTranslationRepository(Context context) {
        this.wordsDB=WordsDB.getInstance(context);
    }

    @Override
    public Completable setTranslations(List<Translation> translations) {
       return wordsDB.TranslationDAO().setTranslations(translations);

    }

    @Override
    public Single<Long> setWord(Word word) {
        return wordsDB.WordDAO().setWord(word);
    }

    @Override
    public Single<List<Word>> getallWords() {
        return wordsDB.WordDAO().getAll();
    }



    @Override
    public Single<Translation> getTranslation(long wordId) {
        return wordsDB.TranslationDAO().getTranslation(wordId);
    }
}
