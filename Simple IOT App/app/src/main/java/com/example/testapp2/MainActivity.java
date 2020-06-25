package com.example.testapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login,signup;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        login = (Button)findViewById(R.id.login);
        signup = (Button)findViewById(R.id.register);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.register:
                Intent intent1 = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent1);
                break;
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mUser!=null){
            SendUserToNewActivity();
        }
        else {
            Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(intent);
        }

    }

    private void SendUserToNewActivity() {
        Intent intent = new Intent(MainActivity.this,NewActivity.class);
        startActivity(intent);
    }
}
