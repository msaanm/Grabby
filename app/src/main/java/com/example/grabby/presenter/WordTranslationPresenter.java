package com.example.grabby.presenter;

import android.content.Intent;
import android.util.Log;

import com.example.grabby.network.TranslationClient;
import com.example.grabby.network.model.DefinitionModel;
import com.example.grabby.network.model.MeaningModel;
import com.example.grabby.network.model.TranslationModel;
import com.example.grabby.room.Repository;
import com.example.grabby.room.model.Translation;
import com.example.grabby.room.model.Word;
import com.example.grabby.room.model.WordTranslation;
import com.example.grabby.view.WordTranslationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableCache;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WordTranslationPresenter {
    private static final String INTERNAL_ERROR_MSG = "Sorry We Couldn't Get your Translation Please Try Again";
    private WordTranslationView view;
    private Repository repository;
    private static final String TAG = "WordTranslationPresente";
    private CompositeDisposable compositeDisposable=new CompositeDisposable();



    public WordTranslationPresenter(WordTranslationView view,Repository repository) {
        this.view = view;
        this.repository=repository;
    }


    public String getWordFromProcess(Intent intent){
        return (String)intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT);
    }

    public void loadTranslation(Intent intent){
        view.showProgress();
        if(null == getWordFromProcess(intent)){

        }else{

            String wordString=getWordFromProcess(intent);
            TranslationClient client=TranslationClient.getINSTANCE();
            Observable<Response<List<TranslationModel>>> translationModelObservable=client.getTranslation(wordString);
            Observable<WordTranslation> wordTranslationObservable=translationModelObservable.concatMap(listResponse -> {
                if(listResponse.code()==404){
                    return Observable.empty();
                }
                return getConvertToRoomModelObservable(listResponse.body());
            });
            compositeDisposable.add(wordTranslationObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(wordTranslation -> {

                compositeDisposable.add(getAddWordTranslationObservable(wordTranslation).subscribe(() -> {
                    Log.d(TAG, "loadTranslation: completed");
                },e-> {
                    view.showInsertError();
                    Log.d(TAG, "loadTranslation: insert error"+e.toString());
                }));

                //printing the result on the view
                view.hideProgress();
                view.showWordTranslation(wordTranslation.getWord(),wordTranslation.getTranslations());



            },e-> view.showError(INTERNAL_ERROR_MSG),() -> view.showWrongWord(wordString)));


        }
    }

    public void unsubscribe(){
        compositeDisposable.clear();
    }



    private Completable getAddWordTranslationObservable(WordTranslation wordTranslation){
        return repository.setWord(wordTranslation.getWord()).map(aLong -> {

            addWordId(wordTranslation.getTranslations(),aLong);
            return wordTranslation;
        }).subscribeOn(Schedulers.io()).flatMapCompletable(wordTranslation1 -> {
            return repository.setTranslations(wordTranslation.getTranslations());
        }).observeOn(AndroidSchedulers.mainThread());

    }
    private Observable<WordTranslation> getConvertToRoomModelObservable(List<TranslationModel> translationModels){
        return Observable.fromCallable(() -> convertToRoomModel(translationModels));
    }
    private void addWordId(List<Translation> translations,long wordId){
        for (Translation t:translations) {
            t.setWordID(wordId);
        }
    }
    private WordTranslation convertToRoomModel(List<TranslationModel> apiData){
        Log.d(TAG, "convertToRoomModel: "+Thread.currentThread().getName());
        Word word=new Word();
        List<Translation> translations=new ArrayList<>();
        WordTranslation wordTranslation;
        word.setWord(apiData.get(0).getWord());
        word.setAudio(apiData.get(0).getPhonetics().get(0).getAudio());
        for (MeaningModel m: apiData.get(0).getMeanings()) {
            for (DefinitionModel d: m.getDefinitions()) {
                translations.add(new Translation(d.getExample(),d.getDefinition()));

            }

        }
        wordTranslation=new WordTranslation(word,translations);
        return wordTranslation;
    }

}
