package au.bystritskaia.tree;

import au.bystritskaia.node.Node;

import java.awt.*;

/**
 * Бинарное дерево
 * @param <T> Значение
 */
@SuppressWarnings({"unchecked", "rawuse"})
public class NodeTree <T extends Comparable<T>> {
    /**
     * Главная нода
     */
    Node root;

    /**
     * Добавить ноду
     * @param value Значение
     * @return Результат добавления
     */
    public boolean addNode(T value) {
        if(root == null) {
            root = new Node();
            root.setColor(Color.BLACK);
            root.setValue(value);
            return true;
        }
        return this.addNode(root, value);
    }

    /**
     * Добавить дочернюю ноду
     * @param node Дода
     * @param value Значение
     * @return Результат добавления
     */
    private boolean addNode(Node node, T value) {
        if (node.getValue().compareTo(value) == 0)
            return false;
        if (node.getValue().compareTo(value) > 0) {
            if (node.getLeftChild() != null) {
                boolean result = addNode(node.getLeftChild(), value);
                node.setLeftChild(rebalanced(node.getLeftChild()));
                return result;
            } else {
                node.setLeftChild(new Node());
                node.getLeftChild().setColor(Color.RED);
                node.getLeftChild().setValue(value);
                return true;
            }
        } else {
            if (node.getRightChild() != null) {
                boolean result = addNode(node.getRightChild(), value);
                node.setRightChild(rebalanced(node.getRightChild()));
                return result;
            } else {
                node.setRightChild(new Node());
                node.getRightChild().setColor(Color.RED);
                node.getRightChild().setValue(value);
                return true;
            }
        }
    }

    /**
     * Содержит ли значение
     * @param value Значение
     * @return Результат проверки
     */
    public boolean contain(T value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.getValue().equals(value))
                return true;
            if (currentNode.getValue().compareTo(value) > 0)
                currentNode = currentNode.getLeftChild();
            else
                currentNode = currentNode.getRightChild();
        }
        return false;
    }

    /**
     * Удалить по значению
     * @param value Значение
     * @return Результат удаления
     */
    public boolean remove(T value) {
        if (!contain(value))
            return false;
        Node deleteNode = root;
        Node prevNode = root;
        while (deleteNode != null) {
            if (deleteNode.getValue().compareTo(value) == 0) {
                Node currentNode = deleteNode.getRightChild();
                if (currentNode == null) {
                    if (deleteNode == root) {
                        root = root.getLeftChild();
                        root.setColor(Color.BLACK);
                        return true;
                    }
                    if (deleteNode.getLeftChild() == null) {
                        deleteNode = null;
                        return true;
                    }
                    if (prevNode.getLeftChild() == deleteNode)
                        prevNode.setLeftChild(deleteNode.getLeftChild());
                    else
                        prevNode.setRightChild(deleteNode.getLeftChild());
                    return true;
                }
                while (currentNode.getLeftChild() != null)
                    currentNode = currentNode.getLeftChild();
                deleteNode.setValue(currentNode.getValue());
                currentNode = null;
                return true;
            }
            if (prevNode != deleteNode) {
                if (prevNode.getValue().compareTo(value) > 0)
                    prevNode = prevNode.getLeftChild();
                else
                    prevNode = prevNode.getRightChild();
            }
            if (deleteNode.getValue().compareTo(value) > 0)
                deleteNode = deleteNode.getLeftChild();
            else
                deleteNode = deleteNode.getRightChild();
        }
        return false;
    }

    /**
     * Ребалансировака
     * @param node Нода
     * @return Нода
     */
    private Node rebalanced(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.getRightChild() != null && result.getRightChild().getColor().equals(Color.RED)
                    && (result.getLeftChild() == null || result.getLeftChild().getColor().equals(Color.BLACK))) {
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.getLeftChild() != null && result.getLeftChild().getColor().equals(Color.RED)
                    && result.getLeftChild().getLeftChild() != null && result.getLeftChild().getLeftChild().getColor().equals(Color.RED)) {
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.getLeftChild() != null && result.getLeftChild().getColor().equals(Color.RED)
                    && result.getRightChild() != null && result.getRightChild().getColor().equals(Color.RED)) {
                needRebalance = true;
                colorSwap(result);
            }
        } while (needRebalance);
        return result;
    }

    /**
     * Поменять цвета
     * @param node нода
     */
    private void colorSwap(Node node) {
        node.getRightChild().setColor(Color.BLACK);
        node.getLeftChild().setColor(Color.BLACK);
        node.setColor(Color.RED);
    }

    /**
     * Левый поворот
     * @param node Нода
     * @return Нода
     */
    private Node leftSwap(Node node) {
        Node leftChild = node.getLeftChild();
        Node between = leftChild.getRightChild();
        leftChild.setLeftChild(node);
        node.setLeftChild(between);
        leftChild.setColor(node.getColor());
        node.setColor(Color.RED);
        return leftChild;
    }

    /**
     * Правый поворот
     * @param node Нода
     * @return Нода
     */
    private Node rightSwap(Node node) {
        Node rightChild = node.getRightChild();
        Node between = rightChild.getLeftChild();
        rightChild.setLeftChild(node);
        node.setRightChild(between);
        node.setColor(Color.RED);
        return rightChild;
    }
}
