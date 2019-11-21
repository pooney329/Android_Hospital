package com.example.administrator.hospital;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OpenChatAdater  extends RecyclerView.Adapter<OpenChatAdater.MyViewHolder>{// 타이핑함,


private ArrayList<Message> msgs ; //movies가 여기로 들어옴 메인에잇는
String id ;
//내부클래스 , 뷰홀더 정의
// 뷰홀더 객체  = 아이템 뷰
public  class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView sender, msg;
    public MyViewHolder(View view){
        super(view);
        sender = (TextView) view.findViewById(R.id.item_username); //컨버트뷰랑 같은방법
        msg = (TextView) view.findViewById(R.id.item_message);


    }


}

    //어뎁터 생성자 정의
    public OpenChatAdater(ArrayList<Message>list,String id){
        msgs = list;
        this.id=id;
    }


    @Override
    public int getItemViewType(int position) {
        if(msgs.get(position).getSender().equals(id)){
            return 1;
        }else{
            return  2;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View itemView;
    if(i==1){
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.openchating_row,viewGroup,false);
    }else{

        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.openchating_left_row,viewGroup,false);


    }


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position ) {


        Message msg = msgs.get(position); // 무비데이터 가져오기

        holder.sender.setText(msg.getSender());
        holder.msg.setText(msg.getMsg());

    }

    @Override
    public int getItemCount() {
        return msgs.size();
    }
}
