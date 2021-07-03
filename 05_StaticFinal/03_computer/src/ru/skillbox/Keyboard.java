package ru.skillbox;

public final class Keyboard {

    private final TypeOfKeyboard type;
    private final boolean backlight;
    private final int weight;

    public Keyboard(TypeOfKeyboard type, boolean backlight, int weight) {
        this.type = type;
        this.backlight = backlight;
        this.weight = weight;
    }

    public TypeOfKeyboard getType() {
        return type;
    }

    public boolean isBacklight() {
        return backlight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "type=" + type +
                ", backlight=" + backlight +
                ", weight=" + weight +
                "\n";
    }
}
