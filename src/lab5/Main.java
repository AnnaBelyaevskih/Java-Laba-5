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
        int numerator = readInt("Введите числитель: ");
        int denominator;

        while (true) {
            denominator = readInt("Введите знаменатель (> 0): ");
            if (denominator > 0) {
                break;
            }
            System.out.println("Ошибка: знаменатель должен быть положительным.");
        }

        Fraction fraction = new Fraction(numerator, denominator);
        System.out.println("Дробь: " + fraction);
        System.out.println("Вещественное значение: " + fraction.getDoubleValue());
    }

    // ---------- ЗАДАНИЕ 2 ----------
    private static void demoCats() {
        System.out.print("Введите имя кота: ");
        String name = SCANNER.nextLine().trim();

        while (name.isEmpty()) {
            System.out.print("Имя не может быть пустым. Введите имя кота: ");
            name = SCANNER.nextLine().trim();
        }

        int times = readInt("Сколько раз кот должен мяукнуть? ", 1, 100);

        Cat cat = new Cat(name);
        MeowCounterCat counterCat = new MeowCounterCat(cat);

        for (int i = 0; i < times; i++) {
            MeowProcessor.process(counterCat);
        }

        System.out.println("Кот мяукал " + counterCat.getMeowCount() + " раз(а)");
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
            System.out.print("Введите фамилию и имя ученика: ");
            String surname = SCANNER.nextLine().trim();

            while (surname.isEmpty()) {
                System.out.print("Фамилия не может быть пустой. Повторите ввод: ");
                surname = SCANNER.nextLine().trim();
            }

            System.out.println("Логин: " + generator.generateLogin(surname));
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
