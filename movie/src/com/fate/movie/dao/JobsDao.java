package com.fate.movie.dao;

import com.fate.movie.bean.Jobs;
import com.fate.movie.bean.Movie;
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

    /**
     * 添加岗位
     * @param name
     * @param place
     * @param age
     * @param gender
     * @param degrees
     * @param major
     * @param ctfct
     * @param salary
     * @param email
     * @param intro
     * @return
     * @throws SQLException
     */
    public int add(String name,long place,int age,int gender,int degrees,String major,String ctfct,
                   int salary,String email,String intro) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="insert into jobs(`name`,place,age,gender,degrees,major,certificates,salary,email,intro) " +
                " values(?,?,?,?,?,?,?,?,?,?)";
        int count = runner.update(conn,sql,name,place,age,gender,degrees,major,ctfct,salary,email,intro);
        DBHelper.close(conn);
        return count;
    }

    /**
     * 修改岗位详细信息
     * @param id
     * @param name
     * @param place
     * @param age
     * @param gender
     * @param degrees
     * @param major
     * @param ctfct
     * @param salary
     * @param email
     * @param intro
     * @return
     * @throws SQLException
     */
    public int modify(long id,String name,long place,int age,int gender,int degrees,String major,String ctfct,
                      int salary,String email,String intro) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="update jobs set `name` = ?, place=?,age=?,gender=?,degrees=?,major=?,certificates=?, " +
                "salary=?,email=?,intro=? where id=?";
        int count = runner.update(conn,sql,name,place,age,gender,degrees,major,ctfct,salary,email,intro,id);
        DBHelper.close(conn);
        return count;
    }

    /**
     * 删除指定岗位
     * @param id
     * @return
     * @throws SQLException
     */
    public int remove(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="delete from jobs where id=?";
        int count = runner.update(conn,sql,id);
        DBHelper.close(conn);
        return count;
    }
    /**
     * 删除指定岗位
     * @param id
     * @return
     * @throws SQLException
     */
    public int removebyplace(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="delete from jobs where place=?";
        int count = runner.update(conn,sql,id);
        DBHelper.close(conn);
        return count;
    }
    /**
     * 所有
     * @return
     * @throws SQLException
     */

    public List<Jobs> getAll() throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select * from  jobs";
        List<Jobs> jobss = runner.query(conn,sql,new BeanListHandler<Jobs>(Jobs.class));
        DBHelper.close(conn);
        return  jobss;
    }

    /**
     * 根据岗位编号查岗位信息
     * @param id
     * @return
     * @throws SQLException
     */
    public Jobs getById(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select * from  jobs where id=?";
        Jobs jobs = runner.query(conn,sql,new BeanHandler<Jobs>(Jobs.class),id);
        DBHelper.close(conn);
        return  jobs;
    }

    /**
     * 获得符合条件的岗位
     * @param place 所属公司
     * @param agel 最低年龄
     * @param ageh 最高年龄
     * @param gender 性别限制
     * @param degrees 最低学历
     * @param salary 最低工资
     * @param key 关键词
     * @param desc 是否按照逆序薪资排序
     * @return
     * @throws SQLException
     */
    public List<Jobs> getAllwithLimit(long place,int agel,int ageh,int gender,int degrees,
                                      int salary,String key,boolean desc) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select * from  jobs ";
        if(place==0){
            sql=sql+ "where age>=? AND age<=? AND gender=? AND degrees>? AND salary >=? ORDER BY salary ";
        }
        else{
            sql=sql+"where place=? AND age>=? AND age<=? AND gender=? AND degrees>? AND salary >=? ";
        }
        if(key!="") sql+="and intro like '%"+key+"%'";
        if(desc) sql=sql+"desc";
        sql+="ORDER BY salary ";
        List<Jobs> jobs ;
        if(place==0) jobs= runner.query(conn,sql,new BeanListHandler<Jobs>(Jobs.class),agel,ageh,gender,degrees,salary);
        else jobs= runner.query(conn,sql,new BeanListHandler<Jobs>(Jobs.class),place,agel,ageh,gender,degrees,salary);
        DBHelper.close(conn);
        return  jobs;
    }

    public boolean exits(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select count(id) from jobs where id = ?";
        Number number= runner.query(conn,sql,new ScalarHandler<>(),id);
        DBHelper.close(conn);
        return number.intValue()>0?true:false;
    }
    /**
     * 判断岗位编号是否存在Record中(作为外键 ）
     * @param id
     * @return
     */
    public boolean exits_e(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql="select count(id) from e2c_record where jobsId = ?";
        Number number= runner.query(conn,sql,new ScalarHandler<>(),id);
        DBHelper.close(conn);
        return number.intValue()>0?true:false;
    }
    /**
     *页面查询(暂时不考虑排序问题)
     * @param pageIndex 第几页,从1开始
     * @param pageSize 每页多少行
     * @return 当前页的信息
     * @throws SQLException
     */
    public List<Jobs>  getByPage(int pageIndex, int pageSize) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql = "select * from jobs limit ?,?";
        int offset = (pageIndex-1)*pageSize;
        List<Jobs> jobs = runner.query(conn,sql,new BeanListHandler<Jobs>(Jobs.class),offset,pageSize);
        DBHelper.close(conn);
        return  jobs;
    }
    public int  getCount() throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql = "select count(id)from jobs";
        Number data = runner.query(conn,sql,new ScalarHandler<>());
        int count = data.intValue();
        DBHelper.close(conn);
        return count;
    }
    public static void main(String[] args) {
        JobsDao jobsDao  = new JobsDao();
    }
}

