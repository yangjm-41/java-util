package com.jm.langx.util.tree;


import com.jm.langx.util.Emptys;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @Description 通用树的构建
 * @Create by yangjm
 * @CreateTime 2020/12/14 21:51
 */
public class CommonTree<T extends BaseNode<T>> implements Tree<T> {
    private List<T> nodes = new ArrayList<>();
    private Map<String, T> nodeMap = new HashMap<>();

    public static final <T extends BaseNode<T>> CommonTree<T> getInstance(Collection<T> nodes) {
        return new CommonTree<T>(nodes);
    }

    public static final <T extends BaseNode<T>> CommonTree<T> getInstance() {
        return new CommonTree<T>(null);
    }

    public CommonTree(Collection<T> nodes) {
        if(Emptys.isNotEmpty(nodes)){
            addNodes(nodes);
        }
    }

    @Override
    public void addNodes(Collection<T> paramList) {
        if(Emptys.isNotEmpty(paramList)){
            // 这里必须要先把节点添加到map上，才能进行将节点挂在到树上。
            nodeMap.putAll(paramList.stream().collect(Collectors.toMap(T::getId, node -> node)));
            paramList.forEach(this::addNode);
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

        T parentNode = (T) this.nodeMap.get(pId);
        // 父节点不为空时，将该节点添加到父节点中
        if (parentNode != null) {
            parentNode.setParent(true);
            parentNode.addChild(node);
        } else {
            // 父节点为空时，遍历root节点,从root中找一遍
            List<T> rootNodes = this.nodes;
            T pnode = null;
            boolean isChild = false;
            pnode = rootNodes.stream().filter(rootNode -> rootNode.getId().equals(node.getPId())).findFirst().orElse(null);
            if (pnode != null) {
                isChild = true;
                pnode.setParent(true);
                pnode.addChild(node);
            }

            // 插入节点不是父节点，遍历rootNodes，如果存在root节点的pId和该插入节点的id相等，则删除这个父节点，并追加上该插入节点到rootNodes上
            boolean isParent = node.isParent();
            if (!isParent) {
                Iterator<T> iter = (Iterator<T>) rootNodes.iterator();
                while (iter.hasNext()) {
                    T rootNode = (T) iter.next();
                    if (node.getId().equals(rootNode.getPId())) {
                        node.setParent(true);
                        node.addChild(rootNode);
                        iter.remove();
                    }
                }
            }

            // 如果在nodeMap和rootNodes中都找不到的话说明它是根节点了，加入根节点list
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
    public T getNode(String id) {
        return this.nodeMap.get(id);
    }

    @Override
    public void forEach(Consumer<T> action) {
        LinkedList<T> nodes = new LinkedList<>(this.nodes);
        while (nodes.size() > 0){
            T poll = nodes.poll();
            action.accept((T) poll);
            final Collection<T> childrens = poll.getChildrens();
            if(Emptys.isNotEmpty(childrens)){
                nodes.addAll(childrens);
            }
        }

    }

    @Override
    public boolean deleteNodeById(String nodeId) {
        LinkedList<T> childTree = new LinkedList<>(Arrays.asList(this.nodeMap.get(nodeId)));
        while (childTree.size() > 0){
            T poll = childTree.poll();
            Collection<T> childrens = (Collection<T>) poll.getChildrens();
            if(Emptys.isNotEmpty(childrens)){
                childTree.addAll(childrens);
                childrens.forEach(child -> this.nodeMap.remove(child.getId()));
                childrens.clear();
            }

            this.nodes.remove(poll);
            final T pNode = this.nodeMap.get(poll.getPId());
            if(Emptys.isNotEmpty(pNode) && Emptys.isNotEmpty(pNode)){
                pNode.getChildrens().remove(poll);
            }

        }
        return true;
    }

    @Override
    public void clear() {
        nodeMap.clear();
        nodes.clear();
    }

    public static void main(String[] args) {

        TreeNode node2 = new TreeNode("2", "节点2", "1");
        TreeNode node3 = new TreeNode("3", "节点3", "1");
        TreeNode node4 = new TreeNode("4", "节点4", "2");
        TreeNode node1 = new TreeNode("1", "根节点", null);
        TreeNode node5 = new TreeNode("5", "节点5", "2");
        TreeNode node6 = new TreeNode("6", "节点6", "3");
        TreeNode node7 = new TreeNode("7", "节点7", "3");
        TreeNode node8 = new TreeNode("8", "节点8", "6");

        TreeNode node9 = new TreeNode("9", "节点9", "ronaldo");

        final List<TreeNode> treeNodes = Arrays.asList(node4, node9, node3, node1, node5, node6, node7, node8,node2);
        CommonTree<TreeNode> tree = CommonTree.getInstance(treeNodes);
        //tree.deleteNodeById("9");
        tree.forEach(node -> System.out.println(node.getName()));
    }
}
