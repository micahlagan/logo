package com.example.moviegenuis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.logging.Level;

public class Levels extends AppCompatActivity {

    private ImageView level1;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        level1  = (ImageView) findViewById(R.id.level1);
        back  = (ImageView) findViewById(R.id.back);


        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(Levels.this, GuessTheWordActivity.class);
                startActivity(intent1);

            }
        });




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(Levels.this, first.class);
                startActivity(intent1);

            }
        });

    }
}