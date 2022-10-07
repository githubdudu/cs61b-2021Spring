package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private BSTNode tree = null;
    private int size = 0;

    @Override
    public void clear() {
        tree = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K k) {
        if (tree == null) {
            return false;
        }
        return tree.getKey(k) != null;
    }

    @Override
    public V get(K k) {
        if (tree == null) {
            return null;
        }
        BSTNode node = tree.getKey(k);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K k, V v) {
        if (tree == null) {
            tree = new BSTNode(k, v);
            size = 1;
        } else {
            int count = tree.setKey(k, v);
            size += count;
        }

    }

    public void printInOrder() {
        printInOrder(tree);
    }

    private void printInOrder(BSTNode node) {
        if (node == null) {
            System.out.println("");
        } else {
            printInOrder(node.left);
            System.out.printf("(%s, %s)", node.key, node.value);
            printInOrder(node.right);
        }
    }

    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        BSTNode(K k, V v, BSTNode left, BSTNode right) {
            key = k;
            value = v;
            this.left = left;
            this.right = right;
        }


        BSTNode(K k, V v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }

        public BSTNode getKey(K k) {
            if (k.equals(key)) {
                return this;
            } else if (k.compareTo(key) < 0) {
                if (left != null) {
                    return left.getKey(k);
                }
                return null;
            } else {
                if (right != null) {
                    return right.getKey(k);
                }
                return null;
            }
        }

        /* Return 0 if no key added, return 1 if one more key added */
        public int setKey(K k, V v) {
            if (k.equals(key)) {
                value = v;
                return 0;
            } else if (k.compareTo(key) < 0) {
                if (left != null) {
                    return left.setKey(k, v);
                } else {
                    left = new BSTNode(k, v);
                    return 1;
                }
            } else {
                if (right != null) {
                    return right.setKey(k, v);
                } else {
                    right = new BSTNode(k, v);
                    return 1;
                }
            }
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
