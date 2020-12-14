package com.jm.langx.util.collection.diff.result;


import com.jm.langx.util.Objects;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description 集合比较器结果集接口
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:41
 * @param <E> 比较对象的类型
 */
public class CollectionDiffResult<E> implements CollectionDifferResult<Collection<E>> {
    private Collection<E> adds = new ArrayList<E>();
    private Collection<E> modifys = new ArrayList<E>();
    private Collection<E> removes = new ArrayList<E>();
    private Collection<E> equals = new ArrayList<E>();

    public Collection<E> getEquals() {
        return this.equals;
    }

    public Collection<E> getAdds() {
        return adds;
    }

    public Collection<E> getModifys() {
        return modifys;
    }

    public Collection<E> getRemoves() {
        return removes;
    }

    public void setModifys(Collection<E> modifys) {
        if(Objects.nonNull(modifys)){
            this.modifys.clear();
            this.modifys.addAll(modifys);
        }
    }

    public void setAdds(Collection<E> adds) {
        if(Objects.nonNull(adds)){
            this.adds.clear();
            this.adds.addAll(adds);
        }
    }

    public void setEquals(Collection<E> equals) {
        if (Objects.nonNull(equals)) {
            this.equals.clear();
            this.equals.addAll(equals);
        }
    }

    public void setRemoves(Collection<E> removes) {
        if (Objects.nonNull(removes)) {
            this.removes.clear();
            this.removes.addAll(removes);
        }
    }

    public boolean hasDiff() {
        return !this.modifys.isEmpty() || !this.removes.isEmpty() || !this.adds.isEmpty();
    }





}
