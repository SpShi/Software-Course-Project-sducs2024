package com.fate.movie.dao;

import com.fate.movie.bean.Member;
import com.fate.movie.bean.User;
import com.fate.movie.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    QueryRunner runner = new QueryRunner();

    /**
     * 添加用户
     * @param pwd
     * @param type
     * @return
     * @throws SQLException
     */
    public int add(long id,String pwd,long type) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="insert into user(id,pwd,type) " + " values(?,?,?)";
        int count = runner.update(conn,sql,id,pwd,type);
        DBHelper.close(conn);
        return count;
    }
    public int modify(long id,String pwd,long type) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="update user set  pwd=?,type=? " +
                "where id=?";
        int count = runner.update(conn,sql,pwd,type,id);
        DBHelper.close(conn);
        return count;
    }
    public int remove(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="delete from user where id=?";
        int count = runner.update(conn,sql,id);
        DBHelper.close(conn);
        return count;
    }


    public List<User> getAll() throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,pwd,`type` from user";
        List<User> users = runner.query(conn,sql,new BeanListHandler<User>(User.class));
        DBHelper.close(conn);
        return  users;
    }

    public User getuser(long id, String pwd) throws SQLException {
        //create poject
        Connection connection= DBHelper.getConnection();
        //conect
        String sql="SELECT * FROM user WHERE id=? and pwd=?";
        //sql
        User user=runner.query(connection,sql,new BeanHandler<User>(User.class),id,pwd);
        //quary
        connection.close();
        //close
        return  user;
        //return
    }
    /**
     * 根据会员编号查会员信息
     * @param id
     * @return
     * @throws SQLException
     */
    public User getById(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select id,pwd,`type` from  user where id=?";
        User user = runner.query(conn,sql,new BeanHandler<User>(User.class),id);
        DBHelper.close(conn);
        return  user;
    }

    /**
     * 根据会员身份证查会员信息
     * @paramidNumber
     * @return
     * @throws SQLException
     */
    public long getidBysp() throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select `name` from sp where id=1";
        int num = runner.query(conn,sql,new ScalarHandler<>());
        long longNum =  num;
        DBHelper.close(conn);
        return longNum;
    }
    public int modifyspname(long id,long name) throws SQLException {
        String sql="update sp set name = ? where id=?";
        Connection conn = DBHelper.getConnection();
        int count = runner.update(conn,sql,name,id);
        DBHelper.close(conn);
        return count;
    }

    /**
     * 判断会员编号是否存在Record中(作为外键 ）
     * @param id
     * @return
     */
//    public boolean exits(long id) throws SQLException {
//        Connection conn = DBHelper.getConnection();
//        String sql="select count(id) from record where memberId = ?";
//        Number number= runner.query(conn,sql,new ScalarHandler<>(),id);
//        DBHelper.close(conn);
//        return number.intValue()>0?true:false;
//    }

    public int modifyPwd(long id,String pwd) throws SQLException {
        String sql="update user set pwd = ? where id=?";
        Connection conn = DBHelper.getConnection();
        int count = runner.update(conn,sql,pwd,id);
        DBHelper.close(conn);
        return count;
    }

    public static void main(String[] args) {
        UserDao dao  = new UserDao();
    }
}
