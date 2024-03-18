package com.fate.movie.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 电影的实体类:注意外键
 * 1.DBUtil无法生成表以外的数据
 * 2.外键的实体对象没有数据，需要后期手动添加biz(业务去实现)
 */
public class Minfo implements Serializable {

    private long id;
    private String mname;
    private String desc;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", mname='" + mname + '\'' +
                ", desc='" + desc + '\'' +
                ", date=" + date +
                '}';
    }
}
