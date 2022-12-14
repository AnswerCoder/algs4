/*
 * @(#)ResizingArrayStack.java
 *
 * Copyright © 2022 3DMed Corporation.
 */
package top.peng.algs4.char1;

import java.util.Iterator;

/**
 * ResizingArrayStack LIFO后进先出栈 (可动态调整数组大小的实现)
 *
 * @author yunpeng.zhang
 * @version 1.0 2022/11/28
 */
public class ResizingArrayStack<T> implements Iterable<T>{

    //栈元素
    private T[] a  = (T[]) new Object[1];
    //元素数量
    private int n = 0;

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int size){
        //将栈移动到一个大小为size的新数组
        T[] temp  = (T[]) new Object[size];
        if (n >= 0)
            System.arraycopy(a, 0, temp, 0, n);
        a = temp;
    }

    //入栈
    public void push(T t){
        if (n == a.length ){
            resize(2 * n);
        }
        a[n++] = t;
    }

    //出栈
    public T pop(){
        T t = a[--n];
        a[n] = null;  //避免对象游离
        if (n > 0 && n == a.length / 4) {
            resize(n / 2);
        }
        return t;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new ReversArrayIterator();
    }

    public class ReversArrayIterator implements Iterator<T> {
        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }


        @Override
        public T next() {
            return a[--i];
        }

        @Override
        public void remove() {
            throw new RuntimeException("UnsupportedOperationException...");
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
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
