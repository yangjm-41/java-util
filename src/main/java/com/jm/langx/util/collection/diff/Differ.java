package com.jm.langx.util.collection.diff;


import com.jm.langx.util.collection.diff.result.DiffResult;

/**
 * @Description 比较器接口
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:45
 * @param <V> 比较的值类型
 * @param <R> 比较器的结果集类型
 */
public interface Differ<V, R extends DiffResult> {

    /**
     * 比较器方法
     * @param newValue 新值集合
     * @param oldValue 旧值集合
     * @return 比较后的结果集
     */
    R diff(V newValue, V oldValue);

}
