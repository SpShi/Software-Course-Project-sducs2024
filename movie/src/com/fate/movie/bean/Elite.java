package com.fate.movie.bean;

public class Elite {
    private long id;
    private String name;
    private String idNumber;
    private long state;
    private String resume;
    private long gender;
    private long age;
    private long degrees;
    private long tel;
    private String major;
    private String email;
    private String certificate;
    private String intention;
    private String selfevaluation;
    private String experience;


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


    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }


    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }


    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }


    public long getGender() {
        return gender;
    }

    public void setGender(long gender) {
        this.gender = gender;
    }


    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }



    public long getDegrees() {
        return degrees;
    }

    public void setDegrees(long degrees) {
        this.degrees = degrees;
    }


    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }

    public String getSelfevaluation() {
        return selfevaluation;
    }
    public void setSelfevaluation(String selfevaluation) {
        this.selfevaluation = selfevaluation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Elite{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", state=" + state +
                ", resune='" + resume + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", degrees=" + degrees +
                ", tel=" + tel +
                ", major='" + major + '\'' +
                ", email='" + email + '\'' +
                ", certificate='" + certificate + '\'' +
                ", intention='" + intention + '\'' +
                ", selfevaluation='" + selfevaluation + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
