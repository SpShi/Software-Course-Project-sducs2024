package com.fate.elite.bean;

public class Admin {
    private long id;
    private String name;
    private long tel;
    private int state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public long gettel() {
        return tel;
    }

    public void settel(long tel) {
        this.tel = tel;
    }

    public int getstate(){
        return state;
    }

    public void setstate(int state){
        this.state=state;
    }


    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
