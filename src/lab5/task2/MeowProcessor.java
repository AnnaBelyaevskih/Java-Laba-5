package lab5.task2;

/**
 * Утилитарный класс для обработки объектов,
 * способных выполнять мяуканье.
 * Содержит статический метод для вызова
 * мяуканья у нескольких объектов.
 */
public class MeowProcessor {

    /**
     * Вызывает метод мяуканья у всех переданных объектов.
     *
     * @param cats объекты, реализующие интерфейс Meowable
     */
    public static void process(Meowable... cats) {
        for (Meowable cat : cats) {
            cat.meow();
        }
    }
}
