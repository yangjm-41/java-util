package com.jm.langx.util.collection.diff;

import com.jm.langx.util.Emptys;
import com.jm.langx.util.collection.diff.builder.CollDifferBuilder;
import com.jm.langx.util.collection.diff.builder.KeyBuilder;
import com.jm.langx.util.collection.diff.builder.MapDifferBuilder;
import com.jm.langx.util.collection.diff.result.CollectionDiffResult;
import com.jm.langx.util.collection.diff.result.CollectionDifferResult;
import com.jm.langx.util.collection.diff.result.MapDiffResult;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 集合比较器
 * @param <V> 比较的值类型
 */
public class CollectionDiffer<V> implements Differ<Collection<V>, CollectionDifferResult<Collection<V>>> {
    private KeyBuilder<String,V> keyBuilder;

    private Comparator<V> comparator;

    public CollectionDiffer(){
    }

    private boolean useCustomComparator(){
        return this.comparator != null;
    }

    public CollectionDiffer(KeyBuilder keyBuilder){
        this.keyBuilder = keyBuilder;
    }

    public void setComparator(Comparator<V> comparator) {
        this.comparator = comparator;
    }

    public void setKeyBuilder(KeyBuilder<String,V> keyBuilder){
        this.keyBuilder = keyBuilder;
    }

    private boolean useKeyBuilder(){
        return Emptys.isNotEmpty(this.keyBuilder);
    }


    @Override
    public CollectionDifferResult<Collection<V>> diff(Collection<V> newValue, Collection<V> oldValue) {
        CollectionDiffResult<V> result = new CollectionDiffResult<V>();

        if(Emptys.isEmpty(newValue) && Emptys.isEmpty(oldValue)){
            return result;
        }
        if(Emptys.isEmpty(newValue)){
            result.setEquals(oldValue);
            return result;
        }
        if(Emptys.isEmpty(oldValue)){
            result.setAdds(newValue);
            return result;
        }

        if(useKeyBuilder()){
            this.useKeyBuilderProcessor(newValue, oldValue, result);
        }else {
            if(useCustomComparator()){
                this.useCustomComparatorProcessor(newValue, oldValue, result);
            }else{
                this.defalutProcessor(newValue, oldValue, result);
            }
        }

        return result;
    }

    private void useKeyBuilderProcessor(Collection<V> newValue, Collection<V> oldValue, CollectionDiffResult<V> result){
        Map<String, V> newValueMap = newValue.stream().collect(Collectors.toMap(keyBuilder::getKey, v -> v));
        Map<String, V> oldValueMap = oldValue.stream().collect(Collectors.toMap(keyBuilder::getKey, v -> v));

        MapDiffResult<String, V> diff = MapDifferBuilder.<String, V>createBuilder()
                .valueComparator(this.comparator)
                .build().diff(newValueMap, oldValueMap);

        result.setEquals(diff.getEquals().values());
        result.setAdds(diff.getAdds().values());
        result.setRemoves(diff.getRemoves().values());
        result.setModifys(diff.getModifys().values());
    }

    private void useCustomComparatorProcessor(Collection<V> newValue, Collection<V> oldValue, CollectionDiffResult<V> result){
        result.setAdds(newValue);
        result.setRemoves(oldValue);
        Collection<V> adds = result.getAdds();
        Collection<V> removes = result.getRemoves();

        // 归集相同元素
        Collection<V> equals = result.getEquals();
        removes.stream().forEach(nValue -> {
            oldValue.stream().anyMatch((oValue -> {
                if(comparator.compare(nValue, oValue) == 0){
                    equals.add(nValue);
                    return true;
                }
                return false;
            }));
        });
        removes.removeAll(equals);
        adds.removeAll(equals);

        // 归集新增元素
        result.getAdds().removeAll(removes);

        // 归集删除元素
        result.getRemoves().removeAll(newValue);

    }

    private void defalutProcessor(Collection<V> newValue, Collection<V> oldValue, CollectionDiffResult<V> result){
        result.setAdds(newValue);
        result.setRemoves(oldValue);
        result.setEquals(oldValue);

        // 归集新增元素
        result.getAdds().removeAll(oldValue);

        // 归集删除元素
        result.getRemoves().removeAll(newValue);

        // 归集相同元素
        result.getEquals().retainAll(newValue);
    }




    public static void main(String[] args) {

        List<String> n = new ArrayList<>(Arrays.asList("yangjm", "roanldo", "messi"));
        List<String> o = new ArrayList<>(Arrays.asList("yangjm", "1111", "2222"));
        final CollectionDiffResult<String> diff = CollDifferBuilder.<String>createBuilder().build().diff(n, o);

        System.out.println("old：" + o);
        System.out.println("new：" + n);
        System.out.println("新增：" + diff.getAdds());
        System.out.println("删除：" + diff.getRemoves());
        System.out.println("相同：" + diff.getEquals());
        System.out.println("修改：" + diff.getModifys());
    }
}
