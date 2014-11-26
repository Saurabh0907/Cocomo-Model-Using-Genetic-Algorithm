/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cocomoga;

/**
 *
 * @author saurabhgarg
 */
import java.io.*;
import java.util.*;

public class GetPopulation
{
private double mutationRatio,crossoverRatio;
private int count = 0;
public double a,b,c,d;
ArrayList<Double> IG = new ArrayList<>();
Scanner s = new Scanner(System.in);

GetPopulation(double mutationRatio, double crossoverRatio,double a,double b,double c,double d,int type) throws FileNotFoundException, IOException
        {
        this.mutationRatio = mutationRatio;
        this.crossoverRatio = crossoverRatio;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        double effort,tdev;

        double val;

        Scanner input = new Scanner(System.in);
        File file = new File("src/cocomoga/input.txt");
        input = new Scanner(file);

        Writer wr = new FileWriter("src/cocomoga/output1.txt");

        while(input.hasNextDouble())
        {
            effort=0;
            tdev=0;
            val = input.nextDouble();
            IG.add(val);
            effort = a*(Math.pow(val,b));
            tdev = c*(Math.pow(effort,d));
            if(type==1)
                wr.write(String.valueOf((int) Math.round(tdev*60)));
            else
                wr.write(String.valueOf((int) Math.round(tdev*12)));
            wr.write("\n");
        }
         wr.close();
        input.close();
        }

public ArrayList<Double> getList()
{
    return IG;
}

public ArrayList<Integer> getMainOutput() throws FileNotFoundException
{
    ArrayList<Integer> listt = new ArrayList<>(); 
    Scanner input1 = new Scanner(System.in);
    File file1 = new File("src/cocomoga/output.txt");
    input1 = new Scanner(file1);
    while(input1.hasNextInt())
    {
        int v = input1.nextInt();
        listt.add(v);
    }
    return listt;
}

public ArrayList<Integer> getOutput1() throws FileNotFoundException
{
    ArrayList<Integer> listt = new ArrayList<>(); 
    Scanner input1 = new Scanner(System.in);
    File file1 = new File("src/cocomoga/output1.txt");
    input1 = new Scanner(file1);
    while(input1.hasNextInt())
    {
        int v = input1.nextInt();
        listt.add(v);
    }
    return listt;
}

public int getMax(ArrayList<Integer> arr)
{
    Iterator iit = arr.listIterator();
    int max = -1;
    int maxIndex = -1;
    int i=0;
    while(iit.hasNext())
    {
        int vall = (int) iit.next();
        if(vall > max)
        {
            max = vall;
            maxIndex = i;
        }
        i++;
    }
    return maxIndex;
}

}