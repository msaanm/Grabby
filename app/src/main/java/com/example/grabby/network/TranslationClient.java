package com.example.grabby.network;

import com.example.grabby.network.model.TranslationModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TranslationClient {
    public static final String BASE_URL="https://api.dictionaryapi.dev/api/v2/entries/en/";
    private IDictionaryApi dictionaryApi;
    private static TranslationClient INSTANCE;

    public static TranslationClient getINSTANCE() {
        if(null==INSTANCE){
            INSTANCE=new TranslationClient();

        }
        return INSTANCE;

    }

    public TranslationClient() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        dictionaryApi=retrofit.create(IDictionaryApi.class);

    }
    public Observable<Response<List<TranslationModel>>> getTranslation(String word){
        return dictionaryApi.getTranslation(word);
    }
}
