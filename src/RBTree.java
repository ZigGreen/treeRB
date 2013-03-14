
/**
 * Created by IntelliJ IDEA.
 * User: Kirill
 * Date: 02.03.13
 * Time: 17:39
 */


public class RBTree<K extends Comparable<K>, V extends Comparable<V>> {


    private INodeColored<K, V> root;
    private int m_size;


    public RBTree() {
        root = null;
    }

    public boolean containsKey(Object key) {
        if (key == null) throw new NullPointerException();
        K k = (K) key;
        INode<K, V> it = findNode(k);
        return (it != null);

    }

    public V put(K key, V data) {
        INode<K, V> current, parent, x;


    /* find where node belongs */
        current = root;
        parent = null;
        while (current != null) {
            int compared = key.compareTo(current.getKey());
            if (compared == 0) {
                V previous = current.getValue();
                current.setValue(data);
                return previous;
            }
            parent = current;
            current = compared < 0 ? current.getLeft() : current.getRight();
        }

    /* setup new node */

        x = new Node<K, V>(key, data);
        x.setParent(parent);

    /* insert node in tree */
        if (parent != null) {
            int compared = key.compareTo(parent.getKey());
            if (compared < 0)
                parent.setLeft(x);
            else
                parent.setRight(x);
        } else {
            root = (INodeColored<K, V>) x;
        }

        //insertFixup(x);
        return null;
    }

    public V get(Object key) {
        if (key == null) throw new NullPointerException();
        K k = (K) key;
        INode<K, V> it = findNode(k);
        if (it != null)
            return it.getValue();

        return null;
    }

    public V remove(Object key) {

        INode<K, V> x, y;
        if (key == null) throw new NullPointerException();
        K k = (K) key;
        INode<K, V> z = findNode(k);
        if (z == null) return null;
        V oldData = z.getValue();

        if (z.getLeft() == null || z.getRight() == null) {
        /* y has a NIL node as a child */
            y = z;
        } else {
        /* find tree successor with a NIL node as a child */
            y = z.getRight();
            while (y.getLeft() != null) y = y.getLeft();
        }

    /* x is y's only child */
        if (y.getLeft() != null)
            x = y.getLeft();
        else
            x = y.getRight();

    /* remove y from the parent chain */
        if (x != null) x.setParent(y.getParent());
        if (y.getParent() != null) {

            if (y == y.getParent().getLeft())
                y.getParent().setLeft(x);
            else
                y.getParent().setRight(x);
        } else
            root = (INodeColored<K, V>) x;

        if (y != z) {
            z.setKey(y.getKey());
            z.setValue(y.getValue());
        }


        //if (y.color == State.BLACK)
        //deleteFixup(x);
        return oldData;
    }

    private INode<K, V> findNode(K key) {
        INode<K, V> it = root;

        while (it != null) {
            int compared = key.compareTo(it.getKey());
            if (compared == 0) {
                return it;
            }
            it = (compared < 0) ? it.getLeft() : it.getRight();
        }
        return null;

    }

    /**
     * void deleteFixup(Node<K, V> x) {
     * <p/>
     * /*************************************
     * maintain Red-Black tree balance  *
     * after deleting node x            *
     * ************************************
     * <p/>
     * while (x != root && x.color == State.BLACK) {
     * if (x == x.parent.left) {
     * Node<K, V> w = x.parent.right;
     * if (w.color == State.RED) {
     * w.color = State.BLACK;
     * x.parent.color = State.RED;
     * rotateLeft(x.parent);
     * w = x.parent.right;
     * }
     * if (w.left.color == State.BLACK && w.right.color == State.BLACK) {
     * w.color = State.RED;
     * x = x.parent;
     * } else {
     * if (w.right.color == State.BLACK) {
     * w.left.color = State.BLACK;
     * w.color = State.RED;
     * rotateRight(w);
     * w = x.parent.right;
     * }
     * w.color = x.parent.color;
     * x.parent.color = State.BLACK;
     * w.right.color = State.BLACK;
     * rotateLeft(x.parent);
     * x = root;
     * }
     * } else {
     * Node<K, V> w = x.parent.left;
     * if (w.color == State.RED) {
     * w.color = State.BLACK;
     * x.parent.color = State.RED;
     * rotateRight(x.parent);
     * w = x.parent.left;
     * }
     * if (w.right.color == State.BLACK && w.left.color == State.BLACK) {
     * w.color = State.RED;
     * x = x.parent;
     * } else {
     * if (w.left.color == State.BLACK) {
     * w.right.color = State.BLACK;
     * w.color = State.RED;
     * rotateLeft(w);
     * w = x.parent.left;
     * }
     * w.color = x.parent.color;
     * x.parent.color = State.BLACK;
     * w.left.color = State.BLACK;
     * rotateRight(x.parent);
     * x = root;
     * }
     * }
     * }
     * x.color = State.BLACK;
     * }
     * <p/>
     * <p/>
     * <p/>
     * private void rotateRight(Node<K, V> x) {
     * <p/>
     * /**************************
     * rotate node x to left *
     * *************************
     * <p/>
     * Node<K, V> y = x.left;
     * <p/>
     * /* establish x.left link *
     * x.left = y.right;
     * if (y.right != NIL) y.right.parent = x;
     * <p/>
     * /* establish y.parent link *
     * if (y != NIL) y.parent = x.parent;
     * if (x.parent != NIL) {
     * if (x == x.parent.left)
     * x.parent.left = y;
     * else
     * x.parent.right = y;
     * } else {
     * root = y;
     * }
     * <p/>
     * /* link x and y *
     * y.right = x;
     * if (x != NIL) x.parent = y;
     * }
     * <p/>
     * private void rotateLeft(Node<K, V> x) {
     * <p/>
     * /**************************
     * rotate node x to left *
     * *************************
     * <p/>
     * Node<K, V> y = x.right;
     * <p/>
     * /* establish x.right link *
     * x.right = y.left;
     * if (y.left != NIL) y.left.parent = x;
     * <p/>
     * /* establish y.parent link *
     * if (y != NIL) y.parent = x.parent;
     * if (x.parent != NIL) {
     * if (x == x.parent.left)
     * x.parent.left = y;
     * else
     * x.parent.right = y;
     * } else {
     * root = y;
     * }
     * <p/>
     * /* link x and y *
     * y.left = x;
     * if (x != NIL) x.parent = y;
     * }
     */

    public void printTree() {
        INode<K, V> it = root;
        //rotateRight(root.left);
        while (it != null) {
            System.out.println(it.getKey() + " -> ");
            it = it.getLeft();
        }
    }
}
