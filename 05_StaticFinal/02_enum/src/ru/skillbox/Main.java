package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        ArithmeticCalculator numbers = new ArithmeticCalculator(4, 5);
        System.out.println(numbers.calculate(Operation.ADD));
        System.out.println(numbers.calculate(Operation.SUBTRACT));
        System.out.println(numbers.calculate(Operation.MULTIPLY));
    }
}
