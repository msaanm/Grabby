
package com.example.grabby.presenter;

import android.content.pm.LabeledIntent;

import com.example.grabby.main_recyclerview.ListModel;
import com.example.grabby.room.IWordDAO;
import com.example.grabby.room.Repository;
import com.example.grabby.room.model.Word;
import com.example.grabby.view.MainActivityView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter {
    private Repository repository;
    private MainActivityView view;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
   

    public MainActivityPresenter(Repository dataRepository, MainActivityView view) {
        this.view = view;
        this.repository=dataRepository;
    }
    public void loadWords(){
        compositeDisposable.add(repository.getallWords()
                .flatMap(words -> {
                    getMainViewTranslations(words);
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(words -> view.showWords(words)));
    }
    public List<ListModel> getMainViewTranslations(List<Word> words){
        List<ListModel> modelList=new ArrayList<>();
        for (Word word :words) {
            modelList.add(new ListModel(word));
        }
        for(ListModel l:modelList){
            compositeDisposable.add(repository.getTranslation(l.getWord().getUid()).subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(translation -> {
                        for(int i=0;i<modelList.size();i++){
                            if(modelList.get(i).getWord().getUid()==translation.getWordID()){
                                modelList.get(i).setTranslation(translation);
                            }
                        }
                    }));
        }
        return words;
    }

    
}
