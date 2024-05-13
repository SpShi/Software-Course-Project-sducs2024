package com.fate.movie.biz;

import com.fate.movie.bean.*;
import com.fate.movie.dao.JobsDao;
import com.fate.movie.dao.EliteDao;
import com.fate.movie.dao.ERecordDao;
import com.fate.movie.util.DBHelper;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ERecordBiz {
    ERecordDao eRecordDao = new ERecordDao();
    EliteDao eliteDao = new EliteDao();
    EliteBiz eliteBiz = new EliteBiz();
    JobsDao jobsDao = new JobsDao();
    JobsBiz jobsBiz = new JobsBiz();
    public List<ERecord> getRecordsByEliteId(long eliteid){
        List<ERecord> records = null;
        try {
            records=eRecordDao.getRecordsByEliteId(eliteid);
            Elite elite = eliteBiz.getById(eliteid);
            for(ERecord record:records){
                long jobId = record.getJobid();
                Jobs jos=jobsBiz.getById(jobId);
                record.setJobs(jos);
                record.setElite(elite);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return records;
    }
    public List<ERecord> getRecordsByJobId(long jobId,boolean desc){
        List<ERecord> records = null;
        try {
            records=eRecordDao.getRecordsByJobId(jobId,desc);
            //1.外键信息
            //1.1 获取会员对象
            // Jobs jobs = jobsDao.getById(jobsId);//拿不到外键对象
            Jobs jobs = jobsBiz.getById(jobId);
            for(ERecord record:records){
                long eliteId = record.getEliteid();
                Elite elite = eliteBiz.getById(eliteId);
                record.setElite(elite);
                record.setJobs(jobs);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return records;
    }

    /**
     * 购买:
     * 1.借一本数： record表添加一行信息(eRecordDao,insert) 1
     * 2.这本电影的数量-1 (ERecord,update) 1
     * 3.会员信息表中 ,更改余额( jobsDao,update) 1
     * 要么全部成功，要么全部失败(一个业务(事务处理))
     * 前提:用同一个connection对象(如何?)
     * @param eliteid
     * @param jobsidList
     * @param comment
     * @return 0:操作失败  1:操作成功
     */
    public int add(long eliteid,List<Long> jobsidList,String comment){
        try {
            //1.启动事务
            DBHelper.beginTransaction();
            double total = 0;
            Elite elite=eliteBiz.getById(eliteid);
            //2.拿到投简历的工作编号
            for(long jobsid: jobsidList){
                //电影编号

                //电影对象
                Jobs jobs = jobsBiz.getById(jobsid);
                //调用价格
                //算押金
                //调用recordDao-->insert
                java.util.Date currentDate = new java.util.Date();
                eRecordDao.add(eliteid,jobsid,currentDate,comment);

            }
            //.事务结束:
            DBHelper.commitTransaction();//事务提交:成功

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                DBHelper.rollbackTransaction();//事务回滚:有异常
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }

        return 1;

    }

    /**
     * 归还功能
     * 开启事务
     * 1.eRecordDao modify: 押金，归还日期，操作员的编号
     * 2.jobsDao modify: 余额
     * 3.电影信息表  modify :数量
     * 成功：提交
     * 失败:回滚
     * @param eliteid
     * @param jobsidList
     * @param comment
     * @return
     */
    public int  modify(long eliteid,List<Long> jobsidList,String comment) {
        //1.开启事务
        try {
            //1.启动事务
            DBHelper.beginTransaction();
            double total = 0;
            Elite elite=eliteBiz.getById(eliteid);
            //2.拿到投简历的工作编号
            for(long jobsid: jobsidList){
                //电影编号

                //电影对象
                Jobs jobs = jobsBiz.getById(jobsid);
                //调用价格
                //算押金
                //调用recordDao-->insert
                java.util.Date currentDate = new java.util.Date();
                eRecordDao.modify(currentDate,comment,eliteid,jobsid);

            }
            //.事务结束:
            DBHelper.commitTransaction();//事务提交:成功
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                DBHelper.rollbackTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }

        return 1;

    }
    public int getById(long eliteid,long jobid){
        int id=0;
        try {
            ERecord eRecord=eRecordDao.getid(eliteid,jobid);
            id=eRecord.getId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }
    /**
     *
     * @param keyword
     * @return
     */
    public List<Map<String,Object>> query( String keyword){
        List<Map<String,Object>> rows = null;
        try {
            rows = eRecordDao.query(keyword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;

    }
    public List<Map<String,Object>> query_0(long id,String keyword){
        List<Map<String,Object>> rows = null;
        try {
            rows = eRecordDao.query_0(id,keyword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;

    }
    public List<Map<String,Object>> query_1(long id,String keyword){
        List<Map<String,Object>> rows = null;
        try {
            rows = eRecordDao.query_1(id,keyword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;

    }
}