package com.example.testapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToggleButtonAdapter extends RecyclerView.Adapter<ToggleButtonAdapter.MyviewHolder> {
    private List<String> mDataSet2;
    private Context mContext2;

    public ToggleButtonAdapter(List<String> mDataSet, Context mContext) {
        this.mDataSet2 = mDataSet;
        this.mContext2 = mContext;
    }
    @NonNull
    @Override
    public ToggleButtonAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(mContext2).inflate(R.layout.toggle_button,parent,false);
        MyviewHolder vh = new MyviewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ToggleButtonAdapter.MyviewHolder holder, final int position) {
        holder.Mytext.setText((String)mDataSet2.get(position));
        // Generate a random color

        // Set a click listener for TextView
        holder.Mytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String animal = mDataSet2.get(position);
                Toast.makeText(mContext2,animal,Toast.LENGTH_SHORT).show();
            }
        });

        // Set a click listener for item remove button
        holder.toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
                Toast.makeText(mContext2,"button is clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet2.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{
        private TextView Mytext;
        private ToggleButton toggleButton;
        private LinearLayout MyLinearLayout;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            Mytext = (TextView)itemView.findViewById(R.id.text_view);
            toggleButton = (ToggleButton)itemView.findViewById(R.id.toggle_button1);
            MyLinearLayout = (LinearLayout)itemView.findViewById(R.id.rl2);
        }
    }

}
