import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    public static final String WRONG_FORMAT = " wrong format!";
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;
        final Pattern PHONE_PATTERN = Pattern.compile("\\+\\d{11}");
        final Pattern EMAIL_PATTERN = Pattern.compile("\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*\\.\\w{2,4}");

        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException("Command input" + WRONG_FORMAT);
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        if (!EMAIL_PATTERN.matcher(components[2]).find()) {
            throw new IllegalArgumentException("Email input " + WRONG_FORMAT);
        }
        if (!PHONE_PATTERN.matcher(components[3]).find()) {
            throw new IllegalArgumentException("Phone input" + WRONG_FORMAT);
        }
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));


    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}