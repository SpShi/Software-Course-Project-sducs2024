package com.fate.movie.biz;

import com.fate.movie.bean.Member;
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

    /**
     * 删除前保证没有子信息
     * @param id
     * @return
     * @throws Exception
     */
    public int remove(long id) throws Exception {

        if(userDao.exits_e(id)){
            throw new Exception("此会员有子信息,删除失败");
        }
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

    /**
     * 密码账号判定
     * @param id
     * @param pwd
     * @return
     */
    public User getUser(long id, String pwd){
        User  user = null;
        try {
            user  = userDao.getuser(id,pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
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
