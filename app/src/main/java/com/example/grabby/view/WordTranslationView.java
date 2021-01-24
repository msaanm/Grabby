package com.example.grabby.view;

import com.example.grabby.network.model.TranslationModel;
import com.example.grabby.room.model.Translation;
import com.example.grabby.room.model.Word;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;

public interface WordTranslationView {
    void showWordTranslation(Word word, List<Translation> translations);
    void showInsertError();
    void showError(String Message);
    void showWrongWord(String word);
    void testApi(Single<List<TranslationModel>> call);
    void showProgress();
    void hideProgress();

}
