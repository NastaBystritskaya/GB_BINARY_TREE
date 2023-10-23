package au.bystritskaia;

import au.bystritskaia.tree.NodeTree;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        NodeTree<Integer> tree = new NodeTree<>();

        tree.addNode(6);
        tree.addNode(3);
        tree.addNode(2);
        tree.addNode(4);
        tree.addNode(5);
        tree.addNode(1);
        tree.addNode(9);
        tree.addNode(7);
        tree.addNode(16);
        tree.addNode(8);
        tree.addNode(11);
        tree.addNode(15);
        tree.addNode(10);
    }
}
