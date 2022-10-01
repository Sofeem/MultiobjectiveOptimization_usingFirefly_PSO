/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiobjectfa;

/**
 *
 * @author SOFEEM
 */
    public interface Constants {
        int SWARM_SIZE = 50;
	int MAX_ITERATION = 40;
	int PROBLEM_DIMENSION = 2;
        int NoofSen = 10;
        static double SRadius = 2;
        static double CRadius = 2;
        double gamma = 0.9;
	double beta = 0.9;
	double delta = 0.9;
        double W_UPPERBOUND = 1.0;
	double W_LOWERBOUND = 0.0;
        static double [][] grid = new double[10][10];
        double hecnposition = (grid.length/2);
        double [][] content = new double[2][NoofSen];
        int [][] graph = new int[NoofSen+1][NoofSen+1];
        int [] com = new int[NoofSen];
        double [][] sPosition = new double[2][NoofSen];
        double [] ed = new double[NoofSen+1];
        double [] energy = new double[NoofSen+1];
        double [] costoflink = new double[NoofSen+1];
        double []data = new double[SWARM_SIZE];
    
}

