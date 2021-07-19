public class Main {
    public static void main(String[] args) {

        Client physicalPerson = new PhysicalPerson();
        Client individualBusinessman = new IndividualBusinessman();
        Client legalPerson = new LegalPerson();

        physicalPerson.put(1000);
        System.out.println("Physical person after 1000 put amount: " + physicalPerson.getAmount());
        individualBusinessman.put(100);
        System.out.println("Individual person after 100 put amount: " + individualBusinessman.getAmount());
        legalPerson.put(5000);
        System.out.println("Legal person after 5000 put amount: " + legalPerson.getAmount());

        physicalPerson.take(1000);
        System.out.println("Physical person after 1000 take amount: " + physicalPerson.getAmount());
        individualBusinessman.take(100);
        System.out.println("Individual person after 100 take amount: " + individualBusinessman.getAmount());
        individualBusinessman.take(individualBusinessman.getAmount());
        System.out.println("Individual person after all balance take amount: " + individualBusinessman.getAmount());
        individualBusinessman.put(1000);
        System.out.println("Individual person after 1000 put amount: " + individualBusinessman.getAmount());
        legalPerson.take(5000);
        System.out.println("Legal person after 5000 take amount: " + legalPerson.getAmount());
        legalPerson.take(1000);
        System.out.println("Legal person after 1000 take amount: " + legalPerson.getAmount());
    }
}
