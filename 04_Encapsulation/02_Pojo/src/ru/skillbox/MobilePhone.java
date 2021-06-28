package ru.skillbox;

public class MobilePhone {

    private String phoneManufacturer;
    private String countryOfProduction;
    private String phoneOS;
    private int operativeMemorySize;
    private int diskStorageSize;

    public MobilePhone(String phoneManufacturer, String countryOfProduction) {
        this.phoneManufacturer = phoneManufacturer;
        this.countryOfProduction = countryOfProduction;
    }

    public String getPhoneManufacturer() {
        return phoneManufacturer;
    }

    public void setPhoneManufacturer(String phoneManufacturer) {
        this.phoneManufacturer = phoneManufacturer;
    }

    public String getCountryOfProduction() {
        return countryOfProduction;
    }

    public void setCountryOfProduction(String countryOfProduction) {
        this.countryOfProduction = countryOfProduction;
    }

    public String getPhoneOS() {
        return phoneOS;
    }

    public void setPhoneOS(String phoneOS) {
        this.phoneOS = phoneOS;
    }

    public int getOperativeMemorySize() {
        return operativeMemorySize;
    }

    public void setOperativeMemorySize(int operativeMemorySize) {
        this.operativeMemorySize = operativeMemorySize;
    }

    public int getDiskStorageSize() {
        return diskStorageSize;
    }

    public void setDiskStorageSize(int diskStorageSize) {
        this.diskStorageSize = diskStorageSize;
    }
}
