package com.jm.langx.util;

/**
 * @Description 对象工具类
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:52
 */
public class Objects {
    /**
     * 判断一个对象非空
     * @param o
     * @return
     */
    public static final boolean nonNull(Object o){
        return Emptys.isNotNull(o);
    }
}
