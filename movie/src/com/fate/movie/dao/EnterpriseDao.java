package com.fate.movie.dao;


import com.fate.movie.bean.Enterprise;
import com.fate.movie.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EnterpriseDao {
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

    public List<Enterprise> getAll() throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,`name`,idNumber,state,license,tel,ename,state from  enterprise";
        List<Enterprise> enterprises = runner.query(conn,sql,new BeanListHandler<Enterprise>(Enterprise.class));
        DBHelper.close(conn);
        return  enterprises;
    }

    /**
     * 根据人才编号查人才信息
     * @param id
     * @return
     * @throws SQLException
     */



    public Enterprise getById(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,`name`,idNumber,state,license,tel,ename,state from  enterprise where id=?";
        Enterprise enterprise = runner.query(conn,sql,new BeanHandler<Enterprise>(Enterprise.class),id);
        DBHelper.close(conn);
        return  enterprise;
    }

    /**
     * 根据人才身份证查人才信息
     * @paramidNumber
     * @return
     * @throws SQLException
     */
    public Enterprise getByLicense(long license) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,`name`,idNumber,state,license,tel,ename,state from  enterprise where license=?";
        Enterprise enterprise = runner.query(conn,sql,new BeanHandler<Enterprise>(Enterprise.class),license);
        DBHelper.close(conn);
        return  enterprise;
    }



    public static void main(String[] args) {
        EnterpriseDao enterpriseDao  = new EnterpriseDao();
    }
}

