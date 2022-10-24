/* Given 2 integers on the command line, compute their sum, difference, product, quotient, remainder, and average.

You can assume the second number won't be 0 (or it's okay if your program crashes when it is 0).
*/

public class Calculations
{
    public static void main (String[] args)
    {
        //initialize variables
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);

        //sum
        int sum = x + y;
        System.out.println("Sum: " + sum);

        //difference
        int diff = x - y;
        System.out.println("Difference: " + diff);

        //product
        int product = x * y;
        System.out.println("Product: " + product);

        //quotient - convert to double
        double quotient = (double) x / (double) y;
        System.out.println("Quotient: " + quotient);

        //remainder
        int remainder = x % y;
        System.out.println("Remainder: " + remainder);

        //average
        double avg = (double) sum / 2;
        System.out.println("Average: " + avg);

    }
}