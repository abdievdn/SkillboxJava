public class Basket {

    private static int count = 0;
    private static int priceCount = 0;
    private static int itemsCount = 0;

    private String items = "";
    private int totalPrice = 0;
    private int limit;
    private double totalWeight = 0;

    public Basket() {
        increaseCount(1);
        items = "Список товаров:\n";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
        increasePriceCount(totalPrice);
        increaseItemsCount(1);
    }

    public static int getCount() {
        return count;
    }

    public static int getPriceCount() {
        return priceCount;
    }

    public static int getItemsCount() {
        return itemsCount;
    }

    public static void increaseCount(int count) {
        Basket.count += count;
    }

    public static void increasePriceCount(int priceCount) {
        Basket.priceCount += priceCount;
    }

    public static void increaseItemsCount(int itemsCount) {
        Basket.itemsCount += itemsCount;
    }

    public static double totalAverageItemsPrice() {
        return (double) Basket.priceCount / Basket.itemsCount;
    }

    public static double totalAverageBasketsPrice() {
        return (double) Basket.priceCount / Basket.count;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, double weight) {
        add(name, price, 1);
        totalWeight += weight;
    }

    public void add(String name, int price, int count, double weight) {
        add(name, price, count);
        totalWeight += weight * count;
    }

    public void add(String name, int price, int itemsCount) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + itemsCount * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
            itemsCount + " шт. - " + price;
        totalPrice += itemsCount * price;
        increasePriceCount(itemsCount * price);
        increaseItemsCount(itemsCount);
    }

    public void clear() {
        items = "";
        totalPrice = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }
}
