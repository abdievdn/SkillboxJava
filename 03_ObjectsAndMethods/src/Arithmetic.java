public class Arithmetic {

    private int number1;
    private int number2;

    public Arithmetic(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public int sum() {
        return number1 + number2;
    }

    public int difference() {
        return number1 - number2;
    }

    public int multiply() {
        return number1 * number2;
    }

    public int average() {
        return number1 * number2 / 2;
    }

    public int max() {
        return Math.max(number1, number2);
    }

    public int min() {
        return Math.min(number1, number2);
    }
}
