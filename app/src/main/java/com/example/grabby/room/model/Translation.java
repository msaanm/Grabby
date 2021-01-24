package com.example.grabby.room.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "translations",foreignKeys = @ForeignKey(entity = Word.class,parentColumns = "uid",childColumns = "word_id"))
public class Translation {

    @PrimaryKey(autoGenerate = true)
    private long uid;

    @ColumnInfo(name = "word_id")
    private long wordID;

    @ColumnInfo(name = "example")
    private String example;

    @ColumnInfo(name = "translation")
    private String translation;

    public Translation(String example, String translation) {
        this.example = example;
        this.translation = translation;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getWordID() {
        return wordID;
    }

    public void setWordID(long wordID) {
        this.wordID = wordID;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }


}
