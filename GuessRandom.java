/*
Write a program that generates a random integer from 1 to 10 (inclusive) and asks the user to guess it.
Then tell the user what the number was and how far they were from it.

Note that the distance they were off by should always be non-negative (i.e., 0 or positive),
whether they guessed higher or lower than the actual number.
 */
import java.util.Scanner;

public class GuessRandom
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("I'm thinking of a number from 1 to 10. Your guess?");
        
        int guess = in.nextInt();

        double randomNum = Math.random();

        int num = (int)(10 * randomNum)+ 1;

        int diff = guess - num;

        int x = Math.abs(diff);

        System.out.println("It was " + num + ". You were off by " + x + ".");
    }
}