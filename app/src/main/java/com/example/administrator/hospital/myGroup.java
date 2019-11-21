package com.example.administrator.hospital;

import java.util.ArrayList;

public class myGroup {
    public ArrayList<String> child;
    public String groupName;
    public String groupdenumber;
    public String groupdename;
    public String groupdedoctor;
    public String groupdephone;
    public String userId;

    myGroup(String groupdenumber, String groupdename , String groupdedoctor, String groupdephone, String userId){
        this.groupdenumber = groupdenumber;
        this.groupdename = groupdename;
        this.groupdedoctor = groupdedoctor;
        this.groupdephone = groupdephone;
        this.userId = userId;
        child = new ArrayList<String>();
    }
}



