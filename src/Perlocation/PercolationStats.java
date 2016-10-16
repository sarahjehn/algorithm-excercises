package Perlocation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PercolationStats {

    private int gridSize;
    private int percolationTrials;
    private double[] percolationThresholdResults;
    private static Logger l = Logger.getLogger(PercolationStats.class.getName());

    public PercolationStats(int n, int trials){
        this.gridSize = n;
        this.percolationTrials = trials;
        this.percolationThresholdResults = getPercolationThresholdResults();
    }

    private double getPercolationThreshold(){

        //Initialize all sites to be blocked.
        double numberOfSites = gridSize * gridSize;
        double numberOfOpenSites = 0;

        Percolation p = new Percolation(gridSize);
        l.log(Level.INFO, "New Percolation Object created with gridSize: " + gridSize);

        while(!p.percolates()){
            //Choose an open site (row i, column j) uniformly at random among all blocked sites
            int randomRow = StdRandom.uniform(0, gridSize);
            int randomColumn = StdRandom.uniform(0, gridSize);

            l.log(Level.INFO, "Choosen random site: [" + randomRow + ", " + randomColumn + "]");

            //Open the site (row i, column j) if not open yet
            if(!p.isOpen(randomRow, randomColumn)){
                p.open(randomRow, randomColumn);
                numberOfOpenSites++;
                l.log(Level.INFO, "Site openened: [" + randomRow + ", " + randomColumn + "]" + ". Current numberOfOpenSite = " + numberOfOpenSites);
            }
        }

        //print result for each try
        p.print();

        //The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold
        return numberOfOpenSites/numberOfSites;
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(percolationThresholdResults);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percolationThresholdResults);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - (1.96 * stddev()/Math.sqrt(percolationTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + (1.96 * stddev()/Math.sqrt(percolationTrials));
    }


    private double[] getPercolationThresholdResults() {
        double[] percolationThresholdResults = new double[percolationTrials];
        for(int i = 0; i < percolationTrials; i++){
            percolationThresholdResults[i] = getPercolationThreshold();
        }
        return percolationThresholdResults;
    }

}


/*
Monte Carlo simulation. To estimate the percolation threshold, consider the following computational experiment:

Initialize all sites to be blocked.
Repeat the following until the system percolates:
Choose a site (row i, column j) uniformly at random among all blocked sites.
Open the site (row i, column j).
The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.
 */