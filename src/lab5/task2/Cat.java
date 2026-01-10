package lab5.task2;

/**
 * Класс, описывающий кота.
 * Содержит имя кота и метод для вывода сообщения о мяуканье.
 * Данный класс изменять нельзя по условию задания.
 */
public class Cat {

    private final String name;

    public Cat(String name) {
        this.name = name;
    }

    /**
     * Выводит сообщение о мяуканье кота в консоль.
     */
    public void meow() {
        System.out.println(name + ": мяу!");
    }

    /**
     * Возвращает строковое представление кота.
     *
     * @return строка вида "кот: Имя"
     */
    @Override
    public String toString() {
        return "кот: " + name;
    }
}
