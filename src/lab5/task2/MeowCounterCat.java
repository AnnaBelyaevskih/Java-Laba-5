package lab5.task2;

/**
 * Класс-адаптер для кота, который считает количество мяуканий.
 * Реализует интерфейс Meowable и оборачивает объект класса Cat.
 */
public class MeowCounterCat implements Meowable {

    /** Объект кота, для которого считается количество мяуканий. */
    private final Cat cat;

    /** Количество выполненных мяуканий. */
    private int meowCount;

    /**
     * Создаёт адаптер для заданного кота.
     *
     * @param cat объект кота
     */
    public MeowCounterCat(Cat cat) {
        this.cat = cat;
        this.meowCount = 0;
    }

    /**
     * Вызывает метод мяуканья у кота
     * и увеличивает счётчик мяуканий.
     */
    @Override
    public void meow() {
        cat.meow();
        meowCount++;
    }

    /**
     * Возвращает количество мяуканий кота.
     *
     * @return количество мяуканий
     */
    public int getMeowCount() {
        return meowCount;
    }

    /**
     * Возвращает строковое представление адаптера кота.
     *
     * @return строка с информацией о коте и количестве мяуканий
     */
    @Override
    public String toString() {
        return cat.toString() + ", количество мяуканий: " + meowCount;
    }
}
