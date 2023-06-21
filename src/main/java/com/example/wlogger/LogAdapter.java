package com.example.wlogger;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.Holder> implements View.OnClickListener {

    Context context;
    List<LogGet> list;
    static listener object;

    public LogAdapter(Context context, List<LogGet> list, listener object) {
        this.context = context;
        this.list = list;
        this.object = object;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.showlogs, parent, false);
        return new Holder(view, object);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.textView1.setText(list.get(position).getName());
        holder.textView2.setText(list.get(position).getWeight());
        holder.textView3.setText(list.get(position).getReps());
        holder.textView4.setText(list.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
    }

    public static class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1, textView2, textView3, textView4;
        Button delete;
        Context c;
        public Holder(@NonNull View itemView, listener object) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView1 = itemView.findViewById(R.id.name);
            textView2 = itemView.findViewById(R.id.weightlifted);
            textView3 = itemView.findViewById(R.id.repsdone);
            textView4 = itemView.findViewById(R.id.number);
            delete = itemView.findViewById(R.id.remove);
            c = itemView.getContext();
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(object != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            object.OnItemClick(position, 0);
                        }
                    }
                }
                });
        }
    }
}
