import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("Enter the option you want to choose:");
        System.out.println("1. Square Root");

        Scanner in = new Scanner(System.in);
        int option = in.nextInt();

        switch (option) {
            case 1:
                System.out.println("Enter the number: ");
                double x = in.nextDouble();
                System.out.println("The Square Root of "+ x + " is: "+ squareRoot(x));
                break;
            default:
                System.out.println("Invalid Option");
        }
    }

    public static double squareRoot(double num) {
        return Math.sqrt(num);
    }
}
