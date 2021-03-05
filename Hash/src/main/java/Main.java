import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> pat = new ArrayList<>();
        ArrayList<Integer> source = new ArrayList<>();
        String string = "box";
        for (int i = 0; i < string.length(); i++){
            pat.add(Character.toString(string.charAt(i)).hashCode());
        }

        String btring = "Skillbox";

        for (int i = 0; i < btring.length(); i++){
            source.add(Character.toString(btring.charAt(i)).hashCode());
        }
        for(int a = 0; a < pat.size(); a++){
            System.out.print(pat.get(a));
        }
        System.out.println();
        for(int a = 0; a < source.size(); a++){
            System.out.print(source.get(a));
        }
    }
}
