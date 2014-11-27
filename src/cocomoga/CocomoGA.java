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
import org.junit.Test;
import static org.junit.Assert.*;

class CocomoGA
{
@SuppressWarnings("empty-statement")
public static void main(String args[]) throws IOException
{
    ArrayList<Double> inputList = new ArrayList<Double>();
    Scanner s = new Scanner(System.in);
    System.out.println("\nChoice of Model :");
    int type = s.nextInt();
    double a=0,b=0,c=0,d=0;
    
    if(type==1)
    {   a=2.4;  b=1.05;   c=2.5;    d=0.38; }
    
    else if(type==2)
    {   a=3.0;  b=1.12; c=2.5;  d=0.35; }

    else if(type==3)
    {   a=3.6;  b=1.20; c=2.5;  d=0.32;}

    else
    {   System.out.println("Input either 1,2 or 3");    return; }
    
    System.out.println("\nCrossOver Ratio :");
    double crossoverRatio = s.nextDouble();
    System.out.println("\nMutation Ratio :");
    double mutationRatio = s.nextDouble();
    System.out.println("\nEnter Generations :");
    int gen = s.nextInt();
    
    
    Writer new_f = new FileWriter("src/cocomoga/total.txt");
    
    GetPopulation pop = new GetPopulation(crossoverRatio,mutationRatio,a,b,c,d,type);
    
    inputList = pop.getList();
    
    ArrayList<Integer> outputMain = new ArrayList<>();
    ArrayList<Integer> output1 = new ArrayList<>();
    ArrayList<Float> output2 = new ArrayList<>();
    
    Double[][] coeffARR = new Double[inputList.size()][4];
    
    //Main Genetic ALGORITHM
    //Initial Generation
    Writer wrr = new FileWriter("src/cocomoga/coeff_old.txt");
            
    for(int k=0;k<inputList.size();k++)
    {
        coeffARR[k][0] = (Math.random()*10)%10.0;
        coeffARR[k][1] = (Math.random()*10)%10.0;
        coeffARR[k][2] = (Math.random()*10)%10.0;
        coeffARR[k][3] = (Math.random())%10.0;
         wrr.write(coeffARR[k][0]+" "+coeffARR[k][1]+" "+coeffARR[k][2]+" "+coeffARR[k][3]+"\n");          
         new_f.write((k+1)+"\n");
    }
    new_f.close();
    
    outputMain = pop.getMainOutput();
    output1 = pop.getOutput1();
    
    for(int t=0;t<gen;t++)
    {
            Iterator MainOutputIT = outputMain.listIterator();

            Writer wr = new FileWriter("src/cocomoga/output2.txt");
            Writer wrf = new FileWriter("src/cocomoga/fitness.txt");
            int i=0;

            float total_val_for_prob=(float) 0.0;
            //Fitness Calculation
            double prob_total=0.0;
            for (Double inputList1 : inputList) {
                double na,nb,nc,nd;
                na = coeffARR[i][0];
                nb = coeffARR[i][1];
                nc = coeffARR[i][2];
                nd = coeffARR[i][3];
                i++;
                Iterator in = inputList.listIterator();
                Iterator ou = output1.listIterator();
                double tdev,eff;
                double total=0.0;
                while(in.hasNext() && ou.hasNext())
                {
                    eff = na*(Math.pow((double) in.next(), nb));
                    tdev = nc*(Math.pow(eff, nd));
                    tdev = tdev*12;
                    int val1 = (int) ou.next();
                    double fitness = Math.abs((double)(val1-tdev)/val1);
                    total = total + fitness;
                }
                total = total/inputList.size();
                wrf.write(Math.abs(total)+ "\n");
                
                output2.add((float)total);
                prob_total +=(float)total;
                //total = (1-total);
                wr.write(Math.abs(total*inputList.size())+ "\n");
            }
            System.out.println(prob_total);
            wr.close();
            wrr.close();
            wrf.close();
            System.out.println("here");
            Iterator iit = output2.listIterator();
            ArrayList<Integer> Freq = new ArrayList<>();
            
            //Roulette Wheel Probability
            while(iit.hasNext())
            {
                float val1 = (float) iit.next();
                float vall = (float) ((float)inputList.size()*(((float)val1)/prob_total));
                Freq.add(Math.round(vall));
                //System.out.println(val1 + " " + vall +" " + Math.round(vall));
            }
            iit = Freq.listIterator();
            while(iit.hasNext())
            {
                System.out.println(iit.next());
            }

            Double[][] coeff = new Double[inputList.size()][4];

            int x=0;

            Writer wr2 = new FileWriter("src/cocomoga/coeff.txt");

            //Crossover
            // We can choose any type of values so I chose the one with max frequency 
            // I chose values equal to crossOver Ratio * inputlIst
            while(x<(inputList.size()*crossoverRatio))
            {
            int index = pop.getMax(Freq);
            int maxFreq = Freq.get(index);    
            System.out.println(index +" "+maxFreq);
            if(maxFreq==0)
                break;
            while(maxFreq!=0)
            {
                if(index>=inputList.size())
                    index = inputList.size() - 1;
                wr2.write(coeffARR[index][0]+" "+coeffARR[index][1]+" "+coeffARR[index][2]+" "+coeffARR[index][3]+"\n");
                x++;
                maxFreq--;
                System.out.println("x :"+x);
              }
            Freq.set(index, -10);

            }
            System.out.println("X :"+x);

            if(x<inputList.size())
            {
                int diff = inputList.size() - x;
                while(diff!=0)
                {
                    int index1 = (int) Math.round((Math.random())*20);
                    if(index1>=inputList.size())
                        index1 = inputList.size() - 1;
                    wr2.write(coeffARR[index1][0]+" "+coeffARR[index1][1]+" "+coeffARR[index1][2]+" "+coeffARR[index1][3]+"\n");
                    diff--;
                    x++;
                    System.out.println("x :"+x);
                 }

            }
            wr2.close();
            System.out.println("X :"+x);
            System.out.println("over");

            Scanner input = new Scanner(System.in);
            File file = new File("src/cocomoga/coeff.txt");
            input = new Scanner(file);

            x=0;
            int y=0;
            while(input.hasNextDouble())
            {
                coeffARR[x][0] = input.nextDouble();coeffARR[x][1] = input.nextDouble();
                coeffARR[x][2] = input.nextDouble();coeffARR[x][3] = input.nextDouble();
                System.out.println(coeffARR[x][0]+" "+coeffARR[x][1]+" "+coeffARR[x][2]+" "+coeffARR[x][3]);
                x++; 
            }
            
            while(y<(inputList.size()*crossoverRatio))
            {
                int x1 = (int)(Math.random()*inputList.size());
                int x2 = (int)(Math.random()*inputList.size());
                double a1,a2,a3,a4;
                a1 = 0.45*(coeffARR[x1][0]) + 0.55*(coeffARR[x2][0]);a2 = 0.45*(coeffARR[x1][1]) + 0.55*(coeffARR[x2][1]);
                a3 = 0.45*(coeffARR[x1][2]) + 0.55*(coeffARR[x2][2]);a4 = 0.45*(coeffARR[x1][3]) + 0.55*(coeffARR[x2][3]);
                
                coeffARR[x2][0] = 0.45*(coeffARR[x2][0]) + 0.55*(coeffARR[x1][0]);coeffARR[x2][1] = 0.45*(coeffARR[x2][1]) + 0.55*(coeffARR[x1][1]);
                coeffARR[x2][2] = 0.45*(coeffARR[x2][2]) + 0.55*(coeffARR[x1][2]);coeffARR[x2][3] = 0.45*(coeffARR[x2][3]) + 0.55*(coeffARR[x1][3]);
                
                coeffARR[x1][0] = a1;coeffARR[x1][1] = a2;coeffARR[x1][2] = a3;coeffARR[x1][3] = a4;
                y+=2;
                
            }

            //Mutation

            int mutateVal = (int) (mutationRatio * inputList.size());
            System.out.println("Mutate :"+mutateVal);
            for(x=0;x<mutateVal;x++)
            {
                int rand = (int) Math.abs(Math.random()*(inputList.size()*4));
                int rand1 = rand%4;
                int rand2 = rand/4;
                coeffARR[rand2][rand1] = (Math.random()*10);
                System.out.println("Val :"+rand2 +" "+rand1);
            }


            //Write to file

            wr2 = new FileWriter("src/cocomoga/coeff.txt");
            for(x=0;x<inputList.size();x++)
            {
                wr2.write(coeffARR[x][0]+" "+coeffARR[x][1]+" "+coeffARR[x][2]+" "+coeffARR[x][3]+"\n");
            }
            wr2.close();
            
            int tt = s.nextInt();
            
    }
}
}
