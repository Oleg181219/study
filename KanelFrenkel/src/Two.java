/**
 * Везде где в заданиях написаноо " сделайте класс" я делаю методы.
 * Так как мне необходимо сохранить разделение выполнения заданий по классам.
 * 1 класс = 1 раздел с множеством заданий одного раздела.
 */

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import static java.util.Calendar.YEAR;

public class Two {

    /**
     * Задание 2.1
     * Напишите класс, который выводит на экран надпись "Hello, world!"
     */

    public static void twoOne() {
        String helloWorld = "Hello, world!";
        System.out.println(helloWorld);
    }

    /**
     * Задание 2.2
     * Напишите класс, который выводит на экран надпись
     * "H-e-1-1-o-,-f-r-i-e-n-d!"
     */
    public static void twoTwo() {
        String helloWorld = "H-e-1-1-o-,-f-r-i-e-n-d!";
        System.out.println(helloWorld);
    }

    /**
     * Задание 2.3
     * Напишите класс, который выводит на экран в первой строке три знака
     * «+»,во второй - четыре знака«!», в третьей - два знака«?».
     */
    public static void twoThree() {
        String firstSring = "+++";
        String secondSring = "!!!!";
        String thirdSring = "??";
        System.out.println(firstSring + "\n" + secondSring + "\n" + thirdSring);
    }

    /**
     * Задание 2.4
     * Напишите класс, который выводит на экран тем же количеством команд
     * знаки, которые выводились в задании № 3, но все знаки должны
     * выводиться в одной строке.
     */
    public static void twoFour() {
        String firstSring = "+++";
        String secondSring = "!!!!";
        String thirdSring = "??";
        System.out.println(firstSring + secondSring + thirdSring);
    }

    /**
     * Задание 2.5
     * Дан класс:
     * import java.util.;
     * class targil2
     * {
     * static Scanner reader=new Scanner(System.in);
     * puЫic static void main(StringO args)
     * {
     * int a=reader.nextlnt();
     * System.out.println("aa="+(aa));
     * System.out.println("alO="+(alO));
     * }
     * }
     * 1. Укажите, какая информация будет выводиться на экран, если с
     * клавиатуры введено значение 6? Объясните ответ.
     * 2. Укажите, какая информация будет выводиться на экран, если с
     * клавиатуры введено значение -6? Объясните ответ.
     * 3. Предложите изменения в программе, чтобы ввод и вывод выглядели
     * более «дружественно».
     */
    public static void twoFive() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        System.out.println("a*a=" + (a * a));
        System.out.println("a*1O=" + (a * 10));
    }

    /**
     * Задание 2.6
     * Дан класс:
     * import java.util.;
     * class targil3
     * {
     * static Scanner reader=new Scanner(System.in);
     * puЬlic static void main(StringП args)
     * {
     * int a=reader.nextlnt();
     * System.out.print(" "+(а));
     * System.out. print(" "+(а+ 1));
     * <p>
     * }
     * }
     * 1. Укажите, какая информация будет выводиться на экран, если с
     * клавиатуры введено значение 8? Объясните ответ.
     * 2. Укажите, какая информация будет выводиться на экран, если с
     * клавиатуры введено значение О? Объясните ответ.
     * 3. Предложите изменения в программе, чтобы ввод и вывод выглядели
     * более «дружественно».
     * 4. На экране выведены отрицательные и положительные числа. Какое
     * число было введено? Обоснуйте свой ответ.
     */
    public static void twoSix() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        System.out.println("a = " + (a));
        System.out.println("(a + 1) = " + (a + 1));
        System.out.println("(a - 2) = " + (a - 2));
    }

    /**
     * Задание 2.7
     * Дан класс:
     * import java.util.;
     * class targil 1
     * {
     * static Scanner reader=new Scanner(System.in);
     * puЬlic static void main(StringП args)
     * {
     * int a=reader.nextlnt();
     * System.out.println(a-lOa);
     * }
     * }
     * 1. Укажите, какая информация будет выводиться на экран, если с
     * клавиатуры введено значение 100? Объясните ответ.
     * 2. Укажите, какая информация будет выводиться на экран, если с
     * клавиатуры введено значение -1? Объясните ответ.
     */

    public static void twoSeven() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        System.out.println(a - 10 * a);
    }

    /**
     * Задание 2.8
     * Напишите класс, который присваивает переменной х значение 6, а затем
     * выводит на экран: в первой строке - это значение, во второй -
     * квадрат этого значения, в третьей - куб этого значения.
     */
    public static void twoEight() {
        int x = 6;
        System.out.println("x= " + x + "\nx2= " + (x * x) + "\nx3= " + (x * x * x));
    }

    /**
     * Задание 2.9
     * Напишите класс, который присваивает переменной х значение 9, а затем
     * выводит на экран: в первой строке - это значение с поясняющей
     * надписью (например, «значение х равно 9»), а во второй - квадрат
     * этого значения (тоже с поясняющей надписью).
     */
    public static void twoNine() {
        int x = 9;
        System.out.println("значение х равно " + x + "\nзначение квадрата х равно " + x * x);
    }

    /**
     * Задание 2.10
     * Напишите класс, который присваивает двум переменным значения, а
     * затем выводит на экран: в первой строке значения обеих переменных
     * (с поясняющими надписями), во второй - произведение этих переменных
     * (с поясняющей надписью), в третьей - сумму этих переменных
     * (с поясняющей надписью).
     */

    public static void twoTen() {
        int a = 4;
        int b = 12;
        System.out.println("Переменная a = " + a + ". Переменная b = " + b);
        System.out.println("Произведение a и b = " + a * b);
        System.out.println("Сумма a и b = " + (a + b));
    }

    /**
     * Задание 2.11
     * Напишите класс, который выводит на экран в одной строке ваше имя и
     * фамилию, в друтой - телефон, все это - в рамке из «звездочек» ().
     * Например:
     * Иван Иванов
     * 7-841-266666
     */
    public static void twoEleven() {
        System.out.println("****************************" + "\n* Прохоров Олег Леонидович *"
                + "\n*     7-913-450-68-60      *" + "\n****************************");
        System.out.println();
    }

    /**
     * Задание 2.12
     * Напишите класс, который выводит на экран «песочные часы», составленные
     * из какого-либо символа.
     */

    public static void twoTwelve() {
        System.out.println("XXXXXXXX");
        System.out.println(" XXXXXX");
        System.out.println("  XXXX");
        System.out.println("   XX ");
        System.out.println("  XXXX");
        System.out.println(" XXXXXX");
        System.out.println("XXXXXXXX");
    }

    /**
     * Задание 2.13
     * Для заданий 1.4-1.7 из предыдущего раздела напишите класс, который
     * не только будет присваивать переменным значения, но и выводить
     * на экран эти значения вместе с соответствующими пояснительными
     * текстами.
     */
    public static void twoThirteen() {
        int a = 4;
        int b = 7;
        double x = 7;
        double c = 7.5;
        One.OneFour(a, b);
        One.OneFive(c);
        One.OneSix(a, b);
        One.OneSeven(c);
    }

    /**
     * Задание 2.14
     * Напишите класс, который принимает с клавиатуры целое число и выводит
     * на экран число, которое больше введенного с клавиатуры на 10.
     */
    public static void twoFourteen() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        System.out.println("Число " + (a + 10) + " больше введенного Вами числа ровно на 10");
    }

    /**
     * Задание 2.15
     * Напишите класс, который принимает с клавиатуры целое число и выводит
     * на экран новое число, которое больше введенного с клавиатуры
     * в 10 раз.
     */
    public static void twoFifteen() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        System.out.println("Число " + (a * 10) + " больше введенного Вами числа ровно в 10 раз");
    }

    /**
     * Задание 2.16
     * Напишите класс, который принимает с клавиатуры целое число и выводит
     * на экран два новых числа (каждое в отдельное строке): число,
     * больше введенного с клавиатуры на 6, и число, меньше введенного с
     * клавиатуры на 12.
     */
    public static void twoSixteen() {
        Scanner reader = new Scanner(System.in);
        int x = reader.nextInt();
        System.out.println("Число " + (x + 6) + " больше веденного Вами на 6"
                + "\nЧисло " + (x - 12) + " меньше введенного Вами на 12");
    }

    /**
     * Задание 2.17
     * Напишите класс, который принимает с клавиатуры целое число и затем
     * выводит на экран его квадрат.
     */
    public static void twoSeventeen() {
        Scanner reader = new Scanner(System.in);
        int x = reader.nextInt();
        System.out.println("Квадрат введенного числа равен " + (x * x));
    }

    /**
     * Задание 2.18
     * Напишите класс, который принимает с клавиатуры целое число и затем
     * выводит на экран его квадрат и его же куб, а между ними знак &.
     */

    public static void twoEighteen() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        System.out.println((a * a) + "&" + (a * a * a));
    }

    /**
     * Задание 2.19
     * Напишите класс, который принимает с клавиатуры целое число и выводит
     * на экран следующие три значения (все - в одной строке, разделенные
     * пробелом): число, меньше введенного с клавиатуры на 1,
     * число, введенное с клавиатуры, и число, больше введенного с клавиатуры
     * на 1.
     */
    public static void twoNineteen() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        System.out.println((a - 1) + " " + a + " " + (a + 1));
    }

    /**
     * Задание 2.20
     * Напишите класс, который принимает с клавиатуры целое число и выводит
     * на экран следующие значения (каждое в отдельной строке): снова
     * введенное число и число, противоположное введенному числу.
     */
    public static void twoTwenty() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        System.out.println("Введенное число = " + a + "\nПротивоположенное число = " + (a * (-1)));
    }

    /**
     * Задание 2.21
     * Напишите класс, который принимает с клавиатуры целое число и выводит
     * на экран следующие значения (каждое в отдельной строке): три
     * целых числа, следующих за значением, введенным с клавиатуры.
     */
    public static void twoTwentyOne() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        System.out.println((a + 1) + "\n" + (a + 2) + "\n" + (a + 3));
    }

    /**
     * Задание 2.22
     * Напишите класс, который принимает с клавиатуры два целых числа и
     * выводит на экран следующие значения (каждое в отдельной строке):
     * сумму введенных чисел, их произведение, разницу между первым и
     * вторым, разницу между вторым и первым.
     */
    public static void twoTwentyTwo() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        int b = reader.nextInt();
        System.out.println(a + b);
        System.out.println(a * b);
        System.out.println(a - b);
        System.out.println(b - a);
    }

    /**
     * Задание 2.23
     * Напишите класс, который принимает с клавиатуры два целых числа и
     * выводит на экран следующие значения (каждое в отдельной строке):
     * квадрат каждого числа (отдельно) и сумму квадратов введенных чисел.
     */
    public static void twoTwentyThree() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        int b = reader.nextInt();
        System.out.println(a * a);
        System.out.println(b * b);
        System.out.println((a * a) + (b * b));
    }

    /**
     * Задание 2.24
     * Напишите класс, который принимает с клавиатуры два целых числа,
     * каждое из которых представляет собой размер одной из сторон прямоугольника
     * и выводит на экран следующие значения (каждое в отдельной
     * строке): площадь прямоугольника и его периметр.
     */

    public static void twoTwentyFour() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        int b = reader.nextInt();
        System.out.println("площадь прямоугольника = " + (a * b));
        System.out.println("периметр прямоугольника = " + ((a + b) * 2));
    }

    /**
     * Задание 2.25
     * Напишите класс, который принимает с клавиатуры три целых числа,
     * каждое из которых представляет собой размер одной из сторон параллелепипеда
     * и выводит на экран следующие значения (каждое - в отдельной
     * строке): объем, площадь каждой из граней, общую площадь и
     * общий периметр.
     */
    public static void twoTwentyFive() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        int b = reader.nextInt();
        int c = reader.nextInt();
        System.out.println("объем = " + (a * b * c));
        System.out.println("площадь 1 грани = " + (a * b));
        System.out.println("площадь 2 грани = " + (a * c));
        System.out.println("площадь 3 грани = " + (c * b));
        System.out.println("площадь параллелепипеда = " + ((a * b) + (a * c) + (c * b)) * 2);
        System.out.println("общий периметр = " + (a + b + c) * 2);
    }

    /**
     * Задание 2.26
     * Напишите класс, который принимает с клавиатуры два целых числа,
     * первое из которых представляет собой год рождения, а второе - нынешний
     * (текущий) год. Класс должен вывести на экран возраст (в целых
     * годах).
     */
    public static void twoTwentySix() {
        Calendar calendar = new GregorianCalendar();
        Scanner reader = new Scanner(System.in);
        System.out.println(calendar.get(Calendar.YEAR) - reader.nextInt());
    }

    /**
     * Задание 2.27
     * Напишите класс, который принимает с клавиатуры целое число, представляющее
     * собой массу тела в граммах, и выводящую на экран массу
     * этого тела в килограммах.
     */
    public static void twoTwentySeven() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введеный вес в кг = " + reader.nextDouble() / 1000);
    }

    /**
     * Задание 2.28
     * Напишите класс, который принимает с клавиатуры целое число, представляющее
     * собой расстояние в единицах, принятых на флоте - в кабельтовых.
     * После этого следует вывести на экран это же расстояние в
     * метрах и километрах. Известно, что 1кабельтов=185.2 метра.
     */
    public static void twoTwentyEight() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        double metr = 185.2;
        System.out.println("Расстояние в метрах = " + (double) a * metr);
        System.out.println("Расстояние в километрах = " + (double) a * metr / 1000);
    }

    /**
     * Задание 2.29
     * Напишите класс, который принимает с клавиатуры целое число, представляющее
     * собой промежуток времени в часах, и выводит на экран этот же промежуток выраженный
     * в секундах
     */
    public static void twoTwentyNine() {
        Scanner reader = new Scanner(System.in);
        System.out.println(reader.nextInt() * 60);
    }

    /**
     * этот же промежуток времени, но выраженный в секундах.
     * Задание 2.30
     * Напишите класс, который принимает с клавиатуры два целых числа,
     * образующих ответ на вопрос «который час?» (первое - часы, второе -
     * минуты, например, 15 и 42) и выводит на экран следующие значения
     * (каждое в отдельной строке): сколько секунд прошло с полуночи до
     * «данного момента» и сколько минут прошло за это же время, а также
     * сколько минут осталось до полуночи.
     */
    public static void twoThirty() {
        Scanner reader = new Scanner(System.in);
        int hours = reader.nextInt();
        int minutes = reader.nextInt();
        System.out.println("прошло секунд = " + ((hours * 60) + minutes) * 60);
        System.out.println("прошло минут = " + ((hours * 60) + minutes));
        System.out.println("минут до полуночи = " + ((24 * 60) - ((hours * 60) + minutes)));
    }

    /**
     * Задание 2.31
     * Кинетическая энергия тела подсчитывается как половина произведения
     * массы тела на квадрат скорости тела.
     * Напишите класс, который принимает с клавиатуры два числа, первое
     * из которых - масса тела, а второе - его скорость.
     * Класс должен подсчитать и вывести на экран значение кинетической
     * энергии тела.
     */
    public static void twoThirtyOne() {
        Scanner reader = new Scanner(System.in);
        double mass = reader.nextInt();
        double speedOf = reader.nextInt();
        double kineticActivity = ((speedOf * speedOf) * mass) / 2;
        System.out.println(kineticActivity);
    }

    /**
     * Задание 2.32
     * Напишите класс, который принимает с клавиатуры число и выводит на
     * экран обратное ему значение.
     */
    public static void twoThirtyTwo() {
        Scanner reader = new Scanner(System.in);
        System.out.println(reader.nextInt() * (-1));
    }

    /**
     * Задание 2.33
     * Напишите класс, который принимает с клавиатуры три числа: значения
     * оснований трапеции и ее высоту.
     * Класс должен подсчитать и вывести на экран площадь этой трапеции.
     */
    public static void twoThreeThree() {
        Scanner reader = new Scanner(System.in);
        double a = reader.nextDouble();
        double b = reader.nextDouble();
        double h = reader.nextDouble();
        System.out.println("Площадь трапеции = " + ((a + b) / 2) * h);
    }

    /**
     * Задание 2.34
     * Напишите класс, который сначала заносит в переменную строковое
     * значение Привет, а затем принимает с клавиатуры ваше имя.
     * Класс должен вывести на экран в одной строке сообщение, составленное
     * из строкового значения Привет, знака «запятая» и вашего имени.
     */
    public static void twoThreeFour() {
        Scanner reader = new Scanner(System.in);
        String hello = reader.nextLine();
        String name = reader.nextLine();
        System.out.println(hello + ", " + name);
    }

    /**
     * Задание 2.35
     * Напишите класс, который принимает с клавиатуры два строковых значения:
     * ваше имя и вашу фамилию.
     * Класс должен вывести на экран сообщение Вас зовут, а рядом с ним
     * (через пробел) ваше имя и фамилию, введенные с клавиатуры.
     */
    public static void twoThreeFive() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Фамилия");
        String familiar = reader.nextLine();
        System.out.println("Имя");
        String name = reader.nextLine();
        System.out.println("Вас зовут: " + familiar + " " + name);
    }

    /**
     * Задание 2.36
     * Напишите класс, который принимает с клавиатуры фамилию пассажира
     * и город, в который он вылетает.
     * Класс должен построить строковую переменную, которая будет содержать
     * сообщение Пассажир ... въmетает в ... , в котором вместо многоточий
     * будут фигурировать фамилия пассажира и название города, в который
     * он вылетает. Эту строковую переменную следует вывести на экран.
     */
    public static void twoThreeSix() {
        Scanner reader = new Scanner(System.in);
        System.out.println("введите фамилию");
        String family = reader.nextLine();
        System.out.println("Введите город прибытия");
        String sity = reader.nextLine();
        System.out.println("Пассажир " + family + " вылетает в " + sity);
    }

    /**
     * Задание 2.37
     * Напишите класс, который принимает с клавиатуры имя и фамилию
     * ученика.
     * Класс должен вывести на экран в одной строке сначала фамилию, а
     * после нее имя ученика, а во второй строке - сначала имя, а после
     * него - фамилию ученика.
     */
    public static void twoThreeSeven() {
        Scanner reader = new Scanner(System.in);
        String firstName = reader.nextLine();
        String secondName = reader.nextLine();
        System.out.println(firstName + " " + secondName);
        System.out.println(secondName + " " + firstName);
    }

    /**
     * Задание 2.38
     * Напишите класс, который принимает с клавиатуры фамилию, имя и
     * отчество, а затем выводит на экран инициалы (без пробела между
     * инициалами).
     */
    public static void twoThreeEight() {
        Scanner reader = new Scanner(System.in);
        String family = reader.nextLine();
        String name = reader.nextLine();
        String soN = reader.nextLine();
        System.out.println(Character.toString(family.charAt(0))
                + Character.toString(name.charAt(0))
                + Character.toString(soN.charAt(0)));
    }


    /**
     * Задание 2.39
     * Напишите класс, который принимает с клавиатуры фамилию, имя и
     * отчество, а затем строит строковое значение по следующему правилу:
     * первая буква имени, точка, первая буква отчества, точка, пробел, фамилия.
     * Затем это строковое значение следует вывести на экран.
     * Например, если с клавиатуры были введены значения
     * Иван
     * Иванович
     * Иванов,
     * то на экран следует вывести значение И. И. Иванов.
     */
    public static void twoThreeNine() {
        Scanner reader = new Scanner(System.in);
        String family = reader.nextLine();
        String name = reader.nextLine();
        String soN = reader.nextLine();
        System.out.println(Character.toString(name.charAt(0)) + "."
                + Character.toString(soN.charAt(0)) + ". "
                + family);
    }
}

