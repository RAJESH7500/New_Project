package com.example.testapp2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SlideButtonAdapter extends RecyclerView.Adapter<SlideButtonAdapter.MyViewHolder> {
    private List<String> mDataSet;
    private Context mContext;
    public SlideButtonAdapter(List<String> mDataSet, Context mContext) {
        this.mDataSet = mDataSet;
        this.mContext = mContext;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public Switch mRemoveButton;
        public LinearLayout mRelativeLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.text_raj);
            mRemoveButton = (Switch)itemView.findViewById(R.id.switch_raj);
            mRelativeLayout = (LinearLayout)itemView.findViewById(R.id.rl_raj);
        }
    }

    @NonNull
    @Override
    public SlideButtonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(mContext).inflate(R.layout.slide_button,parent,false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SlideButtonAdapter.MyViewHolder holder, final int position) {
        holder.mTextView.setText((String)mDataSet.get(position));
        // Generate a random color
        // Set a click listener for TextView

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
