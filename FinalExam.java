/*
Suppose the grade for the course is computed as:

0.5 * a + 0.15 * e1 + 0.15 * e2 + 0.15 * f + 0.05 * r

where a is the average assignment score, e1 and e2 are scores for exam 1 and 2, respectively,
f is the final exam score, and r is the recitation score, all integers in the range 0 to 100.

Given values for the average assignment score, exam 1, exam 2, and recitations (in that order, on the command line), compute what score you'd need on the final to get an A in the course (a total score of at least 90).
You don't need to worry about minor rounding errors due to floating-point arithmetic.
Even if it's impossible to get an A (i.e., the final score must be over 100), you should still print the final score needed.
*/

public class FinalExam
{
    public static void main(String[] args)
    {
       // declare/initialize assignment, exam, and recitation scores
       double a = Double.parseDouble(args[0]);
       double e1 = Double.parseDouble(args[1]);
       double e2 = Double.parseDouble(args[2]);
       double r = Double.parseDouble(args[3]);
       
       // weighted scores of known assessments
       double assignment = 0.5 * a;
       double examOne = 0.15 * e1;
       double examTwo = 0.15 * e2;
       double recitation = 0.05 * r;

       // sum of weighted exam scores
       double sumOfKnown = assignment + examOne + examTwo + recitation;
       // System.out.println(sumOfKnown);

       // 90 - sum
       double difference = 90 - sumOfKnown;
       //System.out.println(difference);

       // difference divided by weight of final exam (0.15)
       double finalExamScore = difference / 0.15;
       System.out.println(finalExamScore);
    }
    
    }        