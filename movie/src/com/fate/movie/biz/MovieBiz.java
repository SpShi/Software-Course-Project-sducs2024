package com.fate.movie.biz;

import com.fate.movie.bean.*;
import com.fate.movie.dao.MovieDao;
import com.fate.movie.dao.RecordDao;
import com.fate.movie.dao.TypeDao;
import com.fate.movie.util.DBHelper;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MovieBiz {
    //MovieDao的对象
    MovieDao  movieDao  = new MovieDao();


    public List<Movie> getMoviesByTypeId(long typeId)  {
        try {
            return movieDao.getMoviesByTypeId(typeId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public int add(long typeId, String name, double price, String desc, String pic,
                   String publish, String author, long stock, int address,Date date)  {
       int count = 0;
        try {
            count =  movieDao.add(typeId,name,price,desc,pic,publish,author,stock,address,date);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;

    }
    public int add(Movie movie){
        try{
            if(!movieDao.checkseat(movie,movie.getStock()))
            {
                return 0;
            }
        }
        catch (Exception e){
            ;
        }
        return add(movie.getTypeId(),movie.getName(),movie.getPrice(),movie.getDesc(),movie.getPic(),movie.getPublish(),movie.getAuthor(),movie.getStock(),movie.getAddress(),movie.getDate());
    }

    public int modify(long id, long typeId, String name, double price, String desc, String pic,
                      String publish, String author, long stock, int address,Date date)  {
        int count = 0;
        try {
            count = movieDao.modify(id,typeId,name,price,desc,pic,publish,author,stock,address,date);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int modify(Movie movie)  {
        int count = 0;
        try {
            count = movieDao.modify(movie.getId(),movie.getTypeId(),movie.getName(),movie.getPrice(),movie.getDesc(),movie.getPic(),movie.getPublish(),movie.getAuthor(),movie.getStock(),movie.getAddress(),movie.getDate());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public int remove(long id) throws Exception {
        RecordDao recordDao = new RecordDao();
        int count = 0;
        try {
            //1.判断id是否存在外键
            List<Record> records = recordDao.getRecordByMovieId(id);
            if(records.size()>0){
                throw new Exception("删除的电影有子信息，删除失败");
            }
            //2.删除
            count = movieDao.remove(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;

    }
    public List<Movie>  getByPage(int pageIndex,int pageSize) {
        TypeDao typeDao = new TypeDao();
        List<Movie> movies = null;
        try {
            movies = movieDao.getByPage(pageIndex,pageSize);
            //处理type对象的数据问题
            for(Movie movie:movies){
               long typeId =  movie.getTypeId();
               movie.getType();//null
                //根据typeid找到对应的type对象
                Type type= typeDao.getById(typeId);
                //设置给movie.setType()
                movie.setType(type);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movies;

    }
    public Movie getById(long id) {
        Movie movie = null;
        TypeDao typeDao = new TypeDao();

        try {
              movie = movieDao.getById(id);
              long typeId = movie.getTypeId();
              Type type =typeDao.getById(typeId);
              movie.setType(type);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return movie;
    }

    /**
     * 由行数算页数
     * @return
     */
    public int  getPageCount(int pageSize) {
        int pageCount = 0;
        try {
            //1.获取行数
            int rowCount = movieDao.getCount();
            //2.根据行数得到页数,每页多少条
           pageCount =  (rowCount-1)/pageSize+1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pageCount;
    }

    public Movie getByName(String movieName)  {
        Movie movie = null;
        try {
            movie = movieDao.getByName(movieName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movie;
    }

}
