package com.example.braintrainer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

import static android.view.View.INVISIBLE;

public class MainActivity extends AppCompatActivity {
    Button GoButton;
    ArrayList<Integer> Answer= new ArrayList<>();
    int locationCorrectAnswer;
    TextView resultTextView;
    int score=0;
    int numberOfQuestion=0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView textView;
    TextView timeView;
    Button PlayAgainButton;
    ConstraintLayout Gamelayout;

    @SuppressLint("SetTextI18n")
    public void  playAgain(View view){
        score=0;
        numberOfQuestion=0;
        timeView.setText("30s");
        scoreTextView.setText(score +"/"+ numberOfQuestion);
        newQuestion();
        PlayAgainButton.setVisibility(INVISIBLE);

        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long l) {
                timeView.setText(l / 1000 + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                PlayAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }


    @SuppressLint("SetTextI18n")
    public void chooseAnswer(View view){
        if (Integer.toString(locationCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
        }else{
            resultTextView.setText("Wrong:(");
        }
            numberOfQuestion++;
            scoreTextView.setText(score +"/"+ numberOfQuestion);
            newQuestion();
    }

    @SuppressLint("SetTextI18n")
    public  void newQuestion(){
        Random random=new Random();
        int a=random.nextInt(21);
        int b=random.nextInt(21);
        textView.setText(a +" + " + b);

        locationCorrectAnswer=random.nextInt(4);
        Answer.clear();

        for (int i=0; i<4; i++){
            if (i==locationCorrectAnswer){
                Answer.add(a+b);
            }else {
                int wrongAnswer=random.nextInt(41);
                while (wrongAnswer==a+b){
                    wrongAnswer=random.nextInt(41);
                }
                Answer.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(Answer.get(0)));
        button1.setText(Integer.toString(Answer.get(1)));
        button2.setText(Integer.toString(Answer.get(2)));
        button3.setText(Integer.toString(Answer.get(3)));

    }

    public void start(View view){
        GoButton.setVisibility(INVISIBLE);
        playAgain(findViewById(R.id.timeView));
        Gamelayout.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.sumTextview);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timeView = findViewById(R.id.timeView);
        Gamelayout=findViewById(R.id.Gamelayout);
        GoButton = findViewById(R.id.goButton);
        PlayAgainButton=findViewById(R.id.PlayAgainButton);
        GoButton.setVisibility(View.VISIBLE);
        Gamelayout.setVisibility(INVISIBLE);
    }
}