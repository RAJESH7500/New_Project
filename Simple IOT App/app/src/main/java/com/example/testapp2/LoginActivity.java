package com.example.testapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextemail,editTextpassword;
    private Button loginbtn;
    private TextView createAccount;
    private FirebaseAuth mAuth;
    private DatabaseReference Ref;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        Ref = FirebaseDatabase.getInstance().getReference();

        initilizefields();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowUserToLogin();
            }
        });
    }





    private void initilizefields() {
        editTextemail = (EditText)findViewById(R.id.Lemail);
        editTextpassword = (EditText)findViewById(R.id.Lpassword);
        loginbtn = (Button)findViewById(R.id.Login);
        createAccount = (TextView)findViewById(R.id.LtextView);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToRegisterActivity();
            }
        });
    }

    private void SendUserToRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    private void AllowUserToLogin() {
        String email = editTextemail.getText().toString();
        String password = editTextpassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            editTextemail.setError("please enter email");
            editTextemail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)){
            editTextpassword.setError("please enter password");
            editTextpassword.requestFocus();
            return;
        }
        else {

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        SendUserToNewActivity();
                        Toast.makeText(LoginActivity.this,"logged suceesfully",Toast.LENGTH_SHORT).show();

                    }
                    else {
                        String message = task.getException().toString();
                        Toast.makeText(LoginActivity.this,"Error"+message,Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void SendUserToNewActivity() {
        Intent intent = new Intent(LoginActivity.this,NewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
