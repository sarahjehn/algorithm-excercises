package Perlocation;

public class PerlocationRun {

    public static void main(String[]args){

        Percolation percolation = new Percolation(4);
        Site[][] perlocationSite = percolation.getSites();

        //open a site

        percolation.open(1, 0);
        printPerlocationSite(percolation.getSites());
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");

        percolation.open(0, 1);
        printPerlocationSite(percolation.getSites());
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");


        percolation.open(1, 2);
        printPerlocationSite(percolation.getSites());
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");


        percolation.open(3, 1);
        printPerlocationSite(percolation.getSites());
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");


        percolation.open(2, 3);
        printPerlocationSite(percolation.getSites());
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");

        percolation.open(0, 2);
        printPerlocationSite(percolation.getSites());
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");


        percolation.open(2, 2);
        printPerlocationSite(percolation.getSites());
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");

        percolation.open(3, 2);
        printPerlocationSite(percolation.getSites());
        System.out.println("Result: " + percolation.percolates());
        System.out.println("----------------------------------------------");

    }

    public static void printPerlocationSite(Site[][] perlocationSite){
        for(int i = 0; i < perlocationSite.length; i++){
            System.out.print("| ");
            for(int j = 0; j < perlocationSite[i].length; j++){
                if(perlocationSite[i][j].isFull()){
                    System.out.print("### | ");
                } else if (perlocationSite[i][j].isOpen()) {
                    System.out.print("+++ | ");
                } else {
                    System.out.print("    | ");
                }
            }
            System.out.println();
        }
    }

}
