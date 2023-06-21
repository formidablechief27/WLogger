package com.example.wlogger;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView textView1, textView2, textView3;
    Context c;

    public Holder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        textView1 = itemView.findViewById(R.id.name);
        textView2 = itemView.findViewById(R.id.ppl);
        textView3 = itemView.findViewById(R.id.part);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
