package com.parse.starter;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;

public class QuestionsAskingActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> questions;

    int i = 0;                                              //Globally to keep track of Question No.

    int sumUser = 0;     //To parse into server

    ParseObject qnAObject; //Global object Declaration

    TextView questionsTextView;
    Button option1Btn;
    Button option2Btn;
    Button option3Btn;
    Button option4Btn;

    String question1 = "Loves a good party";
    String question2 = "Enjoys going to museums and cultural events";
    String question3 = "Likes to talk about feelings and emotions";
    String question4 = "Likes to keep to a schedule";
    String question5 = "Keeps up a steady stream of conversation";
    String question6 = "Likes to talk about intellectual things like art and philosophy";
    String question7 = "Has strict systems for staying organized";
    String question8 = "Is very curious about the world";
    String question9 = "Is loyal to traditions";
    String question10 = "Puts work before play";

    public void showUserList()
    {
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);
        finish();
    }

    public void setQuestionAndOptions()
    {
        option1Btn.setBackground(getResources().getDrawable(R.drawable.round_corners));
        option2Btn.setBackground(getResources().getDrawable(R.drawable.round_corners));
        option3Btn.setBackground(getResources().getDrawable(R.drawable.round_corners));
        option4Btn.setBackground(getResources().getDrawable(R.drawable.round_corners));

        option1Btn.setVisibility(View.VISIBLE);
        option2Btn.setVisibility(View.VISIBLE);
        option3Btn.setVisibility(View.VISIBLE);
        option4Btn.setVisibility(View.VISIBLE);
        i++;
        questionsTextView.setText(questions.get(i-1));
        option1Btn.setText("YES");            // 4
        option2Btn.setText("MAYBE");         // 3
        option3Btn.setText("Don't Know");   // 2
        option4Btn.setText("NO");          // 1

        option1Btn.setOnClickListener(this);
        option2Btn.setOnClickListener(this);
        option3Btn.setOnClickListener(this);
        option4Btn.setOnClickListener(this);

    }

    public void nextQuestion(View view)
    {
        setQuestionAndOptions();
    }

    @Override
    public void onClick(View view) {
        String option = "";
        if(view.getId() == R.id.option1Btn)
        {
            Log.i("OPTION 1 CONTAINS::-->",option1Btn.getText().toString());
            sumUser += 4;
            option = "A";
            option2Btn.setVisibility(View.INVISIBLE);
            option3Btn.setVisibility(View.INVISIBLE);
            option4Btn.setVisibility(View.INVISIBLE);
        }
        else if(view.getId() == R.id.option2Btn)
        {
            Log.i("OPTION 2 CONTAINS::-->",option2Btn.getText().toString());
            sumUser += 3;
            option = "B";
            option1Btn.setVisibility(View.INVISIBLE);
            option3Btn.setVisibility(View.INVISIBLE);
            option4Btn.setVisibility(View.INVISIBLE);
        }
        else if(view.getId() == R.id.option3Btn)
        {
            Log.i("OPTION 3 CONTAINS::-->",option3Btn.getText().toString());
            sumUser += 2;
            option = "C";
            option1Btn.setVisibility(View.INVISIBLE);
            option2Btn.setVisibility(View.INVISIBLE);
            option4Btn.setVisibility(View.INVISIBLE);
        }
        else if(view.getId() == R.id.option4Btn)
        {
            Log.i("OPTION 4 CONTAINS::-->",option4Btn.getText().toString());
            sumUser += 1;
            option = "D";
            option1Btn.setVisibility(View.INVISIBLE);
            option2Btn.setVisibility(View.INVISIBLE);
            option3Btn.setVisibility(View.INVISIBLE);
        }
        qnAObject.put("QuestionNo"+i,option);

        if(i == 10)
        {
            qnAObject.put("UserScore",sumUser);
            showUserList();
        }
        qnAObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException ex) {
                if (ex == null) {
                    Log.i("Parse Result", "Successful!");
                } else {
                    Log.i("Parse Result", "Failed" + ex.toString());
                }
            }
        });
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_btn_change));
        Log.i("SUMUSER-->>",String.valueOf(sumUser));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_asking);


        setTitle("FRIEND-FINDER");

        questionsTextView = findViewById(R.id.questionsTextView);
        option1Btn = findViewById(R.id.option1Btn);
        option2Btn = findViewById(R.id.option2Btn);
        option3Btn = findViewById(R.id.option3Btn);
        option4Btn = findViewById(R.id.option4Btn);

        questions = new ArrayList<String>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);
        questions.add(question10);

        setQuestionAndOptions();
        Log.i("USER---->>>",ParseUser.getCurrentUser().getUsername());
        //Creating Class for QnA:-->
         //ParseObject qnAObject;
         qnAObject = new ParseObject("UserAnswers");
         qnAObject.put("Username", ParseUser.getCurrentUser().getUsername());

    }

}