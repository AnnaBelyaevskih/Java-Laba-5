package lab5;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import lab5.task1.Fraction;
import lab5.task2.Cat;
import lab5.task2.MeowCounterCat;
import lab5.task2.MeowProcessor;
import lab5.task3.ListTask1;
import lab5.task4.LoginGenerator;
import lab5.task5.VoicedConsonantsTask;
import lab5.task6.QueueReverseCopy;
import lab5.task7.PeopleGroupingTask;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Выберите задание (0 — выход): ", 0, 7);

            if (choice == 0) {
                System.out.println("Выход из программы.");
                return;
            }

            System.out.println();

            switch (choice) {
                case 1 -> demoFraction();
                case 2 -> demoCats();
                case 3 -> demoList();
                case 4 -> demoLogins();
                case 5 -> demoSet();
                case 6 -> demoQueue();
                case 7 -> demoPeopleFromFile();
                default -> System.out.println("Неверный пункт меню.");
            }

            System.out.println("\nНажмите Enter для продолжения...");
            SCANNER.nextLine();
        }
    }

    private static void printMenu() {
        System.out.println("1 — Дробь");
        System.out.println("2 — Мяуканье котов");
        System.out.println("3 — Работа со списком");
        System.out.println("4 — Логины учеников");
        System.out.println("5 — Множества");
        System.out.println("6 — Очередь");
        System.out.println("7 — People");
        System.out.println("0 — Выход");
    }

    // ---------- ЗАДАНИЕ 1 ----------
    private static void demoFraction() {
        int numerator1 = readInt("Введите числитель первой дроби: ");
        int denominator1;

        while (true) {
            denominator1 = readInt("Введите знаменатель первой дроби (> 0): ");
            if (denominator1 > 0) {
                break;
            }
            System.out.println("Ошибка: знаменатель должен быть положительным.");
        }

        Fraction fraction1 = new Fraction(numerator1, denominator1);

        int numerator2 = readInt("Введите числитель второй дроби: ");
        int denominator2;

        while (true) {
            denominator2 = readInt("Введите знаменатель второй дроби (> 0): ");
            if (denominator2 > 0) {
                break;
            }
            System.out.println("Ошибка: знаменатель должен быть положительным.");
        }

        Fraction fraction2 = new Fraction(numerator2, denominator2);

        System.out.println("Первая дробь: " + fraction1);
        System.out.println("Вторая дробь: " + fraction2);

        System.out.println("Вещественное значение первой дроби: "
                + fraction1.getDoubleValue());
        System.out.println("Вещественное значение второй дроби: "
                + fraction2.getDoubleValue());

        System.out.println("Дроби равны: " + fraction1.equals(fraction2));
    }


    // ---------- ЗАДАНИЕ 2 ----------
    private static void demoCats() {
        System.out.print("Введите имя первого кота: ");
        String name1 = SCANNER.nextLine().trim();

        while (name1.isEmpty()) {
            System.out.print("Имя не может быть пустым. Введите имя первого кота: ");
            name1 = SCANNER.nextLine().trim();
        }

        int times1 = readInt("Сколько раз первый кот должен мяукнуть? ", 1, 100);

        System.out.print("Введите имя второго кота: ");
        String name2 = SCANNER.nextLine().trim();

        while (name2.isEmpty()) {
            System.out.print("Имя не может быть пустым. Введите имя второго кота: ");
            name2 = SCANNER.nextLine().trim();
        }

        int times2 = readInt("Сколько раз второй кот должен мяукнуть? ", 1, 100);

        Cat cat1 = new Cat(name1);
        Cat cat2 = new Cat(name2);

        MeowCounterCat counterCat1 = new MeowCounterCat(cat1);
        MeowCounterCat counterCat2 = new MeowCounterCat(cat2);

        for (int i = 0; i < times1; i++) {
            MeowProcessor.process(counterCat1);
        }

        for (int i = 0; i < times2; i++) {
            MeowProcessor.process(counterCat2);
        }

        System.out.println("Кот \"" + name1 + "\" мяукал " + counterCat1.getMeowCount() + " раз(а)");
        System.out.println("Кот \"" + name2 + "\" мяукал " + counterCat2.getMeowCount() + " раз(а)");
    }


    // ---------- ЗАДАНИЕ 3 ----------
    private static void demoList() {
        int size = readInt("Введите размер списка: ", 1, 100);
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(readInt("Элемент [" + i + "]: "));
        }

        int value = readInt("Введите элемент для удаления: ");

        System.out.println("Исходный список: " + list);
        ListTask1.removeAll(list, value);
        System.out.println("Результат: " + list);
    }

    // ---------- ЗАДАНИЕ 4 ----------
    private static void demoLogins() {
        int count = readInt("Введите количество учеников: ", 1, 100);
        LoginGenerator generator = new LoginGenerator();

        for (int i = 0; i < count; i++) {
            while (true) {
                System.out.print("Введите фамилию и имя ученика: ");
                String input = SCANNER.nextLine().trim();

                try {
                    String login = generator.generateLogin(input);
                    System.out.println("Логин: " + login);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка ввода: " + e.getMessage());
                }
            }
        }

    }

    // ---------- ЗАДАНИЕ 5 ----------
    private static void demoSet() {
        System.out.print("Введите путь к текстовому файлу: ");
        String pathStr = SCANNER.nextLine().trim();

        while (pathStr.isEmpty()) {
            System.out.print("Путь не может быть пустым. Повторите ввод: ");
            pathStr = SCANNER.nextLine().trim();
        }

        Path path = Path.of(pathStr);

        if (!path.toFile().exists()) {
            System.out.println("Ошибка: файл не существует.");
            return;
        }

        if (!path.toFile().isFile()) {
            System.out.println("Ошибка: указанный путь не является файлом.");
            return;
        }

        try {
            System.out.println(
                    "Звонкие согласные буквы, которые входят хотя бы в одно слово:");
            System.out.println(VoicedConsonantsTask.find(path));
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }


    // ---------- ЗАДАНИЕ 6 ----------
    private static void demoQueue() {
        int size = readInt("Введите размер очереди: ", 1, 100);
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < size; i++) {
            queue.add(readInt("Элемент очереди [" + i + "]: "));
        }

        System.out.println("Исходная очередь: " + queue);
        Queue<Integer> reversed = QueueReverseCopy.copyReversed(queue);
        System.out.println("Очередь в обратном порядке: " + reversed);
    }

    // ---------- ЗАДАНИЕ 7 (работа с людьми из файла) ----------
    private static void demoPeopleFromFile() {
        System.out.print("Введите путь к файлу с людьми: ");
        String pathStr = SCANNER.nextLine().trim();
        Path path = Path.of(pathStr);

        try {
            Map<Integer, List<String>> grouped = PeopleGroupingTask.groupFromFile(path);
            System.out.println(PeopleGroupingTask.formatGrouped(grouped));
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }


    // ---------- ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ----------
    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            if (SCANNER.hasNextInt()) {
                int value = SCANNER.nextInt();
                SCANNER.nextLine();
                return value;
            }
            System.out.println("Ошибка ввода. Введите целое число.");
            SCANNER.nextLine();
        }
    }

    private static int readInt(String message, int min, int max) {
        while (true) {
            int value = readInt(message);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println(
                    "Ошибка ввода. Число должно быть в диапазоне [" + min + "; " + max + "].");
        }
    }
}
