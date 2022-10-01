/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiobjectfa;

/**
 *
 * @author SOFEEM
 */
class Coverage implements Constants {public static double evaluate(Sensor sensor) {

    double ED; int count=0; double cov; double covpr;
    inlGrid();
 //get the value of sensor of firefly 1
        for(int a =0 ;a<2;a++){
            for(int b=0;b<NoofSen;b++){
                content[a][b]= sensor.getSenPos()[a][b];}}//sensor of firefly 1
 
 
        for(int f=0;f<NoofSen;f++){
            double value1 = content[0][f];
            double value2 = content[1][f];
                for (int i = 0; i<grid.length;i++){
                        for(int j=0; j<grid.length;j++){
              
                                ED = EuDistance(value1,value2,i,j);
                //System.out.print("ED = ");
                //System.out.println(ED);
                //System.out.print("Radius= ");
                //System.out.println(Radius);
                
                if ((double)(SRadius) > ED){
                    //System.out.print("In");
                    grid[i][j] = 1;
                    //System.out.print(i);
                    //System.out.print(j);
                    //System.out.println();
                }}}}


 for(int i = 0; i <grid.length ; i++)
        { for (int j =0 ;j<grid.length ; j++){
            
            if (grid [i][j] == 1){
            count++;
            }}}
       double dco= (double)count;
       double dgriSize = (double)grid.length*grid.length;
       cov = dco/dgriSize;
       
       covpr = cov;
       
       //displayGrid(grid);
      //System.out.println();
       //System.out.print(cov);
       //System.out.println();
       //System.out.println(covpr);
       

 //fi.get(index).covfitness=;

return covpr;
}
 public static void inlGrid(){
         for(int i=0;i<grid.length;i++){
             for(int j=0;j<grid.length;j++){
                       grid[i][j] = 0;
            }
        }   for(int i = 0; i < grid.length ; i++)
        { for (int j =0 ;j<grid.length ; j++){
            //System.out.print(grid[i][j]+"   ");
            }
             //System.out.println();
        }
   }   // end of grid
   
      // displaying grid
   public static void displayGrid(double[][] grid){
    
    for(int i = 0; i <grid.length ; i++)
        { for (int j =0 ;j<grid.length ; j++){
            System.out.print(grid[i][j]+"   ");
            }
             System.out.println();
        }
    
    }// end of displaying
   public static double EuDistance(double Sx, double Sy, int Gx, int Gy){
    
    double Eud;
    //System.out.print("Sx = ");
    //System.out.println(Sx);
    //System.out.print("Gx = ");
    //System.out.println(Gx);
    //System.out.print("Sy = ");
    //System.out.println(Sy);
    //System.out.print("Gy = ");
    //System.out.println(Gy);
    
    double dx = (Sx-Gx);
    double dy = (Sy-Gy);
    //System.out.print("dx = ");
    //System.out.println(dx);
    //System.out.print("dy = ");
    //System.out.println(dy);
    
    double dis = (dx*dx)+(dy*dy);
    
    //System.out.print("dis = ");
    //System.out.println(dis);
    
        Eud = Math.sqrt(dis);
        
    
        
        return Eud;
    
    
    }
           
    
}

