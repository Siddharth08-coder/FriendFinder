package com.parse.starter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.List;

public class UserFeedActivity extends AppCompatActivity {

    LinearLayout linearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        setTitle(username + "'s Profile");

        String user1Bio = "I am young, facinating, Cool and Ambitious!!";
        String user1Dis = "SHANYA#3030";

        linearLayout = findViewById(R.id.linLayout);

        TextView usernameTextView = findViewById(R.id.usernameTextView);
        TextView bioTextView = findViewById(R.id.bioLOLView);
        TextView discordTextView = findViewById(R.id.discordLOLView);

        usernameTextView.setText(username.toUpperCase());
        Log.i("USERNAME-FINAL-->>",username);

        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("UserAnswers");
        query1.whereEqualTo("Username", username);
        //query1.getInBackground("Bio").toString();
        Log.i("BIO!1-->>",query1.getInBackground("Bio").toString());


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Image");
        query.whereEqualTo("username",username);
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                 if(e == null && objects.size()>0)
                 {
                     for(ParseObject object : objects)
                     {
                         ParseFile file = (ParseFile) object.get("image");
                         file.getDataInBackground(new GetDataCallback() {
                             @Override
                             public void done(byte[] data, ParseException e) {
                                 if( e==null && data!=null)
                                 {
                                     Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                                     ImageView imageView = new ImageView(getApplicationContext());

                                     imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                             ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                                     ));

                                     imageView.setImageBitmap(bitmap);
                                     linearLayout.addView(imageView);
                                 }
                             }
                         });
                     }
                 }
            }
        });

        if(username.equals("Shanaya Nilmal"))
        {
            Log.i("YESSSS","YESSSS");
            bioTextView.setText(user1Bio);
            discordTextView.setText(user1Dis);
        }
        else if(username.equals("Khayti Mishra"))
        {
            bioTextView.setText("Scratch here ▒▒▒▒▒▒▒▒▒▒ to unveil my secret bio.");
            discordTextView.setText("KHAYTI#0101");
        }
        else if(username.equals("Mithali Kapoor"))
        {
            bioTextView.setText("In a world where you can be anyone, be yourself");
            discordTextView.setText("MITHALI#3030");
        }
        else if(username.equals("Siddhi Joshi"))
        {
            bioTextView.setText("Yup, I’m just another Instagram influencer");
            discordTextView.setText("SIDDHI#1212");
        }
        else if(username.equals("Stuti Nehal"))
        {
            bioTextView.setText("No, this isn’t a dream. This is my reality.");
            discordTextView.setText("STUTI#4567");
        }
        else if(username.equals("Manas Mohan"))
        {
            bioTextView.setText("I’m a cupcake in search for her stud muffin");
            discordTextView.setText("MANAS#4092");
        }
        else if(username.equals("Kabir Lal"))
        {
            bioTextView.setText("Success is in my veins");
            discordTextView.setText("KABIR#1472");
        }
        else if(username.equals("Devrishi"))
        {
            bioTextView.setText("If I was a writer, I’d have a better Friend Finder bio quote");
            discordTextView.setText("DEVI#4098");
        }
        else if(username.equals("Dev Singh"))
        {
            bioTextView.setText("Here’s my story for the history books");
            discordTextView.setText("DEV#3021");
        }
        else if(username.equals("Rahul Srivastav"))
        {
            bioTextView.setText("The best things come from living outside of your comfort zone");
            discordTextView.setText("RAHUL#1920");
        }
        else if(username.equals("siddharth.singh2020@vitstudent.ac.in"))
        {
            setTitle("SIDDHARTH's Profile");
            usernameTextView.setText("SIDDHARTH SINGH");
            bioTextView.setText("I love programming!");
            discordTextView.setText("SIDBENZ#3015");
        }
        else if(username.equals("susmit.singh2020@vitstudent.ac.in"))
        {
            setTitle("SUSMIT's Profile");
            usernameTextView.setText("SUSMIT SINGH");
            bioTextView.setText("I am cool and I know it!");
            discordTextView.setText("SUSMIT#1992");
        }
        else
        {
            bioTextView.setText("Your are currently not connected to the server. Please contact the developer.");
            discordTextView.setText("DEF#2021");
        }

    }
}