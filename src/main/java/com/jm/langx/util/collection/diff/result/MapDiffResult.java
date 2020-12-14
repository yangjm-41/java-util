package com.jm.langx.util.collection.diff.result;


import com.jm.langx.util.Objects;
import com.jm.langx.util.collection.diff.result.DiffResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description map比较器结果集
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:41
 * @param <K> key值对象类型
 * @param <V> value值对象类型
 */
public class MapDiffResult<K, V> implements DiffResult<Map<K,V>> {
    private Map<K,V> adds = new HashMap<K,V>();
    private Map<K,V>modifys = new HashMap<K,V>();
    private Map<K,V> removes = new HashMap<K,V>();
    private Map<K,V> equals = new HashMap<K,V>();

    public Map<K,V> getEquals() {
        return this.equals;
    }

    public Map<K,V> getAdds() {
        return adds;
    }

    public Map<K,V> getModifys() {
        return modifys;
    }

    public Map<K,V> getRemoves() {
        return removes;
    }

    public void setModifys(Map<K,V> modifys) {
        if(Objects.nonNull(modifys)){
            this.modifys = modifys;
        }
    }

    public void setAdds(Map<K,V> adds) {
        if(Objects.nonNull(adds)){
            this.adds = adds;
        }
    }

    public void setEquals(Map<K,V> equals) {
        if (Objects.nonNull(equals)) {
            this.equals = equals;
        }
    }

    public void setRemoves(Map<K,V> removes) {
        if (Objects.nonNull(removes)) {
            this.removes = removes;
        }
    }
    
    public boolean hasDiff() {
        return !this.modifys.isEmpty() || !this.removes.isEmpty() || !this.adds.isEmpty();
    }





}
