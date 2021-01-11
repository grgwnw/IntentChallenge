package com.example.intentschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateContactActivity extends AppCompatActivity {
    EditText name, number, website, location;
    ImageView happy,meh,sad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        website = findViewById(R.id.website);
        location = findViewById(R.id.location);
        happy = findViewById(R.id.happy);
        meh = findViewById(R.id.meh);
        sad = findViewById(R.id.sad);
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAcceptableOutput())
                    sendResult(name.getText().toString().trim(),
                            getString(R.string.happy),
                            number.getText().toString().trim(),
                            website.getText().toString().trim(),
                            location.getText().toString().trim());
                else{
                    Toast.makeText(CreateContactActivity.this,"Invalid input. Please try again", Toast.LENGTH_SHORT);
                }
            }
        });
        meh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isAcceptableOutput())
                    sendResult(name.getText().toString().trim(),
                            getString(R.string.meh),
                            number.getText().toString().trim(),
                            website.getText().toString().trim(),
                            location.getText().toString().trim());
                else{
                    Toast.makeText(CreateContactActivity.this,"Invalid input. Please try again", Toast.LENGTH_SHORT);
                }
            }
        });
        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAcceptableOutput())
                    sendResult(name.getText().toString().trim(),
                            getString(R.string.sad),
                            number.getText().toString().trim(),
                            website.getText().toString().trim(),
                            location.getText().toString().trim());
                else{
                    Toast.makeText(CreateContactActivity.this,"Invalid input. Please try again", Toast.LENGTH_SHORT);
                }
            }
        });
    }
    private void sendResult(String name, String emotion, String phone, String web, String pin){
        String[] result = {name,emotion,phone,web,pin};
        setResult(RESULT_OK,(new Intent()).putExtra("data",result));
        CreateContactActivity.this.finish();
    }
    private boolean isAcceptableOutput(){
        return isAcceptableName(name.getText().toString().trim())
                && isAcceptableNumber(number.getText().toString().trim())
                && isAcceptableWebsite(website.getText().toString().trim());
        //Not sure how to check location
    }
    private boolean isAcceptableWebsite(String name){
        if(name.length() < 4)
            return false;
        String domainName = name.substring(name.length() - 4);
        return domainName == ".com" || domainName != ".org"
                || domainName != ".net" || domainName != ".edu"
                || domainName != ".gov";
    }
    private boolean isAcceptableName(String name){
        return (name == null)? false : name.split(" ").length == 2;
    }
    private boolean isAcceptableNumber(String numbers){
        if(numbers == null)
            return false;
        if(numbers.length() != 10) //If it is not the length of a telephone number
            return false;
        for(char number : numbers.toCharArray()){
            int specificNumber = (int)number; //ASCII code of character
            if(specificNumber < 48 || specificNumber > 57) //if it is outside range of numbers
                return false;
        }
        return true;
    }
}