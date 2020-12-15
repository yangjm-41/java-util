package com.jm.langx.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @Description 判空工具类
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:52
 */
public class Emptys {

    public static final boolean isNotNull(Object o) {
        return o != null;
    }

    public static final boolean isEmpty(Object o){
        if(o == null){
            return true;
        }
        if(o instanceof Collection){
            return ((Collection)o).isEmpty();
        }
        if(o instanceof Map){
            return ((Map)o).isEmpty();
        }
        if(o instanceof Set){
            return ((Set)o).isEmpty();
        }
        if(o instanceof String){
            return ((String)o).isEmpty();
        }
        return false;
    }

    public static final boolean isNotEmpty(Object o){
        return !isEmpty(o);
    }
}
