package com.example.attendanceapp;

public class Model {
    private String PRNno,name,contact;
    public Model()
    {

    }

    public Model(String PRNno, String name, String contact) {
        this.PRNno = PRNno;
        this.name = name;
        this.contact = contact;
    }

    public String getPRNno() {
        return PRNno;
    }

    public void setPRNno(String PRNno) {
        this.PRNno = PRNno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
