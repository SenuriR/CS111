/* 
GOAL:
Write an interpreter for an assembly-like language running on a simplified CPU.

HARDWARE:
The CPU we're simulating has 4 registers (small bits of memory capable of storing a single integer). The registers are denoted ra, rb, rc, and rd.

The integers our machine uses are signed 32-bit values, so the range of values they can represent is roughly -2 billion to 2 billion. You can assume that no computations will produce values outside these ranges.

Each time the interpreter executes a program, all registers are initialized to 0.

The filename of the program to run will be given to the interpreter as a command-line argument (e.g., java Interpreter test1.txt).
You can use Interpreter.java to get started, which reads the input program and simply prints each line of the program.

You can assume the input program's syntax will be correct and that all tokens on a line of input will be separated by exactly one space.

INSTRUCTION SET:
Each program is a sequence of instructions from the following instruction set, where r1, r2, and r3 represent aribtrary registers, n represents an integer constant, and cond represents a boolean condition (described below).
add r1 r2 r3:	Adds r1 and r2, stores result in r3
sub r1 r2 r3:	Subtracts r2 from r1, stores result in r3
mul r1 r2 r3:	Multiplies r1 and r2, stores result in r3
div r1 r2 r3:	Divides r1 by r2, stores result in r3. Any remainder is discarded.
incr r1:	    Increments r1 by one
decr r1:	    Decrements r1 by one
copy r1 r2:	    Copies the value in r1 to r2
load n r1:	    Stores the integer constant n in r1
read r1:	    Reads an integer from standard input, stores result in r1
print r1:	    Prints the value stored in r1 to the screen (with a newline)
goto n:	        Continue program execution from line n (0-indexed)
if cond goto n:	If cond evaluates to true, continue execution from line n (0-indexed). Otherwise continue execution with the next statement.


GO-TO AND IF CONDITIONS:
The line number in a goto or if statement refers to the 0-indexed line of the program. I.e., goto 0 would go back to the beginning of the program, and goto 3 on line 3 would be an infinite loop. If the requested line number is larger than the last program line's index, the program halts.

A condition must be of the form x op y, where x and y can be either registers or constants, and op is one of the following operators:

>	Greater than
>=	Greater than or equal to
<	Less than
<=	Less than or equal to
==	Equal to
!=	Not equal to

Note that the "body" of an if statement must be a goto. They can't contain any other instruction. For example, if r1 > 0 print r1 is not valid.
 */


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class Interpreter
{
    public void readProgramRunOne(String filename)
    {
        // You can ignore the "try" and "catch" parts. These are just
        // to deal with I/O errors (e.g., file not found).
        try {
            Scanner fileIn = new Scanner(new File(filename)); // reading the file
            // count how many lines of code the file has
            int lineCount = 0; // start off at 0
            while(fileIn.hasNextLine()){ // as long as we have next line to count...
                String line = fileIn.nextLine();
                lineCount++; // increment lineCount by 1;
                // System.out.println(line);
            }
            // System.out.println("There are " + lineCount + " lines in this file");
            readProgramRunTwo(filename, lineCount);
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readProgramRunTwo(String filename, int lineCount)
    {
        try {
            Scanner fileIn = new Scanner(new File(filename));
            while (fileIn.hasNextLine()) {

            // create an array of strings, where every line is stored in a new index/slot
            String[] stringArr = new String[lineCount]; // the length of the array will be the amount of lines the file has
            for(int lineIndex = 0; lineIndex < stringArr.length; lineIndex++){
                String lineSave = fileIn.nextLine();
                stringArr[lineIndex] = lineSave;
            }

            compute(stringArr); // where we will do all of our instructions
            }
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    public static void compute(String[] stringArr)
    {
        int[] registers = new int[4];
        int x = 0;
        int y = 0;

        for(int i = 0; i < stringArr.length; i++) // i corresponds to the line index, i is our "program counter"
        {
            String currLine = stringArr[i];
            String[] s = currLine.split(" ");
            String firstWord = s[0];
            switch(firstWord){
                case "add":
                    add(s, registers);
                    break;
                case "sub":
                    sub(s, registers);
                    break;
                case "div":
                    div(s, registers);
                    break;
                case "mul":
                    mul(s, registers);
                    break;
                case "decr":
                    decr(s, registers);
                    break;
                case "incr":
                    incr(s, registers);
                    break;
                case "copy":
                    copy(s, registers);
                    break;
                case "load":
                    load(s, registers);
                    break;
                case "read":
                    read(s, registers);
                    break;
                case "print":
                    print(s, registers);
                    break;
                case "if":
                    boolean xIsAnInt = true;
                    boolean yIsAnInt = true;

                    if(s[1].equals("ra")){
                        x = registers[0];
                        xIsAnInt = false;
                    }   

                    if(s[1].equals("rb")){
                        x = registers[1];
                        xIsAnInt = false;
                    }

                    if(s[1].equals("rc")){
                        x = registers[2];
                        xIsAnInt = false;
                    }

                    if(s[1].equals("rd")){
                        x = registers[3];
                        xIsAnInt = false;
                    }

                    if(xIsAnInt)
                        x = Integer.parseInt(s[1]);
                    
                    if(s[3].equals("ra")){
                        y = registers[0];
                        yIsAnInt = false;
                    }

                    if(s[3].equals("rb")){
                        y = registers[1];
                        yIsAnInt = false;
                    }

                    if(s[3].equals("rc")){
                        y = registers[2];
                        yIsAnInt = false;
                    }

                    if(s[3].equals("rd")){
                        y = registers[3];
                        yIsAnInt = false;
                    }

                    if(yIsAnInt)
                        y = Integer.parseInt(s[3]);

                    String op = s[2];
                    if (op.equals(">")){
                        if (x > y)
                            i = Integer.parseInt(s[5]) - 1;
                    }
                    if (op.equals(">="))
                        if (x >= y)
                            i = Integer.parseInt(s[5]) - 1;
                    if (op.equals("<"))
                        if (x < y)
                            i = Integer.parseInt(s[5]) - 1;
                    if (op.equals("<="))
                        if (x <= y)
                            i = Integer.parseInt(s[5]) - 1;
                    if (op.equals("=="))
                        if (x == y)
                            i = Integer.parseInt(s[5]) - 1;
                    if (op.equals("!="))
                        if (x != y)
                            i = Integer.parseInt(s[5]) - 1;
                    break;
                case "goto":
                    // System.out.println("In goto");
                    i = Integer.parseInt(s[1]) - 1;
                    break;
            }
        }
    }

    public static void add(String[] s, int[] registers)
    {
        boolean addIntX = true;
        boolean addIntY = true;
        int xAdd = 0;
        int yAdd = 0;
        int zAdd = 0;
    // X CODE
        if(s[1].equals("ra")){
            addIntX = false;
            xAdd = registers[0];
        }
        if(s[1].equals("rb")){
            addIntX = false;
            xAdd = registers[1];
        }
        if(s[1].equals("rc")){
            addIntX = false;
            xAdd = registers[2];
        }
        if(s[1].equals("rd")){
            addIntX = false;
            xAdd = registers[3];
        }
        if(addIntX){
            xAdd = Integer.parseInt(s[1]);
        }
    
    // Y CODE
        if(s[2].equals("ra")){
            addIntY = false;
            yAdd = registers[0];
        }
        if(s[2].equals("rb")){
            addIntY = false;
            yAdd = registers[1];
        }
        if(s[2].equals("rc")){
            addIntY = false;
            yAdd = registers[2];
        }
        if(s[2].equals("rd")){
            addIntY = false;
            yAdd = registers[3];
        }
        if(addIntY){
            yAdd = Integer.parseInt(s[1]);
        }
    
    // ADD
        zAdd = xAdd + yAdd;
        if(s[3].equals("ra")){
            registers[0] = zAdd;
        }
        if(s[3].equals("rb")){
            registers[1] = zAdd;
        }
        if(s[3].equals("rc")){
            registers[2] = zAdd;
        }
        if(s[3].equals("rd")){
            registers[3] = zAdd;
        }
    }

    public static void sub(String[] s, int[] registers)
    {
        boolean subIntX = true;
        boolean subIntY = true;
        int xSub = 0;
        int ySub = 0;
        int zSub = 0;
    // X CODE
        if(s[1].equals("ra")){
            subIntX = false;
            xSub = registers[0];
        }
        if(s[1].equals("rb")){
            subIntX = false;
            xSub = registers[1];
        }
        if(s[1].equals("rc")){
            subIntX = false;
            xSub = registers[2];
        }
        if(s[1].equals("rd")){
            subIntX = false;
            xSub = registers[3];
        }
        if(subIntX){
            xSub = Integer.parseInt(s[1]);
        }
    
    // Y CODE
        if(s[2].equals("ra")){
            subIntY = false;
            ySub = registers[0];
        }
        if(s[2].equals("rb")){
            subIntY = false;
            ySub = registers[1];
        }
        if(s[2].equals("rc")){
            subIntY = false;
            ySub = registers[2];
        }
        if(s[2].equals("rd")){
            subIntY = false;
            ySub = registers[3];
        }
        if(subIntY){
            ySub = Integer.parseInt(s[1]);
        }
    
    // SUB
        zSub = xSub - ySub;
        if(s[3].equals("ra")){
            registers[0] = zSub;
        }
        if(s[3].equals("rb")){
            registers[1] = zSub;
        }
        if(s[3].equals("rc")){
            registers[2] = zSub;
        }
        if(s[3].equals("rd")){
            registers[3] = zSub;
        }
    }

    public static void mul(String[] s, int[] registers)
    {
        boolean mulIntX = true;
        boolean mulIntY = true;
        int xMul = 0;
        int yMul = 0;
        int zMul = 0;
    // X CODE
        if(s[1].equals("ra")){
            mulIntX = false;
            xMul = registers[0];
        }
        if(s[1].equals("rb")){
            mulIntX = false;
            xMul = registers[1];
        }
        if(s[1].equals("rc")){
            mulIntX = false;
            xMul = registers[2];
        }
        if(s[1].equals("rd")){
            mulIntX = false;
            xMul = registers[3];
        }
        if(mulIntX){
            xMul = Integer.parseInt(s[1]);
        }
    
    // Y CODE
        if(s[2].equals("ra")){
            mulIntY = false;
            yMul = registers[0];
        }
        if(s[2].equals("rb")){
            mulIntY = false;
            yMul = registers[1];
        }
        if(s[2].equals("rc")){
            mulIntY = false;
            yMul = registers[2];
        }
        if(s[2].equals("rd")){
            mulIntY = false;
            yMul = registers[3];
        }
        if(mulIntY){
            yMul = Integer.parseInt(s[1]);
        }
    
    // MUL
        zMul = xMul * yMul;
        if(s[3].equals("ra")){
            registers[0] = zMul;
        }
        if(s[3].equals("rb")){
            registers[1] = zMul;
        }
        if(s[3].equals("rc")){
            registers[2] = zMul;
        }
        if(s[3].equals("rd")){
            registers[3] = zMul;
        }
    }

    public static void div(String[] s, int[] registers)
    {
        boolean divIntX = true;
        boolean divIntY = true;
        int xDiv = 0;
        int yDiv = 0;
        int zDiv = 0;
    // X CODE
        if(s[1].equals("ra")){
            divIntX = false;
            xDiv = registers[0];
        }
        if(s[1].equals("rb")){
            divIntX = false;
            xDiv = registers[1];
        }
        if(s[1].equals("rc")){
            divIntX = false;
            xDiv = registers[2];
        }
        if(s[1].equals("rd")){
            divIntX = false;
            xDiv = registers[3];
        }
        if(divIntX){
            xDiv = Integer.parseInt(s[1]);
        }
    
    // Y CODE
        if(s[2].equals("ra")){
            divIntY = false;
            yDiv = registers[0];
        }
        if(s[2].equals("rb")){
            divIntY = false;
            yDiv = registers[1];
        }
        if(s[2].equals("rc")){
            divIntY = false;
            yDiv = registers[2];
        }
        if(s[2].equals("rd")){
            divIntY = false;
            yDiv = registers[3];
        }
        if(divIntY){
            yDiv = Integer.parseInt(s[1]);
        }
    
    // DIV
        zDiv = (int) xDiv / yDiv;
        if(s[3].equals("ra")){
            registers[0] = zDiv;
        }
        if(s[3].equals("rb")){
            registers[1] = zDiv;
        }
        if(s[3].equals("rc")){
            registers[2] = zDiv;
        }
        if(s[3].equals("rd")){
            registers[3] = zDiv;
        }
    }

    public static void decr(String[] s, int[] registers)
    {
        if(s[1].equals("ra"))
            registers[0]--;
        if(s[1].equals("rb"))
            registers[1]--;
        if(s[1].equals("rc"))
            registers[2]--;
        if(s[1].equals("rd"))
            registers[3]--;
    }

    public static void incr(String[] s, int[] registers)
    {
        if(s[1].equals("ra"))
            registers[0]++;
        if(s[1].equals("rb"))
            registers[1]++;
        if(s[1].equals("rc"))
            registers[2]++;
        if(s[1].equals("rd"))
            registers[3]++;
    }

    public static void copy(String[] s, int[] registers)
    {
        int temp = 0;
        boolean intFill = true;
        if(s[1].equals("ra")){
            intFill = false;
            temp = registers[0];
        }
        if(s[1].equals("rb")){
            intFill = false;
            temp = registers[1];
        }
        if(s[1].equals("rc")){
            // System.out.println("Copy rc");
            intFill = false;
            temp = registers[2];
        }
        if(s[1].equals("rd")){
            // System.out.println("Copy rd");
            intFill = false;
            temp = registers[3];
        }
        if(intFill)
            temp = Integer.parseInt(s[1]);

        if(s[2].equals("ra"))
            registers[0] = temp;
        if(s[2].equals("rb")){
            // System.out.println("Overwrite rb");
            registers[1] = temp;
        }
        if(s[2].equals("rc"))
            registers[2] = temp;
    }

    public static void load(String[] s, int[] registers)
    {
        registers[3] = Integer.parseInt(s[1]);
        if(s[2].equals("ra"))
            registers[0] = registers[3];
        if(s[2].equals("rb"))
            registers[1] = registers[3];
        if(s[2].equals("rc"))
            registers[2] = registers[3];
    }

    public static void read(String[] s, int[] registers)
    {
        Scanner in = new Scanner(System.in);
        if(s[1].equals("ra"))            
            registers[0] = in.nextInt();
        if(s[1].equals("rb"))
            registers[1] = in.nextInt();
        if(s[1].equals("rc"))
            registers[2] = in.nextInt();
        if(s[1].equals("rd"))
            registers[3] = in.nextInt();
    }

    public static void print(String[] s, int[] registers)
    {
        boolean printIsAnInt = true;
        if(s[1].equals("ra")){
            printIsAnInt = false;
            System.out.println(registers[0]);
        }
            
        if(s[1].equals("rb")){
            printIsAnInt = false;
            System.out.println(registers[1]);
        }
            
        if(s[1].equals("rc")){
            printIsAnInt = false;
            System.out.println(registers[2]);
        }
            
        if(s[1].equals("rd")){
            printIsAnInt = false;
            System.out.println(registers[3]);
        }
            
        if(printIsAnInt){
            System.out.println(s[1]);
        }
    }

    
    public static void main(String[] args)
    {
        if (args.length != 1) {
            System.out.println("Usage: java Interpreter <filename>");
            return;
        }

        Interpreter interpreter = new Interpreter();
        interpreter.readProgramRunOne(args[0]);
    }
}