package com.fate.movie.biz;

import com.fate.movie.bean.*;
import com.fate.movie.dao.ChamberDao;
import com.fate.movie.dao.MovieDao;
import com.fate.movie.dao.RecordDao;
import com.fate.movie.dao.TypeDao;
import com.fate.movie.util.DBHelper;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ChamberBiz {
    //ChamberDao的对象
    ChamberDao  chamberDao  = new ChamberDao();
    public List<Chamber> getAll(){
        try {
            return chamberDao.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public int add(String name,String pic, String address,int volumn)  {
       int count = 0;
        try {
            count =  chamberDao.add(name,pic,address,volumn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;

    }
    public int add(Chamber chamber){
        return add(chamber.getName(),chamber.getPic(),chamber.getAddress(),chamber.getVolumn());
    }

    public int modify(long id,String name,String pic, String address,int volumn)  {
        int count = 0;
        try {
            count = chamberDao.modify(id,name,pic,address,volumn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int modify(Chamber chamber)  {
        int count = 0;
        try {
            count = chamberDao.modify(chamber.getId(),chamber.getName(),chamber.getPic(),chamber.getAddress(),chamber.getVolumn());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public int remove(long id) throws Exception {
        MovieDao movieDao = new MovieDao();
        int count = 0;
        try {
            //1.判断id是否存在外键
            List<Movie> movies = movieDao.getmovieByChamberId(id);
            if(movies.size()>0){
                throw new Exception("删除的影厅有电影未播放，删除失败");
            }
            //2.删除
            count = movieDao.remove(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;

    }
    public List<Chamber>  getByPage(int pageIndex,int pageSize) {
        TypeDao typeDao = new TypeDao();
        List<Chamber> chambers = null;
        try {
            chambers = chamberDao.getByPage(pageIndex,pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return chambers;

    }
    public Chamber getById(long id) {
        Chamber chamber = null;
        TypeDao typeDao = new TypeDao();

        try {
              chamber = chamberDao.getById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return chamber;
    }

    /**
     * 由行数算页数
     * @return
     */
    public int  getPageCount(int pageSize) {
        int pageCount = 0;
        try {
            //1.获取行数
            int rowCount = chamberDao.getCount();
            //2.根据行数得到页数,每页多少条
           pageCount =  (rowCount-1)/pageSize+1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pageCount;
    }

    public Chamber getByName(String chamberName)  {
        Chamber chamber = null;
        try {
            chamber = chamberDao.getByName(chamberName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return chamber;
    }

}
