package com.example.grabby.network;

import com.example.grabby.network.model.TranslationModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface IDictionaryApi {
    @GET("{word}")
    public Observable<Response<List<TranslationModel>>> getTranslation(@Path("word") String word);
}
