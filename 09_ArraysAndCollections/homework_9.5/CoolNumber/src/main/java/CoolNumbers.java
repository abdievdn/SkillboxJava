import java.util.*;

public class CoolNumbers {

    public static List<String> generateCoolNumbers() {
        ArrayList<String> coolNumbers = new ArrayList<>();
        char[] letter = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};
        char X, Y, Z;
        int N1, N2, N3;
        String R;
        int r = 1;
        String number;
            while (r < 200) {
                R = "" + r;
                if (r < 10) {
                    R = "0" + r;
                }
                for (int i = 0; i < letter.length; i++) {
                    for (int j = 0; j < letter.length; j++) {
                        for (int k = 0; k < letter.length; k++) {
                            for (int l = 1; l <= 9; l++) {
                                X = letter[i];
                                Y = letter[j];
                                Z = letter[k];
                                N1 = N2 = N3 = l;
                                number = "" + X + N1 + N2 + N3 + Y + Z + R;
                                coolNumbers.add(number);
                            }
                        }
                    }
                }
                r++;
            }
        return coolNumbers;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        for (String item : list) {
            if (number.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        if (Collections.binarySearch(sortedList, number) >= 0) {
            return true;
        }
        return false;
    }


    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        return hashSet.contains(number);
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        return treeSet.contains(number);
    }

}
