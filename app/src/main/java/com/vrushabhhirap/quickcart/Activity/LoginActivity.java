package com.vrushabhhirap.quickcart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

public class LoginActivity extends AppCompatActivity {
    TextInputEditText email,password;

    private FirebaseAuth auth;
    TextView noacc;
    MaterialButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpass);

        noacc = findViewById(R.id.noacc);
        loginButton = findViewById(R.id.login_button);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class  );
            startActivity(intent);
            finish();
        }

        noacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class  );
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser(v);

//                finish();
            }
        });
    }
    public void loginUser(View view){
        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();

        if(TextUtils.isEmpty(useremail)){
            email.setError("Enter your email");
        }
        if(TextUtils.isEmpty(userpassword)){
            password.setError("Enter your password");
        }

        auth.signInWithEmailAndPassword(useremail,userpassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class  );
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Error : "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}