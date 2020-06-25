package com.example.testapp2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.icu.text.Edits;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ProjestsActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_prject = new ArrayList<>();
    private DatabaseReference ProjectRef;
    private FirebaseAuth mAuth;
    private String currentUserID;
    private Toolbar mtoolbar;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projests);

        instlizingToolBar();

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        ProjectRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("MyProject");
        Intializefields();

        final BottomNavigationView navigationView= (BottomNavigationView)findViewById(R.id.bottom_nav1);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 switch (item.getItemId()){
                     case R.id.actoin_recent:
                         Intent intent = new Intent(ProjestsActivity.this,NewActivity.class);
                         startActivity(intent);
                         break;
                     case R.id.actoin_nearby:
                         Toast.makeText(ProjestsActivity.this,"NearBy",Toast.LENGTH_SHORT).show();
                         break;
                 }
                 return true;
            }
        });
        RetriveAndDisplay();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String current_project_name = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(ProjestsActivity.this,ProjectControlerActivity.class);
                intent.putExtra("project name",current_project_name);
                startActivity(intent);
            }
        });


    }

    private void instlizingToolBar() {
        mtoolbar = (Toolbar)findViewById(R.id.project_bar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("              My Projects");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NewActivity.class));
                finish();
            }
        });
    }

    private void RetriveAndDisplay() {
        ProjectRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<>();
                Iterator iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()){
                    set.add(((DataSnapshot)iterator.next()).getKey());
                }
                list_of_prject.clear();
                list_of_prject.addAll(set);
                arrayAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void Intializefields() {
        listView = (ListView)findViewById(R.id.project_list);
        arrayAdapter = new ArrayAdapter<String>(ProjestsActivity.this,android.R.layout.simple_list_item_1,list_of_prject);
        listView.setAdapter(arrayAdapter);
        listView.setBackground(getBaseContext().getDrawable(R.drawable.border));
    }
}
