package com.fate.movie.bean;


import java.io.Serializable;

public class MemberType implements Serializable {

  private long id;
  private String name;
  private long amount;
  private double discount;
  private long recharge;
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

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }


  public long getRecharge() {
    return recharge;
  }

  public void setRecharge(long recharge) {
    this.recharge = recharge;
  }


  @Override
  public String toString() {
    return "MemberType{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", amount='" + amount + '\'' +
            ", discount=" + discount +
            ", recharge=" + recharge +
            '}';
  }
}
