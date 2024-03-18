package com.fate.movie.dao;

import com.fate.movie.bean.Chamber;
import com.fate.movie.bean.Type;
import com.fate.movie.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ChamberDao {
    QueryRunner runner = new QueryRunner();

    /**
     * 添加电影
     * @return
     */
    public int add(String name,String pic, String address,int volumn) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="insert into chamber(`name`,pic,address,volumn) " +
                "values(?,?,?,?)";
        int count = runner.update(conn,sql,name,pic,address,volumn);
        DBHelper.close(conn);
        return count;

    }
    public List<Chamber> getAll() throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,name from chamber ";
        List<Chamber> chambers = runner.query(conn, sql, new BeanListHandler<>(Chamber.class));
        DBHelper.close(conn);
        return chambers;
    }
    /**
     * 修改电影
     * @param id
     * @param name
     * @param pic
     * @param address
     * @return
     */
    public int modify(long id,String name,String pic, String address,int volumn) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="update chamber set `name` = ?,pic = ?,address = ?,volumn =? where id= ? ";
        int count = runner.update(conn,sql,name,pic,address,volumn,id);
        DBHelper.close(conn);
        return count;

    }

    /**
     * 修改电影的数量
     * @param id
     * @return
     * @throws SQLException
     */

    public int remove(long id) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="delete from chamber where id=? ";
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
    public List<Chamber>  getByPage(int pageIndex,int pageSize) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql = "select * from chamber limit ?,?";
        int offset = (pageIndex-1)*pageSize;
        List<Chamber> chambers = runner.query(conn,sql,new BeanListHandler<Chamber>(Chamber.class),offset,pageSize);
        DBHelper.close(conn);
        return  chambers;
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Chamber getById(long id) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="select * from chamber where id=?";
        Chamber chamber = runner.query(conn,sql,new BeanHandler<Chamber>(Chamber.class),id);
        DBHelper.close(conn);
        return chamber;
    }

    /**
     * 获取电影数量
     * @return
     * @throws SQLException
     */
    public int  getCount() throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql = "select count(id)from chamber";
        Number data = runner.query(conn,sql,new ScalarHandler<>());
        int count = data.intValue();
        DBHelper.close(conn);
        return count;
    }

    /**
     * 根据电影名称查询电影信息
     * @param chamberName
     * @return
     * @throws SQLException
     */
    public Chamber getByName(String chamberName) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="select * from chamber where name=?";
        Chamber chamber = runner.query(conn,sql,new BeanHandler<Chamber>(Chamber.class),chamberName);
        DBHelper.close(conn);
        return chamber;
    }


    public static void main(String[] args) {

    }
}
