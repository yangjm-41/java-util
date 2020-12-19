package com.jm.langx.util.tree;

import java.util.Collection;

/**
 * @Description 面向接口编程树节点，为了更好的通用，树结点的形参名交给程序员自己定义
 * @Create by yangjm
 * @CreateTime 2020/12/19 21:51
 */
public interface BaseNode<T extends BaseNode> {


    public void addChild(T node);

    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    String getPId();

    void setPId(String pId);

    boolean isParent();

    void setParent(boolean parent);

    Collection<T> getChildrens();

    void setChildrens(Collection<T> childrens);
}
