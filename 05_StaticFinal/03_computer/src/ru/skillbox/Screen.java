package ru.skillbox;

public final class Screen {

    private final int diagonal;
    private final TypeOfScreen type;
    private final int weight;

    public Screen(int diagonal, TypeOfScreen type, int weight) {
        this.diagonal = diagonal;
        this.type = type;
        this.weight = weight;
    }

    public int getDiagonal() {
        return diagonal;
    }

    public TypeOfScreen getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return  "diagonal=" + diagonal +
                ", type=" + type +
                ", weight=" + weight +
                "\n";
    }
}
