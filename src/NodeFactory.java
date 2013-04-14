
/**
 * Created by IntelliJ IDEA.
 * User: Kirill
 * Date: 14.04.13
 * Time: 12:12
 */

public class NodeFactory<K,V> implements INodeFactory<K,V> {
    @Override
    public INodeColored<K, V> newNode(K key, V value, boolean isBlack, INodeColored<K, V> left, INodeColored<K, V> right) {
        INodeColored<K, V> newN = new Node<K, V>(key,value);
        newN.setLeft(left);
        newN.setRight(right);
        newN.setColor(isBlack);
        return newN;
    }

    @Override
    public INodeColored<K, V> newNode(K key, V value, boolean isBlack) {
        return newNode(key,value,isBlack,null,null);
    }

    @Override
    public INodeColored<K, V> newNode(K key, V value) {
        return newNode(key,value,false);
    }
}
