package com.fate.elite.biz;

import com.fate.elite.bean.Comp;
import com.fate.elite.bean.Jobs;
import com.fate.elite.dao.CompDao;
import com.fate.elite.dao.JobsDao;

import java.sql.SQLException;
import java.util.List;

public class JobsBiz {
    JobsDao jobsDao = new JobsDao();
    CompDao compDao=new CompDao();
    public int add(String name,long place,int age,int gender,int degrees,String major,String ctfct,
                   int salary,String email,String intro){
        int count = 0;
        try {
            count = jobsDao.add(name,place,age,gender,degrees,major,ctfct,salary,email,intro);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int modify(long id,String name,int age,int gender,int degrees,String major,String ctfct,
                      int salary,String email,String intro){
        int count = 0;
        try {
            count = jobsDao.modify(id,name,age,gender,degrees,major,ctfct,salary,email,intro);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int add(Jobs job){
        return add(job.getName(),job.getPlace(),job.getAge(),job.getGender(),
                job.getDegrees(),job.getMajor(),job.getCertificates(),job.getSalary(),job.getEmail(),job.getIntro());
    }
    public int modify(Jobs job){
        return modify(job.getId(),job.getName(),job.getAge(),job.getGender(),
                job.getDegrees(),job.getMajor(),job.getCertificates(),job.getSalary(),job.getEmail(),job.getIntro());
    }
    public int remove(long id) throws Exception {
        //1.判断会员账号余额 >0 :提示不能删除
        Jobs jobs = getById(id);
        //2.有外键不能删除
        if(jobsDao.exits_e(id)){
            throw new Exception("此会员有子信息,删除失败");
        }
        //3.删除
        int count =0;
        try {
            count = jobsDao.remove(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;

    }

    public List<Jobs> getAll(){
        List<Jobs> jobss = null;
        try {
            jobss =  jobsDao.getAll();
            for(Jobs job:jobss)
            {
                Comp comp=compDao.getById(job.getPlace());
                job.setComp(comp);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return jobss;

    }
    public List<Jobs> getbyplace(long place){
        List<Jobs> jobss = null;
        try {
            jobss =  jobsDao.getbyPalce(place);
            for(Jobs job:jobss)
            {
                Comp comp=compDao.getById(job.getPlace());
                job.setComp(comp);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return jobss;

    }
    public Jobs getById(long id){
        Jobs jobs = null;
        try {
            jobs = jobsDao.getById(id);
            Comp comp=compDao.getById(jobs.getPlace());
            jobs.setComp(comp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jobs;
    }
    public List<Jobs> getAllwithLimit(long place,int agel,int ageh,int gender,int degrees,int salary,String intro,boolean desc){
        List<Jobs> jobss = null;
        try {
            jobss =  jobsDao.getAllwithLimit(place,agel,ageh,gender,degrees,salary,intro,desc);
            for(Jobs job:jobss)
            {
                Comp comp=compDao.getById(job.getPlace());
                job.setComp(comp);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return jobss;

    }
    /**
     * 由行数算页数
     * @return
     */
    public int  getPageCount(int pageSize,long place,int agel,int ageh,int gender,int degrees,int salary,String intro,boolean desc) {
        int pageCount = 0;
        try {
            //1.获取行数
            int rowCount = jobsDao.getCount(place,agel,ageh,gender,degrees,salary,intro,desc);
            //2.根据行数得到页数,每页多少条
            pageCount =  (rowCount-1)/pageSize+1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pageCount;
    }
    public List<Jobs>  getByPage(int pageIndex,int pageSize,long place,int agel,int ageh,int gender,int degrees,int salary,String intro,boolean desc) {
        List<Jobs> jobs = null;
        try {
            jobs = jobsDao.getByPage(pageIndex,pageSize,place,agel,ageh,gender,degrees,salary,intro,desc);
            for(Jobs job:jobs)
            {
                Comp comp=compDao.getById(job.getPlace());
                job.setComp(comp);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jobs;

    }

    // 截取字符串的方法
    public static String getStr(String str,int a,int b)
    {
        b++;
        return str.substring(a,b);
    }

}
