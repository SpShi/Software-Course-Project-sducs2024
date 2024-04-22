package com.fate.movie.biz;

import com.fate.movie.bean.User;
import com.fate.movie.dao.UserDao;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserBiz {
    UserDao userDao = new UserDao();
    public int add(long id,String pwd,long type){
        int count = 0;
        try {
            count = userDao.add(id,pwd,type);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int modify(long id,String pwd,long type){
        int count = 0;
        try {
            count = userDao.modify(id,pwd,type);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int remove(long id) throws Exception {
        //1.判断会员账号余额 >0 :提示不能删除
        User user = getById(id);
//        if(member.getBalance()>0){
//            throw new Exception("此会员消费金额大于0,删除失败");
//        }
//        //2.有外键不能删除
//        if(memberDao.exits(id)){
//            throw new Exception("此会员有子信息,删除失败");
//        }
        //3.删除
        int count =0;
        try {
            count = userDao.remove(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;

    }
    public List<User> getAll(){
        List<User> users = null;
        try {
            users =  userDao.getAll();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;

    }
    public User getById(long id){
        User user = null;
        try {
            user = userDao.getById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
    public long getidBysp(){
        long res=0;
        try {
            res=userDao.getidBysp();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }
    public int modifysp(long id,long name){
        int count =0;
        try {
            count=userDao.modifyspname(id,name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    // 截取字符串的方法
    public static String getStr(String str,int a,int b)
    {
        b++;
        return str.substring(a,b);
    }

    public int modifyPwd(long id,String pwd){
        int count = 0;
        try {
            count = userDao.modifyPwd(id,pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;

    }

}
