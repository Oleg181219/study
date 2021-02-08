public class Main {
    private static final String HADOOP_CONNECTION_URI = "hdfs://0.0.0.0:8020";

    public static void main(String[] args) throws Exception {

        /**
         * Initializes the class, using rootPath as "/" directory
         */
        FileAccess hadoop = new FileAccess(HADOOP_CONNECTION_URI);

        /**
         * Creates empty file or directory
         */
        hadoop.create("test2/ok1.txt");

        /**
         * Appends content to the file
         */
        hadoop.append("test2/append_test2.txt", "\ntesting content for append");

        /**
         * Returns content of the file
         */
        System.out.println(hadoop.read("test2/append_test2.txt"));

        /**
         * Deletes file or directory
         */
        hadoop.delete("test1/append_test1.txt");
        hadoop.delete("test5/append_test5.txt");

        /**
         * Checks, is the "path" is directory or file
         */
        // вариант 1. вывод жуть.
        System.out.println("new - " + hadoop.isDirectory("test1"));
        System.out.println("new - " + hadoop.isDirectory("test5/append_test1.txt"));
        //вариант 2. красивый и понятный вывод. но deprecated
//        System.out.println(hadoop.isDirectory("test1/append_test.txt") ? "да, это директория" : "упс, не директория");
//        System.out.println(hadoop.isDirectory("test1") ? "да, это директория" : "упс, не директория");

        /**
         * Return the list of files and subdirectories on any directory
         */
        System.out.println(hadoop.list("/"));
    }
}
