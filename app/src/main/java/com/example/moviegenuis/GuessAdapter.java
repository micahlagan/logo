package com.example.moviegenuis;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moviegenuis.R;

import java.util.ArrayList;
import java.util.HashMap;

public class GuessAdapter extends RecyclerView.Adapter<GuessAdapter.MyViewHolder> {
    private ArrayList<Character> wordList;
    HashMap<Integer, RecyclerView.ViewHolder> holderList;

    GuessAdapter(){
        holderList = new HashMap<>();
    }

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.etLetter.setSelectAllOnFocus(true);
        holder.etLetter.selectAll();

        if(!holderList.containsKey(position)){
            holderList.put(position,holder);
        }
        holder.etLetter.setText("");

        holder.etLetter.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!(TextUtils.isEmpty(s.toString().trim()))){
                    if ((position+1) != wordList.size()){
                        EditText itemViewEditText = holderList.get(position+1).itemView.findViewById(R.id.et_guess_letter);
                        itemViewEditText.requestFocus();
                    }
                }else {
                    if ((position) >= 1){
                        EditText itemViewEditText = holderList.get(position-1).itemView.findViewById(R.id.et_guess_letter);
                        itemViewEditText.requestFocus();
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.etLetter.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                //This is the filter
                if (event.getAction()!=KeyEvent.ACTION_DOWN)
                    return true;

                if(keyCode == KeyEvent.KEYCODE_DEL){
                    if (position >= 1){
                        EditText itemViewEditText = holderList.get(position-1).itemView.findViewById(R.id.et_guess_letter);
                        itemViewEditText.setSelectAllOnFocus(true);
                        itemViewEditText.requestFocus();
                    }
                }
                return true;
            }
        });

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
