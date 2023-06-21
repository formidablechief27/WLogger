package com.example.wlogger;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> implements View.OnClickListener {

    Context context;
    List<Exercise> list;
    listener object;

    public Adapter(Context context, List<Exercise> list, listener object) {
        this.context = context;
        this.list = list;
        this.object = object;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercise, parent, false);
        return new Holder(view, object);
        //return new Adapter.Holder(LayoutInflater.from(context).inflate(R.layout.exercise, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.textView1.setText(list.get(position).getName());
        holder.textView2.setText(list.get(position).getPpl());
        holder.textView3.setText(list.get(position).getPart());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1, textView2, textView3;
        Context c;
        public Holder(@NonNull View itemView, listener object) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView1 = itemView.findViewById(R.id.name);
            textView2 = itemView.findViewById(R.id.ppl);
            textView3 = itemView.findViewById(R.id.part);
            itemView.setOnClickListener(new View.OnClickListener() {
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
