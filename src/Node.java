/**
 * Created by IntelliJ IDEA.
 * User: Kirill
 * Date: 14.03.13
 * Time: 10:22
 */
class Node<K, V> implements INodeColored<K,V> {
    enum State {BLACK, RED}

    private INode<K, V> left;
    private INode<K, V> right;
    private INode<K, V> parent;
    private State color;
    private V data;
    private K key;

    {
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    Node() {
        this.color = State.BLACK;
    }

    Node(K key, V data) {
        this.key = key;
        this.data = data;
        this.color = State.RED;
    }
    @Override
    public boolean getColor() {

        return  color == State.BLACK;
    }

    @Override
    public boolean setColor(boolean clr) {
        color = clr ? State.BLACK: State.RED;
        return clr;
    }

    @Override
    public INode<K, V> getLeft() {
        return left;
    }

    @Override
    public INode<K, V> setLeft(INode<K, V> l) {
        return  left = l;
    }

    @Override
    public INode<K, V> getRight() {
        return right;
    }

    @Override
    public INode<K, V> setRight(INode<K, V> r) {
        return  right = r;
    }

    @Override
    public INode<K, V> getParent() {
        return parent;
    }

    @Override
    public INode<K, V> setParent(INode<K, V> p) {
        return parent = p;
    }

    @Override
    public K setKey(K k) {
        return  key = k;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return data;
    }

    @Override
    public V setValue(V value) {
        return data = value;
    }
}