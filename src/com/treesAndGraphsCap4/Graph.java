package com.treesAndGraphsCap4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    public Node [] nodes;

    public Node[] getAllNodes() {
        List<Node> list = new LinkedList<>();
        for(Node node: nodes){
            addAllNodes(node, list);
        }
        Node [] nodeArray = new Node[list.size()];
        list.toArray(nodeArray);
        return  nodeArray;
    }

    private void addAllNodes(Node node, List<Node> list) {
        if(node != null){
            list.add(node);
            Node [] children = node.children;
            if(children != null){
                for(Node child : children){
                    addAllNodes(child, list);
                }
            }
        }
    }

}
