package com.fate.movie.dao;

import com.fate.movie.bean.Movie;
import com.fate.movie.bean.ERecord;
import com.fate.movie.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ERecordDao {
    QueryRunner runner = new QueryRunner();

    /**
     * 反向查询
     * @param eliteId
     * @return
     * @throws SQLException
     */
    public List<ERecord> getRecordsByEliteId(long eliteId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql ="select * from e2c_record where eliteid= ?";
        List<ERecord> records = runner.query(conn,sql,new BeanListHandler<ERecord>(ERecord.class),eliteId);
        DBHelper.close(conn);
        return records;
    }
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public List<ERecord> getRecordById(int id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql ="select * from e2c_record where id = ?";
        List<ERecord> records = runner.query(conn,sql,new BeanListHandler<ERecord>(ERecord.class), id);
        DBHelper.close(conn);
        return records;
    }

    /**
     * 查询特定工作的所有简历
     * @param jobid
     * @param desc
     * @return
     * @throws SQLException
     */
    public List<ERecord> getRecordsByJobId(long jobid,boolean desc) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql ="select * from e2c_record where jobid= ? order by senddate";
        if(desc) sql=sql+" desc";
        List<ERecord> records = runner.query(conn,sql,new BeanListHandler<ERecord>(ERecord.class),jobid);
        DBHelper.close(conn);
        return records;
    }

    /**
     * 添加简历发送记录
     * @param eliteid
     * @param jobid
     * @param sendDate
     * @param comment
     * @return
     * @throws SQLException
     */

    public int add(long eliteid,long jobid, Date sendDate,String comment ) throws SQLException {
        String sql="insert into e2c_record values(?,?,?,?)";
        Connection conn = DBHelper.getConnection();
        int count = runner.update(conn,sql,eliteid,jobid,sendDate,comment);
        DBHelper.close(conn);
        return count;
    }

    /**
     *重新投递简历
     * @param sendDate
     * @param comment
     * @param eliteid
     * @param jobid
     * @return
     * @throws SQLException
     */
    public  int modify(Date sendDate,String comment,long eliteid,long jobid) throws SQLException {
        String sql = "update  e2c_record set senddate =?,comment = ? where eliteid=? and jobid= ?";
        Connection conn = DBHelper.getConnection();
        int count = runner.update(conn,sql,sendDate,comment,eliteid,jobid);
        DBHelper.close(conn);
        return count;
    }

    /**
     *查询关键词
     * @param keyWork
     * @return
     * @throws SQLException
     */

    public  List<Map<String,Object>>  query( String keyWork) throws SQLException {
        Connection conn = DBHelper.getConnection();
        StringBuilder sb = new StringBuilder("select * from erecordview where 1=1 ");
        if(keyWork!=null){
            sb.append(" and message like '%"+keyWork+"%' or major like '%"+keyWork+"%' or selfevaluation like '%"+keyWork+
                    "%' or intention like '%"+keyWork+"%' or experience like '%"+keyWork+"%' or certificate like '%"+keyWork+"%' ");
        }
        List<Map<String,Object>> data =runner.query(conn,sb.toString(),new MapListHandler());
        DBHelper.close(conn);
        return data;
    }

    /**
     * 查询特定人物的关键词
     * @param name
     * @param keyWork
     * @return
     * @throws SQLException
     */
    public  List<Map<String,Object>>  query_beta(String name,String keyWork) throws SQLException {
        Connection conn = DBHelper.getConnection();
        StringBuilder sb = new StringBuilder("select * from erecordview where name=? ");
        if(keyWork!=null){
            sb.append(" and message like '%"+keyWork+"%' or major like '%"+keyWork+"%' or selfevaluation like '%"+keyWork+
                    "%' or intention like '%"+keyWork+"%' or experience like '%"+keyWork+"%' or certificate like '%"+keyWork+"%' ");
        }
        List<Map<String,Object>> data =runner.query(conn,sb.toString(),new MapListHandler(),name);
        DBHelper.close(conn);
        return data;
    }
    public static void main(String[] args) {
        try {
            List<Map<String,Object>> records = new RecordDao().query(0,null);
            for(Map<String,Object> row:records){
                for(String key:row.keySet()){
                    System.out.print(key+":"+row.get(key)+"\t");
                }
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
