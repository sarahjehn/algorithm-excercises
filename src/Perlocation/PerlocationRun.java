package Perlocation;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PerlocationRun {

    public static void main(String[]args) {

        /*
        Percolation percolation = new Percolation(4);

        percolation.open(1, 0);
        percolation.print();
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");

        percolation.open(0, 1);
        percolation.print();
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");


        percolation.open(1, 2);
        percolation.print();
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");


        percolation.open(3, 1);
        percolation.print();
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");


        percolation.open(2, 3);
        percolation.print();
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");

        percolation.open(0, 2);
        percolation.print();
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");


        percolation.open(2, 2);
        percolation.print();
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");

        percolation.open(3, 2);
        percolation.print();
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");

        */


        //run percolation stats

        final Logger l = Logger.getLogger("PercolationStats Test Run Logger");

        if (isValidNumberInput(args[0]) && isValidNumberInput(args[1])) {
            int gridSize = Integer.parseInt(args[0]);
            int trials = Integer.parseInt(args[1]);

            l.log(Level.INFO, "gridSize: " + gridSize + ", trials: " + trials);

            PercolationStats stats = new PercolationStats(gridSize, trials);
            double mean = stats.mean();
            double stdev = stats.stddev();
            double confLow = stats.confidenceLo();
            double conHi = stats.confidenceHi();

            System.out.println("########################");
            System.out.println("mean: " + mean);
            System.out.println("StdDev: " + stdev);
            System.out.println("Confidence Interval: [" + confLow + ", " + conHi + "]");

        } else System.out.println("input is not valid!");
    }

    public static boolean isValidNumberInput(String input){
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
}
