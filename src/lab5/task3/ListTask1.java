package lab5.task3;

import java.util.Iterator;
import java.util.List;

/**
 * Утилитарный класс для работы со списками.
 * Содержит метод для удаления всех вхождений
 * заданного элемента из списка.
 */
public class ListTask1 {

    /**
     * Удаляет из списка все элементы,
     * равные указанному значению.
     * Удаление выполняется с использованием итератора,
     * что предотвращает ошибки при обходе коллекции.
     *
     * @param list список, из которого необходимо удалить элементы
     * @param element элемент, который требуется удалить
     * @param <T> тип элементов списка
     */
    public static <T> void removeAll(List<T> list, T element) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(element)) {
                iterator.remove();
            }
        }
    }
}
