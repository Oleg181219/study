import java.math.BigInteger;
import java.util.Scanner;

public class Four {
    /**
     * Задание 4.1
     * Напишите класс, который принимает с клавиатуры целое положительное
     * двузначное число и выводит на экран его цифры, разделенные
     * знаком «пробел».
     */
    public static void fourOne() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        reader.close();
        int a1 = a / 10;
        int a2 = a % 10;
        System.out.println(a1 + " " + a2);
    }

    /**
     * Задание 4.2
     * Напишите класс, который принимает с клавиатуры целое положительное
     * трехзначное число и выводит на экран его цифры, разделенные
     * знаком «пробел».
     */
    public static void fourTwo() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        reader.close();
        int a1 = a / 100;
        int a2 = (a % 100) / 10;
        int a3 = a % 10;
        System.out.println(a1 + " " + a2 + " " + a3);
    }

    /**
     * Задание 4.3
     * Напишите класс, который принимает с клавиатуры целое положительное
     * двузначное число и выводит на экран его цифры, разделенные
     * знаком «пробел», но в обратном порядке. То есть для числа 45 будет
     * выведено на экран 5 4.
     */
    public static void fourThree() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        reader.close();
        int a1 = a / 10;
        int a2 = a % 10;
        System.out.println(a2 + " " + a1);
    }

    /**
     * Задание 4.4
     * Напишите класс, который принимает с клавиатуры целое положительное
     * трехзначное число и выводит на экран его цифры, разделенные
     * знаком «пробел», но в обратном порядке. То есть для числа 415 будет
     * выведено на экран 5 1 4.
     */
    public static void fourFive() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        reader.close();
        int a1 = a / 100;
        int a2 = (a % 100) / 10;
        int a3 = a % 10;
        System.out.println(a3 + " " + a2 + " " + a1);
    }

    /**
     * Задание 4.6
     * Напишите класс, который принимает с клавиатуры целое положительное
     * трехзначное число и выводит его на экран в «полном виде»: например,
     * для числа 364 это будет выглядеть как 300+60+4.
     */
    public static void fourSix() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое трехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int a1 = (a / 100) * 100;
        int a2 = ((a % 100) / 10) * 10;
        int a3 = (a % 10);
        System.out.println(a1 + "+" + a2 + "+" + a3);
    }


    /**
     * Задание 4.7
     * Напишите класс, который принимает с клавиатуры целое положительное
     * двузначное число. Затем следует построить новое значение, составленное
     * из цифр числа, введенного с клавиатуры, но в обратном
     * порядке. После этого новое значение следует увеличить на 8, и окончательный
     * результат вывести на экран. То есть для числа 37 следует построить
     * число 73 и вывести на экран 81.
     */
    public static void fourSeven() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите  целое двухзначное число:");
        int a = reader.nextInt();
        reader.close();
        int a1 = a / 10;
        int a2 = a % 10;
        System.out.println("Новое число = " + (((a2 * 10) + a1) + 8));
    }

    /**
     * Задание 4.8
     * Напишите класс, который принимает с клавиатуры целое положительное
     * трехзначное число. Затем следует построить новое значение, составленное
     * из цифр числа, введенного с клавиатуры, но в обратном
     * порядке. После этого новое значение следует уменьшить на 20, и окончательный
     * результат вывести на экран. То есть для числа 327 следует
     * построить число 723 и вывести на экран 703.
     */
    public static void fourEight() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое трехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int a1 = a / 100;
        int a2 = (a / 10) % 10;
        int a3 = a % 10;
        System.out.println("Новое число = " + (((a3 * 100) + (a2 * 10) + a1) - 20));
    }

    /**
     * Задание 4.9
     * Назовем «весом числа>> сумму его цифр.
     * Напишите класс, который принимает с клавиатуры число (двузначное)
     * и выводит на экран его «вес».
     */
    public static void fourNine() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое духзначное число:");
        int a = reader.nextInt();
        reader.close();
        System.out.println("Вес числа = " + ((a / 10) + (a % 10)));
    }

    /**
     * Задание 4.10
     * Назовем «весом числа>> сумму· его цифр.
     * Напишите класс, который принимает с клавиатуры число (трехзначное)
     * и выводит на экран его «вес».
     */
    public static void fourTen() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое трехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int weight = (a / 100) + (a % 10) + ((a / 10) % 10);
        System.out.println("Вес числа = " + weight);
    }

    /**
     * Задание 4.11
     * Назовем «длиной двузначного числа» разницу между числом десятков
     * (первая цифра) и числом единиц (вторая цифра).
     * Напишите класс, который принимает с клавиатуры двузначное число и
     * выводит на экран его «длину».
     */
    public static void fourEleven() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое двухзначное число: ");
        int a = reader.nextInt();
        reader.close();
        int lenght = (a / 10) - (a % 10);
        System.out.println("Длинна числа = " + lenght);
    }


    /**
     * Задание 4.12
     * Назовем «длиной трехзначного числа» разницу между его первой (число
     * сотен) и последней (число единиц) цифрами, умноженную на его
     * среднюю (число десятков) цифру.
     * Напишите класс, который принимает с клавиатуры целое положительное
     * трехзначное число, вычисляет его «длину» и выводит результат на
     * экран.
     */
    public static void fourTwelve() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое трехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int lenght = ((a / 100) - (a % 10)) * ((a / 10) % 10);
        System.out.println("Длинна числа = " + lenght);
    }

    /**
     * Задание 4.13
     * Назовем «степенью четности» числа количество четных цифр в нем. Например,
     * для числа 4612 степень четности равна 3 (в его составе три четных
     * цифры - 4, 6 и 2). Напишите класс, который принимает с клавиатуры положительное
     * двузначное число, а затем вычисляет и выводит на экран его «степень
     * четности».
     */
    public static void fourThirteen() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое двухзначное число:");
        int a = reader.nextInt();
        reader.close();
        int i = 0;
        int a1 = a / 10;
        int a2 = a % 10;
        if ((a1 % 2) == 0) {
            i++;
        }
        if ((a2 % 2) == 0) {
            i++;
        }
        System.out.println("степень четности числа = " + i);
    }

    /**
     * Задание 4.14
     * Напишите класс, который принимает с клавиатуры положительное
     * трехзначное число, а затем вычисляет и выводит на экран его «степень
     * четности».
     */
    public static void fourFourteen() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое трехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int i = 0;
        int a1 = a / 100;
        int a2 = (a / 10) % 10;
        int a3 = a % 10;
        if ((a1 % 2) == 0) {
            i++;
        }
        if ((a2 % 2) == 0) {
            i++;
        }
        if ((a3 % 2) == 0) {
            i++;
        }
        System.out.println("степень четности числа = " + i);
    }

    /**
     * Задание 4.15
     * Напишите класс, который принимает с клавиатуры положительное
     * четырехзначное число, а затем вычисляет и выводит на экран его «степень
     * четности».
     */
    public static void fourFifthteen() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое четырехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int i = 0;
        int a1 = a / 1000;
        int a2 = ((a / 10) / 10) % 10;
        int a3 = (a / 10) % 10;
        int a4 = a % 10;
        if ((a1 % 2) == 0) {
            i++;
        }
        if ((a2 % 2) == 0) {
            i++;
        }
        if ((a3 % 2) == 0) {
            i++;
        }
        if ((a4 % 2) == 0) {
            i++;
        }
        System.out.println("степень четности числа = " + i);
    }

    /**
     * Задание 4.16
     * Назовем «перестановкой» операцию над числом, при которой его первая
     * и последняя цифры меняются местами. Например, из числа 1234
     * получается число 4231. Напишите программу, которая принимает с
     * клавиатуры трехзначное число и строит из него новое число методом
     * «перестановки».
     */
    public static void fourSixteen() {
        Scanner reader = new Scanner(System.in);
        System.out.println("введите целое трехзначное число");
        int a = reader.nextInt();
        reader.close();
        int a1 = a / 100;
        int a2 = (a / 10) % 10;
        int a3 = a % 10;
        int newNumber = (a3 * 100) + (a2 * 10) + a1;
        System.out.println("перестановленное число = " + newNumber);

    }

    /**
     * Задание 4.17
     * Напишите программу, которая выполняет перестановку над четырехзначным
     * числом.
     */
    public static void fourSeventeen() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите целое четырехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int a1 = a / 1000;
        int a2 = ((a / 10) / 10) % 10;
        int a3 = (a / 10) % 10;
        int a4 = a % 10;
        int newNumber = (a4 * 1000) + (a2 * 100) + (a3 * 10) + a1;
        System.out.println("перестановленное число = " + newNumber);
    }

    /**
     * Задание 4.18
     * Назовем «линейным сдвигом числа влево» операцию, при которой его
     * цифры перемещаются на одну позицию влево (единицы - на место
     * десятков, десятки - на место сотен, сотни - на место тысяч и так далее)
     * - при этом первая цифра удаляется из числа, а на место единиц
     * записывается ноль.
     * Например, из числа 1234 после такой операции получается число 2340.
     * Напишите класс, который принимает с клавиатуры трехзначное число
     * и строит новое число, полученное «линейным сдвигом влево».
     */
    public static void fourEighteen() {
        Scanner reader = new Scanner(System.in);
        System.out.println("ведите целое трехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int a1 = (a % 100) * 10;
        System.out.println(a1);
    }

    /**
     * Задание 4.19
     * Напишите класс, который выполняет «линейный сдвиг влево» для четырехзначного
     * числа.
     */
    public static void fourNineteen() {
        Scanner reader = new Scanner(System.in);
        System.out.println("ведите целое четырехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int a1 = (a % 1000) * 10;
        System.out.println(a1);
    }

    /**
     * Задание 4.20
     * Назовем «сдвигом числа влево по кругу» операцию, при которой его
     * цифры перемещаются на одну позицию влево (единицы - на место
     * десятков, десятки - на место сотен, сотни - на место тысяч и так далее)
     * - при этом первая цифра перемещается на место единиц.
     * Например, из числа 1234 получается число 2341.
     * Напишите класс, который принимает с клавиатуры трехзначное число
     * и строит новое число, полученное «сдвигом влево по кругу».
     */
    public static void fourTwenty() {
        Scanner reader = new Scanner(System.in);
        System.out.println("ведите целое трехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int a1 = ((a % 100) * 10) + (a / 100);
        System.out.println(a1);
    }

    /**
     * Задание 4.21
     * Напишите класс, который выполняет «сдвиг влево по кругу» для четырехзначного
     * числа.
     */
    public static void fourTwentyOne() {
        Scanner reader = new Scanner(System.in);
        System.out.println("ведите целое четырехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int a1 = ((a % 1000) * 10) + (a / 1000);
        System.out.println(a1);
    }

    /**
     * Задание 4.22
     * Назовем «сдвигом числа вправо по кругу» операцию, при которой все
     * цифры числа сдвигаются вправо, а число единиц (последняя цифра)
     * перемещается в старший разряд числа (первая цифра).
     * Например, из числа 1234 получается число 4123.
     * Напишите класс, который принимает с клавиатуры трехзначное число
     * и строит новое число, полученное «сдвигом вправо по кругу».
     */
    public static void fourTwentyTwo() {
        Scanner reader = new Scanner(System.in);
        System.out.println("ведите целое трехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int a1 = ((a / 10) + ((a % 10) * 100));
        System.out.println(a1);
    }

    /**
     * Задание 4.23
     * Напишите класс, который выполняет «сдвиг вправо по кругу» для четырехзначного
     * числа.
     */
    public static void fourTwentyThree() {
        Scanner reader = new Scanner(System.in);
        System.out.println("ведите целое четырехзначное число:");
        int a = reader.nextInt();
        reader.close();
        int a1 = ((a / 10) + ((a % 10) * 1000));
        System.out.println(a1);
    }

    /**
     * Задание 4.24
     * Назовем «обменом» операцию, при которой два числа «обмениваются»
     * своими правыми цифрами.
     * Напишите класс, который принимает с клавиатуры два трехзначных
     * положительных числа и совершает над ними операцию «обмена».
     */
    public static void fourTwentyFour() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите первое трехзначное число:");
        int a1 = reader.nextInt();
        System.out.println("Введите второе трехзначное число:");
        int a2 = reader.nextInt();
        reader.close();
        int a1New = (a1 % 100) + ((a2 / 100) * 100);
        int a2New = (a2 % 100) + ((a1 / 100) * 100);
        System.out.println("новые числа: " + a1New + "\n" + a2New);
    }

    /**
     * Задание 4.25
     * Назовем «обменом» операцию, при которой два числа «обмениваются»
     * своими правыми цифрами.
     * Напишите класс, который принимает с клавиатуры два положительных
     * числа и совершает над ними операцию «обмена». Числа могут быть
     * разной длины.
     */
    public static void fourTwentyFive() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите первое число:");
        int a1 = reader.nextInt();
        System.out.println("Введите второе число:");
        int a2 = reader.nextInt();
        reader.close();
        int a1New = ((a1 / 10) * 10) + (a2 % 10);
        int a2New = ((a2 / 10) * 10) + (a1 % 10);
        System.out.println("новые числа: " + a1New + "\n" + a2New);
    }


    /**
     * Задание 4.26
     * Назовем «большим обменом» операцию, при которой два числа «обмениваются
     * » своими левыми цифрами.
     * 1. Напишите класс, который принимает с клавиатуры два трехзначных
     * положительных числа и совершает над ними операцию «обмена».
     * 2. Возможно ли совершить операцию «большого обмена», если два
     * числа имеют разное количество цифр? Обоснуйте свой ответ.
     */
    public static void fourTwentySix() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите первое число:");
        String a1 = reader.nextLine();
        System.out.println("Введите второе число:");
        String a2 = reader.nextLine();
        BigInteger a1Index = new BigInteger(a1.replace(a1.charAt(0), a2.charAt(0)));
        BigInteger a2Index = new BigInteger(a2.replace(a2.charAt(0), a1.charAt(0)));
        System.out.println(a1Index + "\n" + a2Index);
    }
}
