package ru.job4j.oop;

public class Battery {
    private int load;

    public void exchange(Battery another) {
        another.load += this.load;
        this.load = 0;
    }

    public static void main(String[] args) {
        Battery battery60 = new Battery();
        battery60.load = 60;
        Battery battery30 = new Battery();
        battery30.load = 30;
        battery30.exchange(battery60);
        System.out.println("Battery30 load is " + battery30.load);
        System.out.println("Battery60 load is " + battery60.load);
    }
}
