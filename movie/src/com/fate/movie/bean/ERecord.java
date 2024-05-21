package com.fate.movie.bean;

import java.util.Date;

/**
 * 书的实体类:注意外键
 * 1.DBUtil无法生成表以外的数据
 * 2.外键的实体对象没有数据，需要后期手动添加biz(业务去实现)
 */

public class ERecord {
    private int id;
    private int state;
    private long eliteid;
    private long jobid;
    private Date senddate;
    private Date backdate;
    private String comment;

    //外键
    private Elite elite;
    private Jobs jobs;
    private Comp comp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public long getEliteid() {
        return eliteid;
    }

    public void setEliteid(long eliteid) {
        this.eliteid = eliteid;
    }


    public long getJobid() {
        return jobid;
    }
    public void setJobid(long jobid) {
        this.jobid= jobid;
    }

    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date buyDate) {
        this.senddate = senddate;
    }

    public String getComment(){return comment;}

    public void setComment(String comment){this.comment=comment;}

    public Elite getElite() {
        return elite;
    }

    public void setElite(Elite elite) {
        this.elite = elite;
    }

    public Jobs getJobs() {
        return jobs;
    }

    public void setJobs(Jobs jobs) {
        this.jobs = jobs;
    }

    public Date getBackdate() {
        return backdate;
    }

    public void setBackdate(Date backdate) {
        this.backdate = backdate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Comp getComp(){return comp;}

    public void setComp(Comp comp) {
        this.comp = comp;
    }

    @Override
    public String toString() {
        return "ERecord{" +
                "id=" + id +
                "state=" + state +
                ", eliteid=" + eliteid +
                ", jobid=" + jobid +
                ", senddate=" + senddate +
                ", backdate=" + backdate +
                ", comment=" + comment + '\'' +
                ", elite=" + elite +
                ", job=" + jobs +
                ", comp=" + comp +
                '}';
    }
}
