package com.vrushabhhirap.quickcart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.vrushabhhirap.quickcart.R;

public class SignUpActivity extends AppCompatActivity {
    TextView alreadyhaveanacc;
    MaterialButton signInButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        alreadyhaveanacc = findViewById(R.id.alreadyhaveacc);
        signInButton = findViewById(R.id.sign_in_button);

        alreadyhaveanacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class  );
                startActivity(intent);
                finish();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class  );
                startActivity(intent);
                finish();
            }
        });


    }
}