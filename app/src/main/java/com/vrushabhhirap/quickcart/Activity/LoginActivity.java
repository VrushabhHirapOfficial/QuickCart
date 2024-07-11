package com.vrushabhhirap.quickcart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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
    ImageView google_login,facebook_login, apple_login;

    BeginSignInRequest signInRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpass);
        password.setTransformationMethod(new PasswordTransformationMethod());

        noacc = findViewById(R.id.noacc);
        loginButton = findViewById(R.id.login_button);

        auth = FirebaseAuth.getInstance();


        google_login = findViewById(R.id.iv_google_login);
        facebook_login = findViewById(R.id.iv_facebook_login);
        apple_login = findViewById(R.id.iv_apple_login);

        if(auth.getCurrentUser() != null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class  );
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK    );
            startActivity(intent);
//            finish();
        }

        noacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class  );
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK    );
                startActivity(intent);
//                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser(v);

//                finish();
            }
        });







        google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInRequest = BeginSignInRequest.builder()
                        .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                                .setSupported(true)
                                .setServerClientId(getString(R.string.default_web_client_id))
                                .setFilterByAuthorizedAccounts(true)
                                .build())
                        .build();

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
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK    );
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Error : "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}