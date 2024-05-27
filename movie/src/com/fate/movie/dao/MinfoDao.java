package com.fate.movie.dao;

import com.fate.movie.bean.Minfo;
import com.fate.movie.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MinfoDao {
    QueryRunner runner = new QueryRunner();


    /**
     * 添加电影
     * @return
     */
    public int add(String mname,String desc,Date date,String mvin) throws SQLException {
        Connection conn  = DBHelper.getConnection();


        String sql="insert into "+mvin+"(mname,`desc`,date) " +
                "values(?,?,?)";
        int count = runner.update(conn,sql,mname,desc,date);
        DBHelper.close(conn);
        return count;

    }

    /*
     * @return
     */
    public int modify(long id,String mname,String desc,Date date,String mvin) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="update "+mvin+" set `mname` = ?,`desc`=?,date= ? where id= ? ";
        int count = runner.update(conn,sql,mname,desc,date,id);
        DBHelper.close(conn);
        return count;

    }

    public int remove(long id,String mvin) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="delete from "+mvin+" where id=? ";
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
    public List<Minfo>  getByPage(int pageIndex,int pageSize,String mvin) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql = "select * from "+mvin+" limit ?,?";
        int offset = (pageIndex-1)*pageSize;
        List<Minfo> minfos = runner.query(conn,sql,new BeanListHandler<Minfo>(Minfo.class),offset,pageSize);
        DBHelper.close(conn);
        return  minfos;
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Minfo getById(long id,String mvin) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="select * from "+mvin+" where id=?";
        Minfo minfo = runner.query(conn,sql,new BeanHandler<Minfo>(Minfo.class),id);
        DBHelper.close(conn);
        return minfo;
    }

    /**
     * 获取电影数量
     * @return
     * @throws SQLException
     */
    public int  getCount(String mvin) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql = "select count(id)from "+mvin+" ";
        Number data = runner.query(conn,sql,new ScalarHandler<>());
        int count = data.intValue();
        DBHelper.close(conn);
        return count;
    }



    public static void main(String[] args) {
        try {
//            List<Minfo> minfos = new MovieDao().getMoviesByTypeId(2);
//            System.out.println(minfos.size());//[]:movies对象有的，但是没有数据
//            for(Minfo minfo : minfos){
//                System.out.println(minfo);
//            }
            MovieDao movieDao = new MovieDao();
            // int count = movieDao .getCount();
//            List<Minfo> minfos = movieDao.getByPage(1,3);
//            for(Minfo minfo :minfos){
//                System.out.println(minfo);
//            }
            movieDao.modify(1,-1);

            //System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
