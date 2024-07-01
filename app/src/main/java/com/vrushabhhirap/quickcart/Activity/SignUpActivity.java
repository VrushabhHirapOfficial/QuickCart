package com.vrushabhhirap.quickcart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.vrushabhhirap.quickcart.R;

public class SignUpActivity extends AppCompatActivity {
    TextView alreadyhaveanacc;
    MaterialButton signUnButton;


    ImageView iv_google,iv_facebook,iv_apple;

    TextInputEditText name,email,password;

    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();

        name = findViewById(R.id.etname);
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpass);
        alreadyhaveanacc = findViewById(R.id.alreadyhaveacc);
        signUnButton = findViewById(R.id.signUp);


        iv_google = findViewById(R.id.iv_google_login);
        iv_facebook = findViewById(R.id.iv_facebook_login);
        iv_apple = findViewById(R.id.iv_apple_login);




        alreadyhaveanacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class  );
                startActivity(intent);
                finish();
            }
        });

        signUnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(v);
            }
        });

//        Login with goole facebook and apple

        iv_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iv_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iv_apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public void signUp(View view){
        String username = name.getText().toString();
        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();

        if(TextUtils.isEmpty(username)){
            name.setError("Enter your name");
        }
        if(TextUtils.isEmpty(useremail)){
            email.setError("Enter your email");
        }
        if(TextUtils.isEmpty(userpassword)){
            password.setError("Enter your password");
        }

        auth.createUserWithEmailAndPassword(useremail,userpassword)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(SignUpActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}