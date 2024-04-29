package com.fate.movie.biz;


import com.fate.movie.bean.Comp;
import com.fate.movie.dao.CompDao;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompBiz {
    CompDao compDao = new CompDao();
    public int add(long id,String name,String idNumber,long license,long tel,String ename){
        int count = 0;
        try {
            count = compDao.add(id,name,idNumber,license,tel,ename);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int modify(long id,String name,String idNumber,long license,long tel,String ename){
        int count = 0;
        try {
            count = compDao.modify(id,name,idNumber,license,tel,ename);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int remove(long id) throws Exception {
        //1.判断会员账号余额 >0 :提示不能删除
        Comp comp =getById(id);
        //3.删除
        int count =0;
        try {
            count = compDao.remove(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;

    }

    public List<Comp> getAll(){
        List<Comp> enterprises = null;
        try {
            enterprises =  compDao.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return enterprises;

    }
    public int modifystate(long id,long change){
        int count = 0;
        long state=change;
        try {
            count = compDao.modifystate(id,state);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public Comp getById(long id){
        Comp comp = null;
        try {
            comp = compDao.getById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comp;
    }

    public static boolean checkiN(String idNumber) {

        // 处理最后一位为 x 的情况，小写转大写
        idNumber = idNumber.toUpperCase();

        // 判断身份证号是否合法
        Boolean idLegit = judgeId(idNumber);
        if(idLegit)
        {
            return true;
        }
        else return false;

    }

    // 判断身份证号是否合法
    public static Boolean judgeId(String id)
    {
        Boolean result = true;

        // 长度不等于 18 位
        if(id.length() != 18) return false;

        // 系数算法
        String tempId = getStr(id,0,16);
        int[] coeff = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
        char[] end = {'1','0','X','9','8','7','6','5','4','3','2'};
        int sum = 0;
        for (int i = 0; i < tempId.length(); i++)
        {
            int bye = tempId.charAt(i) - '0';
            sum += bye * coeff[i];
        }
        sum %= 11;
        if(end[sum] != getStr(id,17,17).charAt(0)) result = false;

        return result;
    }

    // 截取字符串的方法
    public static String getStr(String str,int a,int b)
    {
        b++;
        return str.substring(a,b);
    }

    /**
     *
     * @param license
     * @return
     */
    public Comp getByLicense(long license){
        Comp comp = null;
        try {
            comp = compDao.getByLicense(license);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comp;
    }

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
