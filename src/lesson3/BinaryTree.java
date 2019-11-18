package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public void addAll(BinaryTree<T> tree) {
        for (T t : tree) {
            add(t);
        }
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    public int height() {
        return height(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        throw new NotImplementedError();
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    private boolean nodesEquals(T first, T second) {
        return first.compareTo(second) == 0;
    }

    private boolean firstLessSecond(T first, T second) {
        return first.compareTo(second) < 0;
    }

    private boolean firstMoreSecond(T first, T second) {
        return first.compareTo(second) > 0;
    }

    public class BinaryTreeIterator implements Iterator<T> {
        Node<T> current;
        Stack<Node<T>> nodeStack;

        private BinaryTreeIterator() {
            current = null;
            nodeStack = new Stack<>();
            current = root;
        }


        /**
         * Проверка наличия следующего элемента
         * Средняя
         */
        @Override
        public boolean hasNext() {
            return (current != null || !nodeStack.isEmpty());
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        @Override
        public T next() {
            while (current != null) {
                nodeStack.push(current);
                current = current.left;
            }
            current = nodeStack.pop();
            Node<T> node = current;
            current = current.right;
            return node.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
//           T v = next();
//           Node<T> node = find(v);
//           node = new Node<>(null);
//           //node = null;
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        SortedSet<T> tree = new BinaryTree<>();
        tree.add(fromElement);
        BinaryTreeIterator it = (BinaryTreeIterator) iterator();
        while (it.hasNext()) {
            T next = it.next();
            if (firstMoreSecond(next, fromElement) && firstLessSecond(next, toElement)) {
                tree.add(next);
            }
            if (nodesEquals(next, toElement)) break;
        }
        return new BinaryTreeWithRange(this, new Range(fromElement, toElement));
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        BinaryTreeIterator it = (BinaryTreeIterator) iterator();
        SortedSet<T> tree = new BinaryTree();
        while (it.hasNext()) {
            T next = it.next();
            if (firstLessSecond(next, toElement)) {
                tree.add(next);
            } else break;
        }
        return new BinaryTreeWithRange(this, new Range(null, toElement));
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        SortedSet<T> tree = new BinaryTree<T>();
        tree.add(fromElement);
        BinaryTreeIterator it = (BinaryTreeIterator) iterator();
        while (it.hasNext()) {
            T next = it.next();
            if (firstMoreSecond(next, fromElement)) {
                tree.add(next);
            }
        }

        return new BinaryTreeWithRange(this, new Range(fromElement, null));
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    class Range {
        T fe;
        T te;

        Range(T fromElement, T toElement) {
            fe = fromElement;
            te = toElement;
        }

        public BinaryTree<T> valuesInRange() {
            BinaryTree<T> tree = new BinaryTree<>();
            BinaryTreeIterator it = (BinaryTreeIterator) iterator();
            if (fe != null && te != null) {
                tree.add(fe);
                while (it.hasNext()) {
                    T next = it.next();
                    if (firstMoreSecond(next, fe) && firstLessSecond(next, te)) {
                        tree.add(next);
                    }
                    if (nodesEquals(next, te)) break;
                }
            } else if (te != null) {
                while (it.hasNext()) {
                    T next = it.next();
                    if (firstLessSecond(next, te)) {
                        tree.add(next);
                    } else break;
                }
            } else {
                tree.add(fe);
                while (it.hasNext()) {
                    T next = it.next();
                    if (firstMoreSecond(next, fe)) {
                        tree.add(next);
                    }
                }
            }
            return tree;
        }
    }

    private class BinaryTreeWithRange extends BinaryTree<T> {
        private BinaryTree binaryTree;
        private Range range;
        int size;

        BinaryTreeWithRange(BinaryTree binaryTree, Range range) {
            this.binaryTree = binaryTree;
            this.range = range;
            size = 0;
        }

        @Override
        public boolean add(T t) {
            if (!inRange(t)) throw new IllegalArgumentException();
            return binaryTree.add(t);
        }

        @Override
        public boolean contains(Object o) {
            if (!inRange((T) o)) return false;
            return binaryTree.contains(o);
        }

        private boolean inRange(T t) {
            if (range.fe != null && range.te != null) {
                return range.fe.compareTo(t) <= 0 && range.te.compareTo(t) > 0;
            } else if (range.fe == null && range.te != null) {
                return range.te.compareTo(t) >= 0;
            } else if (range.fe != null) {
                return range.fe.compareTo(t) <= 0;
            }
            return true;
        }

        @Override
        public int size() {
            size = range.valuesInRange().size;
            return size;
        }

    }

}
