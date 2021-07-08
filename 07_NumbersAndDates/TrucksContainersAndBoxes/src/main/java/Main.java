import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int totalBoxes = Integer.parseInt(boxes);

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

        if (totalBoxes > 0) {
            trucks = 1;
            containers = 1;
            System.out.println("Грузовик: " + trucks);
            System.out.println("\tКонтейнер: " + containers);
            for (int b = 1; b <= totalBoxes; b++) {
                System.out.println("\t\tЯщик: " + b);
                if (b != totalBoxes) {
                    if (containers % 12 == 0 && b % 27 == 0) {
                        trucks++;
                        System.out.println("Грузовик: " + trucks);
                    }
                    if (b % 27 == 0) {
                        containers++;
                        System.out.println("\tКонтейнер: " + containers);
                    }
                }

            }
        }

        System.out.println("Необходимо:\n" +
                "грузовиков - " + trucks + " шт.\n" +
                "контейнеров - " + containers + " шт.");

    }
}
