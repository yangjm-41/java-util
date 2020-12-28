package com.jm.langx.util;

import java.lang.reflect.Array;
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

    public static final boolean anyEmpty(Object... args){
        if(Emptys.isEmpty(args)){

        }
        for (Object arg : args) {
            if (Emptys.isEmpty(arg)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean allEmpty(Object... args){
        for (Object arg : args) {
            if (Emptys.isNotEmpty(arg)) {
                return false;
            }
        }
        return true;
    }



    public static final boolean isEmpty(Object o){
        if(o == null){
            return true;
        }
        if (o.getClass().isArray()) {
            return Array.getLength(o) <= 0;
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
        if (o instanceof CharSequence) {
            CharSequence cs = (CharSequence) o;
            return cs.length() == 0;
        }
        if (o instanceof Number) {
            return Numbers.isZero(((Number)o));
        }

        return false;
    }

    public static final boolean isNotEmpty(Object o){
        return !isEmpty(o);
    }

    public static void main(String[] args) {
        Object [] adsas = new Object[]{"123","456"};

        System.out.println(Emptys.isEmpty(adsas));
        System.out.println(Emptys.isEmpty(new Object[]{}));
        System.out.println(new Object[]{}.getClass());



    }
}
