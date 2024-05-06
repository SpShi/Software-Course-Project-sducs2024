package com.fate.movie.dao;

import com.fate.movie.bean.Elite;
import com.fate.movie.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EliteDao {
    QueryRunner runner = new QueryRunner();

    /**
     *添加人才
     * @param id
     * @param name
     * @param idNumber
     * @param resume
     * @param gender
     * @param age
     * @param degrees
     * @param tel
     * @param major
     * @param email
     * @param ctfct
     * @param intt
     * @param slfe
     * @param expe
     * @return
     * @throws SQLException
     */
    public int add(long id,String name,String idNumber,String resume,long gender,long age,long degrees,long tel,
                   String major,String email,String ctfct,String intt,String slfe,String expe) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="insert into elite(id,`name`,idNumber,state,resume,gender,age,degrees,tel,major,email,"+
                "certificate,intention,selfevaluation,experience) "  +
                " values(?,?,?,0,?,?,?,?,?,?,?,?,?,?,?)";
        int count = runner.update(conn,sql,id,name,idNumber,resume,gender,age,degrees,tel,major,email,ctfct,
                intt,slfe,expe);
        DBHelper.close(conn);
        return count;
    }

    /**
     * 修改人才详细信息
     * @param id
     * @param name
     * @param idNumber
     * @param resume
     * @param gender
     * @param age
     * @param degrees
     * @param tel
     * @param major
     * @param email
     * @param ctfct
     * @param intt
     * @param slfe
     * @param expe
     * @return
     * @throws SQLException
     */
    public int modify(long id,String name,String idNumber,String resume,long gender,long age,long degrees,long tel,
                      String major,String email,String ctfct,String intt,String slfe,String expe) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="update elite set `name` = ?, idNumber = ?,state = ?,resume = ?,gender = ?,age = ?,degrees = ?,"+
                "tel = ?,major = ?,email = ?,certificate = ?,intention = ?,selfevaluation = ?,experience = ? " +
                "where id=?";
        int count = runner.update(conn,sql,name,idNumber,resume,gender,age,degrees,tel,major,email,ctfct,
                intt,slfe,expe,id);
        DBHelper.close(conn);
        return count;
    }

    /**
     *  update 表名 set 列名 = 值 where 列名=值;
     * @param id
     * @param age
     * @return
     * @throws SQLException
     */
    public int modifyage(long id,long age) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="update elite age = ? where id=?";
        int count = runner.update(conn,sql,age,id);
        DBHelper.close(conn);
        return count;
    }

    /**
     *
     * @param id
     * @param state
     * @return
     * @throws SQLException
     */
    public int modifystate(long id,long state) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="update elite state = ? where id=?";
        int count = runner.update(conn,sql,state,id);
        DBHelper.close(conn);
        return count;
    }
    /**
     * 删除某位人才,回收利用该会员的id(待实现)
     * @param id
     * @return
     * @throws SQLException
     */
    public int remove(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="delete from elite where id=?";
        int count = runner.update(conn,sql,id);
        DBHelper.close(conn);
        return count;
    }


    /**
     * 全部
     * @return
     * @throws SQLException
     */

    public List<Elite> getAll() throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,`name`,idNumber,state,resume,gender,age,degrees,tel,major,email," +
                "certificate,intention,selfevaluation,experience from  elite";
        List<Elite> elites = runner.query(conn,sql,new BeanListHandler<Elite>(Elite.class));
        DBHelper.close(conn);
        return  elites;
    }

    /**
     *
     * @param agel
     * @param ageh
     * @param gender
     * @param degrees
     * @param salary
     * @param key
     * @param desc
     * @return
     * @throws SQLException
     */
    public List<Elite> getAllwithLimit(int agel, int ageh, int gender, int degrees,
                                      int salary, String key, boolean desc) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select * from  jobs ";
        sql=sql+ "where age>=? AND age<=? AND gender=? AND degrees>? AND salary <=? ORDER BY salary ";
        if(key!="") sql+="and intro like '%"+key+"%'";
        if(desc) sql=sql+"desc";
        sql+="ORDER BY salary ";
        List<Elite> jobs = runner.query(conn,sql,new BeanListHandler<Elite>(Elite.class),agel,ageh,gender,degrees,salary);
        DBHelper.close(conn);
        return  jobs;
    }
    /**
     * 根据人才编号查人才信息
     * @param id
     * @return
     * @throws SQLException
     */



    public Elite getById(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,`name`,idNumber,state,resume,gender,age,degrees,tel,major,email," +
                "certificate,intention,selfevaluation,experience from  elite where id=?";
        Elite elite = runner.query(conn,sql,new BeanHandler<Elite>(Elite.class),id);
        DBHelper.close(conn);
        return  elite;
    }

    /**
     * 根据人才身份证查人才信息
     * @paramidNumber
     * @return
     * @throws SQLException
     */
    public Elite getByIdNumber(String idNumber) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,`name`,idNumber,state,resume,gender,age,degrees,tel,major,email," +
                "certificate,intention,selfevaluation,experience from  elite where idNumber=?";
        Elite elite = runner.query(conn,sql,new BeanHandler<Elite>(Elite.class),idNumber);
        DBHelper.close(conn);
        return  elite;
    }



    public static void main(String[] args) {
        EliteDao eliteDao  = new EliteDao();
    }
}
