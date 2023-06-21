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

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.Holder> implements View.OnClickListener{
    Context context;
    List<DateGet> list;
    static listener object;

    public DateAdapter(Context context, List<DateGet> list, listener object) {
        this.context = context;
        this.list = list;
        this.object = object;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public DateAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.showdate, parent, false);
        return new DateAdapter.Holder(view, object);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        TextView textView;
        Context c;
        public Holder(@NonNull View itemView, listener object) {
            super(itemView);
            textView = itemView.findViewById(R.id.date);
            c = itemView.getContext();
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
