package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Get how many questions you won
        int rightAnswerCount = getIntent().getIntExtra("rightAnswerCount",0);

        //Get how many questions are
        int quizCount = getIntent().getIntExtra("quizCount",0);

        TextView tvRightAnswerCount = findViewById(R.id.resultCountLabel);
        tvRightAnswerCount.setText(rightAnswerCount+"/" + quizCount);
    }

    public void returnTop(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
