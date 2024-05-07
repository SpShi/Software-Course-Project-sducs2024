package com.fate.movie.bean;

import java.util.Date;

/**
 * 书的实体类:注意外键
 * 1.DBUtil无法生成表以外的数据
 * 2.外键的实体对象没有数据，需要后期手动添加biz(业务去实现)
 */
public class CRecord {
    private int id;
    private long jobid;
    private long eliteid;
    private Date senddate;
    private int fromid;
    private String comment;

    //外键
    private Elite elite;
    private Jobs jobs;
    private ERecord eRecord;

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

    public int getFromid() {
        return fromid;
    }

    public void setFromid(int fromid) {
        this.fromid = fromid;
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

    public ERecord geteRecord() {
        return eRecord;
    }

    public void seteRecord(ERecord eRecord) {
        this.eRecord = eRecord;
    }

    @Override
    public String toString() {
        return "ERecord{" +
                "id=" + id +
                ", eliteid=" + eliteid +
                ", jobid=" + jobid +
                ", senddate=" + senddate +
                ",fromid" + fromid+
                ", comment=" + comment + '\'' +
                ", elite=" + elite +
                ", job=" + jobs +
                ",eRecord="+eRecord+
                '}';
    }
}
