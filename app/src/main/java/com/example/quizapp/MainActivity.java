package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private Button btAnswer1;
    private Button btAnswer2;
    private Button btAnswer3;
    private Button btAnswer4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    //ArrayList<String> test = new ArrayList<>();

    String quizData[][] = {{"particle", "粒子", "原子", "電子", "胞子"},
            {"electron", "電子","原子", "粒子", "胞子"},
            {"quantum", "量子", "粒子", "電子", "因子"},
            {"proton", "陽子", "電子", "中性子", "因子"},
            {"neutron", "中性子", "電子", "陽子", "胞子"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLabel = findViewById(R.id.countLabel);
        questionLabel = findViewById(R.id.questionLabel);
        btAnswer1 = findViewById(R.id.btAns1);
        btAnswer2 = findViewById(R.id.btAns2);
        btAnswer3 = findViewById(R.id.btAns3);
        btAnswer4 = findViewById(R.id.btAns4);

        //Store all data of quiz
        for(int i = 0; i<quizData.length; i++){
            ArrayList<String> tmpArray = new ArrayList<>();

            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][3]);
            tmpArray.add(quizData[i][4]);

            quizArray.add(tmpArray);
        }

        showNextQuiz();

    }

    public void showNextQuiz(){
        countLabel.setText("Q" + quizCount);

        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //Get a quiz you want
        ArrayList<String> quiz = quizArray.get(randomNum);

        //Show the question
        questionLabel.setText(quiz.get(0));

        //Set the right answer
        rightAnswer = quiz.get(1);

        //Remove the question(English word) from the array
        quiz.remove(0);

        //Shuffle Both right answer and others
        Collections.shuffle(quiz);

        //Set the each text button
        btAnswer1.setText(quiz.get(0));
        btAnswer2.setText(quiz.get(1));
        btAnswer3.setText(quiz.get(2));
        btAnswer4.setText(quiz.get(3));

        //Remove this question from the quizArray
        quizArray.remove(randomNum);
    }

    public void checkAnswer(View view){

        //Depends on which button was chosen
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;
        if(btnText.equals(rightAnswer)){
            alertTitle = "Correct!";
            rightAnswerCount++;
        } else{
            alertTitle = "Wrong!";
        }

        //Create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer: " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(quizCount == QUIZ_COUNT){
                    // Move to result
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("rightAnswerCount", rightAnswerCount);
                    intent.putExtra("quizCount", QUIZ_COUNT);
                    startActivity(intent);
                } else{
                    quizCount++;
                    showNextQuiz();
                }
            }
        });

        builder.setCancelable(false);
        builder.show();

    }
}
