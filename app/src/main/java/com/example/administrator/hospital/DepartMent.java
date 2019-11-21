package com.example.administrator.hospital;

public class DepartMent {
    private String dename;
    private String dedoctor;
    public DepartMent(String dename, String dedoctor) {
        super();
        this.dename = dename;
        this.dedoctor =dedoctor;

    }

    public String getDename() {
        return dename;
    }

    public void setDename(String dename) {
        this.dename = dename;
    }

    public String getDedoctor() {
        return dedoctor;
    }

    public void setDedoctor(String dedoctor) {
        this.dedoctor = dedoctor;
    }
}
