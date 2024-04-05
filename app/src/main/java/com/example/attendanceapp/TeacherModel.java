package com.example.attendanceapp;

import androidx.annotation.NonNull;

public class TeacherModel {
     String Department,Classes,Division,Subject,Name;
    public TeacherModel()
    {

    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getClasses() {
        return Classes;
    }

    public void setClasses(String Classes) {
        this.Classes=Classes;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String Division) {
        this.Division = Division;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public TeacherModel( String Classes,String Department, String Division,String Name, String Subject ) {
        this.Department = Department;
        this.Classes = Classes;
        this.Division = Division;
        this.Name = Name;
        this.Subject=Subject;
    }
}
