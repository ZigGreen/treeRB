/**
 * Created by IntelliJ IDEA.
 * User: Kirill
 * Date: 28.03.13
 * Time: 9:49
 */
public interface INodeFactory<K, V> {
    INodeColored<K, V> newNode(K key, V value, boolean isBlack,INodeColored<K,V> left, INodeColored<K,V> right);
    INodeColored<K, V> newNode(K key, V value, boolean isBlack);
    INodeColored<K, V> newNode(K key, V value);
}
