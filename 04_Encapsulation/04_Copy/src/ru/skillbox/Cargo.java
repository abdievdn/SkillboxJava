package ru.skillbox;

public class Cargo {

    private final Dimensions dimensions;
    private final int weight;
    private final String deliveryAddress;
    private final boolean canFlip;
    private final String registrationNumber;
    private final boolean fragile;

    public Cargo(Dimensions dimensions, int weight, String deliveryAddress, boolean canFlip, String registrationNumber, boolean fragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.canFlip = canFlip;
        this.registrationNumber = registrationNumber;
        this.fragile = fragile;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public int getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean isCanFlip() {
        return canFlip;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean isFragile() {
        return fragile;
    }

    public Cargo setDeliveryAddress(String deliveryAddress) {
        return new Cargo(dimensions, weight, deliveryAddress, canFlip, registrationNumber, fragile);
    }

    public Cargo setDimensions(Dimensions dimensions) {
        return new Cargo(dimensions, weight, deliveryAddress, canFlip, registrationNumber, fragile);
    }

    public Cargo setWeight(int weight) {
        return new Cargo(dimensions, weight, deliveryAddress, canFlip, registrationNumber, fragile);
    }

    @Override
    public String toString() {
        return "Cargo\n" +
                " dimensions: " + dimensions +
                ",\n weight=" + weight +
                ",\n deliveryAddress='" + deliveryAddress + '\'' +
                ",\n canFlip=" + canFlip +
                ",\n registrationNumber='" + registrationNumber + '\'' +
                ",\n fragile=" + fragile +
                "\n";
    }
}
