package lab5.task6;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Класс для создания копии очереди в обратном порядке.
 * Метод copyReversed принимает исходную очередь и возвращает новую очередь,
 * элементы которой идут в обратном порядке относительно исходной.
 */
public class QueueReverseCopy {

    /**
     * Создаёт копию очереди source в обратном порядке.
     * @param <T>    тип элементов очереди
     * @param source исходная очередь
     * @return новая очередь с элементами в обратном порядке
     */
    public static <T> Queue<T> copyReversed(Queue<T> source) {
        Stack<T> stack = new Stack<>();

        // Кладём все элементы исходной очереди в стек
        for (T element : source) {
            stack.push(element);
        }

        Queue<T> result = new ArrayDeque<>();
        // Извлекаем элементы из стека и добавляем в новую очередь
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}
