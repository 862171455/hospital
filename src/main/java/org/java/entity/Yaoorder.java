package org.java.entity;

public class Yaoorder {
    private Integer yaoorderId;

    private Integer yaoorderNum;

    private String yaoorderBr;

    private Integer yaoorderYao;

    private Integer yaoorderMoney;

    private Integer yaoorderAll;

    private String yaoorderDd;

    public Integer getYaoorderId() {
        return yaoorderId;
    }

    public void setYaoorderId(Integer yaoorderId) {
        this.yaoorderId = yaoorderId;
    }

    public Integer getYaoorderNum() {
        return yaoorderNum;
    }

    public void setYaoorderNum(Integer yaoorderNum) {
        this.yaoorderNum = yaoorderNum;
    }

    public String getYaoorderBr() {
        return yaoorderBr;
    }

    public void setYaoorderBr(String yaoorderBr) {
        this.yaoorderBr = yaoorderBr == null ? null : yaoorderBr.trim();
    }

    public Integer getYaoorderYao() {
        return yaoorderYao;
    }

    public void setYaoorderYao(Integer yaoorderYao) {
        this.yaoorderYao = yaoorderYao;
    }

    public Integer getYaoorderMoney() {
        return yaoorderMoney;
    }

    public void setYaoorderMoney(Integer yaoorderMoney) {
        this.yaoorderMoney = yaoorderMoney;
    }

    public Integer getYaoorderAll() {
        return yaoorderAll;
    }

    public void setYaoorderAll(Integer yaoorderAll) {
        this.yaoorderAll = yaoorderAll;
    }

    public String getYaoorderDd() {
        return yaoorderDd;
    }

    public void setYaoorderDd(String yaoorderDd) {
        this.yaoorderDd = yaoorderDd == null ? null : yaoorderDd.trim();
    }
}