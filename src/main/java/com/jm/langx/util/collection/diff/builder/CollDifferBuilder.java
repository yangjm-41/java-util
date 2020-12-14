package com.jm.langx.util.collection.diff.builder;

import com.jm.langx.util.collection.diff.*;
import com.jm.langx.util.collection.diff.result.CollectionDiffResult;

import java.util.Collection;
import java.util.Comparator;

/**
 * @Description 集合比较器的构建器
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:07
 * @parma <VALUE> 需要比较的具体对象
 */
public class CollDifferBuilder<VALUE> {
    private Comparator<VALUE> comparator;
    private KeyBuilder<String, VALUE> keyBuilder;

    /**
     * 创建一个集合比较器的构造器
     * @param <VALUE> 需要比较的数据对象类型
     * @return 返回一个集合比较器构造器
     */
    public static final <VALUE> CollDifferBuilder<VALUE> createBuilder(){
        return new CollDifferBuilder<VALUE>();
    }

    /**
     * 自定义比较规则，通过实现Comparator完成自定义规则
     * @param comparator 比较器
     * @return
     */
    public CollDifferBuilder<VALUE> comparator(Comparator<VALUE> comparator){
        this.comparator = comparator;
        return this;
    }

    /**
     * 比较对象的key值构造器，实现该keyBuilder接口，可以指定对象的key，用于比较出更新的数据
     * @param keyBuilder key值构造器，实现keyBuilder
     * @return
     */
    public CollDifferBuilder<VALUE> keyBuilder(KeyBuilder<String,VALUE> keyBuilder){
        this.keyBuilder = keyBuilder;
        return this;
    }

    /**
     * 完成比较器的构建
     * @return 返回集合比较器比较器
     */
    public Differ<Collection<VALUE>, CollectionDiffResult<VALUE>> build(){
        Differ differ = new CollectionDiffer<VALUE>();
                ((CollectionDiffer)differ).setKeyBuilder(this.keyBuilder);
                ((CollectionDiffer)differ).setComparator(this.comparator);
        return (Differ<Collection<VALUE>, CollectionDiffResult<VALUE>>) differ;
    }
}
