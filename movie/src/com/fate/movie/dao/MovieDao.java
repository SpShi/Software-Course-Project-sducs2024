package com.fate.movie.dao;

import com.fate.movie.bean.Movie;
import com.fate.movie.bean.Record;
import com.fate.movie.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MovieDao {
    QueryRunner runner = new QueryRunner();

    /**
     * 根据类型查询对应的电影信息
     * @param typeId
     * @return
     * @throws SQLException
     */
    public List<Movie> getMoviesByTypeId(long typeId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from movie where typeId = ?";
        List<Movie> movies = runner.query(conn,sql,new BeanListHandler<Movie>(Movie.class),typeId);
        DBHelper.close(conn);
        return movies;

    }
    public List<Movie> getmovieByChamberId(long address) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql ="select * from movie where address= ?";
        List<Movie> movies = runner.query(conn,sql,new BeanListHandler<Movie>(Movie.class),address);
        DBHelper.close(conn);
        return movies;
    }
    public boolean checkseat (Movie movie, long seat) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select volumn from chamber where id=?";
        int res = runner.query(conn,sql,new ScalarHandler<>(),movie.getAddress());
        if(seat>res)
            return false;
        DBHelper.close(conn);
        return true;
    }
    /**
     * 添加电影
     * @return
     */
    public int add(long typeId,String name,double price,String desc,String pic,
                   String publish,String author,long stock,int address,Date date) throws SQLException {
        Connection conn  = DBHelper.getConnection();


        String sql="insert into movie(typeId,`name`,price,`desc`,pic,publish,author,stock,address,date) " +
                "values(?,?,?,?,?,?,?,?,?,?)";
        int count = runner.update(conn,sql,typeId,name,price,desc,pic,publish,author,stock,address,date);
        String s="CREATE TABLE "+name+" ( `id` INT(8) NOT NULL AUTO_INCREMENT, `mname` VARCHAR(255) NOT NULL, `date` DATETIME NOT NULL, `text` VARCHAR(255) NOT NULL, PRIMARY KEY (`id`) ) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci";
        try {int wee =runner.execute(conn,s);}
        catch(Exception e){
            ;
        }
        DBHelper.close(conn);
        return count;

    }

    /**
     * 修改电影
     * @param id
     * @param typeId
     * @param name
     * @param price
     * @param desc
     * @param pic
     * @param publish
     * @param author
     * @param stock
     * @param address
     * @return
     */
    public int modify(long id, long typeId, String name, double price, String desc, String pic,
                      String publish, String author, long stock, int address,Date date) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="update movie set typeId= ?,`name` = ?,price =?,`desc`=?,pic = ?,publish = ?,author =?,stock=?,address = ?,date= ? where id= ? ";
        int count = runner.update(conn,sql,typeId,name,price,desc,pic,publish,author,stock,address,date,id);
        DBHelper.close(conn);
        return count;

    }

    /**
     * 修改电影的数量
     *

     * @param id
     * @param amount  整数:+1  负数 -1

     * @return
     * @throws SQLException
     */
    public int modify( long id, int amount) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="update movie set stock=stock + ? where id= ? ";
        int count = runner.update(conn,sql,amount,id);
        DBHelper.close(conn);
        return count;

    }
    public int remove(long id) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="delete from movie where id=? ";
        int count = runner.update(conn,sql,id);
        DBHelper.close(conn);
        return count;
    }

    /**
     *页面查询(暂时不考虑排序问题)
     * @param pageIndex 第几页,从1开始
     * @param pageSize 每页多少行
     * @return 当前页的信息
     * @throws SQLException
     */
    public List<Movie>  getByPage(int pageIndex,int pageSize) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql = "select * from movie limit ?,?";
        int offset = (pageIndex-1)*pageSize;
        List<Movie> movies = runner.query(conn,sql,new BeanListHandler<Movie>(Movie.class),offset,pageSize);
        DBHelper.close(conn);
        return  movies;
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Movie getById(long id) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="select * from movie where id=?";
        Movie movie = runner.query(conn,sql,new BeanHandler<Movie>(Movie.class),id);
        DBHelper.close(conn);
        return movie;
    }

    /**
     * 获取电影数量
     * @return
     * @throws SQLException
     */
    public int  getCount() throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql = "select count(id)from movie";
        Number data = runner.query(conn,sql,new ScalarHandler<>());
        int count = data.intValue();
        DBHelper.close(conn);
        return count;
    }

    /**
     * 根据电影名称查询电影信息
     * @param movieName
     * @return
     * @throws SQLException
     */
    public Movie getByName(String movieName) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="select * from movie where name=?";
        Movie movie = runner.query(conn,sql,new BeanHandler<Movie>(Movie.class),movieName);
        DBHelper.close(conn);
        return movie;
    }


    public static void main(String[] args) {
        try {
//            List<Movie> movies = new MovieDao().getMoviesByTypeId(2);
//            System.out.println(movies.size());//[]:movies对象有的，但是没有数据
//            for(Movie movie : movies){
//                System.out.println(movie);
//            }
            MovieDao movieDao = new MovieDao();
           // int count = movieDao .getCount();
//            List<Movie> movies = movieDao.getByPage(1,3);
//            for(Movie movie :movies){
//                System.out.println(movie);
//            }
            movieDao.modify(1,-1);

            //System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
