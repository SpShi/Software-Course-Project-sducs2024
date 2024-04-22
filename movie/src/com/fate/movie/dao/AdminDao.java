package com.fate.movie.dao;

import com.fate.movie.bean.Admin;
import com.fate.movie.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdminDao {
    QueryRunner runner = new QueryRunner();

    /**
     * @param id
     * @param name
     * @param tel
     * @return
     * @throws SQLException
     */
    public int add(long id, String name, long tel) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "insert into admin(id,`name`,tel,state values(?,?,?,1)";
        int count = runner.update(conn, sql, id, name, tel);
        DBHelper.close(conn);
        return count;
    }

    /**
     * @param id
     * @param name
     * @param tel
     * @return
     * @throws SQLException
     */

    public int modify(long id, String name, long tel) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "update admin set `name` = ?,tel = ? where id=?";
        int count = runner.update(conn, sql, name, tel, id);
        DBHelper.close(conn);
        return count;
    }

    /**
     * 删除某位人才,回收利用该会员的id(待实现)
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public int remove(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "delete from admin where id=?";
        int count = runner.update(conn, sql, id);
        DBHelper.close(conn);
        return count;
    }


    /**
     * 全部
     *
     * @return
     * @throws SQLException
     */

    public List<Admin> getAll() throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select id,`name`,tel,state from  admin";
        List<Admin> admins = runner.query(conn, sql, new BeanListHandler<Admin>(Admin.class));
        DBHelper.close(conn);
        return admins;
    }

    /**
     * 根据人才编号查人才信息
     *
     * @param id
     * @return
     * @throws SQLException
     */


    public Admin getById(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select id,`name`,tel,state from  admin where id=?";
        Admin admin = runner.query(conn, sql, new BeanHandler<Admin>(Admin.class), id);
        DBHelper.close(conn);
        return admin;
    }


    public static void main(String[] args) {
        AdminDao adminDao = new AdminDao();
    }
}