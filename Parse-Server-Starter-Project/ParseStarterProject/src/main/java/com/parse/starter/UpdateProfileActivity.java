package com.parse.starter;

import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;

public class UpdateProfileActivity extends AppCompatActivity {

    EditText bioEditText;
    EditText discordEditText;
    ParseObject profileObject;

    public void getPhoto()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        Uri selectedImage = data.getData();

        if(requestCode == 1 && resultCode == RESULT_OK && data!= null)
        {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                Log.i("Image Selected!!!!","GOOD WORK!!");

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] byteArray = stream.toByteArray();
                ParseFile file = new ParseFile("image.png",byteArray);
                ParseObject object = new ParseObject("Image");
                object.put("image",file);
                object.put("username", ParseUser.getCurrentUser().getUsername());
                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null)
                        {
                            Toast.makeText(UpdateProfileActivity.this, "Image has been Shared!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(UpdateProfileActivity.this, "There has been an issue uploadiang the image:(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1 )
        {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getPhoto();
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sharePic(View view)
    {
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }else{
            getPhoto();
        }
    }

    public void setProfile(View view)
    {
        profileObject.put("Bio", bioEditText.getText().toString());
        profileObject.put("Discord", discordEditText.getText().toString());
        profileObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException ex) {
                if (ex == null) {
                    Log.i("Parse Result", "Successful!");
                    Toast.makeText(UpdateProfileActivity.this, "Profile Updated Succesfully!!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("Parse Result", "Failed" + ex.toString());
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        setTitle(ParseUser.getCurrentUser().getUsername()+"'s Profile");

        bioEditText = findViewById(R.id.bioTextView);
        discordEditText = findViewById(R.id.discordEditText);

        profileObject = new ParseObject("UserAnswers");

    }
}