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
    private int N = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int size){
        //将栈移动到一个大小为size的新数组
        T[] temp  = (T[]) new Object[size];
        if (N >= 0)
            System.arraycopy(a, 0, temp, 0, N);
        a = temp;
    }

    //入栈
    public void push(T t){
        if (N == a.length ){
            resize(2 * N);
        }
        a[N++] = t;
    }

    //出栈
    public T pop(){
        T t = a[--N];
        a[N] = null;  //避免对象游离
        if (N > 0 && N == a.length / 4) {
            resize(N / 2);
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
        private int i = N;

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
            System.out.println(s);
        }
    }
}
