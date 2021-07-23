package com.ivv;

import java.util.Iterator;
import java.util.Random;

public class Task2 {
    public static class Main {
        public static void main(String[] args) {
            Simple<String> strings = new SimpleArray<>();
            strings.add("0");
            strings.add("1");
            strings.add("2");
            strings.add("3");
            strings.add("4");
            strings.add("5");
            System.out.println(strings.get(1));
            System.out.println(strings.size());
            strings.update(2, "2 update");
            System.out.println(strings.get(2));
            strings.delete(1);
            for (String s : strings) {
                System.out.println(s);
            }
            System.out.println(strings.getRandomElement());
        }
    }

    public static class ArrayIterator<E> implements Iterator<E> {
        private int index = 0;
        private E[] values;

        public ArrayIterator(E[] values) {
            this.values = values;
        }

        @Override
        public boolean hasNext() {
            return index < values.length;
        }

        @Override
        public E next() {
            return values[index++];
        }
    }

    public interface Simple<E> extends Iterable<E> {
        boolean add(E e);

        boolean delete(int index);

        int size();

        void update(int index, E e);

        E get(int index);

        E getRandomElement();
    }

    public static class SimpleArray<E> implements Simple<E> {
        private E[] values;

        public SimpleArray() {
            values = (E[]) new Object[0];
        }

        @Override
        public E get(int index) {
            return values[index];
        }

        @Override
        public E getRandomElement() {
            Random random = new Random();
            return values[random.nextInt(values.length)];
        }

        @Override
        public boolean add(E e) {
            try {
                E[] temp = values;
                values = (E[]) new Object[temp.length + 1];
                System.arraycopy(temp, 0, values, 0, temp.length);
                values[values.length - 1] = e;
                return true;
            } catch (ClassCastException ex) {
                ex.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean delete(int index) {
            try {
                E[] temp = values;
                values = (E[]) new Object[temp.length - 1];
                System.arraycopy(temp, 0, values, 0, index);
                System.arraycopy(temp, index + 1, values, index, temp.length - index - 1);
                return true;
            } catch (ClassCastException ex) {
                ex.printStackTrace();
            }
            return false;
        }

        @Override
        public int size() {
            return values.length;
        }

        @Override
        public void update(int index, E e) {
            values[index] = e;
        }

        @Override
        public Iterator<E> iterator() {
            return new ArrayIterator<>(values);
        }
    }
}