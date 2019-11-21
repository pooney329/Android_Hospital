package com.example.administrator.hospital;

public class User {
    private String id;
    private String pwd;
    private int check;
    public User(String id, String pwd ,int check) {
        this.id = id;
        this.pwd = pwd;
        this.check=check;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
