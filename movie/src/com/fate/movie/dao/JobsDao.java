package com.fate.movie.dao;

import com.fate.movie.bean.Jobs;
import com.fate.movie.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobsDao {
    QueryRunner runner = new QueryRunner();

    /**/
    public int add(String name,long place,int age,int gender,int degrees,String major,String ctfct,
                   int salary,String email) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="insert into jobs(`name`,place,age,gender,degrees,major,certificates,salary,email) " +
                " values(?,?,?,?,?,?,?,?,?)";
        int count = runner.update(conn,sql,name,place,age,gender,degrees,major,ctfct,salary,email);
        DBHelper.close(conn);
        return count;
    }
    public int modify(long id,String name,long place,int age,int gender,int degrees,String major,String ctfct,
                      int salary,String email) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="update jobs set `name` = ?, place=?,age=?,gender=?,degrees=?,major=?,certificates=?, " +
                "salary=?,email=? where id=?";
        int count = runner.update(conn,sql,name,place,age,gender,degrees,major,ctfct,salary,email,id);
        DBHelper.close(conn);
        return count;
    }
    public int remove(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="delete from jobs where id=?";
        int count = runner.update(conn,sql,id);
        DBHelper.close(conn);
        return count;
    }


    public List<Jobs> getAll() throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,`name`,place,age,gender,degrees,major,certificates,salary,email from  jobs";
        List<Jobs> jobss = runner.query(conn,sql,new BeanListHandler<Jobs>(Jobs.class));
        DBHelper.close(conn);
        return  jobss;
    }

    /**
     * 根据会员编号查会员信息
     * @param id
     * @return
     * @throws SQLException
     */
    public Jobs getById(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,`name`,place,age,gender,degrees,major,certificates,salary,email from  jobs where id=?";
        Jobs jobs = runner.query(conn,sql,new BeanHandler<Jobs>(Jobs.class),id);
        DBHelper.close(conn);
        return  jobs;
    }

    /**
     * 根据会员身份证查会员信息
     * @paramidNumber
     * @return
     * @throws SQLException
     */
    public List<Jobs> getAllwithLimit(long place,int agel,int ageh,int gender,int degrees,int salary,boolean desc) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql=null;
        if(place==0){
            sql="select id,`name`,place,age,gender,degrees,major,certificates,salary,email from  jobs "+
                    "where age>=? AND age<=? AND gender=? AND degrees>? AND salary >=? ORDER BY salary ";
        }
        else{
            sql="select id,`name`,place,age,gender,degrees,major,certificates,salary,email from  jobs "+
                    "where place=? AND age>=? AND age<=? AND gender=? AND degrees>? AND salary >=? ORDER BY salary ";
        }
        if(desc) sql=sql+"desc";
        List<Jobs> jobs = runner.query(conn,sql,new BeanListHandler<Jobs>(Jobs.class),place,agel,ageh,gender,degrees,salary);
        DBHelper.close(conn);
        return  jobs;
    }


    /**
     * 判断会员编号是否存在Record中(作为外键 ）
     * @param id
     * @return
     */
    public boolean exits(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select count(id) from record where jobsId = ?";
        Number number= runner.query(conn,sql,new ScalarHandler<>(),id);
        DBHelper.close(conn);
        return number.intValue()>0?true:false;
    }

    public static void main(String[] args) {
        JobsDao jobsDao  = new JobsDao();
    }
}

