import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook();
        String phone = "";
        String name = "";
        Scanner scanner = new Scanner(System.in);

        for (;;) {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine();
            if (input.equals("LIST")) {
                for(String contact : phoneBook.getAllContacts())
                    System.out.println(contact);
                continue;
            }
            if (!input.isEmpty() && Character.isDigit(input.charAt(0))) {
                phone = input;
                name = scanner.nextLine();
            }
            else {
                name = input;
                phone = scanner.nextLine();
            }
            phoneBook.addContact(phone, name);
        }
    }
}
