/*
A Sierpinski triangle is a recursive structure of triangles within triangles.
The order of the Sierpinski triangle refers to how many levels of nested triangles to draw.
There is one outer triangle that points upward. Then we can draw a downward facing triangle within it.
This then creates three new upward facing triangles (above, left, and right of the downward one), in which we can repeat this process.
 
Write a program that takes the order as a command-line argument (e.g., java Sierpinski 2) and draws the corresponding Sierpinski triangle.
Your program should use recursion.

To draw a triangle, you may want to use the g.drawPolygon(xs, ys, n) method, where xs is a list of x-coordinates, ys a list of y-coordinates, and n the number of vertices.
 */
import java.awt.*;
import javax.swing.*;
import java.util.Arrays;

public class Sierpinski extends Canvas
{
    public int n;
    public int size;

    public void paint(Graphics g)
    {
        int x1 = 350;
        int x2 = 0;
        int x3 = 700;
        int y1 = 0;
        int y2 = 700;
        int y3 = 700;
        int [] xs = {x1, x2, x3};
        int [] ys = {y1, y2, y3};
        drawUpwards(xs, ys, n, g);
    }
    public static void drawUpwards(int[] xs, int[] ys, int n, Graphics g){
        if (n > -1){
            g.drawPolygon(xs, ys, 3);
            int[] upwardX = {xs[0], (xs[1] + xs[0])/2, (xs[0] + xs[2])/2 };
            int[] upwardY = {ys[0], (ys[1] + ys[0])/2, (ys[1] + ys[0])/2};
            drawUpwards(upwardX, upwardY, n-1, g);
            int[] leftX = {(xs[1] + xs[0])/2, xs[1], (xs[1] + xs[2])/2 };
            int[] leftY = {(ys[0] + ys[1])/2, ys[1], ys[1]};
            drawUpwards(leftX, leftY, n-1, g);
            int[] rightX = {(xs[2] + xs[0])/2, xs[2], (xs[1] + xs[2])/2 };
            int[] rightY = {(ys[0] + ys[1])/2, ys[1], ys[1]};
            drawUpwards(rightX, rightY, n-1, g);
        }
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Sierpinski drawing = new Sierpinski();
        drawing.n = Integer.parseInt(args[0]);
        drawing.size = 700;
        drawing.setSize(drawing.size, drawing.size);
        frame.add(drawing);
        frame.pack();
        frame.setVisible(true);
    }
}
