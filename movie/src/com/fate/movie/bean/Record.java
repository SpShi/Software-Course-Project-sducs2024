package com.fate.movie.bean;


import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {

    private long id;
    private long memberId;
    private long movieId;
    private long chamberId;
    private Date buyDate;
    private Date backDate;
    private double deposit;
    private long userId;
    private String bname;
    private long seat;

    //外键
    private Member member;
    private Movie movie;
    private Member user;
    private Chamber chamber;

    public long getId() {
    return id;
    }

    public void setId(long id) {
    this.id = id;
    }


    public long getMemberId() {
    return memberId;
    }

    public void setMemberId(long memberId) {
    this.memberId = memberId;
    }


    public long getMovieId() {
    return movieId;
    }

    public void setMovieId(long movieId) {
    this.movieId = movieId;
    }

    public long getChamberId() {
        return chamberId;
    }

    public void setChamberId(long chamberId) {
        this.chamberId =chamberId;
    }


    public Date getBuyDate() {
    return buyDate;
    }

    public void setBuyDate(Date buyDate) {
    this.buyDate = buyDate;
    }


    public Date getBackDate() {
    return backDate;
    }

    public void setBackDate(Date backDate) {
    this.backDate = backDate;
    }


    public double getDeposit() {
    return deposit;
    }

    public void setDeposit(double deposit) {
    this.deposit = deposit;
    }


    public long getUserId() {
    return userId;
    }

    public void setUserId(long userId) {
    this.userId = userId;
    }

    public long getSeat() {
        return seat;
    }

    public void setSeat(long seat) {
        this.seat = seat;
    }

    public String getBname() {
    return bname;
    }

    public void setBname(String bname) {
    this.bname = bname;
    }

    public Member getMember() {
    return member;
    }

    public void setMember(Member member) {
    this.member = member;
    }

    public Movie getMovie() {
    return movie;
    }

    public void setMovie(Movie movie) {
    this.movie = movie;
    }

    public Member getUser() {
    return user;
    }

    public void setUser(Member user) {
    this.user = user;
    }

    public Chamber getChamber() {
    return chamber;
    }

    public void setChamber(Chamber chamber) {
    this.chamber = chamber;
    }
    @Override
    public String toString() {
    return "Record{" +
            "id=" + id +
            ", memberId=" + memberId +
            ", movieId=" + movieId +
            ", chamberId=" + chamberId +
            ", buyDate=" + buyDate +
            ", backDate=" + backDate +
            ", deposit=" + deposit +
            ", userId=" + userId +
            ", isbn='" + bname + '\'' +
            ", member=" + member +
            ", movie=" + movie +
            ", user=" + user +
            ", Chember=" + chamber +
            ", Seat=" + seat +
            '}';
    }
}
