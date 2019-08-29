package org.java.entity;

import java.util.Date;

public class Yaook {
    private String yaookId;

    private Integer yaookMoney;

    private String yaookBr;

    private Date cretime;

    public String getYaookId() {
        return yaookId;
    }

    public void setYaookId(String yaookId) {
        this.yaookId = yaookId == null ? null : yaookId.trim();
    }

    public Integer getYaookMoney() {
        return yaookMoney;
    }

    public void setYaookMoney(Integer yaookMoney) {
        this.yaookMoney = yaookMoney;
    }

    public String getYaookBr() {
        return yaookBr;
    }

    public void setYaookBr(String yaookBr) {
        this.yaookBr = yaookBr == null ? null : yaookBr.trim();
    }

    public Date getCretime() {
        return cretime;
    }

    public void setCretime(Date cretime) {
        this.cretime = cretime;
    }
}