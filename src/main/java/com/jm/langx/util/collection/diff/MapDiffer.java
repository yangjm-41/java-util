package com.jm.langx.util.collection.diff;

import com.jm.langx.util.collection.diff.builder.CollDifferBuilder;
import com.jm.langx.util.collection.diff.result.CollectionDiffResult;
import com.jm.langx.util.collection.diff.result.MapDiffResult;

import java.util.*;

/**
 * @Description map比较器
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:45
 * @param <V> 比较的map的value类型
 * @param <K> 比较的map的key类型
 */
public class MapDiffer<K,V> implements Differ<Map<K,V>, MapDiffResult<K, V>> {
    private Comparator<K> keyComparator;
    private Comparator<V> valueComparator;

    public MapDiffer(){
    }

    public void setValueComparable(Comparator<V> comparator) {
        this.valueComparator = comparator;
    }

    public void setKeyComparator(Comparator<K> comparator) {
        this.keyComparator = comparator;
    }

    private boolean useCustomKeyComparator(){
        return  keyComparator != null;
    }
    private boolean useCustomValueComparator(){
        return  valueComparator != null;
    }


    @Override
    public MapDiffResult<K, V> diff(Map<K, V> newValue, Map<K, V> oldValue) {
        MapDiffResult<K,V> result = new MapDiffResult<>();
        if (newValue == null && oldValue == null) {
            return result;
        }
        if (newValue == null && oldValue != null) {
            result.setRemoves(oldValue);
            return result;
        }
        if (newValue != null && oldValue == null) {
            result.setAdds(newValue);
            return result;
        }

        Set<K> newKeys = new HashSet<>(newValue.keySet());
        Set<K> oldKeys = new HashSet<>(oldValue.keySet());
        CollectionDiffResult<K> keyDiff = CollDifferBuilder.<K>createBuilder().comparator(keyComparator).build().diff(newKeys, oldKeys);

        // 删除元素/添加元素归集
        Map<K, V> removes = result.getRemoves();
        keyDiff.getRemoves().forEach(key -> removes.put(key, oldValue.get(key)));
        Map<K, V> adds = result.getAdds();
        keyDiff.getAdds().forEach(key -> adds.put(key, newValue.get(key)));

        // 相同元素/修改元素归集
        Collection<K> equalsKey = keyDiff.getEquals();
        Map<K, V> modifys = result.getModifys();
        Map<K, V> equals = result.getEquals();
        equalsKey.forEach(key -> {
            V oValue = oldValue.get(key);
            V nValue = newValue.get(key);
            if((useCustomValueComparator() && valueComparator.compare(oValue,nValue) == 0 ) || (!useCustomValueComparator() && oldValue.equals(newValue))){
                equals.put(key, oValue);
            }else {
                modifys.put(key, nValue);
            }
        });

        return result;
    }
}
