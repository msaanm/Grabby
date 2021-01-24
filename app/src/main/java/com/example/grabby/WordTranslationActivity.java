package com.example.grabby;

import androidx.annotation.MainThread;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.text.CaseMap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grabby.network.model.TranslationModel;
import com.example.grabby.presenter.WordTranslationPresenter;
import com.example.grabby.room.Repository;
import com.example.grabby.room.WordTranslationRepository;
import com.example.grabby.room.model.Translation;
import com.example.grabby.room.model.Word;
import com.example.grabby.view.WordTranslationView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordTranslationActivity extends AppCompatActivity implements WordTranslationView {
    private static final String TAG = "WordTranslationActivity";
    private WordTranslationPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_translation);
        String word= (String) getIntent().getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT);
        Repository repository=new WordTranslationRepository(this);
        presenter= new WordTranslationPresenter(this,repository);
        presenter.loadTranslation(getIntent());
        if(null == word){
            Log.d(TAG, "onCreate: yes you're right");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();

    }

    @Override
    public void showWordTranslation(Word word, List<Translation> translations) {
        showErrorOrWord(((LinearLayout)findViewById(R.id.wordLayout)),((LinearLayout)findViewById(R.id.errorMessage)),((TextView)findViewById(R.id.wordTextView)),word.getWord());
        LinearLayout translationLayout=findViewById(R.id.translationsLayout);
        for (Translation t:translations) {
           translationLayout.addView(getTitleTextView("Definition"));
           translationLayout.addView(getTranslationTextView(t.getTranslation()));
           if(null!=t.getExample()){
               translationLayout.addView(getTitleTextView("Example"));
               translationLayout.addView(getExampleView(t.getExample()));
           }



        }
    }
    public LinearLayout getExampleView(String example){
        LinearLayout linearLayout=new LinearLayout(this);
        TextViewCreator textViewCreator=new TextViewCreator(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView bullet=textViewCreator.setLayoutParameters(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                        .setMarginParameters(0,0,25,6)
                        .setTextSize(TypedValue.COMPLEX_UNIT_SP,16)
                        .setText("\u2022")
                        .getTextView();
        textViewCreator=new TextViewCreator(this);
        TextView exampleTextView=textViewCreator.setLayoutParameters(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                                .setMarginParameters(0,0,0,15)
                                .setText(example)
                                .setTextSize(TypedValue.COMPLEX_UNIT_SP,16)
                                .getTextView();

        linearLayout.addView(bullet);
        linearLayout.addView(exampleTextView);
        return linearLayout;


    }
    public TextView getTitleTextView(String title){
        TextViewCreator textViewCreator=new TextViewCreator(this);
        return textViewCreator.setLayoutParameters(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .setMarginParameters(25,15,15,15)
                .setTextSize(TypedValue.COMPLEX_UNIT_SP,22)
                .setText(title)
                .getTextView();
    }

    @Override
    public void hideProgress() {
        ((ProgressBar)findViewById(R.id.progressBar)).setVisibility(View.GONE);

    }

    @Override
    public void showProgress() {
        ((ProgressBar)findViewById(R.id.progressBar)).setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TextView getTranslationTextView(String translation){
        TextViewCreator textViewCreator=new TextViewCreator(this);
        return textViewCreator.setLayoutParameters(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setMarginParameters(0,0,15,15)
                .setTextSize(TypedValue.COMPLEX_UNIT_SP,16)
                .setBackground(R.drawable.list_rounded_corners_background)
                .setPadding(15,15,15,15)
                .setElevation(20)
                .setText(translation)
                .getTextView();
    }
    @Override
    public void showError(String message) {

        showErrorOrWord(((LinearLayout)findViewById(R.id.errorMessage)),((LinearLayout)findViewById(R.id.wordLayout)),((TextView)findViewById(R.id.errorTextView)),message);

    }
    public void showErrorOrWord(View visible,View gone,TextView textView,String value){
        visible.setVisibility(View.VISIBLE);
        gone.setVisibility(View.GONE);
        textView.setText(value);
    }

    @Override
    public void showWrongWord(String word) {
        showErrorOrWord(((LinearLayout)findViewById(R.id.wordLayout)),((LinearLayout)findViewById(R.id.errorMessage)),((TextView)findViewById(R.id.wordTextView)),word);
    }

    @Override
    public void showInsertError() {
        Toast.makeText(this, "Error while saving the Word", Toast.LENGTH_LONG).show();
    }



    @Override
    public void testApi(Single<List<TranslationModel>> call) {
        SingleObserver<List<TranslationModel>> observer=new SingleObserver<List<TranslationModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<TranslationModel> translationModels) {
                Log.d(TAG, "onResponse: "+translationModels.get(0).getMeanings().get(0).getDefinitions().get(0).getDefinition());

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onFailure: "+e.toString());

            }
        };
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);




    }
}