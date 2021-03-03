import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SuffixTree suffixTree = new SuffixTree("djfjkjajsdbbbbgyysaashhdehdehdelasdjhasldhl");
        suffixTree.printTree();
        List<String> arrayList = suffixTree.searchText("hde");
        System.out.println(arrayList.get(0));




    }
}
