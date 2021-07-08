import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boxes = scanner.nextInt();
        int totalBoxes = boxes;

        // TODO: вывести в консоль коробки разложенные по грузовикам и контейнерам
        // пример вывода при вводе 2
        // для отступа используйте табуляцию - \t

        /*
        Грузовик: 1
            Контейнер: 1
                Ящик: 1
                Ящик: 2
        Необходимо:
        грузовиков - 1 шт.
        контейнеров - 1 шт.
        */

        int boxNumber = 0;
        int containers = 0;
        int containerNumber = 0;
        int trucks = 0;

        // **** алгоритм с while

        long start = new Date().getTime();

        if (boxes > 0) {
            trucks = 1;
            containers = 1;
            containerNumber = 1;
            boxNumber = 1;
            System.out.println("Грузовик: " + trucks);
            System.out.println("\tКонтейнер: " + containerNumber);

            while(boxes > 0) {
                if (containerNumber > 12) {
                    trucks++;
                    containerNumber = 1;
                    System.out.println("Грузовик: " + trucks);
                    System.out.println("\tКонтейнер: " + containerNumber);
                }
                if (boxNumber > 27) {
                    containers++;
                    containerNumber++;
                    boxNumber = 1;
                    if(containerNumber > 12) continue;
                    System.out.println("\tКонтейнер: " + containerNumber);
                }
                System.out.println("\t\tЯщик: " + boxNumber);
                boxes--;
                boxNumber++;
            }
        }

        System.out.println("Необходимо:\n" +
                "грузовиков - " + trucks + " шт.\n" +
                "контейнеров - " + containers + " шт.");

        long end = new Date().getTime();

        System.out.println('\n');

        // **** алгоритм с 3-мя for
        boxes = totalBoxes;
        long start2 = new Date().getTime();

        if (boxes > 0 && boxes <= 27) {
            containerNumber = 1;
            trucks = 1;
        }
        else if (boxes > 27) {
            containerNumber = (boxes % 27) == 0 ? boxes / 27 : boxes / 27 + 1;
            trucks = (containerNumber % 12) == 0 ? containerNumber / 12 : containerNumber / 12 + 1;
        }
        containers = containerNumber;
        for (int t = 1; t <= trucks; t++) {
            System.out.println("Грузовик: " + trucks);
            for (int c = 1; c <= 12; c++) {
                if (containerNumber == 0) break;
                System.out.println("\tКонтейнер: " + c);
                containerNumber --;
                for (int b = 1; b <= 27; b++) {
                    if (boxes == 0) break;
                    System.out.println("\t\tЯщик: " + b);
                    boxes--;
                }
            }
        }

        System.out.println("Необходимо:\n" +
                "грузовиков - " + trucks + " шт.\n" +
                "контейнеров - " + containers + " шт.");

        long end2 = new Date().getTime();

        System.out.println('\n');
        System.out.println("If - While - If algorithm with counters: " + (end - start) + "ms");
        System.out.println("Math calculation and If - 3 For - If algorithm with counters: " + (end2 - start2) + "ms");




    }
}
