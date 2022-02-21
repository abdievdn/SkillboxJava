import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();


        ArrayList<Thread> threads = new ArrayList<>();

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for (int regionCode = 1; regionCode < 100; regionCode++) {
            int finalRegionCode = regionCode;
            threads.add(new Thread(() -> {
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter("res/numbers".concat(padNumber(finalRegionCode, 2)).concat(".txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                for (int number = 1; number < 1000; number++) {
                    StringBuffer carNumber = new StringBuffer();
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                carNumber
                                        .append(firstLetter)
                                        .append(padNumber(number, 3))
                                        .append(secondLetter)
                                        .append(thirdLetter)
                                        .append(padNumber(finalRegionCode, 2))
                                        .append('\n');
                            }
                        }
                    }
                    writer.write(String.valueOf(carNumber));
                }

                writer.flush();
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
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }

        return numberStr;
    }
}
