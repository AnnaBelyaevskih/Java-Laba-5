package lab5.task1;

import java.util.Objects;

/**
 * Класс, представляющий математическую дробь.
 * Хранит числитель и знаменатель и позволяет
 * получать вещественное значение дроби.
 */
public class Fraction implements FractionValue {

    private int numerator;
    private int denominator;
    private Double cachedValue;

    /**
     * Создаёт объект дроби с заданными значениями.
     *
     * @param numerator числитель дроби
     * @param denominator знаменатель дроби (должен быть положительным)
     * @throws IllegalArgumentException если знаменатель меньше либо равен нулю
     */
    public Fraction(int numerator, int denominator) {
        if (denominator <= 0) {
            throw new IllegalArgumentException("Знаменатель должен быть положительным");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Возвращает вещественное значение дроби.
     * При первом вызове значение вычисляется и сохраняется,
     * при последующих — берётся из кэша.
     *
     * @return вещественное значение дроби
     */
    @Override
    public double getDoubleValue() {
        if (cachedValue == null) {
            cachedValue = (double) numerator / denominator;
        }
        return cachedValue;
    }

    /**
     * Изменяет числитель дроби и сбрасывает кэш.
     *
     * @param numerator новый числитель
     */
    @Override
    public void setNumerator(int numerator) {
        this.numerator = numerator;
        cachedValue = null;
    }

    /**
     * Изменяет знаменатель дроби и сбрасывает кэш.
     *
     * @param denominator новый знаменатель (должен быть положительным)
     * @throws IllegalArgumentException если знаменатель меньше либо равен нулю
     */
    @Override
    public void setDenominator(int denominator) {
        if (denominator <= 0) {
            throw new IllegalArgumentException("Знаменатель должен быть положительным");
        }
        this.denominator = denominator;
        cachedValue = null;
    }

    /**
     * Возвращает строковое представление дроби.
     *
     * @return строка вида "числитель/знаменатель"
     */
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    /**
     * Проверяет равенство дробей по числителю и знаменателю.
     *
     * @param o объект для сравнения
     * @return true, если дроби равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fraction)) {
            return false;
        }
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator
                && denominator == fraction.denominator;
    }

    /**
     * Возвращает хэш-код дроби.
     *
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}

