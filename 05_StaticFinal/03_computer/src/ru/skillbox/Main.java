package ru.skillbox;

public class Main {

    public static void main(String[] args) {

        Computer computer = new Computer("Lenovo", "Black Box");
        computer.setProcessor(new Processor(3200, 8, "Intel", 28));
        computer.setRamMemory(new RamMemory(TypeOfRamMemory.DDR4, 16, 18));
        computer.setHardDisk(new HardDisk(TypeOfHardDisk.SDD, 1000, 76));
        computer.setScreen(new Screen(32, TypeOfScreen.VA, 6300));
        computer.setKeyboard(new Keyboard(TypeOfKeyboard.MECHANICAL, true, 625));

        System.out.println(computer);
        System.out.println("Total weight: " + computer.totalWeight());
    }
}
