/**
 * Created by IntelliJ IDEA.
 * User: Kirill
 * Date: 14.03.13
 * Time: 10:22
 */
class Node<K, V> implements INodeColored<K,V> {
    enum State {BLACK, RED}

    private INodeColored<K, V> left;
    private INodeColored<K, V> right;
    private INodeColored<K, V> parent;
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
    public INodeColored<K, V> getLeft() {
        return left;
    }

    @Override
    public INodeColored<K, V> setLeft(INodeColored<K, V> l) {
        return  left = l;
    }

    @Override
    public INodeColored<K, V> getRight() {
        return right;
    }

    @Override
    public INodeColored<K, V> setRight(INodeColored<K, V> r) {
        return  right = r;
    }

    @Override
    public INodeColored<K, V> getParent() {
        return parent;
    }

    @Override
    public INodeColored<K, V> setParent(INodeColored<K, V> p) {
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