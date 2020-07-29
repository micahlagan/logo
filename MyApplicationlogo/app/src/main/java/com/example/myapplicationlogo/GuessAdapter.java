package com.example.moviegenuis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moviegenuis.R;

import java.util.ArrayList;

public class GuessAdapter extends RecyclerView.Adapter<GuessAdapter.MyViewHolder> {
    private ArrayList<Character> wordList;

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public GuessAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guess_letter_item_view, parent, false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //Character letter = wordList.get(position);
        holder.etLetter.setText("");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (null == wordList) return 0;
        return wordList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public EditText etLetter;
        public MyViewHolder(View itemView) {
            super(itemView);
            etLetter = itemView.findViewById(R.id.et_guess_letter);
        }
    }

    public void setWordList(ArrayList<Character> wordList) {
        this.wordList = wordList;
        notifyDataSetChanged();
    }
}
