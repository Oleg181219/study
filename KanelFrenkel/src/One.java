/**
 * Везде где в заданиях написаноо " сделайте класс" я делаю методы.
 * Так как мне необходимо сохранить разделение выполнения заданий по классам.
 * 1 класс = 1 раздел с множеством заданий одного раздела.
 */


public class One {
    /**
     * 1.4
     * Напишите фрагмент класса (группу команд), который будет присваивать
     * значения 5 и 9 двум переменным, а затем третья переменная будет
     * получать значение, равное сумме значений первых двух переменных.
     */
    public static void OneFour(int a, int b) {
//        int a = 5;
//        int b = 9;
        System.out.println("a= " + a + " b= " + b + "a + b = " + (a + b));
    }

    /**
     * Задание 1.5
     * Напишите фрагмент класса, который будет присваивать значение 7.5
     * переменной х, а затем будет присваивать переменным а и Ь значения
     * вдвое и второе (соответственно) большие, чем значение переменной х.
     */
    public static void OneFive(double a) {
//        double a = 7.5;
        double b = a * 2;
        double c = a * 3;
        System.out.println("a= " + a + " :b= " + b + " :c= " + c);
    }

    /**
     * Задание 1.6
     * Напишите фрагмент класса, в котором переменным а и Ь будут присваиваться
     * числовые значения из диапазона «однозначное положительное число».
     * Затем следует присвоить переменной с значение,
     * составленное следующим образом: значение переменной а является
     * числом десятков, значение переменной Ь является числом единиц.
     */
    public static void OneSix(int a, int b) {
//        int a = 4;
//        int b = 7;
        int c = (a * 10) + b;
        System.out.println("a= " + a + " :b= " + b + " :c= " + c);
    }

    /**
     * Задание 1.7
     * Напишите фрагмент класса, который присваивает переменной х целочисленное
     * значение, а затем присваивает переменной у треть от значения
     * переменной х.
     */

    public static void OneSeven(double x) {
//        double x = 7;
        double y = x / 3;
        System.out.println("x= " + x + " :y= " + y);
    }

}
