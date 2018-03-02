package com.russia.app_sqlite_demo_3.model;

/**
 * Created by VLADIMIR PUTIN on 3/1/2018.
 */

public class Student {
    private int idStudent;
    private String name;
    private String telephone;
    private String email;
    private String address;

    public Student() {
    }

    public Student(String name, String telephone, String email, String address) {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    public Student(int idStudent, String name, String telephone, String email, String address) {
        this.idStudent = idStudent;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
