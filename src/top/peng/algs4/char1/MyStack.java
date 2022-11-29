/*
 * @(#)MyStack.java
 *
 * Copyright © 2022 3DMed Corporation.
 */
package top.peng.algs4.char1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MyStack 下压堆栈 (链表实现)
 *
 * @author yunpeng.zhang
 * @version 1.0 2022/11/29
 */
public class MyStack<T> implements Iterable<T>{
    //栈顶元素
    private Node first;
    //元素数量
    private int n = 0;

    private class Node{
        T val;
        Node next;
        Node(){}
        Node(T val, Node next){
            this.val = val;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    //入栈
    public void push(T t){
        Node oldFirst = first;
        first = new Node(t,oldFirst);
        n++;
    }

    //出栈
    public T pop(){
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        T v =  first.val;
        first = first.next;
        n--;
        return v;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    public class ListIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }


        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T v = current.val;
            current = current.next;
            return v;
        }

        @Override
        public void remove() {
            throw new RuntimeException("UnsupportedOperationException...");
        }
    }

    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();
        stack.push("to");
        stack.push("be");
        stack.push("or");
        System.out.println(stack.pop());
        stack.push("not");
        stack.push("to");
        stack.push("be");
        for (String s : stack) {
            System.out.print(s+" ");
        }
    }
}
