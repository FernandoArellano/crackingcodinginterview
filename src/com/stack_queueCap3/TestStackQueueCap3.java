package com.stack_queueCap3;

public class TestStackQueueCap3 {
    public static void main(String args[]){
        System.out.println("BasicStack");

        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(4);
        myStack.push(8);
        myStack.push(12);
        myStack.printData();
        myStack.push(16);
        myStack.push(20);
        myStack.push(30);
        myStack.printData();
        myStack.pop();
        myStack.pop();
        myStack.push(40);
        myStack.push(50);
        myStack.printData();

        System.out.println("******************************************\n\n");


        System.out.println("Basic Queue");
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(4);
        queue.add(6);
        queue.add(8);
        queue.add(10);
        queue.add(0);
        queue.printAll();
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        queue.printAll();
        System.out.println("******************************************\n\n");
            }

}
