package com.example.wlogger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapader extends RecyclerView.Adapter<Adapader.Holder> implements View.OnClickListener {
    Context context;
    List<LogGet> list;

    public Adapader(Context context, List<LogGet> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public Adapader.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.showloggers, parent, false);
        return new Adapader.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapader.Holder holder, int position) {
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
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView1 = itemView.findViewById(R.id.name);
            textView2 = itemView.findViewById(R.id.weightlifted);
            textView3 = itemView.findViewById(R.id.repsdone);
            textView4 = itemView.findViewById(R.id.number);
        }
    }
}
