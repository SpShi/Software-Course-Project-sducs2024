package com.fate.movie.bean;

import java.io.Serializable;
/**
 * 人才客户的实体类:注意外键
 * 1.DBUtil无法生成表以外的数据
 * 2.外键的实体对象没有数据，需要后期手动添加biz(业务去实现)
 */
public class User {

    private long id;
    private String pwd;
    private long type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", pwd='" + pwd + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
