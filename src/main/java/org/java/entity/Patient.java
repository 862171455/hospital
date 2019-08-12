package org.java.entity;

import java.io.Serializable;


public class Patient implements Serializable{
    private String patientId;

    private String patientUsername;

    private String patientPwd;

    private String patientName;

    private Integer patientGender;

    private String patientTel;

    private String patientIdcard;

    private String prtientEmail;

    private Integer patientArea;

    private Integer patientMoney;

    private String patientAdd;

    private String patientE;

    private String patientN;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername == null ? null : patientUsername.trim();
    }

    public String getPatientPwd() {
        return patientPwd;
    }

    public void setPatientPwd(String patientPwd) {
        this.patientPwd = patientPwd == null ? null : patientPwd.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public Integer getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(Integer patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientTel() {
        return patientTel;
    }

    public void setPatientTel(String patientTel) {
        this.patientTel = patientTel == null ? null : patientTel.trim();
    }

    public String getPatientIdcard() {
        return patientIdcard;
    }

    public void setPatientIdcard(String patientIdcard) {
        this.patientIdcard = patientIdcard == null ? null : patientIdcard.trim();
    }

    public String getPrtientEmail() {
        return prtientEmail;
    }

    public void setPrtientEmail(String prtientEmail) {
        this.prtientEmail = prtientEmail == null ? null : prtientEmail.trim();
    }

    public Integer getPatientArea() {
        return patientArea;
    }

    public void setPatientArea(Integer patientArea) {
        this.patientArea = patientArea;
    }

    public Integer getPatientMoney() {
        return patientMoney;
    }

    public void setPatientMoney(Integer patientMoney) {
        this.patientMoney = patientMoney;
    }

    public String getPatientAdd() {
        return patientAdd;
    }

    public void setPatientAdd(String patientAdd) {
        this.patientAdd = patientAdd == null ? null : patientAdd.trim();
    }

    public String getPatientE() {
        return patientE;
    }

    public void setPatientE(String patientE) {
        this.patientE = patientE == null ? null : patientE.trim();
    }

    public String getPatientN() {
        return patientN;
    }

    public void setPatientN(String patientN) {
        this.patientN = patientN == null ? null : patientN.trim();
    }
    
    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", patientUsername='" + patientUsername + '\'' +
                ", patientPwd='" + patientPwd + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientGender=" + patientGender +
                ", patientTel='" + patientTel + '\'' +
                ", patientIdcard='" + patientIdcard + '\'' +
                ", prtientEmail='" + prtientEmail + '\'' +
                ", patientArea=" + patientArea +
                ", patientMoney=" + patientMoney +
                ", patientAdd='" + patientAdd + '\'' +
                ", patientE='" + patientE + '\'' +
                ", patientN='" + patientN + '\'' +
                '}';
    }
}