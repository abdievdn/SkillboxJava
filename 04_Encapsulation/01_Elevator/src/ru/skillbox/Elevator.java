package ru.skillbox;

public class Elevator {

    private int currentFloor = 1;
    private int minFloor;
    private int maxFloor;

    public Elevator (int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveDown() {
        currentFloor = currentFloor > minFloor ? --currentFloor : currentFloor;
        System.out.println("Спускаемся вниз: " + currentFloor);
    }

    public void moveUp() {
        currentFloor = currentFloor < maxFloor ? ++currentFloor : currentFloor;
        System.out.println("Поднимаемся вверх: " + currentFloor);
    }

    public void move(int floor) {
        if (floor >= minFloor && floor <= maxFloor) {
            System.out.println("Текущий этаж: " + currentFloor);
            while (currentFloor != floor) {
                if (currentFloor < floor) moveUp();
                if (currentFloor > floor) moveDown();
            }
            System.out.println("Лифт на указанном этаже.\n");
        }
        else System.out.println("Указанного этажа не существует!\n");
    }
}
