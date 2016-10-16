package Perlocation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private Site[][] sites;
    private int gridSize;
    private int gridLength;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        gridSize = n;
        gridLength = n * n;
        sites = new Site[gridSize][gridSize];
        for(int j = 0; j < gridSize; j++) {
            for (int i = 0; i < gridSize; i++) {
                sites[j][i] = new Site();
            }
        }

        weightedQuickUnionUF = new WeightedQuickUnionUF(gridLength + 2); //2 virtual nodes
        //top virtual node with index 0 with first row nodes
        for (int i = 0; i <= gridSize; i++){
            weightedQuickUnionUF.union(0,i);
        }

        //bottom virtual node with index n with last row nodes
        for (int i = (gridLength + 1 - gridSize); i < (gridLength + 2); i++){
            weightedQuickUnionUF.union(gridLength + 1,i);
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j)  {
        int indexCurrent = getGridIndex(i, j);
        if(sites[i][j].isBlocked()){
            sites[i][j].open();

            //connect adjacent sites if open and fill if connected to top
            int topNode = 0;
            int bottomNode = gridLength + 1;
            //top
            int upperRowIndex = i - 1;
            if(upperRowIndex >= 0) {
                if (isOpen(upperRowIndex, j)) {
                    int indexTop = getGridIndex(upperRowIndex, j);
                    weightedQuickUnionUF.union(indexCurrent, indexTop);
                    if (weightedQuickUnionUF.connected(topNode, indexTop)
                            || weightedQuickUnionUF.connected(bottomNode, indexTop)) {
                        sites[upperRowIndex][j].fill();
                    }
                }
            }
            //bottom
            int bottomRow = i + 1;
            if(bottomRow < gridSize) {
                if (isOpen(bottomRow, j)) {
                    int indexBottom = getGridIndex(bottomRow, j);
                    weightedQuickUnionUF.union(indexCurrent, indexBottom);
                    if (weightedQuickUnionUF.connected(topNode, indexBottom)
                            || weightedQuickUnionUF.connected(bottomNode, indexBottom)) {
                        sites[bottomRow][j].fill();
                    }
                }
            }

            //left
            int leftColumn = j - 1;
            if(leftColumn >= 0) {
                if (isOpen(i, leftColumn)) {
                    int indexLeft = getGridIndex(i, leftColumn);
                    weightedQuickUnionUF.union(indexCurrent, indexLeft);
                    if (weightedQuickUnionUF.connected(topNode, indexLeft)
                            || weightedQuickUnionUF.connected(bottomNode, indexLeft)) {
                        sites[i][leftColumn].fill();
                    }
                }
            }

            //right
            int rightColumn = j + 1;
            if(rightColumn < gridSize) {
                if (isOpen(i, rightColumn)) {
                    int indexRight = getGridIndex(i, rightColumn);
                    weightedQuickUnionUF.union(indexCurrent, indexRight);
                    if (weightedQuickUnionUF.connected(topNode, indexRight)
                            || weightedQuickUnionUF.connected(bottomNode, indexRight)) {
                        sites[i][rightColumn].fill();
                    }
                }
            }

            if(weightedQuickUnionUF.connected(topNode, indexCurrent)
                    || weightedQuickUnionUF.connected(bottomNode, indexCurrent)){
                sites[i][j].fill();
            }
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j){
        return sites[i][j].isOpen();
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j)    {
        return sites[i][j].isFull();
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, gridLength + 1);
    }

    public Site[][] getSites(){
        return sites;
    }

    public void print(){
        for(int i = 0; i < sites.length; i++){
            System.out.print("| ");
            for(int j = 0; j < sites[i].length; j++){
                if(sites[i][j].isFull()){
                    System.out.print("### | ");
                } else if (sites[i][j].isOpen()) {
                    System.out.print("+++ | ");
                } else {
                    System.out.print("    | ");
                }
            }
            System.out.println();
        }
    }

    //gets index for row, column of grid
    private int getGridIndex(int i, int j){
        return (gridSize * i) + j + 1;
    }
}