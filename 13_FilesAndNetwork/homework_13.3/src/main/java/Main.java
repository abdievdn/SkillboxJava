public class Main {

    public static final String MOVEMENT_LIST_CSV = "src/test/resources/movementList.csv";

    public static void main(String[] args) {
        Movements movements = new Movements(MOVEMENT_LIST_CSV);
        System.out.println(movements.getBankStatement());
    }
}
