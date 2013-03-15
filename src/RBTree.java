
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
        INodeColored<K, V> it = findNode(k);
        return (it != null);

    }

    public V put(K key, V data) {
        INodeColored<K, V> current, parent, x;


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
            root =  x;
        }

        //insertFixup(x);
        return null;
    }

    public V get(Object key) {
        if (key == null) throw new NullPointerException();
        K k = (K) key;
        INodeColored<K, V> it = findNode(k);
        if (it != null)
            return it.getValue();

        return null;
    }

    public V remove(Object key) {

        INodeColored<K, V> x, y;
        if (key == null) throw new NullPointerException();
        K k = (K) key;
        INodeColored<K, V> z = findNode(k);
        if (z == null) return null;
        V oldData = z.getValue();

        if (z.getLeft() == null || z.getRight() == null) {
        /* y has a null node as a child */
            y = z;
        } else {
        /* find tree successor with a null node as a child */
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
            root =  x;

        if (y != z) {
            z.setKey(y.getKey());
            z.setValue(y.getValue());
        }


        //if (y.color == true)
        //deleteFixup(x);
        return oldData;
    }

    private INodeColored<K, V> findNode(K key) {
        INodeColored<K, V> it = root;

        while (it != null) {
            int compared = key.compareTo(it.getKey());
            if (compared == 0) {
                return it;
            }
            it = (compared < 0) ? it.getLeft() : it.getRight();
        }
        return null;

    }


    void deleteFixup(INodeColored<K, V> x) {

        /*************************************
         * maintain Red-Black tree balance  *
         * after deleting node x            *
         * ************************************/

        while (x != root && x.getColor())

        {
            if (x == x.getParent().getLeft()) {
                INodeColored<K, V> w = x.getParent().getRight();
                if (!w.getColor()) {
                    w.setColor(true);
                    x.getParent().setColor(false);  
                    rotateLeft(x.getParent());
                    w = x.getParent().getRight();
                }
                if (w.getLeft().getColor() && w.getRight().getColor() ) {
                    w.setColor(false);
                    x = x.getParent();
                } else {
                    if (w.getRight().getColor()) {
                        w.getLeft().setColor(true);
                        w.setColor(false);
                        rotateRight(w);
                        w = x.getParent().getRight();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(true);
                    w.getRight().setColor(true);
                    rotateLeft(x.getParent());
                    x = root;
                }
            } else {
                INodeColored<K, V> w = x.getParent().getLeft();
                if (!w.getColor()) {
                    w.setColor(true);
                    x.getParent().setColor(false);
                    rotateRight(x.getParent());
                    w = x.getParent().getLeft();
                }
                if (w.getRight().getColor() && w.getLeft().getColor()) {
                    w.setColor(false);
                    x = x.getParent();
                } else {
                    if (w.getLeft().getColor() ) {
                        w.getRight().setColor(true);
                        w.setColor(false);
                        rotateLeft(w);
                        w = x.getParent().getLeft();
                    }
                    w.setColor( x.getParent().getColor());
                    x.getParent().setColor(true);
                    w.getLeft().setColor(true);
                    rotateRight(x.getParent());
                    x = root;
                }
            }
        }

        x.setColor(true);
    }

    private void rotateRight(INodeColored<K, V> x) {    //TODO: переписать

        /**************************
         rotate node x to left *
         *************************/

        INodeColored<K, V> y = x.getLeft();

      /* establish x.left link */
        x.setLeft( y.getRight());
        if (y.getRight() != null) y.getRight().setParent(x);

      /* establish y.parent link */
        if (y != null) y.setParent(x.getParent());
        if (x.getParent() != null) {
            if (x == x.getParent().getLeft())
                x.getParent().setLeft(y);
            else
                x.getParent().setRight(y);
        } else {
            root = y;
        }

      /* link x and y */
        y.setRight(x);
        if (x != null) x.setParent(y);
    }

    private void rotateLeft(INodeColored<K, V> x) {   //TODO: переписать

        /**************************
         rotate node x to left *
         *************************/

        INodeColored<K, V> y = x.getRight();
        /** establish x.right link */
        x.setRight( y.getLeft());
        if (y.getLeft() != null) y.getLeft().setParent(x);

      /* establish y.parent link */
        if (y != null) y.setParent(x.getParent());
        if (x.getParent() != null) {
            if (x == x.getParent().getLeft())
                x.getParent().setLeft(y);
            else
                x.getParent().setRight(y);
        } else {
            root = y;
        }

      /* link x and y */
        y.setLeft( x);
        if (x != null) x.setParent(y);
    }

    public void printTree() {
        INodeColored<K, V> it = root;
        //rotateRight(root.left);
        while (it != null) {
            System.out.println(it.getKey() + " -> ");
            it = it.getLeft();
        }
    }
}
