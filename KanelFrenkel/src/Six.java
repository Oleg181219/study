import java.util.Scanner;

public class Six {

    /**
     * Задание 6.4
     * Напишите класс, который принимает с клавиатуры целое число и, если
     * оно положительное, увеличивает его вдвое. Класс должен выводить на
     * экран новое значение.
     */
    public static void sixFour() {
        Scanner reader = new Scanner(System.in);
        System.out.println("ведите целое число:");
        int a = reader.nextInt();
        reader.close();
        if (a > 0) {
            System.out.println(a * 2);
        }
    }

    /**
     * Задание 6.5
     * Напишите класс, который принимает с клавиатуры целое число и, если
     * оно равно нулю, выводит на экран сообщение Ноль.
     */
    public static void sixFive() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое число:");
        int a = reader.nextInt();
        reader.close();
        if (a == 0) {
            System.out.println("Ноль");
        }
    }

    /**
     * Задание 6.6
     * Напишите класс, который принимает с клавиатуры целое число и выводит
     * на экран одно из следующих сообщений: Положительное, или
     * Отрицательное, или Ноль, - в зависимости от значения числа.
     */
    public static void sixSix() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое число:");
        int a = reader.nextInt();
        reader.close();
        if (a < 0) {
            System.out.println("Отрицательное");
        }
        if (a == 0) {
            System.out.println("Ноль");
        }
        if (a > 0) {
            System.out.println("Положительное");
        }
    }

    /**
     * Задание 6.7
     * Напишите класс, который принимает с клавиатуры целое положительное
     * число и, если оно как минимум трехзначное и положительное,
     * уменьшает его на 1.
     */
    public static void sixSeven() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое положительное число:");
        int a = reader.nextInt();
        reader.close();
        if (a > 99) {
            a = a - 1;
        }
        System.out.println(a);
    }

    /**
     * Задание 6.8
     * Напишите класс, который принимает с клавиатуры два целых числа и,
     * если первое больше второго, выводит на экран их сумму, если же наоборот
     * - выводит на экран их произведение. В случае же, если числа
     * одинаковы, программа выводит на экран сообщение Числа равны.
     */
    public static void sixEight() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое число:");
        int a = reader.nextInt();
        System.out.println("Введите еще одно целое число:");
        int b = reader.nextInt();
        reader.close();
        if (a == b) {
            System.out.println("Числа равны");
        }
        if (a > b) {
            System.out.println(a + b);
        }
        if (a < b) {
            System.out.println(a * b);
        }
    }

    /**
     * Задание 6.9
     * Трехзначное положительное число называется «четно-красивым», если
     * каждая из его цифр - четная. Напишите класс, который принимает с
     * клавиатуры трехзначное число и проверяет, является ли оно «четнокрасивым
     * ». В соответствии с результатом проверки надо вывести на
     * экран соответствующее текстовое сообщение.
     * Обратите внимание: задание имеет решение с использованием одного
     * оператора if и без составного условия!
     */
    public static void sixNine() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое трехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int newA = (((a % 10) / 2) * 2) + ((((a / 100) / 2) * 2) * 100) + (((((a / 10) % 10) / 2) * 2) * 10);
        if (a == newA) {
            System.out.println("число четно-красивое");
        }
    }

    /**
     * Задание 6.10
     * Напишите программу (класс), которая принимает с клавиатуры двузначное
     * число и проверяет, что больше: само это число или произведение
     * его цифр.
     */
    public static void sixTen() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите двухзначное положительно число:");
        int a = reader.nextInt();
        reader.close();
        if (((a % 10) * (a / 10)) > a) {
            System.out.println("Произвдение цифр больше числа");
        }
        if (((a % 10) * (a / 10)) < a) {
            System.out.println("Произвдение цифр меньше числа");

        }
    }

    /**
     * Задание 6.11
     * Напишите класс, который принимает с клавиатуры два целых числа и,
     * если оба имеют одинаковый знак, на экран выводится сообщение
     * Один и тот же знак, а если разный, то на экран выводится сообщение
     * Разные знаки. Если же хотя бы одно из чисел равно О, выводится сообщение
     * Некорректно.
     */
    public static void sixEleven() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое число:");
        int b = reader.nextInt();
        System.out.println("Введите еще одно целое число:");
        int a = reader.nextInt();
        reader.close();
        if ((a > 0 & b > 0) | (a < 0 & b < 0)) {
            System.out.println("Один и тот же знак");
        }
        if ((a > 0 & b < 0) | (a < 0 & b > 0)) {
            System.out.println("Разные знаки");
        }
        if (a == 0 | b == 0) {
            System.out.println("Некорректно");
        }
    }

    /**
     * Задание 6.12
     * Напишите класс, который принимает с клавиатуры три значения (а, Ь
     * и с), являющиеся коэффициентами квадратного уравнения.
     * Класс должен определить, имеется ли у этого уравнения хотя бы один
     * корень (решение), и вывести на экран соответствующее текстовое сообщение.
     */


    /**
     * Задание 6.13
     * Напишите класс, который принимает с клавиатуры два значения: первое
     * - числитель дроби, второе - ее знаменатель.
     * Класс должен определить, является ли эта дробь «законной» (существующей),
     * и вывести на экран соответствующее текстовое сообщение.
     */
}
