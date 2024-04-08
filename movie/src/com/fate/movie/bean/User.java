package com.fate.movie.bean;

import java.io.Serializable;
/**
 * 人才客户的实体类:注意外键
 * 1.DBUtil无法生成表以外的数据
 * 2.外键的实体对象没有数据，需要后期手动添加biz(业务去实现)
 */
public class User {

    private long id;
    private String name;
    private String pwd;
    private long typeId;
    private double balance;
    private java.sql.Date regdate;
    private String tel;
    private String idNumber;
    private  long state;
    //外键会员
    //private MemberType  type;


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


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public java.sql.Date getRegdate() {
        return regdate;
    }

    public void setRegdate(java.sql.Date regdate) {
        this.regdate = regdate;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", balance='" + balance + '\'' +
                ", regdate=" + regdate +
                ", tel='" + tel + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", state=" + state +
                '}';
    }
}
