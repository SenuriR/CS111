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