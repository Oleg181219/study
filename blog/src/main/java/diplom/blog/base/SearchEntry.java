package diplom.blog.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SearchEntry {
    private String text;
    private TreeMap<Integer, Integer> number2position = new TreeMap<>();
    private int a = 256;
    private int q = 101;

    public TreeMap<Integer, Integer> getNumber2position() {
        return number2position;
    }

    public SearchEntry(String text) {
        this.text = text;

    }

    public boolean search(String query) {
        System.out.println("query-- " + query);
        System.out.println("text-- " + text);
        List<Integer> indices = new ArrayList<>();
        if (query.length() < text.length()) {
            int patternLength = query.length();
            int patternHash = 0;
            for (int i = 0; i < patternLength; i++) {
                patternHash = (a * patternHash + query.charAt(i)) % q;
            }
            createIndex(patternLength);
            int finalQueryHash = patternHash;
            indices = number2position.entrySet().stream()
                    .filter(entry -> {
                        if (finalQueryHash == entry.getValue()) {
                            int j;

                            for (j = 0; j < patternLength; j++) {
                                if (text.charAt(entry.getKey() + j) != query.charAt(j)) {
                                    break;
                                }
                            }
                            return j == patternLength;
                        }
                        return false;
                    })
                    .map(Map.Entry::getKey).collect(Collectors.toList());
        }
        return !indices.isEmpty();
    }

    private void createIndex(int patternLength) {
        int h = 1;
        int pos;
        int textHash = 0;

        for (pos = 0; pos < patternLength - 1; pos++)
            h = (h * a) % q;
        for (pos = 0; pos < patternLength; pos++) {
            textHash = (a * textHash + text.charAt(pos)) % q;
        }
        for (pos = 0; pos <= text.length() - patternLength; pos++) {
            number2position.put(pos, textHash);
            if (pos < text.length() - patternLength) {
                textHash = (a * (textHash - text.charAt(pos) * h) + text.charAt(pos + patternLength)) % q;
                if (textHash < 0)
                    textHash = (textHash + q);
            }
        }
    }

}