package com.example.grabby.room;

import androidx.constraintlayout.helper.widget.Flow;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.grabby.room.model.Word;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface IWordDAO {

    @Query("SELECT * FROM words")
    Single<List<Word>> getAll();

    @Query("SELECT * FROM words WHERE correct_count=0 and wrong_count=0")
    Single<List<Word>> getNewWords();

    @Query("SELECT * FROM words where correct_count<>0 and wrong_count<>0 Order by wrong_count/(wrong_count+correct_count) desc")
    List<Word> getMostWrongWords();

    @Query("SELECT * FROM words where correct_count<>0 and wrong_count<>0 order by time asc")
    List<Word> getOldWords();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Single<Long> setWord(Word word);
}
