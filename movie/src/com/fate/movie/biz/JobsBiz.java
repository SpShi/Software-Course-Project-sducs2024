package com.fate.movie.biz;

import com.fate.movie.bean.Jobs;
import com.fate.movie.dao.JobsDao;

import java.sql.SQLException;
import java.util.List;

public class JobsBiz {
    JobsDao jobsDao = new JobsDao();
    public int add(String name,long place,int age,int gender,int degrees,String major,String ctfct,
                   int salary,String email){
        int count = 0;
        try {
            count = jobsDao.add(name,place,age,gender,degrees,major,ctfct,salary,email);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int modify(long id,String name,long place,int age,int gender,int degrees,String major,String ctfct,
                      int salary,String email){
        int count = 0;
        try {
            count = jobsDao.modify(id,name,place,age,gender,degrees,major,ctfct,salary,email);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int remove(long id) throws Exception {
        //1.判断会员账号余额 >0 :提示不能删除
        Jobs jobs = getById(id);
        //2.有外键不能删除
        if(jobsDao.exits(id)){
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return jobss;

    }
    public Jobs getById(long id){
        Jobs jobs = null;
        try {
            jobs = jobsDao.getById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jobs;
    }
    public List<Jobs> getAllwithLimit(long place,int agel,int ageh,int gender,int degrees,int salary,boolean desc){
        List<Jobs> jobss = null;
        try {
            jobss =  jobsDao.getAllwithLimit(place,agel,ageh,gender,degrees,salary,desc);
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return jobss;

    }



    // 截取字符串的方法
    public static String getStr(String str,int a,int b)
    {
        b++;
        return str.substring(a,b);
    }

}
