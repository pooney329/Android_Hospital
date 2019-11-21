package com.example.administrator.hospital;


import java.util.ArrayList;

public class MyPage {
    public   String doctornumber;
    public   String dedocter;
    public   String dename;
    public   String date;
    public ArrayList<String> child;


    public MyPage(String doctornumber,String dedocter,String dename,String date) {
        super();
        this.doctornumber = doctornumber;
        this.dedocter = dedocter;
        this.dename = dename;
        this.date = date;
        child = new ArrayList<String>();
    }


}

