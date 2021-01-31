public class Five {

    /**
     * Задание 5.4
     * Дано логическое выражение:
     * (z>x)ll(x<O)&&(z-y>9)
     * Чему равно значение этого выражения для следующих значений переменных:
     * x=-2;y=S;z=l3?
     */

    public static void fiveFour() {
        int x = -2;
        int y = 5;
        int z = 13;
        Boolean a = ((z > x) || (x < 0) && (z - y > 9));
        System.out.println(a);
    }

    /**
     * Задание 5.5
     * Дано логическое выражение:
     * ((z>x)l l(x<O))& &(z-y>9)
     * Чему равно значение этого выражения для следующих значений переменных:
     * х=-2; y=S; z=13?
     */

    public static void fiveFive() {
        int x = -2;
        int y = 5;
        int z = 13;
        boolean a = (((z > x) || (x < 0)) && (z - y > 9));
        System.out.println(a);
    }

}
