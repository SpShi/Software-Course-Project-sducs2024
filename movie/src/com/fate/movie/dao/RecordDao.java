package com.fate.movie.dao;

import com.fate.movie.bean.Movie;
import com.fate.movie.bean.Record;
import com.fate.movie.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RecordDao {
    QueryRunner runner = new QueryRunner();
    public List<Record> getRecordByMovieId(long movieId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql ="select * from record where movieId= ?";
        List<Record> records = runner.query(conn,sql,new BeanListHandler<Record>(Record.class),movieId);
        DBHelper.close(conn);
        return records;
    }
    /**
     * 根据用户的身份证号查询用户购买信息
     * @param idNum
     * @return
     */
    public List<Record> getRecordsByIdNum(String idNum) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql ="select * from record where memberId = (select id from member where idNumber = ?)";
        List<Record> records = runner.query(conn,sql,new BeanListHandler<Record>(Record.class), idNum);
        DBHelper.close(conn);
        return records;
    }

    /**
     * 根据用户的会员编号查询用户购买信息
     * @param memberId
     * @return
     */
    public List<Record> getRecordsByMemberId(long memberId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql ="select * from record where memberId= ? and backDate is null";
        List<Record> records = runner.query(conn,sql,new BeanListHandler<Record>(Record.class),memberId);
        DBHelper.close(conn);
        return records;
    }
    public boolean checkseat (Movie movie, long seat) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select count(id) from record where movieId=? AND seat=?";
        Number data = runner.query(conn,sql,new ScalarHandler<>(),movie.getId(),seat);
        if(data.intValue()>0)
            return false;
        sql = "select volumn from chamber where id=?";
        int res = runner.query(conn,sql,new ScalarHandler<>(),movie.getAddress());
        if(seat>res)
            return false;
        DBHelper.close(conn);
        return true;
    }
    /**
     * 添加购买记录
     * @param memberId
     * @param movieId
     * @param deposit
     * @param userId
     * @return
     * @throws SQLException
     */

    public int add(long memberId,long movieId,Date buyDate,double deposit,long userId,String bname,long chamberId,long seat) throws SQLException {
        String sql="insert into record values(null,?,?,?,null,?,?,?,?,?)";
        Connection conn = DBHelper.getConnection();
        int count = runner.update(conn,sql,memberId,movieId,buyDate,deposit,userId,bname,chamberId,seat);
        DBHelper.close(conn);
        return count;
    }

    /**
     *
     * @param userId 管理员编号
     * @param id 记录编号
     * @return
     */
    public  int modify(Date backDate,long userId,long id) throws SQLException {
        String sql = "update  record set backDate =?,userId = ? where id=?";
        Connection conn = DBHelper.getConnection();
        int count = runner.update(conn,sql,backDate,userId,id);
        DBHelper.close(conn);
        return count;
    }

    public Record getById(long recordId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql ="select * from record where id=?";
        Record record = runner.query(conn,sql,new BeanHandler<Record>(Record.class),recordId);
        DBHelper.close(conn);
        return record;
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

    public  List<Map<String,Object>>  query(int typeId,String keyWork) throws SQLException {
        Connection conn = DBHelper.getConnection();
        StringBuilder sb = new StringBuilder("select * from recordView where 1=1 ");
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
