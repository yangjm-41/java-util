package com.jm.langx.util.tree;


import com.jm.langx.util.Emptys;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description 通用树的构建
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:51
 */
public class CommonTree<T extends TreeNode> implements Tree<T> {
    private List<T> nodes = new ArrayList<>();
    private Map<String, T> nodeMap = new HashMap<>();

    public CommonTree(Collection<T> nodes) {
        if(Emptys.isNotEmpty(nodes)){
            nodeMap.putAll(nodes.stream().collect(Collectors.toMap(TreeNode::getId,e -> e)));
            nodes.forEach(this::addNode);
        }
    }

    @Override
    public void addNodes(List<T> paramList) {
        if(Emptys.isNotEmpty(paramList)){
            nodeMap.putAll(nodes.stream().collect(Collectors.toMap(TreeNode::getId,e -> e)));
            nodes.forEach(this::addNode);
        }
    }

    @Override
    public void addNode(T node) {
        addNode(node.getPId(), node);
    }


    @Override
    public void addNode(String pId, T node) {
        if (pId == null) {
            pId = node.getPId();
        }
        node.setPId(pId);

        TreeNode parentNode = (TreeNode) this.nodeMap.get(pId);
        if (parentNode != null) {
            parentNode.setParent(true);
            parentNode.addChild(node);
        } else {
            List<T> rootNodes = this.nodes;


            TreeNode pnode = null;
            boolean isChild = false;
            for (TreeNode rootNode : rootNodes) {
                if (rootNode.getId().equals(node.getPId())) {
                    pnode = rootNode;
                    break;
                }
            }
            if (pnode != null) {
                isChild = true;
                pnode.setParent(true);
                pnode.addChild(node);
            }


            boolean isParent = node.isParent();
            if (!isParent) {
                Iterator<TreeNode> iter = (Iterator<TreeNode>) rootNodes.iterator();
                while (iter.hasNext()) {
                    TreeNode rootNode = (TreeNode) iter.next();
                    if (node.getId().equals(rootNode.getPId())) {
                        node.setParent(true);
                        node.addChild(rootNode);
                        iter.remove();
                    }
                }
            }

            if (!isChild) {
                this.nodes.add(node);
            }
        }

        this.nodeMap.put(node.getId(), node);
    }

    @Override
    public List<T> getRootNodes() {
        return this.nodes;
    }

    @Override
    public void clear() {
        nodeMap.clear();
        nodes.clear();
    }

    public static void main(String[] args) {

        TreeNode node2 = new TreeNode("2", "节点1", "1");
        TreeNode node3 = new TreeNode("3", "节点2", "1");
        TreeNode node4 = new TreeNode("4", "节点3", "2");
        TreeNode node1 = new TreeNode("1", "根节点", null);
        TreeNode node5 = new TreeNode("5", "节点4", "2");
        TreeNode node6 = new TreeNode("6", "节点5", "3");
        TreeNode node7 = new TreeNode("7", "节点7", "3");
        TreeNode node8 = new TreeNode("8", "节点8", "6");

        TreeNode node9 = new TreeNode("9", "节点9", "ronaldo");

        final List<TreeNode> treeNodes = Arrays.asList(node4, node9, node3, node1, node5, node6, node7, node8,node2);
        final CommonTree<TreeNode> commonTree = new CommonTree(treeNodes);
        System.out.println(commonTree.getRootNodes().stream().map(TreeNode::getName).collect(Collectors.joining(",")));
    }
}
