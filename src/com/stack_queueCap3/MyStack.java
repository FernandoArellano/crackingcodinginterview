package com.stack_queueCap3;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

public class MyStack<T> {
    private List<T> list = new LinkedList<>();

    private class StackNode<T>{
        private T data;
        private StackNode<T> next;

        public StackNode(T data){
            this.data = data;
        }
    }

    private StackNode<T> top;

    public T pop(){
        if(top == null) throw new EmptyStackException();
        T item = top.data;
        list.remove(item);
        top = top.next;
        return item;
    }

    public void push(T item){
        StackNode<T> t = new StackNode<>(item);
        list.add(item);
        t.next = top;
        top = t;
    }

    public T peek(){
        if(top==null) throw new EmptyStackException();
        return top.data;
    }

    public boolean isEmpty(){
        return top==null;
    }

    public void printData(){
        System.out.println(list);
    }

}
