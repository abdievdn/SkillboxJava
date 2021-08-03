import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Copy files.");
            System.out.print("Input source directory: ");
            String source = input.nextLine();
            System.out.print("Input destination directory: ");
            String dest = input.nextLine();
            FileUtils.copyFolder(source, dest);
            System.out.println("Completed!");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
