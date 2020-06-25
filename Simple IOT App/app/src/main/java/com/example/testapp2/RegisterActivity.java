package com.example.testapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

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

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextemail,editTextpassword;
    private Button signupbtn;
    private TextView RtextView;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    private DatabaseReference Ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        Ref = FirebaseDatabase.getInstance().getReference();

        initilizeFieds();
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowUserToRegister();
            }
        });
        RtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToLoginActivity();
            }
        });
    }

    private void SendUserToLoginActivity() {
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    private void AllowUserToRegister() {
        String email = editTextemail.getText().toString();
        String password = editTextpassword.getText().toString();
        if (TextUtils.isEmpty(email)){
            editTextemail.setError("please enter email");
            editTextemail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)){
            editTextpassword.setError("set a password");
            editTextpassword.requestFocus();
        }
        else {


            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        String currentUserID = mAuth.getCurrentUser().getUid();
                        Ref.child("Users").child(currentUserID).setValue("");
                        SendUserToNewActivity();
                        Toast.makeText(RegisterActivity.this,"Account Created successfully",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String message = task.getException().toString();
                        Toast.makeText(RegisterActivity.this,"Error"+message,Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }
    }

    private void SendUserToNewActivity() {
        Intent intent = new Intent(RegisterActivity.this,NewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void initilizeFieds() {
        editTextemail = (EditText)findViewById(R.id.Remail);
        editTextpassword = (EditText)findViewById(R.id.Rpassword);
        signupbtn = (Button)findViewById(R.id.Rsignup);
        RtextView = (TextView)findViewById(R.id.RtextView);

    }
}
