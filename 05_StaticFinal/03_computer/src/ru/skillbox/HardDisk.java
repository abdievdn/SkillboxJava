package ru.skillbox;

public final class HardDisk {

    private final TypeOfHardDisk type;
    private final int volume;
    private final int weight;

    public HardDisk(TypeOfHardDisk type, int volume, int weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public TypeOfHardDisk getType() {
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
