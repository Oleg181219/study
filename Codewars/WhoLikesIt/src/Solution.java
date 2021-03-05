import java.util.ArrayList;
import java.util.Arrays;

/**
 * likes {} // must be "no one likes this"
 * likes {"Peter"} // must be "Peter likes this"
 * likes {"Jacob", "Alex"} // must be "Jacob and Alex like this"
 * likes {"Max", "John", "Mark"} // must be "Max, John and Mark like this"
 * likes {"Alex", "Jacob", "Mark", "Max"} // must be "Alex, Jacob and 2 others like this"
 */


class Solution {
    //    private String
    public static String whoLikesIt(String... names) {
        if (names.length > 0) {
            if (names.length == 1) {
                String a = names[0] + " likes this";
                return a;
            }
            if (names.length == 2) {
                String a = names[0] + " and " + names[1] + " like this";
                return a;
            }
            if (names.length == 3) {
                String a = names[0] +", " + names[1] + " and " + names[2] + " like this";
                return a;
            }
            if (names.length >= 4) {
                String a = names[0] +", " + names[1] + " and " + (names.length - 2) + " others like this";
                return a;
            }
        }
        return "no one likes this";
    }
}