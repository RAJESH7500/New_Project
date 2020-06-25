package com.example.testapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.MergeAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class ProjectControlerActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    Button edit,run;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_controler);
        toolbar=(Toolbar)findViewById(R.id.button_toolbar);
        final Intent intent=getIntent();
        String projectname=intent.getStringExtra("project name");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(projectname);

        mContext = getApplicationContext();
        edit = (Button)findViewById(R.id.edit_name);
        run = (Button)findViewById(R.id.run_project);


        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.navigation_view);

        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.openNavDrawer,R.string.closeNavDrawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);
//        final String[] animals = {
//
//        };
//        final String[] animals1 = {
//
//        };
        // Initialize a new String array


        // Intilize an array list from array
        final List<String> animalsList = new ArrayList();
        final List<String> animalsList1 = new ArrayList();
        List<String> animalsList2 = new ArrayList();

        mLayoutManager = new GridLayoutManager(mContext,2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize a new instance of RecyclerView Adapter instance
        final SlideButtonAdapter adapter1 = new SlideButtonAdapter(animalsList,mContext);
        final ToggleButtonAdapter adapter2 = new ToggleButtonAdapter(animalsList1,mContext);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.slid1:
                        int pos=animalsList.size()+animalsList1.size();
                        animalsList.add("button"+pos);

                        mRecyclerView.scrollToPosition(pos);
                        MergeAdapter adapter = new MergeAdapter(adapter1,adapter2);
                        mRecyclerView.setAdapter(adapter);

                        Toast.makeText(ProjectControlerActivity.this,"length is"+pos,Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.toggle1:
                        int pos1=animalsList.size()+animalsList1.size();
                        animalsList1.add("button"+pos1);

                        mRecyclerView.scrollToPosition(pos1);
                        MergeAdapter adapter3 = new MergeAdapter(adapter1,adapter2);
                        mRecyclerView.setAdapter(adapter3);
                        Toast.makeText(ProjectControlerActivity.this,"length is"+pos1,Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });
        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProjectControlerActivity.this,"rajesh",Toast.LENGTH_SHORT).show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProjectControlerActivity.this,"disbled", Toast.LENGTH_SHORT).show();}
        });







    }
}
