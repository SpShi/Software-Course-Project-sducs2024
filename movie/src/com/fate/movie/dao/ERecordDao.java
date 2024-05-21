package com.fate.movie.dao;


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
    public List<ERecord> getRecordsByEliteId(long eliteId,int state) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql ="select * from e2c_record where eliteid= ?";
        List<ERecord> records;
        if(state <2) {
            sql+=" and state=?";
            records= runner.query(conn,sql,new BeanListHandler<ERecord>(ERecord.class),eliteId,state);
        }
        else records= runner.query(conn,sql,new BeanListHandler<ERecord>(ERecord.class),eliteId);
        DBHelper.close(conn);
        return records;
    }
    /**
     *页面查询(暂时不考虑排序问题)
     * @param pageIndex 第几页,从1开始
     * @param pageSize 每页多少行
     * @return 当前页的信息
     * @throws SQLException
     */
    public List<ERecord>  getByPage(int pageIndex,int pageSize,long eliteId,int state) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql = "select * from e2c_record where eliteid= ? limit ?,?";
        int offset = (pageIndex-1)*pageSize;
        List<ERecord> records ;
        if(state <2) {
            sql+=" and state=?";
            records= runner.query(conn,sql,new BeanListHandler<ERecord>(ERecord.class),eliteId,state,offset,pageSize);
        }
        else records= runner.query(conn,sql,new BeanListHandler<ERecord>(ERecord.class),eliteId,offset,pageSize);
        DBHelper.close(conn);
        return  records;
    }

    /**
     * 获取电影数量
     * @return
     * @throws SQLException
     */
    public int  getCount(long eliteId,int state) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql = "select count(id)from e2c_record where eliteid= ?";
        Number data;
        if(state <2) {
            sql+=" and state=?";
            data = runner.query(conn,sql,new ScalarHandler<>(),eliteId,state);
        }
        else data = runner.query(conn,sql,new ScalarHandler<>(),eliteId);

        int count = data.intValue();
        DBHelper.close(conn);
        return count;
    }
    /**
     * 根据id查询
     * @param id
     * @return
     */

    public ERecord getById(long id) throws SQLException {
        Connection conn  = DBHelper.getConnection();
        String sql="select * from e2c_record where id=?";
        ERecord record = runner.query(conn,sql,new BeanHandler<ERecord>(ERecord.class),id);
        DBHelper.close(conn);
        return record;
    }
    /**
     * 查询特定工作的所有简历
     * @param jobid
     * @param desc
     * @return
     * @throws SQLException
     */
    public List<ERecord> getRecordsByJobId(long jobid,boolean desc,int state) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql ="select * from e2c_record where jobid= ? ";

        List<ERecord> records;
        if(state <2) {
            sql+=" and state=? order by senddate";
            if(desc) sql=sql+" desc";
            records= runner.query(conn,sql,new BeanListHandler<ERecord>(ERecord.class),jobid,state);
        }
        else {
            sql+="order by senddate";
            if(desc) sql=sql+" desc";
            records= runner.query(conn,sql,new BeanListHandler<ERecord>(ERecord.class),jobid);
        }
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

    public int add(long eliteid,long jobid, Date sendDate,String comment) throws SQLException {
        String sql="insert into e2c_record values(?,?,?,?,0)";
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
    public  int modify(Date sendDate,String comment,int state,long eliteid,long jobid) throws SQLException {
        String sql = "update  e2c_record set backdate =?,comment = ?,state=? where eliteid=? and jobid= ?";
        Connection conn = DBHelper.getConnection();
        int count = runner.update(conn,sql,sendDate,comment,state,eliteid,jobid);
        DBHelper.close(conn);
        return count;
    }
    /**
     *查询id
     * @param eliteid
     * @param jobid
     * @return
     * @throws SQLException
     */
    public ERecord getid(long eliteid,long jobid) throws SQLException {
        String sql = "select * from e2c_record where eliteid=? and jobid= ?";
        Connection conn = DBHelper.getConnection();
        ERecord eRecord=runner.query(conn,sql,new BeanHandler<ERecord>(ERecord.class),eliteid,jobid);
        DBHelper.close(conn);
        return eRecord;
    }
    /**
     *查询关键词
     * @param keyWork
     * @return
     * @throws SQLException
     */

    public  List<Map<String,Object>>  query( String keyWork,int state) throws SQLException {
        Connection conn = DBHelper.getConnection();
        StringBuilder sb = new StringBuilder("select * from erecordview where 1=1 ");
        if(state <2) sb.append(" and state=?");
        if(keyWork!=null){
            sb.append(" and message like '%"+keyWork+"%' or major like '%"+keyWork+"%' or selfevaluation like '%"+keyWork+
                    "%' or intention like '%"+keyWork+"%' or experience like '%"+keyWork+"%' or certificate like '%"+keyWork+"%' ");
        }
        List<Map<String,Object>> data;
        if(state <2)  data=runner.query(conn,sb.toString(),new MapListHandler(),state);
        else data =runner.query(conn,sb.toString(),new MapListHandler());
        DBHelper.close(conn);
        return data;
    }

    /**
     * 查询特定人物的关键词
     * @param id
     * @param keyWork
     * @return
     * @throws SQLException
     */
    public  List<Map<String,Object>>  query_0(long id,String keyWork,int state) throws SQLException {
        Connection conn = DBHelper.getConnection();
        StringBuilder sb = new StringBuilder("select * from erecordview where eliteid=? ");

        if(keyWork!=null){
            sb.append(" and message like '%"+keyWork+"%' or major like '%"+keyWork+"%' or selfevaluation like '%"+keyWork+
                    "%' or intention like '%"+keyWork+"%' or experience like '%"+keyWork+"%' or certificate like '%"+keyWork+"%' ");
        }
        List<Map<String,Object>> data;
        if(state <2) {
            sb.append(" and state=?");
            data=runner.query(conn,sb.toString(),new MapListHandler(),id,state);
        }
        else data=runner.query(conn,sb.toString(),new MapListHandler(),id);
        DBHelper.close(conn);
        return data;
    }
    public  List<Map<String,Object>>  query_1(long id,String keyWork,int state) throws SQLException {
        Connection conn = DBHelper.getConnection();
        StringBuilder sb = new StringBuilder("select * from erecordview where place=? ");

        if(keyWork!=null){
            sb.append(" and message like '%"+keyWork+"%' or major like '%"+keyWork+"%' or selfevaluation like '%"+keyWork+
                    "%' or intention like '%"+keyWork+"%' or experience like '%"+keyWork+"%' or certificate like '%"+keyWork+"%' ");
        }

        List<Map<String,Object>> data ;
        if(state <2){
            sb.append(" and state=?");
            data=runner.query(conn,sb.toString(),new MapListHandler(),id,state);
        }
        else data=runner.query(conn,sb.toString(),new MapListHandler(),id);
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
