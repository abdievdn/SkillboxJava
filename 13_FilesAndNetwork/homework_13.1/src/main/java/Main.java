import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        for (; ; ) {
            Scanner input = new Scanner(System.in);
            System.out.println("Введите путь к директории: ");
            String path = input.nextLine();
            System.out.println("Размер папки " + path + " cоставляет " + printSize(FileUtils.calculateFolderSize(path)) + '\n');
        }
    }

    private static String printSize(Long size) {
        if (size <= 0) return "0";
        String[] units = {"б", "Кб", "Мб", "Гб"};
        int unitsGroup = (int) (Math.log10(size)/Math.log10(1024));
        return String.format("%.1f %s", (size/Math.pow(1024, unitsGroup)), units[unitsGroup]);
    }
}
