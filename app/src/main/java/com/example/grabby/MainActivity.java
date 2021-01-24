package com.example.grabby;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.os.Bundle;
import android.view.Menu;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grabby.room.Repository;
import com.example.grabby.room.WordTranslationRepository;
import com.example.grabby.room.model.Word;
import com.example.grabby.presenter.MainActivityPresenter;
import com.example.grabby.view.MainActivityView;

import java.util.List;

import com.example.grabby.main_recyclerview.WordsAdapter;


public class MainActivity extends AppCompatActivity implements MainActivityView {
    private Repository dataRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Action Bar Buttons
        dataRepository=new WordTranslationRepository(this);
        MainActivityPresenter presenter=new MainActivityPresenter(dataRepository,this);
        presenter.loadWords();


    }


    //Action Bar Buttons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public void showWords(List<Word> wordsList) {
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        WordsAdapter wordsAdapter=new WordsAdapter(wordsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(wordsAdapter);
    }
}