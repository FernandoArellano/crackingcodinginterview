package com.treesAndGraphsCap4;

import java.util.LinkedList;
import java.util.List;

public class Node {
    public String name;
    public Node[] children;
    public State state;

    public List<Node> getChildrenList(){
        List<Node> list = new LinkedList();
        if(children == null ){
            return list;
        }
        for(Node node : children){
            list.add(node);
        }
        return list;
    }
}
