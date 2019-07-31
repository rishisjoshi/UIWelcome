package com.example.uiwelcome;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    ImageView regbackpressed;
    EditText userEmail, userPassword;
    TextView tvLogin;

    //regBtn
    //Button regBtn;
    FirebaseAuth firebaseAuth;

    android.widget.Button regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regBtn = findViewById(R.id.registerationbutton);
        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        tvLogin = findViewById(R.id.tvLogin);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();


                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
//                                //sendEmailVerification();
//                                sendUserData();
//                                firebaseAuth.signOut();
                                Toast.makeText(RegistrationActivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegistrationActivity.this, loginActivity.class));
                            }else{
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }
//                Intent intent = new Intent(RegistrationActivity.this,loginActivity.class);
//                startActivity(intent);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();


        //backpresses
        regbackpressed = findViewById(R.id.regbackpressed);
        regbackpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrationActivity.super.onBackPressed();
//                Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
//                startActivity(intent);

                //finish();
            }
        });

        //

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });


    }

    public boolean validate(){
        Boolean result = false;

        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if(email.isEmpty()){
            Toast.makeText(this, "Please enter the Email address",Toast.LENGTH_LONG).show();
        }
        else if(password.isEmpty()){
            Toast.makeText(this, "Please enter the Password",Toast.LENGTH_LONG).show();
        }
        else{
            result = true;
        }
        return result;
    }
}
