package com.lx.shell.mvp.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lixu on 2017/9/11.
 */
@Entity
public class DaoBean_2 {
    @Id
    private Long id;
    private String my;
    @Generated(hash = 310837458)
    public DaoBean_2(Long id, String my) {
        this.id = id;
        this.my = my;
    }
    @Generated(hash = 575192583)
    public DaoBean_2() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMy() {
        return this.my;
    }
    public void setMy(String my) {
        this.my = my;
    }
}
