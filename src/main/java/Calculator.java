import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("Enter the option you want to choose:");
        System.out.println("1. Square Root");
        System.out.println("2. Factorial (of an integer)");
        System.out.println("3. Natural logarithm");
        System.out.println("4. Power function");
        System.out.println("5. Leave");

        Scanner in = new Scanner(System.in);
        int option = in.nextInt();

        switch (option) {
            case 1:
                System.out.println("Enter the number: ");
                double x = in.nextDouble();
                System.out.println("The Square Root of "+ x + " is: "+ squareRoot(x));
                break;
            case 2:
                System.out.println("Enter the number (an integer): ");
                int y = in.nextInt();
                System.out.println("The Factorial of "+ y + " is: "+ factorial(y));
                break;
            case 3:
                System.out.println("Enter the number: ");
                double z = in.nextDouble();
                System.out.println("The Natural Logarithm of "+ z + " is: "+ nLog(z));
                break;
            case 4:
                System.out.println("Enter the base: ");
                double a = in.nextDouble();
                System.out.println("Enter the exponent: ");
                double b = in.nextDouble();
                System.out.println(a + " raised to the power of "+ b + " is: "+ power(a,b));
                break;
            case 5:
                System.out.println("Fin");
                break;
            default:
                System.out.println("Invalid Option. Please try again.");
        }
    }

    private static final Logger logger = LogManager.getLogger(Calculator.class);

    public static double squareRoot(double num) {
        logger.info("Performing square root operation on "+num);
        return Math.sqrt(num);
    }

    public static int factorial(int num) {
        logger.info("Performing factorial operation on "+num);
        if (num == 0) {
            return 1;
        }
        return num*factorial(num-1);
    }

    public static double nLog(double num) {
        logger.info("Performing natural logarithm operation on "+num);
        return Math.log(num);
    }

    public static double power(double x, double y) {
        logger.info("Performing power operation on "+ x + "," + y);
        return Math.pow(x,y);
    }
}
