import java.util.Scanner;

public class Seven {

    public static void SevenOne() {
        /**
         * Если значение переменной avg равно
         * 100, то выведи на экран сообщение
         * Молодец
         */
        double avg = 100;
        if (avg == 100) {
            System.out.println("Молодец");
        }
        /**
         *Если значение переменной mark
         * меньше 60, то увеличить это значе-
         * ние на 10%
         */
        double mark = 20;
        if (mark < 60) {
            mark = mark * 1.10;
        }
        /**
         * Если значение переменной one
         * больше значения переменной two, то
         * вывести на экран значение one; в
         * противном случае - вывести на эк-
         * ран значение переменной two
         */
        double one = 10;
        double two = 20;
        System.out.println(one > two ? one : two);

        /**
         * Если значение переменной num
         * больше нуля, то вывести на экран
         * сообщение Положительное.
         * Если значение переменной num
         * меньше нуля, то вывести на экран
         * сообщение Отрицательное
         */
        double num = 2;
        System.out.println(num > 0 ? "Положительное" : "Отрицательное");

        /**
         * Если сумма переменных ugol1, ugol2
         * и ugol3 составляет 180, то вывести на
         * экран сообщение Это уrлы одноrо
         * треуrольника; в противном случае
         * вывести на экран сообщение Это не
         * уrлы одноrо треуrольника
         */
        float ugol1 = 20;
        float ugol2 = 30;
        float ugol3 = 40;
        System.out.println((ugol1 + ugol2 + ugol3) == 180 ? "углы одного треугольника" : "Это не уrлы одноrо треуrольника");
    }

    /**
     * Задание 7.6
     * Напишите класс, который принимает с клавиатуры два числа и проверяет,
     * равны они друг другу или нет.
     */
    public static void SevenSix() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Wright first number");
        double a = reader.nextDouble();
        System.out.println("Wright second number");
        double b = reader.nextDouble();
        reader.close();
        System.out.println(a == b ? "numbers is equal" : "numbers is not equal");
    }

    /**
     * Задание 7.7
     * Напишите класс, который принимает с клавиатуры два числа, первое
     * из которых - количество учеников в классе, а второе - количество
     * стульев в классной комнате. Программа должна проверить, всем ли
     * ученикам будет, где сесть.
     */
    public static void SevenSeven() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the number of students in the class");
        double a = reader.nextDouble();
        System.out.println("Enter the number of chairs in the class");
        double b = reader.nextDouble();
        reader.close();
        System.out.println(a <= b ? "There are enough chairs" : "Chairs are not enough");
    }

    /**
     * Задание 7.8
     * Напишите класс, который принимает с клавиатуры два числа, первое
     * из которых - количество учеников в классе, а второе - количество
     * столов в классной комнате. Программа должна проверить, всем ли
     * ученикам будет, где сесть.
     * Предполагается, что за столом могут сидеть два ученика.
     */
    public static void SevenEight() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the number of students in the class");
        double a = reader.nextDouble();
        System.out.println("Enter the number of table in the class");
        double b = reader.nextDouble();
        reader.close();
        System.out.println(((a / 2) <= b) ? "There are enough tables" : "Tables are not enough");
    }

    /**
     * Задание 7.9
     * Напишите класс, который принимает с клавиатуры три числа: два первых
     * должны быть длинами сторон катетов прямоугольного треугольника,
     * а третье - длиной гипотенузы. Программа должна проверить,
     * соответствуют ли введенные значения этому требованию.
     * Внимание: допустимо предположить, что все вводимые числа - положительные.
     */
    public static void SevenNine() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the length of the first leg");
        double a = reader.nextDouble();
        System.out.println("Enter the length of the second leg");
        double b = reader.nextDouble();
        System.out.println("Enter the length of the hypotenuse");
        double c = reader.nextDouble();
        reader.close();
        if (a < c & b < c) {
            System.out.println(((a * a) + (b * b)) == (c * c) ? "This is a right triangle" : "This is not a right triangle");
        }
    }

    /**
     * Задание 7.10
     * Напишите программу, которая принимает с клавиатуры число и превращает
     * его в положительное, если оно отрицательное либо в ноль во
     * всех остальных случаях.
     */

    public static void SevenTen() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the number");
        double a = reader.nextDouble();
        reader.close();
        if (a >= 0) {
            a = 0;
        } else if (a < 0) {
            a = Math.abs(a);
        }
        System.out.println(a);
    }

    /**
     * Задание 7.11
     * Напишите класс, который принимает с клавиатуры два различных числа.
     * Класс должен вывести на экран эти числа в порядке возрастания -
     * в одной строке, а в другой - их же, но в порядке убывания.
     */
    public static void SevenOneOne() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter first number");
        double a = reader.nextDouble();
        System.out.println("Enter second number");
        double b = reader.nextDouble();
        reader.close();
        System.out.println(a > b ? a + " " + b + "\n" + b + " " + a : b + " " + a + "\n" + a + " " + b);
    }


}
