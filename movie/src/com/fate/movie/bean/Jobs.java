package com.fate.movie.bean;

public class Jobs {
    private long id;
    private String name;
    private long place;
    private int age;
    private int gender;
    private int degrees;
    private String major;
    private String certificates;
    private int salary;
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPlace() {
        return place;
    }

    public void setPlace(long place) {
        this.place = place;
    }

    public int getAge() { return age; }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() { return gender; }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getDegrees() { return degrees; }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCertificates() {
        return certificates;
    }

    public void setCertificates(String certificates) {
        this.certificates=certificates;
    }

    public int getSalary(){
        return salary;
    }

    public void setSalary(int salary){
        this.salary=salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", place='" + place +
                ", age='" + age +
                ", gender='" + gender +
                ", degrees='" + degrees +
                ", major='" + major + '\'' +
                ", certificates='" + certificates + '\'' +
                ", salary='" + salary +
                ", email='" + email + '\'' +
                '}';
    }
}
