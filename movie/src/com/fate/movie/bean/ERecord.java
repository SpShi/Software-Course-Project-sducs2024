package com.fate.movie.bean;

import java.util.Date;

public class ERecord {
    private int id;
    private long eliteid;
    private long jobid;
    private Date senddate;
    private String comment;

    //外键
    private Elite elite;
    private Jobs jobs;

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

    @Override
    public String toString() {
        return "ERecord{" +
                "id=" + id +
                ", eliteid=" + eliteid +
                ", jobid=" + jobid +
                ", senddate=" + senddate +
                ", comment=" + comment + '\'' +
                ", elite=" + elite +
                ", job=" + jobs +
                '}';
    }
}
