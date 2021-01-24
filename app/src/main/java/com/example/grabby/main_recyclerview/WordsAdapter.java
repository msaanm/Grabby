package com.example.grabby.main_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grabby.R;
import com.example.grabby.room.model.Word;

import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.ViewHolder>  {
    private List<Word> listdata;

    public WordsAdapter(List<Word> listdata) {
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.wordTextView.setText(listdata.get(position).getWord());
       // holder.translationView.setText(listdata.get(position).getTranslation());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView wordTextView;
        public TextView translationView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wordTextView=itemView.findViewById(R.id.wordTextView);
            translationView=itemView.findViewById(R.id.translationTextView);
        }
    }
}
