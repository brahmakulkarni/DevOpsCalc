import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger logger = LogManager.getLogger(Calculator.class);

    public static double squareRoot(double num) {
        logger.info("Performing square root operation on "+num);
        return Math.sqrt(num);
    }
}
