package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

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
        return get(k, tree) != null;
    }

    @Override
    public V get(K k) {
        BSTNode node = get(k, tree);
        if (node == null) return null;
        return node.value;
    }

    private BSTNode get(K k, BSTNode node) {
        if (node == null) return null;
        int cmp = k.compareTo(node.key);
        if (cmp < 0) return get(k, node.left);
        else if (cmp > 0) return get(k, node.right);
        else return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K k, V v) {
        tree = put(k, v, tree);
    }

    private BSTNode put(K k, V v, BSTNode node) {
        if (node == null) {
            size += 1;
            return new BSTNode(k, v);
        }
        int cmp = k.compareTo(node.key);
        if (cmp < 0) node.left = put(k, v, node.left);
        else if (cmp > 0) node.right = put(k, v, node.right);
        else node.value = v;
        return node;
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

        BSTNode(K k, V v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }

    /* Below is optional for Lab 7 */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new TreeSet();
        addToKeySet(keySet, tree);
        return keySet;
    }

    private void addToKeySet(Set<K> set, BSTNode node) {
        if (node != null) {
            addToKeySet(set, node.left);
            set.add(node.key);
            addToKeySet(set, node.right);
        }
    }

    @Override
    /* If key doesn't exist, return null */
    public V remove(K k) {
        BSTNode node = get(k, tree);
        V v;
        if (node == null) {
            v = null;
        } else {
            size -= 1;
            v = node.value;
        }

        tree = remove(k, tree);
        return v;
    }

    private BSTNode remove(K k, BSTNode node) {
        if (node == null) return null;

        int cmp = k.compareTo(node.key);
        if (cmp < 0) node.left = remove(k, node.left);
        else if (cmp > 0) node.right = remove(k, node.right);
        else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            BSTNode temp = node;
            node = min(temp.right);
            node.right = removeMin(temp.right);
            node.left = temp.left;

        }
        return node;
    }

    private BSTNode min(BSTNode node) {
        if (node.left == null) return node;
        else return min(node.left);
    }

    private BSTNode removeMin(BSTNode node) {
        return remove(min(node).key, node);
    }

    @Override
    public V remove(K k, V v) {
        BSTNode node = get(k, tree);
        V value;
        if (node == null || node.value != v) {
            value = null;
        } else {
            size -= 1;
            value = node.value;
            tree = remove(k, tree);
        }

        return value;
    }

    @Override
    /* Iterator over the keys */
    public Iterator<K> iterator() {
        class BSTMapIterator implements Iterator<K> {
            /* Use Broad Priority Search */
            private final Iterator<K> iter = keySet().iterator();

            public boolean hasNext() {
                return iter.hasNext();
            }

            public K next() {
                BSTNode node = get(iter.next(), tree);
                if (node != null) {
                    return node.key;
                }
                return null;
            }
        }

        return new BSTMapIterator();
    }
}
