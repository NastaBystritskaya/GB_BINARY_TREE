package au.bystritskaia.node;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.awt.*;

/**
 * Нода
 * @param <T> Тип значения ноды
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Node<T extends Comparable<T>> {

    /**
     * Значение
     */
    T value;
    /**
     * Леый потомок
     */
    Node leftChild;
    /**
     * Правый потоммок
     */
    Node rightChild;
    /**
     * Цвет ноды
     */
    Color color;
}
