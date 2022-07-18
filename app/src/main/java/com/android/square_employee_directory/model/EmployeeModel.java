package com.android.square_employee_directory.model;

import java.io.Serializable;
import java.util.Comparator;

public class EmployeeModel implements Serializable {

    private String uuid;
    private String full_name;
    private String phone_number;
    private String email_address;
    private String biography;
    private String photo_url_small;
    private String photo_url_large;
    private String team;
    private String employee_type;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhoto_url_small() {
        return photo_url_small;
    }

    public void setPhoto_url_small(String photo_url_small) {
        this.photo_url_small = photo_url_small;
    }

    public String getPhoto_url_large() {
        return photo_url_large;
    }

    public void setPhoto_url_large(String photo_url_large) {
        this.photo_url_large = photo_url_large;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getEmployee_type() {
        return employee_type;
    }

    public void setEmployee_type(String employee_type) {
        this.employee_type = employee_type;
    }

    public static Comparator<EmployeeModel> EmpNameComp = (e1, e2) -> {
        String empName1 = "No Name", empName2 = "No Name";
        if (e1.getFull_name() != null && e2.getFull_name()!=null) {
            empName1 = e1.getFull_name().toUpperCase();
            empName2 = e2.getFull_name().toUpperCase();
        }

        //ascending order
        return empName1.compareTo(empName2);

        //descending order
        //return empName2.compareTo(empName1);
    };
}
