package com.jm.langx.util.collection.diff.builder;

import java.security.Key;

/**
 * @Description key构造器
 * @param <K> 生成的key值对象
 * @param <O> 需要生成key的对象
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:34
 */

public interface KeyBuilder<K,O> {
    K getKey(O o);
}
