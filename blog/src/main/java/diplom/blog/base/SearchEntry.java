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

    public boolean search(String squery) {
         String query = squery.toLowerCase();
         String fText = text.toLowerCase();
        List<Integer> indices = new ArrayList<>();
        if (query.length() < fText.length()) {
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
                                if (fText.charAt(entry.getKey() + j) != query.charAt(j)) {
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
        String fText = text.toLowerCase();
        int h = 1;
        int pos;
        int textHash = 0;

        for (pos = 0; pos < patternLength - 1; pos++)
            h = (h * a) % q;
        for (pos = 0; pos < patternLength; pos++) {
            textHash = (a * textHash + fText.charAt(pos)) % q;
        }
        for (pos = 0; pos <= fText.length() - patternLength; pos++) {
            number2position.put(pos, textHash);
            if (pos < fText.length() - patternLength) {
                textHash = (a * (textHash - fText.charAt(pos) * h) + fText.charAt(pos + patternLength)) % q;
                if (textHash < 0)
                    textHash = (textHash + q);
            }
        }
    }

}