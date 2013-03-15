import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Kirill
 * Date: 14.03.13
 * Time: 10:11
 */
public interface INodeColored<K,V> extends Map.Entry<K,V> {
    boolean getColor();
    boolean setColor(boolean color);

    INodeColored<K,V> getLeft();
    INodeColored<K,V> setLeft(INodeColored<K,V> l);
    INodeColored<K,V> getRight();
    INodeColored<K,V> setRight(INodeColored<K,V> r);

    INodeColored<K,V> getParent();
    INodeColored<K,V> setParent(INodeColored<K,V> p);

    K setKey(K key);
}
