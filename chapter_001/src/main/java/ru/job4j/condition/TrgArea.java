package job4j.condition;

public class TrgArea {
    public static double area(double a, double b, double c) {
        final double p = (a + b + c) / 2;
        final double area = Math.sqrt(p * ((p - a) * (p - b) * (p - c)));

        return area;
    }

    public static void main(String[] args) {
        final double rsl = area(2, 2, 2);
        System.out.println("area (2, 2, 2) = " + rsl);
    }
}
