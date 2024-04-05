package com.example.attendanceapp;

public class listModel {
    String rollNo,Name;
    int ADBMS,CNA,CT,FLAT,SEAD;

    public listModel() {
    }

    public listModel(String rollNo, String name, int ADBMS, int CNA, int CT, int FLAT, int SEAD) {
        this.rollNo = rollNo;
        Name = name;
        this.ADBMS = ADBMS;
        this.CNA = CNA;
        this.CT = CT;
        this.FLAT = FLAT;
        this.SEAD = SEAD;
    }


    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getADBMS() {
        return ADBMS;
    }

    public void setADBMS(int ADBMS) {
        this.ADBMS = ADBMS;
    }

    public int getCNA() {
        return CNA;
    }

    public void setCNA(int CNA) {
        this.CNA = CNA;
    }

    public int getCT() {
        return CT;
    }

    public void setCT(int CT) {
        this.CT = CT;
    }

    public int getFLAT() {
        return FLAT;
    }

    public void setFLAT(int FLAT) {
        this.FLAT = FLAT;
    }

    public int getSEAD() {
        return SEAD;
    }

    public void setSEAD(int SEAD) {
        this.SEAD = SEAD;
    }
}
