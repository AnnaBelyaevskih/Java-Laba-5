package lab5.task5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Класс для поиска звонких согласных букв в тексте.
 */
public class VoicedConsonantsTask {

    /** Множество всех звонких согласных букв. */
    private static final Set<Character> VOICED =
            Set.of('б', 'в', 'г', 'д', 'ж', 'з', 'й', 'л', 'м', 'н', 'р');

    /**
     * Находит все звонкие согласные буквы, которые встречаются хотя бы в одном слове
     * текста, и возвращает их в алфавитном порядке.
     *
     * @param path путь к файлу с текстом
     * @return множество звонких согласных букв в алфавитном порядке
     * @throws IOException если произошла ошибка чтения файла
     */
    public static Set<Character> find(Path path) throws IOException {
        Set<Character> found = new HashSet<>();
        String fullText = Files.readString(path);
        // нижний регистр
        String lowerText = fullText.toLowerCase();
        String[] words = lowerText.split("\\s+");
        //Обрабатываем каждое слово по очереди
        for (String word : words) {
            // Превращаем слово в набор отдельных символов
            char[] letters = word.toCharArray();
            for (char c : letters) {
                // Проверяем: есть ли этот символ в нашем списке звонких?
                if (VOICED.contains(c)) {
                    found.add(c);
                }
            }
        }
        return new TreeSet<>(found);
    }
}
