package com.fate.movie.biz;



import com.fate.movie.bean.Admin;
import com.fate.movie.dao.AdminDao;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminBiz {
    AdminDao adminDao = new AdminDao();
    public int add(long id,String name,long tel){
        int count = 0;
        try {
            count = adminDao.add(id,name,tel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int modify(long id,String name,long tel){
        int count = 0;
        try {
            count = adminDao.modify(id,name,tel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int remove(long id) throws Exception {
        Admin admin = getById(id);
        //3.删除
        int count =0;
        try {
            count = adminDao.remove(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;

    }
    public List<Admin> getAll(){
        List<Admin> admins = null;
        try {
            admins =  adminDao.getAll();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admins;

    }
    public Admin getById(long id){
        Admin admin = null;
        try {
            admin = adminDao.getById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admin;
    }


    // 截取字符串的方法
    public static String getStr(String str,int a,int b)
    {
        b++;
        return str.substring(a,b);
    }

    /**
     * 判断电话号码是否合法
     * @param tele
     * @return
     */
    public static boolean checktel(String tele) {
        try {
            if (tele.length() != 11) {
                return false;
            } else {
                String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(tele);
                if (m.matches()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception E) {
            return false;
        }
    }

}
