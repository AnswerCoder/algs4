/*
 * @(#)MyQueue.java
 *
 * Copyright © 2022 3DMed Corporation.
 */
package top.peng.algs4.char1;

import java.util.Iterator;

/**
 * MyQueue 先进先出队列
 *
 * @author yunpeng.zhang
 * @version 1.0 2022/11/29
 */
public class MyQueue<T> implements Iterable<T>{
    //队列头
    private Node first;
    //队列尾
    private Node last;
    //元素数量
    private int n = 0;

    private class Node{
        T val;
        Node next;
        Node(){}
        Node(T val,Node next){
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

    //入队
    public void enqueue(T t){
        Node oldLast = last;
        last = new Node(t,null);
        if (isEmpty()){
            first = last;
        }else {
            oldLast.next = last;
        }
        n++;
    }

    //出队
    public T dequeue(){
        T v =  first.val;
        first = first.next;
        if (isEmpty()){
            last = null;
        }
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
        MyQueue<String> queue = new MyQueue<>();
        queue.enqueue("to");
        queue.enqueue("be");
        queue.enqueue("or");
        System.out.println(queue.dequeue());
        queue.enqueue("not");
        queue.enqueue("to");
        queue.enqueue("be");
        for (String s : queue) {
            System.out.print(s+" ");
        }
    }
}
