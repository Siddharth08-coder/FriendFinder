/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener{

  Boolean signUpModeActive = true;
  TextView loginTextView;

  EditText usernameEditText;
  EditText passwordEditText;

  @Override
  public boolean onKey(View view, int i, KeyEvent keyEvent) {
    if(i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN)
    {
        signUpClicked(view);
    }
    return false;
  }

  @Override
  public void onClick(View view) {
    if(view.getId() == R.id.loginTextView)
    {
      Button signUpButton = findViewById(R.id.signUpButton);
      if(signUpModeActive)
      {
        signUpModeActive = false;
        signUpButton.setText("LOGIN");
        loginTextView.setText("Or, SignUp");
      }
      else
      {
        signUpModeActive = true;
        signUpButton.setText("SIGNUP");
        loginTextView.setText("Or, Login");
      }
    }
    else if( view.getId() == R.id.backgroungLayout)
    {
      InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
      inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
  }


  ParseObject iHackUSERS;
  //ParseObject qnAObject;

  ArrayList<String> item1Answers = new ArrayList<>();
  ArrayList<String> item2Answers = new ArrayList<>();

//This whole intent function will come under QuestionsActivity
  public void showUserList()
  {
      Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
      startActivity(intent);
  }

  public void showQuestionsActivity()
  {
    Intent intent = new Intent(getApplicationContext(), QuestionsAskingActivity.class);
    startActivity(intent);
  }

  public void syncNow(View view)
  {
    Log.i("Item1 Array:10 ",item1Answers.get(0));
    Log.i("Item1 Array:11 ",item1Answers.get(1));
    Log.i("Item1 Array:12 ",item1Answers.get(2));
    Log.i("Item1 Array:13 ",item1Answers.get(3));

    Log.i("Item2 Array:20 ",item2Answers.get(0));
    Log.i("Item2 Array:22 ",item2Answers.get(1));
    Log.i("Item2 Array:23 ",item2Answers.get(2));
    Log.i("Item2 Array:24 ",item2Answers.get(3));



  }


  public void compare()
  {
    Log.i("CHECKPOINT!!","ACHIEVE");
    ParseQuery<ParseObject> item1 = ParseQuery.getQuery("QAnswers");
    item1.getInBackground("tgseAWvIBo", new GetCallback<ParseObject>() {    //Getting username and answers through Id
      @Override
      public void done(ParseObject object, ParseException e) {
        if(e==null && object!=null)
        {
          Log.i("USERNAME TRACKED!!-->",object.getString("Username"));
          Log.i("QUESTION1-->",object.getString("QuestionNo1"));
          Log.i("QUESTION2-->",object.getString("QuestionNo2"));
          Log.i("QUESTION3-->",object.getString("QuestionNo3"));
          Log.i("QUESTION4-->",object.getString("QuestionNo4"));

          //ArrayList<String> item1Answers = new ArrayList<>();
          item1Answers.add(object.getString("QuestionNo1"));
          item1Answers.add(object.getString("QuestionNo2"));
          item1Answers.add(object.getString("QuestionNo3"));
          item1Answers.add(object.getString("QuestionNo4"));

          Log.i("Item1 Array:10 ",item1Answers.get(0));
          Log.i("Item1 Array:11 ",item1Answers.get(1));
          Log.i("Item1 Array:12 ",item1Answers.get(2));
          Log.i("Item1 Array:13 ",item1Answers.get(3));

        }
        else
        {
          if(object==null)
          {
            Log.i("Object mein locha","Hai PAkka");
          }
          Log.i("Object mein locha","Hai");
        }
      }
    });


    ParseQuery<ParseObject> item2 = ParseQuery.getQuery("QAnswers");
    item2.getInBackground("Gt87tFxPX7", new GetCallback<ParseObject>() {    //Getting username and answers through Id
      @Override
      public void done(ParseObject object, ParseException e) {
        if(e==null && object!=null)
        {
          Log.i("USERNAME TRACKED!!-->",object.getString("Username"));
          Log.i("QUESTION1-->",object.getString("QuestionNo1"));
          Log.i("QUESTION2-->",object.getString("QuestionNo2"));
          Log.i("QUESTION3-->",object.getString("QuestionNo3"));
          Log.i("QUESTION4-->",object.getString("QuestionNo4"));

          item2Answers.add(object.getString("QuestionNo1"));
          item2Answers.add(object.getString("QuestionNo2"));
          item2Answers.add(object.getString("QuestionNo3"));
          item2Answers.add(object.getString("QuestionNo4"));

          Log.i("Item2 Array:20 ",item2Answers.get(0));
          Log.i("Item2 Array:22 ",item2Answers.get(1));
          Log.i("Item2 Array:23 ",item2Answers.get(2));
          Log.i("Item2 Array:24 ",item2Answers.get(3));
        }
        else
        {
          if(object==null)
          {
            Log.i("Object mein locha2","Hai PAkka");
          }
          Log.i("Object mein locha","Hai");
        }
      }
    });

  }
  /*
    public void userAnswer()
    {

      qnAObject.put("Username",usernameEditText.getText().toString());
      qnAObject.put("QuestionNo6", "D");
      qnAObject.put("QuestionNo5", "D");
      qnAObject.put("QuestionNo4", "D");
      qnAObject.put("QuestionNo3", "D");
      qnAObject.put("QuestionNo2", "D");
      qnAObject.put("QuestionNo1", "D");
      qnAObject.saveInBackground(new SaveCallback () {
        @Override
        public void done(ParseException ex) {
          if (ex == null) {
            Log.i("Parse Result", "Successful!");
          } else {
            Log.i("Parse Result", "Failed" + ex.toString());
          }
        }
      });
      Log.i("Tis Working!!","YEAH");
    }
  */
    public void signUpClicked(View view)
    {
      if(usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches(""))
      {
        Toast.makeText(this, "A Username and a Password is required!", Toast.LENGTH_SHORT).show();
      }else{

          if(signUpModeActive) {
            ParseUser user = new ParseUser();
            user.setUsername(usernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
              @Override
              public void done(ParseException e) {
                if (e == null) {
                  Log.i("Sign Up", "SUCCESFULL!!!!");
                  showQuestionsActivity();
                } else {
                  Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
              }
            });

            iHackUSERS.put("USERNAME", usernameEditText.getText().toString());
            iHackUSERS.saveInBackground(new SaveCallback() {
              @Override
              public void done(ParseException ex) {
                if (ex == null) {
                  Log.i("Parse Result", "Successful!");
                } else {
                  Log.i("Parse Result", "Failed" + ex.toString());
                }
              }
            });
          }
          else
          {
            //Login
             ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
               @Override
               public void done(ParseUser user, ParseException e) {
                 if(user!=null){
                   Log.i("LOGIN !!","SUCCESS");
                   //showQuestionsActivity();
                   showUserList();
                 }else
                 {
                   Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                 }
               }
             });
          }
        }
      //userAnswer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      setTitle("USER-LOGIN");

      if(ParseUser.getCurrentUser() != null)
      {
        //showUserList();
      }

      usernameEditText = findViewById(R.id.editTextTextPersonName);
      passwordEditText = findViewById(R.id.editTextTextPassword);

      iHackUSERS = new ParseObject("iHackUSERS");


      RelativeLayout backgroundLayout = findViewById(R.id.backgroungLayout);

      backgroundLayout.setOnClickListener(this);

      //Getting UserName from Parse Server
      ParseQuery<ParseObject> query = ParseQuery.getQuery("iHackUSERS");
      query.getInBackground("4yjtXYNYeB", new GetCallback<ParseObject>() {    //Getting username through Id
        @Override
        public void done(ParseObject object, ParseException e) {
          if(e==null && object!=null)
          {
            Log.i("USERNAME TRACKED-->",object.getString("USERNAME"));
          }
        }
      });


      //Creating Class for QnA:-->

      //qnAObject = new ParseObject("QAnswers");
      /*qnAObject.put("Username", "LOL");
      qnAObject.put("QuestionNo6", "A");
      qnAObject.put("QuestionNo5", "B");
      qnAObject.put("QuestionNo4", "C");
      qnAObject.put("QuestionNo3", "D");
      qnAObject.put("QuestionNo2", "C");
      qnAObject.put("QuestionNo1", "A");                              //CLASS/OBJECT Initialization

      qnAObject.saveInBackground(new SaveCallback () {
        @Override
        public void done(ParseException ex) {
          if (ex == null) {
            Log.i("Parse Result", "Successful!");
          } else {
            Log.i("Parse Result", "Failed" + ex.toString());
          }
        }
      });*/
    compare();

    loginTextView = findViewById(R.id.loginTextView);
    loginTextView.setOnClickListener(this);

    passwordEditText.setOnKeyListener(this);
    
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}