package com.jm.langx.util.tree;

import java.util.List;
import java.util.function.Consumer;

/**
 * @Description 树的顶层接口
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:51
 */
public interface Tree<T extends BaseNode> {

    /**
     * 添加一批树节点
     * @param nodes 添加的节点集合
     */
    void addNodes(List<T> nodes);

    /**
     * 添加单个树节点
     * @param node 添加的节点
     */
    void addNode(T node);

    /**
     * 指定树节点添加
     * @param pId 指定父id
     * @param node 添加的节点
     */
    void addNode(String pId, T node);

    /**
     * 获取构造好的树的根节点
     * @return
     */
    List<T> getRootNodes();

    /**
     * 获取构造好的树的根节点
     * @return
     */
    void forEach(Consumer<T> action);

    /**
     * 删除节点
     * @param nodeId
     * @return
     */
    boolean deleteNodeById(String nodeId);

    /**
     * 清除树的所有节点
     */
    void clear();

}
