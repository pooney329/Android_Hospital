package com.example.administrator.hospital;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChatTableAdater extends RecyclerView.Adapter<ChatTableAdater.MyViewHolder> {
    private ArrayList<ChatTable_a> chatTable_as;
    Context context;
    String sender;
    // 내부클래스. 뷰홀더 정의
    // 뷰홀더 객체 = 아이템 뷰
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView patient;
           public Button Treatmentcontents;

        public MyViewHolder(View view) {
            super(view);
            patient = (TextView) view.findViewById(R.id.userid);

            Treatmentcontents = (Button) view.findViewById(R.id.button5);

        }
    }
    // 생성자 정의
    public ChatTableAdater(ArrayList<ChatTable_a> list,Context context,String id) {
        chatTable_as = list;
        this.context=context;
        this.sender=id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.chattable_row, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChatTable_a chatTable_a = chatTable_as.get(position);

        final String reciver = chatTable_a.getUserid();
        holder.patient.setText(reciver);
        holder.patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,OpenChat.class);
                intent.putExtra("reciver",reciver);
                intent.putExtra("sender",sender);
                intent.putExtra("ch","3");
                context.startActivity(intent);


            }
        });
        holder.Treatmentcontents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Treatment.class);
                intent.putExtra("patient",reciver);
                intent.putExtra("doctor",sender);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chatTable_as.size();
    }
}












