
/**
 * Created by IntelliJ IDEA.
 * User: Kirill
 * Date: 03.03.13
 * Time: 11:47
 */


public class Main {
                   //ololo
    public static void main(String[] args) {

      RBTree<String,Integer> tree = new RBTree<String, Integer>();
        tree.put("z",18);
        tree.put("o",17);
        tree.put("w",16);
        tree.put("r",15);
        tree.put("f",14);
        tree.put("d",13);
        //tree.put("n",12);

        tree.printTree();
    }
}
