package rabin_karp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class RabinKarpExtended {
    private String text;
    private TreeMap<Integer, Integer> number2position = new TreeMap<>();

    public RabinKarpExtended(String text) {
        this.text = text;
        createIndex();
    }

    public List<Integer> search(String query) {
        ArrayList<Integer> indices = new ArrayList<>();

        long hashQuery = getHash(query, 0, query.length());
        for (int key : number2position.keySet()) {
            if (query.charAt(0) == number2position.get(key)) {
                long hashSearch = getHash(text, key, key + query.length());
                if (hashSearch == hashQuery) {
                    //Compare founded pattern
                    String founded = "";
                    for (int i = 0; i < query.length(); i++) {
                        for (int j = key + i; j < key + query.length(); j++) {
                            if (text.charAt(j) == query.charAt(i)) {
                                founded += text.charAt(j);
                                break;
                            }
                            else {
                                indices.clear();
                                return indices;
                            }
                        }
                    }
                    indices.add(key);
                    indices.add(key + (query.length() - 1));
                    System.out.println("Match is founded! -> " + founded);
                    System.out.println("First index - " + key + ", last index - " + (key + (query.length() - 1)));
                }
            }
        }
        return indices;
    }

    //Hash by Horner
    private long getHash(String searchingText, int from, int to) {
        long hashValue = 0;
        int b = 11; //const
        int Q = 999999; //mod const
        for (int i = from; i < to; i++) {
            int charCode = searchingText.charAt(i);
            hashValue = (hashValue * b + charCode) % Q;
        }
        return hashValue;
    }

    private void createIndex() {
        //implement text indexing
        for (int i = 0; i < text.length(); i++) {
            number2position.put(i, (int) text.charAt(i));
        }
    }
}