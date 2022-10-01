/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiobjectfa;
import java.util.Random;
import java.util.Vector;
import java.util.Arrays;

/**
 *
 * @author SOFEEM
 */
public class MOFAProcess1 implements Constants{
  

    private Vector<Firefly> fireflies = new Vector<Firefly>();
    Random generator = new Random();
    Random r = new Random();
    private double [] ligInc = new double[SWARM_SIZE];
    private double [] ligInl = new double[SWARM_SIZE];
    int t = 0;
        private Vector<NonDominateSolution> nonds = new Vector<NonDominateSolution>();
    private Vector<NonDominateSolution> finalnonds = new Vector<NonDominateSolution>();
     private Vector<NonDominateSolution> sortnonds = new Vector<NonDominateSolution>();
    private int [] dominate = new int [SWARM_SIZE];
    private double weight1 = 0.5;
    private double weight2 = 0.5;
   
     public void execute(){
         intializefirefly();
         updatelightintensity();
         intializedominatearray();
         
         while (t<MAX_ITERATION ){
                  //intializedominatearray();
                 System.out.println("No.of fireflies: " + fireflies.size());
                 System.out.println("In Iteration : " + t + "-------------------------------------------------");
                 
                   for(int i = 0 ; i < fireflies.size(); i++){
                       for(int j = 0 ; j < fireflies.size(); j++){
                       
                           if(i != j){
                         if  (fireflies.get(j).getLightIn() > fireflies.get(i).getLightIn() && fireflies.get(j).getLightInli()>fireflies.get(i).getLightInli()){
                   
                               //Mark jth firefly as dominate  
                             System.out.println("Size: " + dominate.length);
                               dominate[j] = 1;
                               System.out.println("dominate found index " + j + "with firefly index " +  i);
                               move(i,j);
                               //updateliinten(i);
                               
                               
                               Showupdatedin();
                                }}}
                       
                           boolean f = checkanydominatesolution(i);
                          if(f == true){
                          
                             int best = findbestsolution();
                             Randomwalk(best);
                             System.out.println("no non-dominate found " + "Showing updated intensity after random walk");
                             Showdominatearray();
                             Showupdatedin();
                          
                          
                          }
                              
                           // if no nondominated solution found
                          
                       
                       
                   }
                       
                       Showdominatearray();
                       Showupdatedin();
                       nextiteration();
                       
                   
                   

                   t = t+1;
         }
    //sortoptimalsolution();
         
      paretofront();
    
     
    
}
     private void intializefirefly() {
        Firefly f;
        //double [] checkarray = new double [NoofSen];
        //for(int i = 0 ; i < NoofSen ; i++){checkarray[i] = 0;}
        //boolean ch;
        for (int i = 0; i<SWARM_SIZE;i++){
              
            f = new Firefly();
            
            //Step1 intial position of firefly
            
            // randomize location inside a space defined in Problem Set
			double[] loc = new double[PROBLEM_DIMENSION];
			loc[0] = ProblemSet.LOC_X_LOW + generator.nextDouble() * (ProblemSet.LOC_X_HIGH - ProblemSet.LOC_X_LOW);
			loc[1] = ProblemSet.LOC_Y_LOW + generator.nextDouble() * (ProblemSet.LOC_Y_HIGH - ProblemSet.LOC_Y_LOW);
			Location location = new Location(loc);
            
             //adding sensors position
                        
                        
                        double[][] sen =  new double[2][NoofSen];
                        for (int k = 0; k<2;k++){
                        for (int j=0;j<NoofSen;j++){
                            double R = r.nextInt(grid.length-1) + 1;
                            //Senposlist[j]=R;
                           
                              sen[k][j]=R;
                            
                        }
                            
                        } 
                        Sensor senpos = new Sensor(sen);
                        
                        
                        f.setLocation(location);
                        f.setSenpos(senpos);
                        fireflies.add(f);
                        System.out.println("Firefly  " +(i+1)+ " added");
                        System.out.println("Firefly Size "+ fireflies.size());
                        
                       
                        
            
            
            
        }
    }
     private void updatelightintensity() {
        
        for(int i=0; i<fireflies.size(); i++) {
            
           
			
                        //System.out.print("Fitness "+(i+1)+" :  " + fitnessValue[i]);
                        fireflies.get(i).setLightIn(fireflies.get(i).getsensor());
                        ligInc[i]=fireflies.get(i).getLightIn();
                         
                        System.out.println("LightIntensityC  "+(i+1)+" :  " + ligInc[i] + ".");
                       
                        //ligInl[i]=fireflies.get(i).getLightInli(fireflies.get(i).getsensor());
                        //System.out.println("LightIntensityL "+(i+1)+" :  " + ligInl[i] + ".");
                        fireflies.get(i).setLightInli(fireflies.get(i).getsensor());
                        ligInl[i]=fireflies.get(i).getLightInli();
                        System.out.println("LightIntensityL "+(i+1)+" :  " + ligInl[i] + ".");
                        
                        //System.out.println();
        
        } 
        
        //int best = getbestfirefly();
                    //System.out.println("BestLightIntensity"+(best+1)+" :  " + ligInc[best] + "-- ");
    }

    private void intializedominatearray() {
        for(int q =0 ;q< dominate.length ;q++){
            
            dominate[q] = 0;
        }
    }

    private void move(int i, int j) {
        Firefly f1 = fireflies.get(i);
        Firefly f2 = fireflies.get(j);
        
        double[] newLoc = new double[PROBLEM_DIMENSION];
                           newLoc[0]= f1.getLocation().getLoc()[0] + movement(f1,f2,0);
                           newLoc[1]= f2.getLocation().getLoc()[1] + movement(f1,f2,1);
                           double c = movement(f1,f2,0);
                           double c1 = movement(f1,f2,1);
                           
                            Location loc = new Location(newLoc);
			   f1.setLocation(loc);
                           // Step 2 upadte Sensor
        
         double [][] newSen = new double[2][NoofSen];
            for (int k = 0; k<2;k++){
               for (int l=0;l<NoofSen;l++){
                   if (k == 0){
                                                         
                      newSen[k][l] = newSenpositionxaxis(f1,f2,c,l);
                      //System.out.println(" newSen x-axis : "+l+ " : " + newSen[k][l] + " :  Firefly " + i);
                              
                         }
                   if (k == 1){
                                                         
                            newSen[k][l] = newSenpositionyaxis(f1,f2,c,l);                              
                                                          }
                                                         //System.out.println(" newSen y-axis : "+l+ " : " + newSen[k][l] + "Firefly : " + j);
                                                      
                                                      }
                                        }
    
                           Sensor sen = new Sensor(newSen);
                           f1.setSenpos(sen);
                           fireflies.set(i, f1);
                  
                           fireflies.get(i).setLightIn(fireflies.get(i).getsensor());
                           fireflies.get(i).setLightInli(fireflies.get(i).getsensor());
                   
    
    
    
    
    
 }
    
    
    
    
    
    
    public double movement(Firefly f1, Firefly f2, int index){
      
           double x =f1.getLocation().getLoc()[index]-f2.getLocation().getLoc()[index] ;
           double eD = Math.sqrt((x*x));
           Random random = new Random();
           double range = 0.5 - 0.1;
           double scaled = random.nextDouble() * range;
           double shifted = scaled + 0;
           //System.out.println("ran: " + shifted);
           
           double exp = 1/(1+(gamma*(Math.pow(Math.exp(1*eD),2))));
           double b = beta*exp;
           double alpha = shifted*delta;
           //double newalpha = shifted*alpha;
           double result = alpha+b;
           
           
      //System.out.println("m: " +result);
      
      
      return result;
      }

   

    private double newSenpositionxaxis(Firefly f1, Firefly f2,double c,int l) {
        
        double Euclideandis = EuDistance(f1.getsensor().getSenPos()[0][l],f1.getsensor().getSenPos()[1][l],f2.getsensor().getSenPos()[0][l],f2.getsensor().getSenPos()[1][l]);
        double a =  ((Euclideandis + c)/grid.length);
        double newsenpo = 0;
        if (f2.getsensor().getSenPos()[0][l] > f1.getsensor().getSenPos()[0][l]){
            
           newsenpo = f1.getsensor().getSenPos()[0][l] + (a+0.5);
        }else if (f2.getsensor().getSenPos()[0][l] <= f1.getsensor().getSenPos()[0][l]){
            
           newsenpo = f1.getsensor().getSenPos()[0][l] - (a+0.5);
        }
                   
           if (newsenpo >= grid.length) {
               
               newsenpo = newsenpo%grid.length;
               
               
           }
        
    
    return newsenpo;}
    
    private double newSenpositionyaxis(Firefly f1, Firefly f2,double c,int l) {
        
        double Euclideandis = EuDistance(f1.getsensor().getSenPos()[0][l],f1.getsensor().getSenPos()[1][l],f2.getsensor().getSenPos()[0][l],f2.getsensor().getSenPos()[1][l]);
        double a =  ((Euclideandis + c)/grid.length);
        double newsenpo = 0;
        if (f2.getsensor().getSenPos()[1][l] > f1.getsensor().getSenPos()[1][l]){
            
           newsenpo = f1.getsensor().getSenPos()[1][l] + a;
        }else if (f2.getsensor().getSenPos()[1][l] <= f1.getsensor().getSenPos()[1][l]){
            
           newsenpo = f1.getsensor().getSenPos()[1][l] - a;
        }
                   
           if (newsenpo >= grid.length) {
               
               newsenpo = newsenpo%grid.length;
               
               
           }
        
    
    return newsenpo;
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
    
        Eud = Math.sqrt(dis);
        
    
        
        return Eud;
    
    
    }

    private void updateliinten(int i) {
                        
                        //ligInc[i]=fireflies.get(i).getLightIn();
                         
                        //System.out.println("LightIntensityC  "+(i+1)+" :  " + ligInc[i] + ".");
                       
                        //ligInl[i]=fireflies.get(i).getLightInli(fireflies.get(i).getsensor());
                        //System.out.println("LightIntensityL "+(i+1)+" :  " + ligInl[i] + ".");
                        fireflies.get(i).setLightInli(fireflies.get(i).getsensor());
                        //ligInl[i]=fireflies.get(i).getLightInli();
                        //System.out.println("LightIntensityL "+(i+1)+" :  " + ligInl[i] + ".");
                        
    }

    private void Showupdatedin() {
        for(int y =0;y<fireflies.size();y++){
          
            System.out.println("LightIntensityC  "+(y+1)+" :  " + fireflies.get(y).getLightIn() + ".");
            System.out.println("LightIntensityL "+(y+1)+" :  " + fireflies.get(y).getLightInli() + ".");
        
        }
    }

    private boolean checkanydominatesolution(int i) {
        
        int counter = 0;
        boolean  check = false;
        
        for(int p = 0 ; p < dominate.length;p++){
            if (i != p){
           if(dominate[p] == 1){
                counter++;
              }}}
           if(counter == (dominate.length -1)){
               
               check = true;
           
           }
        
        
    return check;}

    private int findbestsolution() {
        
        double [] weightedff= new double[fireflies.size()];
        for (int i = 0 ; i < fireflies.size();i++){
        
                weightedff[i] = (weight1*fireflies.get(i).getLightIn())+(weight2*fireflies.get(i).getLightInli());
        
        }
        
        int b = bestfirefly(weightedff);
        
    return b;}

    private int bestfirefly(double[] weightedff) {
        int pos = 0;
		double maxValue = weightedff[0];
		
		for(int i=0; i<weightedff.length; i++) {
			if(weightedff[i] > maxValue) {
				pos = i;
				maxValue = weightedff[i];
			}
		}
		
		return pos;
    }

    private void Randomwalk(int best) {
        
        Firefly bestFirefly = fireflies.get(best);
        
        for(int i = 0 ; i < fireflies.size();i++){
           
            if(i != best){
           move(i,best);
        
        
        
            }
        }
        
       
    }

    private void nextiteration() {
        
        DominateSolution d;
        //NonDominateSolution nd = new NonDominateSolution();
        for(int i = 0 ; i<dominate.length;i++ ){
        
         //if(dominate[i] == 0){
           
            NonDominateSolution nd = new NonDominateSolution();
            nd.setff(fireflies.get(i));
            nd.setindex(t);
            
            nonds.add(nd);
            
           
           //}
        
        }
        
        /*for(int i = 0 ; i< fireflies.size();i++ ){
        if(dominate[i] == 1){
         
            fireflies.remove(i);
            
            } } */
           //dominate = new int[fireflies.size()]; 
           intializedominatearray();
           finenond();
    }


    

    private void Showdominatearray() {
        for(int i = 0; i < dominate.length;i++){
        
        System.out.print( dominate[i] + " , ");
        System.out.println();
        
        }
    }

   

    private void finenond() {
        
        //Object n = nonds;
        ///Object sn = nonds.clone();
        
       /* for(int i = 0; i<nonds.size();i++){
          //for(int j = 0 ; j<nonds)
          
        //System.out.println(" Before :" + nonds.get(i).Fobj.getLightIn() + ": " + nonds.get(i).Fobj.getLightInli());
        
     }*/
        while(!nonds.isEmpty()) {
       
            int best = findbestindex();
        NonDominateSolution nd = new NonDominateSolution();
               nd.setff(nonds.get(best).Fobj);
               nd.setindex(t);
         
               sortnonds.add(nd);
               nonds.remove(best);
               
        }
        
        
        for(int w = 0; w<sortnonds.size();  w++){
        
            System.out.println("Sorted :" +sortnonds.get(w).Fobj.getLightIn() + ": " + sortnonds.get(w).Fobj.getLightInli());
            //System.out.println("final 0 :"+finalnonds.get(w).Fobj.getLightIn() + ": " + finalnonds.get(w).Fobj.getLightInli());
        }
      
        
        if (!sortnonds.isEmpty()){
        NonDominateSolution fnd = new NonDominateSolution();
          fnd.setff(sortnonds.get(0).Fobj);
          fnd.setindex(t);
          finalnonds.add(fnd);
          
          
          //for(int r1 = 0 ; r1 < finalnonds.size();r1++){
              for(int e =1;e < sortnonds.size();e++){
          
             if(finalnonds.lastElement().Fobj.getLightInli() < sortnonds.get(e).Fobj.getLightInli()){
                 
                 
             
                   fnd = new NonDominateSolution();
                   fnd.setff(sortnonds.get(e).Fobj);
                   fnd.setindex(t);
                   finalnonds.add(fnd);
             }
          
          
          }
              sortnonds.removeAllElements();
        }
          
          
          
          
          for(int w = 0; w<finalnonds.size();  w++){
        
            //System.out.println("final 0:" +finalnonds.get(w).Fobj.getLightIn() + ": " + finalnonds.get(w).Fobj.getLightInli());
            //System.out.println("final 0 :"+finalnonds.get(w).Fobj.getLightIn() + ": " + finalnonds.get(w).Fobj.getLightInli());
        }

           for(int w = 0; w<finalnonds.size()-1;  w++){
               for(int w1 = 0; w1<finalnonds.size();  w1++){
        
            if(w != w1){
               if(finalnonds.get(w).Fobj.getLightIn() == finalnonds.get(w1).Fobj.getLightIn()){
               finalnonds.remove(w1);
               }
               }}
            //System.out.println("final 0 :"+finalnonds.get(w).Fobj.getLightIn() + ": " + finalnonds.get(w).Fobj.getLightInli());
        }
            for(int w = 0; w<finalnonds.size();  w++){
        
            System.out.println("final 1:" +finalnonds.get(w).Fobj.getLightIn() + ": " + finalnonds.get(w).Fobj.getLightInli());
            //System.out.println("Rank:" +finalnonds.get(w).getindex());
            
            //System.out.println("final 0 :"+finalnonds.get(w).Fobj.getLightIn() + ": " + finalnonds.get(w).Fobj.getLightInli());
        }
}

    private int findbestindex() {
        int b = 0;
        
       double maxValue = nonds.get(b).Fobj.getLightIn();
		
		for(int i=0; i<nonds.size(); i++) {
			if(nonds.get(i).Fobj.getLightIn() > maxValue) {
				b= i;
				maxValue = nonds.get(i).Fobj.getLightIn();
			}
		}
	//System.out.print(" best" + nonds.get(b).Fobj.getLightIn());
    
    return b;}
    
    
    
    
    /////////////////////////////////////////////////////////////////////
      //////////////////////////////////////////////////////////////
       ///////////////////////////////////////////////////////////
    
    
    
    private void paretofront() {
        
        
     for(int i = 0; i<finalnonds.size();i++){
          //for(int j = 0 ; j<nonds)
          
        System.out.println(" Before :" + finalnonds.get(i).Fobj.getLightIn() + ": " + finalnonds.get(i).Fobj.getLightInli());
        
     }
        while(!finalnonds.isEmpty()) {
       
            int best = findbestindex1();
        NonDominateSolution nd = new NonDominateSolution();
               nd.setff(finalnonds.get(best).Fobj);
               int rank = finalnonds.get(best).getindex();
               nd.setindex(rank);
         
               sortnonds.add(nd);
               finalnonds.remove(best);
               
        }
        for(int e = 0; e<sortnonds.size();  e++){
        
            //System.out.println("last Sorted:" +sortnonds.get(e).Fobj.getLightIn() + ": " + sortnonds.get(e).Fobj.getLightInli());
            //System.out.println("Rank:" +sortnonds.get(e).getindex());
            
            
        }
        
        
        if (!sortnonds.isEmpty()){
        NonDominateSolution fnd = new NonDominateSolution();
          fnd.setff(sortnonds.get(0).Fobj);
          int rank = sortnonds.get(0).getindex();
          fnd.setindex(rank);
          finalnonds.add(fnd);
          
          //for(int r1 = 0 ; r1 < finalnonds.size();r1++){
              for(int e =1;e < sortnonds.size();e++){
          
             if(finalnonds.lastElement().Fobj.getLightInli() < sortnonds.get(e).Fobj.getLightInli()){
                 
                 
             
                   fnd = new NonDominateSolution();
                   fnd.setff(sortnonds.get(e).Fobj);
                   fnd.setindex(t);
                   finalnonds.add(fnd);
             }
          
          
          }}
        
         for(int e = 0; e< finalnonds.size();  e++){
        
            System.out.println("final nonds:" +finalnonds.get(e).Fobj.getLightIn() + ": " + finalnonds.get(e).Fobj.getLightInli());
            //System.out.println("final Rank:" +finalnonds.get(e).getindex());
            
            
        }
            
                
                
                         
                     
            
        
            
            
            
            //System.out.println("Rank:" +finalnonds.get(w).getindex());
            
            //System.out.println("final 0 :"+finalnonds.get(w).Fobj.getLightIn() + ": " + finalnonds.get(w).Fobj.getLightInli());
        
    }
     private int findbestindex1() {
        int b = 0;
        
       double maxValue = finalnonds.get(b).Fobj.getLightIn();
		
		for(int i=0; i<finalnonds.size(); i++) {
			if(finalnonds.get(i).Fobj.getLightIn() > maxValue) {
				b= i;
				maxValue = finalnonds.get(i).Fobj.getLightIn();
			}
		}
	//System.out.print(" best" + nonds.get(b).Fobj.getLightIn());
    
    return b;}

}
    


