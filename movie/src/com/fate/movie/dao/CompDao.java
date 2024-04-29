package com.fate.movie.dao;


import com.fate.movie.bean.Comp;
import com.fate.movie.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CompDao {
    QueryRunner runner = new QueryRunner();

    /**
     * 
     * @param id
     * @param name
     * @param idNumber
     * @param license
     * @param tel
     * @param ename
     * @return
     * @throws SQLException
     */
    public int add(long id,String name,String idNumber,long license,long tel,String ename) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="insert into enterprise(id,`name`,idNumber,state,license,tel,ename"+
                " values(?,?,?,0,?,?,?)";
        int count = runner.update(conn,sql,id,name,idNumber,license,tel,ename);
        DBHelper.close(conn);
        return count;
    }

    /**
     * 
     * @param id
     * @param name
     * @param idNumber
     * @param license
     * @param tel
     * @param ename
     * @return
     * @throws SQLException
     */

    public int modify(long id,String name,String idNumber,long license,long tel,String ename) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="update enterprise set `name` = ?, idNumber = ?,state = ?,license = ?,tel = ?, ename = ? " +
                "where id=?";
        int count = runner.update(conn,sql,name,idNumber,license,tel,ename,id);
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
        String sql="delete from enterprise where id=?";
        int count = runner.update(conn,sql,id);
        DBHelper.close(conn);
        return count;
    }


    /**
     * 全部
     * @return
     * @throws SQLException
     */

    public List<Comp> getAll() throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,`name`,idNumber,state,license,tel,ename,state from  enterprise";
        List<Comp> enterprises = runner.query(conn,sql,new BeanListHandler<Comp>(Comp.class));
        DBHelper.close(conn);
        return  enterprises;
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
        String sql="update enterprise state = ? where id=?";
        int count = runner.update(conn,sql,state,id);
        DBHelper.close(conn);
        return count;
    }
    /**
     * 根据人才编号查人才信息
     * @param id
     * @return
     * @throws SQLException
     */



    public Comp getById(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,`name`,idNumber,state,license,tel,ename,state from  enterprise where id=?";
        Comp comp = runner.query(conn,sql,new BeanHandler<Comp>(Comp.class),id);
        DBHelper.close(conn);
        return comp;
    }

    /**
     * 根据人才身份证查人才信息
     * @paramidNumber
     * @return
     * @throws SQLException
     */
    public Comp getByLicense(long license) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,`name`,idNumber,state,license,tel,ename,state from  enterprise where license=?";
        Comp comp = runner.query(conn,sql,new BeanHandler<Comp>(Comp.class),license);
        DBHelper.close(conn);
        return comp;
    }



    public static void main(String[] args) {
        CompDao compDao = new CompDao();
    }
}

