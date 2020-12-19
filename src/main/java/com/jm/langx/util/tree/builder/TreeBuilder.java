package com.jm.langx.util.tree.builder;

import com.jm.langx.util.Emptys;
import com.jm.langx.util.tree.BaseNode;
import com.jm.langx.util.tree.CommonTree;

import java.util.ArrayList;
import java.util.List;

public class TreeBuilder<TREENODE extends BaseNode<TREENODE>> {

    private List<TREENODE> nodes = new ArrayList<>();

    public static final <TREENODE extends BaseNode<TREENODE>>  TreeBuilder<TREENODE> createBuilder(){
        return new TreeBuilder<TREENODE>();
    }

    public TreeBuilder<TREENODE> addNodes(List<TREENODE> nodes){
        if (Emptys.isNotEmpty(nodes)) {
            this.nodes.addAll(nodes);
        }
        return this;
    }

    public CommonTree<TREENODE> getTree(){
        return new CommonTree<TREENODE>(this.nodes);
    }
}
