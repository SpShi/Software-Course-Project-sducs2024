package com.fate.movie.biz;

import com.fate.movie.bean.Comp;
import com.fate.movie.bean.ERecord;
import com.fate.movie.bean.Elite;
import com.fate.movie.bean.Jobs;
import com.fate.movie.dao.CompDao;
import com.fate.movie.dao.ERecordDao;
import com.fate.movie.dao.EliteDao;
import com.fate.movie.dao.JobsDao;
import com.fate.movie.util.DBHelper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ERecordBiz {
    ERecordDao eRecordDao = new ERecordDao();
    EliteDao eliteDao = new EliteDao();
    EliteBiz eliteBiz = new EliteBiz();
    JobsDao jobsDao = new JobsDao();
    JobsBiz jobsBiz = new JobsBiz();
    CompDao compDao=new CompDao();
    public List<ERecord> getRecordsByEliteId(long eliteid,int state){
        List<ERecord> records = null;
        try {
            records=eRecordDao.getRecordsByEliteId(eliteid,state);
            Elite elite = eliteBiz.getById(eliteid);
            for(ERecord record:records){
                long jobId = record.getJobid();
                Jobs jobs=jobsBiz.getById(jobId);
                record.setJobs(jobs);
                Comp comp=compDao.getById(jobs.getPlace());
                record.setComp(comp);
                record.setElite(elite);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return records;
    }
    public List<ERecord>  getByPage(int pageIndex,int pageSize,long eliteid,int state) {
        List<ERecord> records = null;
        try {
            records = eRecordDao.getByPage(pageIndex,pageSize,eliteid,state);
            Elite elite=eliteDao.getById(eliteid);
            //处理type对象的数据问题
            for(ERecord record:records){
                record.setElite(elite);
                long jobid=record.getJobid();
                Jobs jobs=jobsDao.getById(jobid);
                //设置给movie.setType()
                record.setJobs(jobs);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return records;

    }
    /**
     * 由行数算页数
     * @return
     */
    public int  getPageCount(int pageSize,long eliteid,int state) {
        int pageCount = 0;
        try {
            //1.获取行数
            int rowCount = eRecordDao.getCount(eliteid,state);
            //2.根据行数得到页数,每页多少条
            pageCount =  (rowCount-1)/pageSize+1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pageCount;
    }
    public List<ERecord> getRecordsByJobId(long[] jobsId,boolean desc,int state){
        List<ERecord> records = null;
        try {
            for(long jobId:jobsId)
            {
                records=eRecordDao.getRecordsByJobId(jobId,desc,state);
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
     * 回复简历
     * @param eliteid
     * @param jobid
     * @param comment
     * @param state
     * @return
     */
    public int  modify(long eliteid,long jobid,String comment,int state) {
        //1.开启事务
        try {
            //1.启动事务
            DBHelper.beginTransaction();
            java.util.Date currentDate = new java.util.Date();
            eRecordDao.modify(currentDate,comment,state,eliteid,jobid);

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
    public List<Map<String,Object>> query( String keyword,int state){
        List<Map<String,Object>> rows = null;
        try {
            rows = eRecordDao.query(keyword,state);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;

    }
    public List<Map<String,Object>> query_0(long id,String keyword,int state){
        List<Map<String,Object>> rows = null;
        try {
            rows = eRecordDao.query_0(id,keyword,state);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;

    }
    public List<Map<String,Object>> query_1(long id,String keyword,int state){
        List<Map<String,Object>> rows = null;
        try {
            rows = eRecordDao.query_1(id,keyword,state);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;

    }
}