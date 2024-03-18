package com.fate.movie.bean;

import java.io.Serializable;

/**
 * 书的实体类:注意外键
 * 1.DBUtil无法生成表以外的数据
 * 2.外键的实体对象没有数据，需要后期手动添加biz(业务去实现)
 */
public class Chamber implements Serializable {

  private long id;
  private String name;
  private String pic;
  private String address;
  private int volumn;
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getVolumn() {
    return volumn;
  }

  public void setVolumn(int volumn) {
    this.volumn = volumn;
  }
  @Override
  public String toString() {
    return "Chamber{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", pic='" + pic + '\'' +
            ", address='" + address + '\'' +
            ",volumn="+volumn+
            '}';
  }
}
