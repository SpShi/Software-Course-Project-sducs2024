package com.fate.movie.biz;

import com.fate.movie.bean.Member;
import com.fate.movie.bean.MemberType;
import com.fate.movie.bean.Movie;
import com.fate.movie.dao.MemberDao;
import com.fate.movie.dao.MemberTypeDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberBiz {
  MemberDao  memberDao = new MemberDao();
  MemberTypeDao   typeDao = new MemberTypeDao();
  public int add(String name,String pwd,long typeId,double balance,String tel,String idNumber){
    int count = 0;
    try {
      count = memberDao.add(name,pwd,typeId,balance,tel,idNumber);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return count;
  }
  public int modify(long id,String name,String pwd,long typeId,double balance,String tel,String idNumber){
    int count = 0;
    try {
      count = memberDao.modify(id,name,pwd,typeId,balance,tel,idNumber);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return count;
  }
  public int remove(long id) throws Exception {
    //1.判断会员账号余额 >0 :提示不能删除
    Member member = getById(id);
    if(member.getBalance()>0){
      throw new Exception("此会员消费金额大于0,删除失败");
    }
    //2.有外键不能删除
    if(memberDao.exits(id)){
      throw new Exception("此会员有子信息,删除失败");
    }
    //3.删除
    int count =0;
    try {
      count = memberDao.remove(id);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return count;

  }
  public int modifyBalance(String idNumber,double amount){
    int count = 0;
    try {
      count = memberDao.modifyIdNumber(idNumber,amount);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return count;
  }
  public List<Member> getAll(){
    MemberTypeDao typeDao = new MemberTypeDao();
    List<Member> members = null;
    try {
      members =  memberDao.getAll();
      for(Member member:members){
        //根据类型编号获取类型对象
        MemberType type = typeDao.getById(member.getTypeId());
        member.setType(type);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();

    }
    return members;

  }
  public Member getById(long id){
    Member member = null;
    try {
      member = memberDao.getById(id);
      MemberType memberType = typeDao.getById(member.getTypeId());
      member.setType(memberType);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return member;
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
   * 会员信息+会员对应的类型信息
   * @param idNumber
   * @return
   */
  public Member getByIdNumber(String idNumber){
    Member member = null;
    try {
      member = memberDao.getByIdNumber(idNumber);
      MemberType memberType = typeDao.getById(member.getTypeId());
      member.setType(memberType);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return member;
  }
  public Member getMember(String name, String pwd){
    Member  member = null;
    try {
      member  = memberDao.getmember(name,pwd);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return member;
  }
  public int modifyPwd(long id,String pwd){
    int count = 0;
    try {
      count = memberDao.modifyPwd(id,pwd);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return count;

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
