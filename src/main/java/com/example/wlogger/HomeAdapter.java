package com.example.wlogger;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> implements View.OnClickListener{
    Context context;
    List<HomeLog> list;
    static listener object;

    public HomeAdapter(Context context, List<HomeLog> list, listener object) {
        this.context = context;
        this.list = list;
        this.object = object;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public HomeAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.details, parent, false);
        return new HomeAdapter.Holder(view, object);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.Holder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getDp());
        String text = list.get(position).getName() + " Logged " + list.get(position).getDate();
        holder.textView1.setText(text);
        holder.textView2.setText(list.get(position).getLog1());
        holder.textView3.setText(list.get(position).getLog2());
        holder.textView4.setText(list.get(position).getLog3());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1, textView2, textView3, textView4;
        Button view;
        public Holder(@NonNull View itemView, listener object) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView1 = itemView.findViewById(R.id.name);
            textView2 = itemView.findViewById(R.id.log1);
            textView3 = itemView.findViewById(R.id.log2);
            textView4 = itemView.findViewById(R.id.log3);
            textView2.setOnClickListener(new View.OnClickListener() {
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
            textView3.setOnClickListener(new View.OnClickListener() {
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
            textView4.setOnClickListener(new View.OnClickListener() {
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
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(object != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            object.OnItemClick(position, 1);
                        }
                    }
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(object != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            object.OnItemClick(position, 1);
                        }
                    }
                }
            });
        }

    }
}
