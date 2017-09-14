package com.lx.shell.core;

import android.content.Context;

import java.lang.reflect.Field;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lixu on 2017/5/12.
 */

public class Constance {
    public static final List<String> chooseList = Arrays.asList("传输内线数据关联性指标","传输内线字段必填性指标","传输内线业务逻辑合理性指标");
    public static final List<String> chooseList2_1 = Arrays.asList("设备所属机房关联到机房表","设备所属机房关联到站点表","设备所属机房关联到机架表","尾线所属隧道关联到隧道表");
    public static final List<String> chooseList2_2 = Arrays.asList("设备所属机房必填性","机房所属站点必填性","设备所属机架必填性","尾线所属隧道必填性");
    public static final List<String> chooseList2_3 = Arrays.asList("接入类型的传输机房内不用超过5个网元");


    public static final List<String> indexList = Arrays.asList("设备所属机房关联到机房表","机房所属站点关联到站点表","设备归属机架关联到机架表","伪线所属隧道关联到隧道表");



    public static final String LOGIN_MVP = "login";
    public static final String LOGIN_NAME_MVP = "name";
    public static final String LOGIN_PWD_MVP = "pwd";


    public static final String List_PAGE_MVP = "page";
    public static final String List_PAGESIZE_MVP = "pageSize";
    public static final String List_UID_MVP = "uid";

    /**
     * 获取状态栏的高度
     * @return
     */
    public static int getStatusBarHeight(Context mContext){
        try
        {
            Class<?> c=Class.forName("com.android.internal.R$dimen");
            Object obj=c.newInstance();
            Field field=c.getField("status_bar_height");
            int x=Integer.parseInt(field.get(obj).toString());
            return  mContext.getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

     public static String getMsgByCode(int Code){
         if(Code == 500){
             return "服务器异常";
         }else if(Code == 400){
             return "请求错误";
         }else if(Code == 401){
             return "登录过期，请重新登录";
         }else if(Code == 404){
             return "操作异常，请联系网络管理员";
         }
         return String.valueOf(Code);
     }

      public static String getMsgByException(Throwable t){
        if(t instanceof SocketTimeoutException){
            return "连接超时，请检查网络";
        }else if(t instanceof ConnectException){
            return "网络连接错误";
        }else if(t instanceof SocketException){
            return "请求被关闭";
        }else if(t.getMessage().toString().contains("Canceled")){
            return "请求被关闭";
        }
        return t.getMessage().toString();
    }
}
