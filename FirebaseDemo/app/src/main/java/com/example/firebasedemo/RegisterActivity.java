package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Script;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private  EditText email;
    private EditText password;
    private  Button register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        mAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String txt_email=email.getText().toString();
              String txt_password=email.getText().toString();
              if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
                  Toast.makeText(RegisterActivity.this,"Empty Credential!! ",Toast.LENGTH_LONG).show();
              }else if(txt_password.length()<8){
                  Toast.makeText(RegisterActivity.this," Strong Password Please!! ",Toast.LENGTH_LONG).show();
              }else{
                  registerUser(txt_email,txt_password);
              }
            }

            private void registerUser(String email, String password) {
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Registering User Successfull",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(RegisterActivity.this,"Registration is failed",Toast.LENGTH_LONG).show();
                        }


                    }
                });
            }
        });

    }
}