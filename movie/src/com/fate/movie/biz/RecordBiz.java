package com.fate.movie.biz;

import com.fate.movie.bean.Member;
import com.fate.movie.bean.Movie;
import com.fate.movie.bean.Record;
import com.fate.movie.dao.MemberDao;
import com.fate.movie.dao.MovieDao;
import com.fate.movie.dao.RecordDao;
import com.fate.movie.util.DBHelper;
import com.fate.movie.util.DateHelper;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class RecordBiz {
    RecordDao  recordDao = new RecordDao();
    MovieDao  movieDao = new MovieDao();
    MemberDao memberDao = new MemberDao();
    MemberBiz  memberBiz = new MemberBiz();
    public List<Record> getRecordsByIdNum(String idNum){
        List<Record> records = null;
        try {
            records=recordDao.getRecordsByIdNum(idNum);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return records;
    }
    public List<Record> getRecordsByMemberId(long memberId){
        List<Record> records = null;
        try {
            records=recordDao.getRecordsByMemberId(memberId);
            //1.外键信息
            //1.1 获取会员对象
           // Member member = memberDao.getById(memberId);//拿不到外键对象
            Member member = memberBiz.getById(memberId);
            for(Record record:records){
                long movieId = record.getMovieId();
                Movie movie = movieDao.getById(movieId);
                record.setMovie(movie);
                record.setMember(member);
                //2.应还时间 ,购买时间+keeyDay
                record.setBackDate(movie.getDate());
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return records;
    }

    /**
     * 购买:
     * 1.借一本数： record表添加一行信息(recordDao,insert) 1
     * 2.这本电影的数量-1 (movieDao,update) 1
     * 3.会员信息表中 ,更改余额( memberDao,update) 1
     * 要么全部成功，要么全部失败(一个业务(事务处理))
     * 前提:用同一个connection对象(如何?)
     * @param memberId
     * @param movieIdList
     * @param userId
     * @return 0:操作失败  1:操作成功
     */
    public int add(long memberId,List<Long> movieIdList,long userId,long seat){
        try {
            //1.启动事务
            DBHelper.beginTransaction();
            double total = 0;
            //2.拿到购买的电影编号
            for(long movieId: movieIdList){
                //电影编号

                //电影对象
                Movie movie = movieDao.getById(movieId);
                if(!recordDao.checkseat(movie,seat))
                {
                    DBHelper.rollbackTransaction();//事务回滚:有异常
                    return 0;
                }
                Member member=memberDao.getById(memberId);
                //调用价格
                double price = movie.getPrice();
                double log=1;
                if(member.getBalance()<100)
                {
                    log = 1-(Math.log(member.getBalance()/10)/Math.log(10))/10;
                }
                else if(member.getBalance()<=3013)
                {
                  log = 1-(Math.log(member.getBalance()/10)/Math.log(10))/10-(member.getBalance()-100)/20000;
                }
                else
                {
                    log =0.6;
                }

                total += price*log;
                //算押金
                //调用recordDao-->insert
                java.util.Date currentDate = new java.util.Date();
                recordDao.add(memberId,movieId,currentDate,price*log,userId,movie.getName(),movie.getAddress(),seat);
                //调用movieDao --> update 数量
                movieDao.modify(movieId,-1);

            }
            //调用memberDao -->update 余额
            memberDao.modifyBalance(memberId,0-total);
            //.事务结束:
            DBHelper.commitTransaction();//事务提交:成功

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                DBHelper.rollbackTransaction();//事务回滚:有异常
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }

        return 1;

    }
    /**
     * 归还功能
     * 开启事务
     * 1.recordDao modify: 押金，归还日期，操作员的编号
     * 2.memberDao modify: 余额
     * 3.电影信息表  modify :数量
     * 成功：提交
     * 失败:回滚
     *
     * @param memberId
     * @param recordIds
     * @param userId
     * @return
     */
    public int  modify(long memberId, List<Long> recordIds, long userId) {
        //1.开启事务
        try {
            DBHelper.beginTransaction();
            double total = 0;
            Member member = memberBiz.getById(memberId);
            for(long recordId:recordIds){
                //2.2 通过recordId 获取记录对象:电影
               Record record = recordDao.getById(recordId);
               Movie movie=movieDao.getById(record.getMovieId());
                total+=record.getDeposit();
                Date backDate =movie.getDate();
                //系统当前时间
                java.util.Date currentDate = new java.util.Date();
                if(currentDate.after(backDate)){
                    return 0;
                }
               //2.2 更改record
                recordDao.modify(currentDate,userId,recordId);
                //2.3 修改电影 +1
                movieDao.modify(record.getMovieId(),1);
            }
            //2。4修改余额
            memberDao.modifyBalance(memberId,total);
            DBHelper.commitTransaction();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                DBHelper.rollbackTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }

        return 1;

    }

    /**
     * 查询
     * @param typeId
     * @param keyword
     * @return
     */
    public List<Map<String,Object>> query(int typeId, String keyword){
        List<Map<String,Object>> rows = null;
        try {
            rows = recordDao.query(typeId,keyword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;

    }
    public List<Map<String,Object>> query_beta(String name,int typeId, String keyword){
        List<Map<String,Object>> rows = null;
        try {
            rows = recordDao.query_beta(name,typeId,keyword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;

    }
}
