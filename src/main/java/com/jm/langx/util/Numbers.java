package com.jm.langx.util;

import java.math.BigDecimal;

/**
 * @Description number类型数据工具类
 * @Create by yangjm
 * @CreateTime 2020/12/25 16:03
 */
public class Numbers {
    public static final boolean isZero(Number number) {
        if (number == null) {
            return true;
        }

        if (number instanceof Byte) {
            return number.equals(Byte.valueOf("0"));
        }
        if (number instanceof Short) {
            return number.equals(Short.valueOf("0"));
        }
        if(number instanceof Integer){
            return ((Integer) number) == 0;
        }
        if (number instanceof Long) {
            return ((Long) number) == 0L;
        }
        if (number instanceof Double) {
            return ((Double) number) == 0D;
        }
        if (number instanceof Float) {
            return ((Float) number) == 0F;
        }
        if (number instanceof BigDecimal){
            return new BigDecimal(0).equals(number);
        }
        return false;
    }

    public static final Float newFloat(String value) {
        return Nulls.<String,Float>returnNull(value, Float::new);
    }
    public static void main(String[] args) {
        Double d = -1D;
        System.out.println(Numbers.isZero(d));
    }
}
