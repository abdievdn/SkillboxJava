public class Main {

    public static void main(String[] args) {
        System.out.println("Basket TESTS\n------");
        Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.print("Milk");
        System.out.println("Total price: " + basket.getTotalPrice());
        System.out.println("Total weight: " + basket.getTotalWeight());
        System.out.println();

        Basket basket1 = new Basket();
        basket1.add("Beer", 80, 500.00);
        basket1.add("Chips", 60, 300.00);
        basket1.print("Basket #1");
        System.out.println("Total price: " + basket1.getTotalPrice());
        System.out.println("Total weight: " + basket1.getTotalWeight());
        System.out.println();

        Basket basket2 = new Basket();
        basket2.add("Pen", 10, 5, 20.00);
        basket2.add("Glue", 25, 2, 40.00);
        basket2.print("Basket #2");
        System.out.println("Total price: " + basket2.getTotalPrice());
        System.out.println("Total weight: " + basket2.getTotalWeight());
        System.out.println();

        System.out.println("Arithmetic TESTS\n------");
        Arithmetic numbers = new Arithmetic(5, 8);
        System.out.println(numbers.sum());
        System.out.println(numbers.difference());
        System.out.println(numbers.multiply());
        System.out.println(numbers.max());
        System.out.println(numbers.min());
        System.out.println();

        System.out.println("Printer TESTS\n------");
        Printer printer = new Printer();
        printer.append("just text");
        printer.append("some text", "Document Title");
        printer.append("another text", "Head Title", 5);
        System.out.println("Documents in queue: " + printer.getDocumentsCount());
        System.out.println("Pages in queue: " + printer.getPagesCount());
        printer.print();
        System.out.println("Documents in queue: " + printer.getDocumentsCount());
        System.out.println("Pages in queue: " + printer.getPagesCount());
        printer.totalPrintedPagesAndDocs();
        printer.append("And he was running almost half of his life.", "Run Forest Run!",8);
        System.out.println("Documents in queue: " + printer.getDocumentsCount());
        System.out.println("Pages in queue: " + printer.getPagesCount());
        printer.print();
        System.out.println("Documents in queue: " + printer.getDocumentsCount());
        System.out.println("Pages in queue: " + printer.getPagesCount());
        printer.totalPrintedPagesAndDocs();

    }
}
