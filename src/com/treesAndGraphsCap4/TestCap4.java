package com.treesAndGraphsCap4;

import java.util.ArrayList;
import java.util.LinkedList;

enum State { Unvisited, Visited, Visiting}

public class TestCap4 {

    private static Integer lastPrinted = null;

    public static void main(String [] args){

        System.out.println("Find if there is a route between 2 nodes");

        Node root = new Node();
        root.name = "Root";

        Node rootChild1 = new Node();
        rootChild1.name = "RootChild1";
        Node rootChild2 = new Node();
        rootChild2.name = "RootChild2";
        Node rootChild3 = new Node();
        rootChild3.name = "RootChild3";

        Node rootChild1Child1 = new Node();
        rootChild1Child1.name = "RootChild1Child1";

        Node rootChild2Child1 = new Node();
        rootChild2Child1.name = "RootChild2Child1";
        Node rootChild2Child2 = new Node();
        rootChild2Child2.name = "RootChild2Child2";

        Node rootChild3Child1 = new Node();
        rootChild3Child1.name = "RootChild3Child1";

        Node endNode = rootChild2Child1;

        rootChild1.children = new Node[]{rootChild1Child1};

        rootChild2.children = new Node[]{rootChild2Child1, rootChild2Child2};

        rootChild3.children = new Node[]{rootChild3Child1};

        root.children = new Node[]{rootChild1, rootChild2, rootChild3};

        Graph graph = new Graph();
        graph.nodes = new Node[]{root};

        System.out.println(search(graph, root, rootChild3Child1 ));

        System.out.println("*************************************************");

        System.out.println("giving and increasing order array create a minimal tree");

        TreeNode result = createMinimalBST(new int[] {1,2,3,4,5,6,7,8,9,10,11});

        ArrayList<LinkedList<TreeNode>> levelLinkedList = createLevelLinkedList(result);

        System.out.println("*************************************************");

        System.out.println("*****check if a binary tree is a binary search tree (no duplicates)");

        TreeNode bstRoot = new TreeNode(3);
        TreeNode bstLeftChild1 = new TreeNode(1);
        TreeNode bstLeftChild1RightChild1 = new TreeNode(2);
        TreeNode bstRootRightChild1 = new TreeNode(4);
        TreeNode bstRootRightChild1RightChild1 = new TreeNode(5);
        TreeNode wrongOrderChild = new TreeNode(15);

        bstRoot.left = bstLeftChild1;
        bstLeftChild1.right = bstLeftChild1RightChild1;
        bstRoot.right = bstRootRightChild1;
        bstRootRightChild1.right = bstRootRightChild1RightChild1;
        bstRootRightChild1.left= wrongOrderChild;

        System.out.println(checkBST(bstRoot));
    }


    static boolean search(Graph graph, Node start, Node end){
        if(start == end) return true;

        LinkedList<Node> q = new LinkedList<>();

        for(Node u: graph.getAllNodes()){
            u.state = State.Unvisited;
        }
        start.state = State.Visiting;
        q.add(start);
        Node u;
        while(!q.isEmpty()){
            u= q.removeFirst();
            if(u != null){
                if(u.children != null){
                    for(Node v : u.children){
                        if(v.state == State.Unvisited){
                            if(v == end){
                                return true;
                            }
                            else{
                                v.state = State.Visited;
                                q.add(v);
                            }
                        }
                    }
                }
               u.state = State.Visited;
            }
        }
        return false;
    }

    static TreeNode createMinimalBST(int array[]){
        return createMinimalBST(array, 0, array.length-1);
    }

    private static TreeNode createMinimalBST(int[] array, int start, int end) {
        if(end < start){
            return null;
        }

        int mid = (start + end) / 2;
        int value = array[mid];
        TreeNode n = new TreeNode(value);
        n.left = createMinimalBST(array, start, mid-1);
        n.right = createMinimalBST(array, mid+1, end);
        return n;
    }

    /**
     * Given a binary tree design an algorithm which creates a linked list of all nodes at each depth
     * tree depth d will get d linked lists
     */
    private static void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level){
        if(root == null){ return;}
        
        LinkedList<TreeNode> list = null;
        if(lists.size() == level){
            list = new LinkedList<>();
            lists.add(list);
        }else{
            list = lists.get(level);
        }
        list.add(root);
        createLevelLinkedList(root.left, lists,level+1);
        createLevelLinkedList(root.right, lists,level+1);
    }

    static ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root){
        ArrayList<LinkedList<TreeNode>> lists = new ArrayList<>();
        createLevelLinkedList(root, lists, 0);
        return lists;
    }

    static boolean checkBST(TreeNode n){
        if(n==null){ return true; }

        //recursive left
        if(!checkBST(n.left)) return false;

        //check current
        if(lastPrinted != null && n.value <= lastPrinted) return false;
        lastPrinted = n.value;

        //check right
        if(!checkBST(n.right)) return false;

        return true;
    }

}
