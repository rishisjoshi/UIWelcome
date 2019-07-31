package com.example.uiwelcome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {

    ImageView backpressed;
    Button loginbutton;
    TextView tvRegister;
    EditText userEmail, userPassword;
    FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginbutton = findViewById(R.id.loginBtnn);
        backpressed = findViewById(R.id.backpressed);
        tvRegister = findViewById(R.id.tvRegister);
        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        // is the user loggen in already
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//
//        if(user != null){
//            finish();
//            startActivity(new Intent(loginActivity.this,LoggedIn.class));
//        }

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

        //Toast.makeText(this,"LoginActivity",Toast.LENGTH_SHORT).show();
        backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(userEmail.getText().toString(),userPassword.getText().toString());
            }
        });

    }

    private void validate(String userName, String userPassword){

        progressDialog.setMessage("Logging In...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    startActivity(new Intent(loginActivity.this,mainpage.class));
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(loginActivity.this,"Login failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
