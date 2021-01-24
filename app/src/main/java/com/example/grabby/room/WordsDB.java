package com.example.grabby.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.grabby.room.model.Translation;
import com.example.grabby.room.model.Word;

@Database(entities = {Word.class, Translation.class}, version = 1)
public abstract class WordsDB extends RoomDatabase {
    private static volatile WordsDB INSTANCE;

    public abstract IWordDAO WordDAO();
    public abstract ITranslationDAO TranslationDAO();


    public static WordsDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (WordsDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordsDB.class, "Words.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
