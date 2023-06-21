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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder> implements View.OnClickListener{
    Context context;
    List<User> list;
    static listener object;

    public UserAdapter(Context context, List<User> list, listener object) {
        this.context = context;
        this.list = list;
        this.object = object;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public UserAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.showuser, parent, false);
        return new UserAdapter.Holder(view, object);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.Holder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.textView1.setText(list.get(position).getUsername());
        holder.textView2.setText("Days Logged = " + list.get(position).getLogs());
        holder.textView3.setText("Member Since " + list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1, textView2, textView3;
        public Holder(@NonNull View itemView, listener object) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView1 = itemView.findViewById(R.id.name);
            textView2 = itemView.findViewById(R.id.logs);
            textView3 = itemView.findViewById(R.id.date);
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
