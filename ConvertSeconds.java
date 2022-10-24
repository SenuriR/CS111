/* 
 Write a program that takes a number of seconds as an integer command-line argument, and prints the number of years, days, hours, minutes, and seconds it's equal to.
 Assume a year is exactly 365 days. Some values may be 0.

You can assume the number of seconds will be no larger than 2,147,483,647 (the largest positive value a Java int can store).
 */
public class ConvertSeconds
{
    public static void main(String[] args)
    {
        double s = Double.parseDouble(args[0]);

        int years = (int) (s / 31536000);
        System.out.print(years + " years, ");

        double remainderOne = (double) (s % 31536000);
        int days = (int) (remainderOne / 86400);
        System.out.print(days + " days, ");

        double remainderTwo = (double) (remainderOne % 86400);
        int hours = (int) (remainderTwo / 3600);
        System.out.print(hours + " hours, ");

        double remainderThree = (double) (remainderTwo % 3600);
        int minutes = (int) (remainderThree / 60);
        System.out.print(minutes + " minutes, and ");

        double remainderFour = (double) (remainderThree % 60);
        int finalSeconds = (int) remainderFour;
        System.out.println(finalSeconds + " seconds");
    }
}

