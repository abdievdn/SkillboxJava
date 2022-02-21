import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Loader {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        ArrayList<Thread> threads = new ArrayList<>();

        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for (int regionCode = 1; regionCode < 100; regionCode++) {
            String finalPadNumber = padNumber(regionCode, 2);
            threads.add(new Thread(() -> {
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter("res/numbers".concat(finalPadNumber).concat(".txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                for (int number = 1; number < 1000; number++) {
                    StringBuilder carNumber = new StringBuilder();
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                carNumber
                                        .append(firstLetter)
                                        .append(padNumber(number, 3))
                                        .append(secondLetter)
                                        .append(thirdLetter)
                                        .append(finalPadNumber)
                                        .append('\n');
                            }
                        }
                    }
                    assert writer != null;
                    writer.write(String.valueOf(carNumber));
                }

                writer.close();
            }));
        }

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, '0');
        }

        return numberStr.toString();
    }
}
