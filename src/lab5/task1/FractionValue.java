package lab5.task1;

/**
 * Интерфейс для работы с дробью.
 * Определяет методы получения вещественного значения
 * и изменения числителя и знаменателя.
 */
public interface FractionValue {
    /**
     * Возвращает вещественное значение дроби.
     *
     * @return вещественное значение дроби
     */
    double getDoubleValue();

    /**
     * Устанавливает числитель дроби.
     *
     * @param numerator новый числитель
     */
    void setNumerator(int numerator);

    /**
     * Устанавливает знаменатель дроби.
     *
     * @param denominator новый знаменатель
     */
    void setDenominator(int denominator);
}
