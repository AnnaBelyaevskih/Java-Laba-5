package lab5.task4;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для генерации уникальных логинов учеников по фамилии.
 * - Если фамилия встречается первый раз, логин = фамилия
 * - Если фамилия встречается повторно, к фамилии добавляется число (например, Петров2)
 */
public class LoginGenerator {

    private final Map<String, Integer> counters = new HashMap<>();

    /**
     * Генерирует уникальный логин для переданной строки "Фамилия Имя".
     * Из входной строки берётся только фамилия.
     * Логин формируется по правилу:
     * - первый раз: фамилия
     * - второй и далее: фамилия + номер повторения
     * @param fullName строка вида "Фамилия Имя"
     * @return уникальный логин (только фамилия и число при повторе)
     */
    public String generateLogin(String fullName) {
        String surname = fullName.split(" ")[0];  // берём только фамилию
        int count = counters.getOrDefault(surname, 0) + 1;
        counters.put(surname, count);
        return count == 1 ? surname : surname + count;
    }
}
