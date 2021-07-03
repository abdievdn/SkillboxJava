package ru.skillbox;

public class ArithmeticCalculator {

    private int num1;
    private int num2;

    public ArithmeticCalculator(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public int calculate(Operation operation) {
            if(operation == Operation.ADD) return num1 + num2;
            if(operation == Operation.SUBTRACT) return num1 - num2;
            if(operation == Operation.MULTIPLY) return num1 * num2;
            return 0;
    }
}
