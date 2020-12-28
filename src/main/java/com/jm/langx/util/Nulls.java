package com.jm.langx.util;

import java.util.function.Consumer;

/**
 * @Description
 * @Create by yangjm
 * @CreateTime 2020/12/25 17:22
 */
public class Nulls {

    public static final <INPUT,OUTPUT> OUTPUT returnNull(INPUT o, ReturnNullAction<INPUT,OUTPUT> returnNullAction){
        if(o == null){
            return null;
        }
        return returnNullAction.action(o);

    }

    interface ReturnNullAction<INPUT,OUTPUT>{
        OUTPUT action(INPUT input);
    }
}
