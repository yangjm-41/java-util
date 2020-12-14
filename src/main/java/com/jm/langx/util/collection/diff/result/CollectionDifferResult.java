package com.jm.langx.util.collection.diff.result;

import com.jm.langx.util.collection.diff.result.DiffResult;

/**
 * @Description 集合比较器结果集接口
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:41
 * @param <C> 集合类型
 */
public interface CollectionDifferResult<C> extends DiffResult<C> {

    /**
     * 获取添加数据集合的方法
     * @return
     */
    C getAdds();

    /**
     * 获取修改数据集合的方法
     * @return
     */
    C getModifys();

    /**
     * 获取相等数据集合的方法
     * @return
     */
    C getEquals();

    /**
     * 获取删除数据集合的方法
     * @return
     */
    C getRemoves();

}
