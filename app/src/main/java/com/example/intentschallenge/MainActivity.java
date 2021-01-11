package com.example.intentschallenge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button createContact;
    ImageView emotion, phone, web, pin;
    private String[] data; //Contains 4 indexes for the 4 pieces of information: web, location, call, and emotion
    public static final int insertInfoActivityCode = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createContact = findViewById(R.id.createContact);
        emotion = findViewById(R.id.emotion);
        phone = findViewById(R.id.phone);
        web = findViewById(R.id.web);
        pin = findViewById(R.id.pin);
        createContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        com.example.intentschallenge.CreateContactActivity.class);
                startActivityForResult(intent,insertInfoActivityCode);
            }
        });
        phone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data[2])));
            }
        });
        web.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(data[3])));
            }
        });
        pin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + data[4])));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == insertInfoActivityCode){

            //If the data is invalid or other activity was cancelled
            if(resultCode == RESULT_CANCELED || data.getStringArrayExtra("data").length != 5){return;}

            //Else set the data equal (it was checked by CreateContactActivity.java before sent
            this.data = data.getStringArrayExtra("data");
            //Change emotion based on what they selected in the previous create contact
            if(this.data[1].equals(getString(R.string.sad)))
                emotion.setImageResource(R.drawable.sad);
            if(this.data[1].equals(getString(R.string.meh)))
                emotion.setImageResource(R.drawable.meh);
            if(this.data[1].equals(getString(R.string.happy)))
                emotion.setImageResource(R.drawable.happy);

            //Make buttons visible
            emotion.setVisibility(View.VISIBLE);
            phone.setVisibility(View.VISIBLE);
            web.setVisibility(View.VISIBLE);
            pin.setVisibility(View.VISIBLE);
        }
    }
}