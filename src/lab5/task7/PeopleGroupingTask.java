package lab5.task7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для группировки людей по их номеру.
 * - Игнорируем людей без номера
 * - Имя приводим к виду: первая буква заглавная, остальные строчные
 * - Группируем по номеру
 * - Вывод в формате: [номер1:[имена], номер2:[имена], ...]
 */
public class PeopleGroupingTask {

    /**
     * Считывает строки из файла и группирует людей по их номеру.
     * @param path путь к файлу с данными вида "имя:номер"
     * @return Map<Integer, List<String>> — ключ = номер, значение = список имён
     * @throws IOException если произошла ошибка при чтении файла
     */
    public static Map<Integer, List<String>> groupFromFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        return group(lines);
    }

    /**
     * Группирует список строк вида "имя:номер" по номеру.
     * @param people список строк вида "имя:номер"
     * @return Map<Integer, List<String>> — ключ = номер, значение = список имён
     */
    public static Map<Integer, List<String>> group(List<String> people) {
        // Группировка по номеру с нормализацией имени
        Map<Integer, List<String>> grouped = people.stream()
                .map(String::trim)
                .filter(s -> s.contains(":"))                      // оставляем строки с номером
                .map(s -> s.split(":", 2))                         // разделяем на имя и номер
                .filter(parts -> parts.length == 2 && !parts[1].isEmpty())
                .collect(Collectors.groupingBy(
                        parts -> Integer.parseInt(parts[1].trim()),   // ключ — номер
                        Collectors.mapping(
                                parts -> capitalize(parts[0].trim()), // имя с первой заглавной
                                Collectors.toList()
                        )
                ));

        return grouped;
    }

    /**
     * Форматирует имя: первая буква заглавная, остальные строчные.
     * @param name имя
     * @return имя с заглавной первой буквой
     */
    private static String capitalize(String name) {
        if (name.isEmpty()) return name;
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    /**
     * Преобразует Map<Integer, List<String>> в строку в формате:
     * [номер1:[имена], номер2:[имена], ...]
     * @param grouped Map с группировкой
     * @return строка в нужном формате
     */
    public static String formatGrouped(Map<Integer, List<String>> grouped) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean firstEntry = true;
        for (Map.Entry<Integer, List<String>> entry : grouped.entrySet()) {
            if (!firstEntry) sb.append(", ");
            sb.append(entry.getKey()).append(":").append(entry.getValue());
            firstEntry = false;
        }
        sb.append("]");
        return sb.toString();
    }
}

