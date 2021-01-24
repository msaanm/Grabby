package com.example.grabby.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "words")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private long uid;

    @ColumnInfo(name = "word")
    private String word;

    @ColumnInfo(name = "audio")
    private String audio;

    @ColumnInfo(name = "correct_count",defaultValue = "0")
    private int correctCount;

    @ColumnInfo(name = "wrong_count",defaultValue = "0")
    private int wrongCount;

    public Word() {
    }

    @ColumnInfo(name = "time",defaultValue = "0")
    private long time;


    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public void setWrongCount(int wrongCount) {
        this.wrongCount = wrongCount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Word(long uid, String word, String audio, int correctCount, int wrongCount, long time) {
        this.uid = uid;
        this.word = word;
        this.audio = audio;
        this.correctCount = correctCount;
        this.wrongCount = wrongCount;
        this.time = time;
    }
}
