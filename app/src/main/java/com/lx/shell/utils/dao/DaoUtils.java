package com.lx.shell.utils.dao;

import android.content.Context;

/**
 * Created by lixu on 2017/9/11.
 */

public class DaoUtils {
    private  static MyGreenDaoManager customerManager;
    public  static Context context;

    public static void init(Context context){
        DaoUtils.context = context.getApplicationContext();
    }

    /**
     * 单列模式获取CustomerManager对象
     * @return
     */
    public static MyGreenDaoManager getCustomerInstance(){
        if (customerManager == null) {
            customerManager = new MyGreenDaoManager(context);
        }
        return customerManager;
    }

}
