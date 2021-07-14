import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {

    private final static Pattern PATTERN_PHONE = Pattern.compile("\\d{11}");
    private final static Pattern PATTERN_NAME = Pattern.compile("[А-Я][а-я]+");
    private final static String CONTACT_NOT_FOUND = "Контакт не найден";
    public final static String INVALID_INPUT_FORMAT = "Неверный формат ввода";

    TreeMap<String, String> phoneBook = new TreeMap<>();

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if (isMatcherNotFind(phone, PATTERN_PHONE) || isMatcherNotFind(name, PATTERN_NAME)) return;
        phoneBook.put(phone, name);
    }

    private boolean isMatcherNotFind(String matcherString, Pattern pattern) {
        Matcher matcher = pattern.matcher(matcherString);
        if (!matcher.find()) {
            System.out.println(INVALID_INPUT_FORMAT);
            return true;
        }
        return false;
    }

    public String getNameByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        if (isMatcherNotFind(phone, PATTERN_PHONE)) return "";
        for (String key : phoneBook.keySet()) {
            if (phoneBook.containsKey(phone))
                return phoneBook.get(key) + " - " + key;
        }
        System.out.println(CONTACT_NOT_FOUND);
        return "";
    }

    public Set<String> getPhonesByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        TreeSet<String> contactsByName = new TreeSet<>();
        if (isMatcherNotFind(name, PATTERN_NAME)) return contactsByName;
        for (String key : phoneBook.keySet()) {
            if (phoneBook.get(key).equals(name)) {
                contactsByName.add(name + " - " + key);
            }
        }
        if (contactsByName.isEmpty()) System.out.println("Контакт не найден");
        return contactsByName;
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        TreeSet<String> allContacts = new TreeSet<>();
        String phones = "";
        String name = "";
        for (String key : phoneBook.keySet()) {
            name = phoneBook.get(key);
            phones = String.join(", ", getPhonesByName(name));
            phones = phones.replaceAll(name + " - ", "");
            allContacts.add(name + " - " + phones);
        }
        return allContacts;
    }

}