package com.example.moviegenuis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class first extends AppCompatActivity {

    private ImageView play_button;
    private ImageView setting_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        play_button  = (ImageView) findViewById(R.id.play_button);
       setting_button  = (ImageView) findViewById(R.id.setting_button);

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(first.this, Levels.class);
                startActivity(intent1);

            }
        });



    }
}