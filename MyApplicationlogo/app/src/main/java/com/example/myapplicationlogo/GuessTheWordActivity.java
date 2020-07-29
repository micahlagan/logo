package com.example.moviegenuis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GuessTheWordActivity extends AppCompatActivity {

    private ImageView imgViewQuestion;
    private GuessAdapter guessAdapter;
    private String correctAnswer;
    private ArrayList<Character> wordList;
    private int[] image_list ={
            R.drawable.blogger,
            R.drawable.deviantart,
            R.drawable.digg,
            R.drawable.dropbox,
            R.drawable.evernote,
            R.drawable.facebook,
            R.drawable.flickr,
            R.drawable.google,
            R.drawable.googleplus,
            R.drawable.hyves,
            R.drawable.instagram,
            R.drawable.linkedin,
            R.drawable.myspace,
            R.drawable.picasa,
            R.drawable.pinterest,
            R.drawable.reddit,
            R.drawable.rss,
            R.drawable.skype,
            R.drawable.soundcloud,
            R.drawable.stumbleupon,
            R.drawable.twitter,
            R.drawable.vimeo,
            R.drawable.wordpress,
            R.drawable.yahoo,
            R.drawable.youtube
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_wrod);

        //Init View
        initView();
    }

    private void initView() {
        ConstraintLayout constraintLayout = findViewById(R.id.parent_layout);
        imgViewQuestion = findViewById(R.id.img_logo);
        final RecyclerView recyclerView = findViewById(R.id.rv_guess);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        wordList = new ArrayList<>();
        guessAdapter = new GuessAdapter();
        recyclerView.setAdapter(guessAdapter);

        //Add SetupList Here
        setupList();

        Button btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String wordFromRecyclerView = getWordFromEditTextInRecyclerView(recyclerView);
                Log.e("TAG","wordFromRecyclerView: "+wordFromRecyclerView);
               if (wordFromRecyclerView.equals(correctAnswer)){
                   Toast.makeText(GuessTheWordActivity.this, "Correct Answer, You Won!", Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(GuessTheWordActivity.this, "Wrong Answer, You Lose!", Toast.LENGTH_SHORT).show();
               }

                setupList();
            }
        });

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                view.requestFocus();
            }
        });
    }

    private void setupList() {
        // Random logo
        Random random = new Random();
        int imageSelected = image_list[random.nextInt(image_list.length)];
        imgViewQuestion.setImageResource(imageSelected);

        correctAnswer = getResources().getResourceName(imageSelected);
        correctAnswer = correctAnswer.substring(correctAnswer.lastIndexOf("/")+1);

        wordList.clear();
        wordList.addAll(convertStringToCharList(correctAnswer));

        Log.e("TAG","Correct Word: "+correctAnswer);
        guessAdapter.setWordList(wordList);
    }

    public String getWordFromEditTextInRecyclerView(RecyclerView recyclerView){
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < wordList.size(); i++) {
            View view =  recyclerView.getChildAt(i);
            EditText etLetter = view.findViewById(R.id.et_guess_letter);
            word.append(etLetter.getText().toString());
        }
        return word.toString();
    }

    private List<Character> convertStringToCharList(String str) {
        // Create an empty List of character
        List<Character> chars = new ArrayList<>();
        // For each character in the String
        // add it to the List
        for (char ch : str.toCharArray()) {
            chars.add(ch);
        }
        // return the List
        return chars;
    }

    private void hideKeyboard(View view){
        if((view != null)){
            InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(),0);
        }
    }
}