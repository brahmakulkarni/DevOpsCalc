import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        int toggle = 0;
        do {
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
                    toggle = 1;
                    break;
                default:
                    System.out.println("Invalid Option. Please try again.");
            }
        } while (toggle == 0);

    }

    private static final Logger logger = LogManager.getLogger(Calculator.class);

    public static double squareRoot(double num) {
        logger.info("Performing square root operation on "+num);
        double ret =  Math.sqrt(num);
        logger.info("Result of square root operation on "+num+" is: "+ret);
        return ret;
    }

    public static int factorial(int num) {
        logger.info("Performing factorial operation on "+num);
        int ret = 1;
        for (int i = 1; i <= num; i++) {
            ret *= i;
        }
        logger.info("Result of factorial operation on "+num+" is: "+ret);
        return ret;
    }

    public static double nLog(double num) {
        logger.info("Performing natural logarithm operation on "+num);
        double ret = Math.log(num);
        logger.info("Result of natural logarithm operation on "+num+" is: "+ret);
        return ret;
    }

    public static double power(double x, double y) {
        logger.info("Performing power operation on "+ x + "," + y);
        double ret =  Math.pow(x,y);
        logger.info("Result of power operation on "+ x + "," + y + " is: " + ret);
        return ret;
    }
}
