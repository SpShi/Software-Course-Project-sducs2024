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
    public List<ERecord> getRecordByEliteId(long eliteId) throws SQLException {
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
        String sql="insert into e2c_record values(null,?,?,?,null,?,?,?,?,?)";
        Connection conn = DBHelper.getConnection();
        int count = runner.update(conn,sql,eliteid,jobid,sendDate,comment);
        DBHelper.close(conn);
        return count;
    }

    /**
     *
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
     * 全部:0
     *     已归还:1
     *    未归还 :2
     *    最近一周需归还:3
     * @param typeId
     * @param keyWork
     * @return
     * @throws SQLException
     */

    public  List<Map<String,Object>>  query( String keyWork) throws SQLException {
        Connection conn = DBHelper.getConnection();
        StringBuilder sb = new StringBuilder("select * from recordView where 1=1 ");
        if(keyWork!=null){
            sb.append(" and movieName like '%"+keyWork+"%' or memberName like '%"+keyWork+"%' or   concat(buyDate,'') like '%"+keyWork+"%'");
        }
        List<Map<String,Object>> data =runner.query(conn,sb.toString(),new MapListHandler());
        DBHelper.close(conn);
        return data;
    }
    public  List<Map<String,Object>>  query_beta(String name,int typeId,String keyWork) throws SQLException {
        Connection conn = DBHelper.getConnection();
        StringBuilder sb = new StringBuilder("select * from recordView where memberName=? and 1=1 ");
        switch (typeId){
            case 0:
                break;
            case 1:
                sb.append("and  backDate is not null ");
                break;
            case 2:
                sb.append("and  backDate is  null ");
                break;
            case 3:
                sb.append("and  backDate is null and  returnDate < date_add(CURRENT_DATE,interval 1 DAY) ");
                break;
        }
        if(keyWork!=null){
            sb.append(" and movieName like '%"+keyWork+"%' or memberName like '%"+keyWork+"%' or   concat(buyDate,'') like '%"+keyWork+"%'");
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
