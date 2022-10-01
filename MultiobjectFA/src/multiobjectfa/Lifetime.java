/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiobjectfa;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.util.Random;
import java.lang.Double;
import java.text.DecimalFormat;

/**
 *
 * @author SOFEEM
 */
public class Lifetime implements Constants {public static double evaluate(Sensor sensor){
    

       
      
        
        
       
        
        initialGraph();
        inlGrid();
        asenergy();
        SenPos(sensor,sPosition);
        communicationrelaynodes();
        displayGraph();
         Random r = new Random();
        
         
        
        Vector<Vertex> vec = new Vector<Vertex>();
        
        
        
        for (int s =0 ;s<NoofSen+1;s++){
            
            Vertex f = new Vertex("sen"+s);
            vec.add(f);
        }
        
     
     
       int cyc = 0; 
           boolean check = true;
         
           while (check != false) {
             int p =0;
           
             
       
             for (int w =0 ;w<NoofSen+1;w++){
                 
           int sizearray = 0;
                 sizearray = arrsize(w);
                 int in = 0;
             vec.get(w).adjacencies = new Edge[sizearray];
             
             
                
             
             
             
             
             
             for (int d =0 ;d<NoofSen+1;d++){
                 
                     if (graph[w][d] == 1){
                         //System.out.print("Value of Energy: " +energy[w]);
                         vec.get(w).adjacencies[in] = new Edge(vec.get(d),costoflink[w]);
                         in++;
                        }
                     
             }
             
         }
       
             
    Vertex[] vertices = new Vertex[NoofSen+1];
    for (int j = 0 ;j <NoofSen+1;j++){
           vertices [j] = vec.get(j);
           }
       
       
    
    
    
    computePaths(vec.get(NoofSen));
       
       
       
       
       for (Vertex v : vertices)
	{  
	    //System.out.println("Distance to " + v + ": " + v.minDistance);
	    List<Vertex> path = getShortestPathTo(v);
	    //System.out.println("Path: " + path);
            //System.out.println("Path Size : " + path.size());
            double R = 0 + (2 - 0) * r.nextDouble();
            costoflink[p] = (path.size()-1+R);
            p++;
           
             
           
        }
        for (int s =0 ;s<NoofSen+1;s++){
            
             energy[s] = energy[s]- costoflink[s];
           //System.out.println(energy[s] + ": " + costoflink[s]);
           
        }
           
       check = energylevel(energy);
       //System.out.println("Cycle: " + cyc);
       
       cyc ++;
            
       
	}
           
    double ltime = (double)cyc/20;
         //System.out.println("Final litetime: " + ltime);
          displayGraph();
          DecimalFormat df = new DecimalFormat("0.00");
          double r1 = 0 + (0.5 - 0.1) * r.nextFloat();
         ltime = ltime+r1;
         String litime = df.format(ltime);
         double litime1 = Double.valueOf(litime);
         //double litime = (double)(ltime);
         if(litime1 > 1){
            litime1 = 1;
         }
         
         return (litime1);
    }
       
        
        
        
        
        
         
public static double EuDistance(double Sx, double Sy, double Gx, double Gy){
    
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
    
        Eud =  Math.sqrt(dis);
        
    
        
        return Eud;
    
    
    }
           
  public static void communicationrelaynodes(){
           
           double E;
           int count = 0;
           
           
               for(int c =0 ;c<com.length;c++){
                  com[c]=0;
               }
           
           for(int i = 0 ; i < NoofSen;i++){
               
               
           E = EuDistance(hecnposition,hecnposition,sPosition[0][i],sPosition[1][i]);
               
          // System.out.println("E "+(i+1)+ "= " + E);
           ed[i]=E;
           
           if (E<CRadius){
           
                 //System.out.println("Sensor is  " + (i+1) + " communication relay ");
                 com[i]=1;
                 count++;
                }
           }
           
           //System.out.print("com array :");
           for(int h = 0;h<com.length;h++){
           
          
           //System.out.print(com[h]+ " ");
           
           }
           comtooth(count);
       }
           
           
           
           
 public static void comtooth(int b){
               
            // commnunication connection 
           int [] comsen = new int[b];
           int p=0;
           
           for (int j=0;j<com.length;j++){
              if (com[j]== 1){
                
               
               comsen[p]=j;
               p++;
              }
          }
            //System.out.print("comsen array :");
           for(int k = 0;k<comsen.length;k++){
           
          
           //System.out.print(comsen[k]+ " ");
           
           }
           //System.out.println();
                
                int c = 0;
                int re = 0;
                for(int y =0 ;y<com.length;y++){
                if (com[y] == 0){
                
                    c++;
                }
                    }
                
                int [] osen = new int[c];
                for(int g =0 ;g<com.length;g++){
                if (com[g] == 0){
                
                    osen[re] = g;
                    re++;
                }
                }  
               // System.out.println(" down");
                //System.out.print("osen array :");
           for(int k = 0;k<osen.length;k++){
           
          
           //System.out.print(osen[k]+ " ");
           
           } 
           //System.out.println();
           connection(comsen,osen);
           
           }
 
public static void connection(int [] a, int [] b){
 
    
    
    
 for(int i = 0 ; i<a.length;i++){
     for(int j =0;j<b.length;j++)
     {
   
     double check = EuDistance(sPosition[0][a[i]],sPosition[1][a[i]],sPosition[0][b[j]], sPosition[1][b[j]]);
     //System.out.println("Check : " + check);
     if (check<CRadius){
                        
                    graph[a[i]][b[j]]=1;
                    graph[b[j]][a[i]]=1;
                    
                    //System.out.println("ok");
                    
     
     }
             }
     
     
    
 }
  for(int l =0;l<a.length;l++){
        int y = (graph.length-1);
        int g = a[l];
       
     //System.out.println(y);
        graph[y][g] = 1;
        graph[g][y] = 1;
    }
 
 
        
 
 
 }
public static void displayGraph(){
         for(int i=0;i<graph.length;i++){
             for(int j=0;j<graph.length;j++){
                 
             //System.out.print(graph[i][j] + " ");
            
         }
             //System.out.println();
        }
        
        
        }

    public static void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
      	vertexQueue.add(source);

	while (!vertexQueue.isEmpty()) {
	    Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {  
                
                Vertex v = e.target;
                
                
                double weight = e.weight;
               
                double distanceThroughU = u.minDistance + weight;
		if (distanceThroughU < v.minDistance) {
		    vertexQueue.remove(v);
		    v.minDistance = distanceThroughU ;
		    v.previous = u;
		    vertexQueue.add(v);
		}
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
    
        return path;
    }
    
    public static int arrsize(int a){
    int size =0;
    for(int c = 0;c<NoofSen+1;c++){    
    if(graph[a][c] == 1){
    size++;
    
    }}
    //System.out.print(size);
    //System.out.println("/////////");
    
    
    return size;}
    
    public static boolean energylevel(double [] t)
    {
    boolean c = true;
        for (int r = 0; r<t.length-1;r++){
        
        if (t[r] <= 0){
            c = false;
            break;
        }
        
       
            
           
                
     }
    return c;
    }
     public static int weight(Lifetime re){
               int s =0;
    
    return s; } 
    

    
       
   
     


public static void initialGraph(){
         for(int i=0;i<graph.length;i++){
             for(int j=0;j<graph.length;j++){
                 
             graph[i][j] = 0;
            
         }
        }
        
        
        }
 public static void inlGrid(){
      //intialize grid
        
        for(int i=0;i<grid.length;i++){
             for(int j=0;j<grid.length;j++){
                 
             grid[i][j] = 0;
            
         }
        }   
    
    }
  
 
 
 public static void SenPos(Sensor Sen,double [][] sPosition){
    //sensor position alloacation
        for(int a =0 ;a<2;a++){
            for(int b=0;b<NoofSen;b++){
                sPosition[a][b]= Sen.getSenPos()[a][b];}}
       
       //System.out.print(answer + "  ");
       
       
       
           
        
//System.out.println();
       for(int i = 0; i < sPosition.length ; i++)
        { for (int j =0 ;j<NoofSen ; j++){
            //System.out.print(sPosition[i][j]+"   ");
            }
             //System.out.println();
        }
 }

public static void asenergy(){
for(int f=0;f<energy.length;f++){
                    energy[f] = 20;
           }

}


}