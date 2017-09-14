package com.lx.shell.mvp.model.bean;

/**
 * Created by lixu on 2017/5/20.
 */

public class IdeaDetailBean {
    private String name;
    private String dNum;
    private String pNum;
    private String sace;
    public IdeaDetailBean(){}

    public IdeaDetailBean(String name, String dNum, String pNum, String sace) {
        this.name = name;
        this.dNum = dNum;
        this.pNum = pNum;
        this.sace = sace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getdNum() {
        return dNum;
    }

    public void setdNum(String dNum) {
        this.dNum = dNum;
    }

    public String getpNum() {
        return pNum;
    }

    public void setpNum(String pNum) {
        this.pNum = pNum;
    }

    public String getSace() {
        return sace;
    }

    public void setSace(String sace) {
        this.sace = sace;
    }
}
