/**
 * Created by IntelliJ IDEA.
 * User: Kirill
 * Date: 14.03.13
 * Time: 10:11
 */
public interface INodeColored<K,V> extends INode<K,V> {
    boolean getColor();
    boolean setColor(boolean color);
}
