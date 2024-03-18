package com.fate.movie.biz;

import com.fate.movie.bean.*;
import com.fate.movie.dao.MinfoDao;
import com.fate.movie.dao.RecordDao;
import com.fate.movie.dao.TypeDao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MinfoBiz {
    //MinfoDao的对象
    MinfoDao  minfoDao  = new MinfoDao();

    public int add(String mname,String desc,String mvin)  {
        int count = 0;
        try {
            Date date =new Date();

            count =  minfoDao.add(mname,desc,date,mvin);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;

    }
    public int add(Minfo minfo,String mvin){
        return add(minfo.getMname(),minfo.getDesc(),mvin);
    }

    public int modify(long id,String mname,String desc,Date date,String mvin)  {
        int count = 0;
        try {
            count = minfoDao.modify(id,mname,desc,date,mvin);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int modify(Minfo minfo,String mvin)  {
        int count = 0;
        try {
            count = minfoDao.modify(minfo.getId(),minfo.getMname(),minfo.getDesc(),minfo.getDate(),mvin);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public int remove(long id,String mvin) throws Exception {
        RecordDao recordDao = new RecordDao();
        int count = 0;
        try {
            count = minfoDao.remove(id,mvin);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;

    }
    public List<Minfo>  getByPage(int pageIndex,int pageSize,String mvin) {
        List<Minfo> minfos = null;
        try {
            minfos = minfoDao.getByPage(pageIndex,pageSize,mvin);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return minfos;

    }
    public Minfo getById(long id,String mvin) {
        Minfo minfo = null;
        TypeDao typeDao = new TypeDao();

        try {
            minfo = minfoDao.getById(id,mvin);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return minfo;
    }

    /**
     * 由行数算页数
     * @return
     */
    public int  getPageCount(int pageSize,String mvin) {
        int pageCount = 0;
        try {
            //1.获取行数
            int rowCount = minfoDao.getCount(mvin);
            //2.根据行数得到页数,每页多少条
            pageCount =  (rowCount-1)/pageSize+1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pageCount;
    }


}
