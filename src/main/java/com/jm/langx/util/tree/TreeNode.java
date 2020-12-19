package com.jm.langx.util.tree;

import com.jm.langx.util.Emptys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @Description 树节点
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:51
 */
public class TreeNode implements BaseNode<TreeNode> {
    private String id;
    private String name;
    private String pId;
    private boolean isParent;
    private Collection<TreeNode> childrens;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode node = (TreeNode) o;
        return isParent == node.isParent &&
                Objects.equals(id, node.id) &&
                Objects.equals(name, node.name) &&
                Objects.equals(pId, node.pId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pId, isParent);
    }

    public TreeNode() {
    }

    public TreeNode(String id, String name, String pId) {
        this.id = id;
        this.name = name;
        this.pId = pId;
    }

    @Override
    public void addChild(TreeNode node){
        if(Emptys.isEmpty(childrens)){
            childrens = new ArrayList<>();
        }
        this.childrens.add(node);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPId() {
        return pId;
    }

    @Override
    public void setPId(String pId) {
        this.pId = pId;
    }

    @Override
    public boolean isParent() {
        return isParent;
    }

    @Override
    public void setParent(boolean parent) {
        isParent = parent;
    }

    @Override
    public Collection<TreeNode> getChildrens() {
        return childrens;
    }

    @Override
    public void setChildrens(Collection<TreeNode> childrens) {
        this.childrens = childrens;
    }
}
