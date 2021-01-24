package com.example.grabby.room;

import com.example.grabby.room.model.Translation;
import com.example.grabby.room.model.Word;

import java.util.List;
import java.util.Observer;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface Repository {
    Completable setTranslations(List<Translation> translations);
    Single<Long> setWord(Word word);
    Single<List<Word>> getallWords();
    Single<Translation> getTranslation(long wordId);
}
