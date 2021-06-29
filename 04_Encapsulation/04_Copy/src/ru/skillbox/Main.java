package ru.skillbox;

public class Main {

    public static void main(String[] args) {

        Cargo cargo1 = new Cargo(new Dimensions(120, 40, 120), 60,
                "Moscow, Petrovsky str. 12", true, "2273365RE", false);
        Cargo cargo2 = new Cargo(new Dimensions(20, 40, 30), 10,
                "Piranha, Moskovitz str. 31", false, "3274365ME", true);

        System.out.println(cargo1);
        System.out.println(cargo2);

        Cargo cargo3 = cargo1.setDeliveryAddress("Vilnius, Starry Kumiko str. 54");
        Cargo cargo4 = cargo2.setDimensions(new Dimensions(22, 60, 15));
        Cargo cargo5 = cargo4.setWeight(20);

        System.out.println(cargo3);
        System.out.println(cargo4);
        System.out.println(cargo5);
    }
}
