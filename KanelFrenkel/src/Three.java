import java.util.Scanner;

/**
 * Везде где в заданиях написаноо " сделайте класс" я делаю методы.
 * Так как мне необходимо сохранить разделение выполнения заданий по классам.
 * 1 класс = 1 раздел с множеством заданий одного раздела.
 */

public class Three {
    /**
     * Задание 3.1
     * Связь между температурой по шкале Цельсия и температурой по шкале
     * Фаренгейта выражается формулой: C=S(F-32)/9, где С - температура
     * по шкале Цельсия, F - температура по шкале Фаренгейта.
     * Напишите класс, который принимает с клавиатуры температуру по
     * шкале Фаренгейта, а затем выводит на экран эту же температуру, но по
     * шкале Цельсия.
     */
    public static void threeOne() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите температуру в фаренгейтах");
        int farenheyt = reader.nextInt();
        System.out.println("Температура в градусах Цельсия = " + 5 * (farenheyt - 32) / 9);
    }

    /**
     * Задание 3.2
     * Во многих странах для измерения небольших размеров используется
     * не только единица длины «сантиметр», но и такая единица, как
     * «дюйм>> (1дюйм=2.54 сантиметра).
     * Напишите класс, который принимает с клавиатуры значение, равное
     * размеру в сантиметрах, и затем подсчитывает и выводит на экран это
     * же значение, но в дюймах.
     */
    public static void threeTwo() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите значение в см:");
        double sm = reader.nextDouble();
        System.out.println("значение в дюймах = " + sm / 2.54);
    }

    /**
     * Задание 3.3
     * Во многих странах для измерения небольших размеров используется
     * не только единица длины «сантиметр», но и такая единица, как
     * «дюйм» (1 дюйм= 2.54 сантиметра).
     * Напишите класс, который принимает с клавиатуры значение, равное
     * размеру в дюймах, и затем подсчитывает и выводит на экран это же
     * значение, но в сантиметрах.
     */
    public static void threeThree() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите занчение в дюймах:");
        double dm = reader.nextDouble();
        System.out.println("Значение в см = " + dm * 2.54);
    }

    /**
     * Задание 3.4
     * Для пересчета суммы в одной валюте - в сумму в другой валюте (например,
     * между суммой в рублях и этой же суммой, но в евро) используется
     * так называемый «банковский курс»: числовой коэффициент,
     * показывающий, чему равна единица одной валюты в единицах другой.
     * Напишите класс, который принимает с клавиатуры два числа: первое -
     * сумму в одной валюте (например, в долларах), и второе - «банковский
     * курс». Класс должен подсчитать и вывести на экран сумму во второй
     * валюте.
     */

    public static void threeFour() {
        Scanner reader = new Scanner(System.in);
        System.out.println("введите сумму в долларах:");
        double usd = reader.nextDouble();
        System.out.println("введите коэфициент перевода: ");
        double alfa = reader.nextDouble();
        System.out.println("сумма в тугриках непонятных = " + (usd * alfa));
    }

    /**
     * Задание 3.5
     * Напишите класс, который принимает с клавиатуры три числа: длины
     * двух катетов и гипотенузы прямоугольного треугольника, и выводит
     * на экран площадь и периметр этого треугольника.
     * При решении следует предусмотреть возможность того, что размеры
     * сторон треугольника не выражаются целыми числами.
     */
    public static void threeFive() {
        Scanner reader = new Scanner(System.in);
        System.out.println("введите размер первого катета");
        double katetOne = reader.nextDouble();
        System.out.println("введите размер второго катета");
        double katetTwo = reader.nextDouble();
        System.out.println("введите размер гипотинузы");
        double gipotinuza = reader.nextDouble();
        reader.close();
        double perimetr = katetOne + katetTwo + gipotinuza;
        System.out.println("Периметр равен = " + perimetr);
        double p = perimetr / 2;
        double one = p * (p - katetOne);
        double two = (p - katetTwo);
        double three = (p - gipotinuza);
        double square = Math.sqrt(p * one * two * three);
        System.out.println("площадь треугольника = " + square);
    }

    /**
     * Задание 3.6
     * Напишите класс, который принимает с клавиатуры целое число и выводит
     * на экран (в одной строке) три следующих за ним целых числа так,
     * чтобы разница между каждым числом и следующим за ним составляла 2.
     * Например: для введенного с клавиатуры числа 6 надо вывести на экран
     * числа 8, 10, 12.
     */
    public static void threeSix() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        System.out.println((a + 2) + ", " + (a + 4) + ", " + (a + 6));
    }

    /**
     * Задание 3.7
     * Напишите класс, который принимает с клавиатуры целое число и выводит
     * на экран три предыдущих ему целых числа, причем каждое - в
     * отдельной строке.
     */
    public static void threeSeven() {
        Scanner reader = new Scanner(System.in);
        int a = reader.nextInt();
        System.out.println((a - 1) + "\n" + (a - 2) + "\n" + (a - 3));
    }

    /**
     * Задание 3.8
     * Напишите класс, который принимает с клавиатуры два значения: первое
     * - длина основания равнобедренного треугольника, второе - высоту
     * этого треугольника. Затем класс должен подсчитать и вывести на экран:
     * • площадь этого треугольника;
     * • периметр прямоугольника, сторонами которого являются основание
     * и высота описанного в задаче треугольника.
     */
    public static void threeEight() {
        Scanner reader = new Scanner(System.in);
        double l = reader.nextDouble();
        double h = reader.nextDouble();
        System.out.println("площадь треугольника = " + (l / 2) * h);
        System.out.println("периметр прямоугольника = " + (h + l) * 2);
    }

    /**
     * Задание 3.9
     * Компания по продаже минеральной воды проводит конкурс «Балл под
     * крышкой»: на внутренней стороне пробок, которыми закрыты бутылки,
     * выпускаемые компанией, напечатаны призовые баллы. Есть пробки,
     * «равные» 10 баллам, есть - 100, есть - 1000.
     * Напишите класс, который принимает с клавиатуры три числа, означающие
     * количество пробок каждого типа, собранных Васей, и выводит
     * на экран общее количество баллов, которые Вася получил.
     */
    public static void threeNine() {
        Scanner reader = new Scanner(System.in);
        System.out.println(" сколько у Васи пробок с 10-ми? ");
        int ten = reader.nextInt();
        System.out.println(" сколько у Васи пробок с 100-ми? ");
        int hundred = reader.nextInt();
        System.out.println(" сколько у Васи пробок с 1000-ми? ");
        int thousand = reader.nextInt();
        reader.close();
        System.out.println("У Васи " + ((ten * 10) + (hundred * 100) + (thousand * 1000)) + " балов");
    }

    /**
     * Задание 3.10
     * Подрабатывая в течение года, Вася собрал себе определенную сумму на
     * поездку за границу, а родители добавили ему от себя еще некоторую
     * сумму.
     * Напишите класс, который принимает с клавиатуры три значения: первое
     * - сумма в рублях, заработанная Васей, второе - сумма в рублях,
     * которую ему дали родители, третье - курс рубля по отношению к евро.
     * Класс должен подсчитать и вывести на экран сумму в евро, которая
     * имеется у Васи для поездки за границу.
     */
    public static void threeTen() {
        Scanner reader = new Scanner(System.in);
        System.out.println("сколько Вася накопил?");
        double vasyaMoney = reader.nextDouble();
        System.out.println("сколько Васе заняли родители?");
        double vasyaMoneyParents = reader.nextDouble();
        System.out.println("какой курс евро?");
        double euro = reader.nextDouble();
        System.out.println("У Васи есть на поездку " + (vasyaMoney + vasyaMoneyParents) / euro + " euro");
    }


}
