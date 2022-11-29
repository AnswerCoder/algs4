/*
 * @(#)MyBag.java
 *
 * Copyright © 2022 3DMed Corporation.
 */
package top.peng.algs4.char1;

import java.util.Iterator;

/**
 * MyBag 背包
 *
 * @author yunpeng.zhang
 * @version 1.0 2022/11/29
 */
public class MyBag<T> implements Iterable<T>{
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

    //加入背包
    public void add(T t){
        Node oldFirst = first;
        first = new Node(t,oldFirst);
        n++;
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
        MyBag<String> bag = new MyBag<>();
        bag.add("to");
        bag.add("be");
        bag.add("or");
        bag.add("not");
        bag.add("to");
        bag.add("be");
        for (String s : bag) {
            System.out.print(s+" ");
        }
    }
}
