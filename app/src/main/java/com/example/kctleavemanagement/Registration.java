package com.example.kctleavemanagement;



public class Registration {
    String name, uname , email ,  mobile,  faculty, semester, password, c_password;

    public Registration(){ }

    public Registration(String name, String uname, String email, String mobile, String faculty, String semester, String password, String c_password) {
        this.name = name;
        this.uname = uname;
        this.email = email;
        this.mobile = mobile;
        this.faculty = faculty;
        this.semester = semester;
        this.password = password;
        this.c_password = c_password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getC_password() {
        return c_password;
    }

    public void setC_password(String c_password) {
        this.c_password = c_password;
    }
}


