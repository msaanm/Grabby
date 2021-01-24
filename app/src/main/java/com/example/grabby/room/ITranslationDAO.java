package com.example.grabby.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.grabby.room.model.Translation;
import com.example.grabby.room.model.Word;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ITranslationDAO {

    @Query("SELECT * FROM words where uid=:id")
    List<Word> getWord(int id);

    @Query("SELECT * FROM translations where word_id=:wordId")
    Single<Translation> getTranslation(long wordId);


    @Insert
    Completable setTranslations(List<Translation> translations);

}
