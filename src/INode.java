import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Kirill
 * Date: 14.03.13
 * Time: 9:47
 */
public interface INode<K,V> extends Map.Entry<K,V>{
    INode<K,V> getLeft();
    INode<K,V> setLeft(INode<K,V> l);
    INode<K,V> getRight();
    INode<K,V> setRight(INode<K,V> r);

    INode<K,V> getParent();
    INode<K,V> setParent(INode<K,V> p);

    K setKey(K key);
}
