package ru.skillbox;

public final class RamMemory {

    private final TypeOfRamMemory type;
    private final int volume;
    private final int weight;

    public RamMemory(TypeOfRamMemory type, int volume, int weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public TypeOfRamMemory getType() {
        return type;
    }

    public int getVolume() {
        return volume;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "type=" + type +
                ", volume=" + volume +
                ", weight=" + weight +
                "\n";
    }
}
