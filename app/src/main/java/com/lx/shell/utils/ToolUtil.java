package com.lx.shell.utils;

import java.util.List;

/**
 * Created by lixu on 2017/5/12.
 */

public class ToolUtil {
    public static boolean isEmpty(List strList){
        if(strList != null && strList.size() != 0){
            return false;
        }
        return true;
    }
}
