package com.example.testapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    DatabaseReference Ref;
    private Toolbar mtoobar;
    String currentUserId;
    private ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        imageView1 = (ImageView)findViewById(R.id.my_project1);
        imageView2 = (ImageView)findViewById(R.id.my_project2);
        imageView3= (ImageView)findViewById(R.id.my_project3);
        imageView4 = (ImageView)findViewById(R.id.my_project4);
        imageView5 = (ImageView)findViewById(R.id.my_project5);
        imageView6 = (ImageView)findViewById(R.id.my_project6);

        mAuth = FirebaseAuth.getInstance();
        Ref = FirebaseDatabase.getInstance().getReference();
        currentUserId = mAuth.getCurrentUser().getUid();



        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        imageView6.setOnClickListener(this);



    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_project1:
                createProjet();
                break;
            case R.id.my_project2:
                Toast.makeText(NewActivity.this,"Profile Activity",Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_project3:
                Toast.makeText(NewActivity.this,"setting Activity",Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_project4:
                Intent intent = new Intent(NewActivity.this,ProjestsActivity.class);
                startActivity(intent);
                break;
            case R.id.my_project5:
                Toast.makeText(NewActivity.this,"notification Activity",Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_project6:
                Toast.makeText(NewActivity.this,"help Activity",Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void createProjet() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NewActivity.this,R.style.AlertDialog);
        builder.setTitle("Enter Group Name");
        final EditText groupNameField = new EditText(NewActivity.this);
        groupNameField.setHint("e.g INDIAN");
        builder.setView(groupNameField);
        builder.setPositiveButton("create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String group = groupNameField.getText().toString();
                if(TextUtils.isEmpty(group)){
                    Toast.makeText(NewActivity.this,"please write a valid group Name",Toast.LENGTH_SHORT).show();
                }
                else {
                    CreateNewGroup(group);
                }
            }
        });

        builder.setNegativeButton("cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        builder.show();
    }

    private void CreateNewGroup(final String group) {
        Ref.child("Users").child(currentUserId).child("MyProject").child(group).setValue("xyz").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(NewActivity.this,group+" is created",Toast.LENGTH_SHORT).show();
                    SendUserToProjestsActivity();
                }
            }
        });
    }

    private void SendUserToProjestsActivity() {
        Intent intent = new Intent(NewActivity.this,ProjestsActivity.class);
        startActivity(intent);
    }


    private void SendUserToMainActivity() {
        Intent intent = new Intent(NewActivity.this,MainActivity.class);
        startActivity(intent);
    }


}
