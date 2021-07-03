package ru.skillbox;

public class Computer {

    private final String vendor;
    private final String name;

    private Processor processor;
    private RamMemory ramMemory;
    private HardDisk hardDisk;
    private Screen screen;
    private Keyboard keyboard;

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public RamMemory getRamMemory() {
        return ramMemory;
    }

    public void setRamMemory(RamMemory ramMemory) {
        this.ramMemory = ramMemory;
    }

    public HardDisk getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(HardDisk hardDisk) {
        this.hardDisk = hardDisk;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public int totalWeight() {
        return processor.getWeight() +
               ramMemory.getWeight() +
               hardDisk.getWeight() +
               screen.getWeight() +
               keyboard.getWeight();
    }

    @Override
    public String toString() {
        return "Vendor: " + vendor + ".\n" +
                "Name: " + name + ".\n" +
                " processor: " + processor +
                " ramMemory: " + ramMemory +
                " hardDisk: " + hardDisk +
                " screen: " + screen +
                " keyboard: " + keyboard;
    }
}
