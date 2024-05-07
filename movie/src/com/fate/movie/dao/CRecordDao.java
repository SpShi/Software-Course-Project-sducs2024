package com.fate.movie.dao;

import com.fate.movie.bean.CRecord;
import com.fate.movie.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CRecordDao {
    QueryRunner runner = new QueryRunner();

    /**
     * 反向查询
     * @param eliteId
     * @return
     * @throws SQLException
     */
    public List<CRecord> getRecordsByEliteId(long eliteId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql ="select * from c2e_record where eliteid= ?";
        List<CRecord> records = runner.query(conn,sql,new BeanListHandler<CRecord>(CRecord.class),eliteId);
        DBHelper.close(conn);
        return records;
    }
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public List<CRecord> getRecordById(int id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql ="select * from c2e_record where id = ?";
        List<CRecord> records = runner.query(conn,sql,new BeanListHandler<CRecord>(CRecord.class), id);
        DBHelper.close(conn);
        return records;
    }

    /**
     * 查询投向特定工作的简历
     * @param jobid
     * @param desc
     * @return
     * @throws SQLException
     */
    public List<CRecord> getRecordsByJobId(long jobid,boolean desc) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql ="select * from c2e_record where jobid= ? order by senddate";
        if(desc) sql=sql+" desc";
        List<CRecord> records = runner.query(conn,sql,new BeanListHandler<CRecord>(CRecord.class),jobid);
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

    public int add(long eliteid, long jobid, Date sendDate, String comment,int fromid ) throws SQLException {
        String sql="insert into c2e_record values(?,?,?,?,?)";
        Connection conn = DBHelper.getConnection();
        int count = runner.update(conn,sql,jobid,eliteid,sendDate,fromid,comment);
        DBHelper.close(conn);
        return count;
    }

    /**
     *修改详细信息,在重新投递简历时用到
     * @param sendDate
     * @param comment
     * @param eliteid
     * @param jobid
     * @return
     * @throws SQLException
     */
    public  int modify(Date sendDate,String comment,long eliteid,long jobid,int fromid) throws SQLException {
        String sql = "update  c2e_record set senddate =?,comment = ? ,fromid=? where eliteid=? and jobid= ?";
        Connection conn = DBHelper.getConnection();
        int count = runner.update(conn,sql,sendDate,comment,fromid,eliteid,jobid);
        DBHelper.close(conn);
        return count;
    }

    public static void main(String[] args) {
        CRecordDao cRecordDao=new CRecordDao();
    }
}
