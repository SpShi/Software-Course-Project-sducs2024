package com.fate.elite.bean;

public class Comp {
    private long id;
    private long license;
    private String idnumber;
    private String name;
    private String ename;
    private long tel;
    private int state;
    private String addr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getlicense() {
        return license;
    }

    public void setlicense(long license) {
        this.license = license;
    }

    public String getidnumber() {
        return idnumber;
    }

    public void setidnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Comp{" +
                "id=" + id +
                ", license='" + license + '\'' +
                ", idnumber='" + idnumber + '\'' +
                ", name='" + name + '\'' +
                ", ename='" + ename + '\'' +
                ", tel='" + tel + '\'' +
                ", state='" + state + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
