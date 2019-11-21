package com.example.administrator.hospital;

public class Message {


    private String sender;
    private String msg;
    private String date;

    // 기본생성자 정의 : Firebase DB에 데이터로 저장하기 위해.
    public Message(String sneder, String msg, String date) {
        // empty constructor
        this.sender=sneder;
        this.msg=msg;
        this.date=date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


