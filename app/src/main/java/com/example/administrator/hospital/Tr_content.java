package com.example.administrator.hospital;

import java.util.ArrayList;

public class Tr_content {
    public ArrayList<String> child;
    private String name;
    private String content;
    private String date;


    public Tr_content(String name, String content, String date) {
        super();
        child = new ArrayList<String>();
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public ArrayList<String> getChild() {
        return child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setChild(ArrayList<String> child) {
        this.child = child;
    }


}
