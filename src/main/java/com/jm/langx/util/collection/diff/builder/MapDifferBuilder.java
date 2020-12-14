package com.jm.langx.util.collection.diff.builder;

import com.jm.langx.util.collection.diff.CollectionDiffer;
import com.jm.langx.util.collection.diff.Differ;
import com.jm.langx.util.collection.diff.MapDiffer;
import com.jm.langx.util.collection.diff.result.CollectionDiffResult;
import com.jm.langx.util.collection.diff.result.MapDiffResult;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

/**
 * @Description map比较器的构建器
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:07
 * @parma <KEY> 需要比较的map，key值类型
 * @parma <VALUE> 需要比较的map，value值类型
 */
public class MapDifferBuilder<KEY, VALUE> {
    private Comparator<VALUE> valueComparator;
    private Comparator<KEY> keyComparator;

    /**
     * 返回一个map比较器
     * @parma <KEY> 需要比较的map，key值类型
     * @parma <VALUE> 需要比较的map，value值类型
     * @return map比较器
     */
    public static final <KEY, VALUE> MapDifferBuilder<KEY,VALUE> createBuilder(){
        return new MapDifferBuilder<KEY,VALUE>();
    }

    /**
     * 设置value比较器
     * @param comparator 通过实现Comparator完成map的value值的比较
     * @return map比较器
     */
    public MapDifferBuilder<KEY,VALUE> valueComparator(Comparator<VALUE> comparator){
        this.valueComparator = comparator;
        return this;
    }

    /**
     * 设置value比较器
     * @param comparator 通过实现Comparator完成map的key值的比较
     * @return map比较器
     */
    public MapDifferBuilder<KEY,VALUE> keyomparator(Comparator<KEY> comparator){
        this.keyComparator = comparator;
        return this;
    }

    /**
     * 完成比较器的构建
     * @return map比较器
     */
    public Differ<Map<KEY,VALUE>, MapDiffResult<KEY, VALUE>> build(){
        Differ differ = new MapDiffer<KEY,VALUE>();
                ((MapDiffer<KEY,VALUE>)differ).setKeyComparator(this.keyComparator);
                ((MapDiffer<KEY,VALUE>)differ).setValueComparable(this.valueComparator);
        return (Differ<Map<KEY,VALUE>, MapDiffResult<KEY, VALUE>>) differ;
    }
}
