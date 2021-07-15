import java.util.*;

public class Main {
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска введимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */
    private static String IS_FOUND = "номер найден";
    private static String NOT_FOUND = "номер не найден";

    public static void main(String[] args) {
        ArrayList<String> coolNumbers = (ArrayList<String>) CoolNumbers.generateCoolNumbers();
        HashSet<String> coolNumbersHashSet = new HashSet<>(coolNumbers);
        TreeSet<String> coolNumbersTreeSet = new TreeSet<>(coolNumbers);
        long start, end;
        boolean isFind = false;
        Map<Long, String> findTimes = new TreeMap<>();

        /*        for (String number : coolNumbers) {
            System.out.println(number);
        }*/

        System.out.println("Сгенерировано номеров: " + coolNumbers.size() + '\n');

        start = System.nanoTime();
        isFind = CoolNumbers.bruteForceSearchInList(coolNumbers, "У777НС66");
        end = System.nanoTime();
        long findTime1 = end - start;
        findTimes.put(findTime1, "Поиск перебором");
        System.out.println("Поиск перебором: " + isFind(isFind) + ", поиск занял " + findTime1 + "нс");

        Collections.sort(coolNumbers);
        start = System.nanoTime();
        isFind = CoolNumbers.binarySearchInList(coolNumbers, "У777НС66");
        end = System.nanoTime();
        long findTime2 = end - start;
        findTimes.put(findTime2, "Бинарный поиск");
        System.out.println("Бинарный поиск: " + isFind(isFind) + ", поиск занял " + findTime2 + "нс");

        start = System.nanoTime();
        isFind = CoolNumbers.searchInHashSet(coolNumbersHashSet, "У777НС66");
        end = System.nanoTime();
        long findTime3 = end - start;
        findTimes.put(findTime3, "Поиск в HashSet");
        System.out.println("Поиск в HashSet: " + isFind(isFind) + ", поиск занял " + findTime3 + "нс");

        start = System.nanoTime();
        isFind = CoolNumbers.searchInTreeSet(coolNumbersTreeSet, "У777НС66");
        end = System.nanoTime();
        long findTime4 = end - start;
        findTimes.put(findTime4, "Поиск в TreeSet");
        System.out.println("Поиск в TreeSet: " + isFind(isFind) + ", поиск занял " + findTime4 + "нс");

        System.out.println();
        for (Map.Entry entry : findTimes.entrySet()) {
            System.out.println(entry);
        }
    }

    private static String isFind(boolean isFind) {
        if (isFind) return IS_FOUND;
        return NOT_FOUND;
    }
}
