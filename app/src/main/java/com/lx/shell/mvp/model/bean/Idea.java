package com.lx.shell.mvp.model.bean;

/**
 * @auther lixu
 * Created by lixu on 2017/5/9 0009.
 * 测试类
 */
public class Idea {
    private int score;//分数
    private String ideaInfo;//建议

    public Idea(){}

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getIdeaInfo() {
        return ideaInfo;
    }

    public void setIdeaInfo(String ideaInfo) {
        this.ideaInfo = ideaInfo;
    }
}
