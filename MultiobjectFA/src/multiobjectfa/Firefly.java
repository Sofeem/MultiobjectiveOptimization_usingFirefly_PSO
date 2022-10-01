/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiobjectfa;

/**
 *
 * @author SOFEEM
 */
public class Firefly {
    private double lightintensity;
    private double lightintensity1;
    private Location location;
    private Sensor sen;
    //private double fitnessvalue;
    
    
    
    
    public Firefly() {
		super();
	}
    
    public Firefly(Sensor sen, double fitnessValue, Location location,double lightintensity,double lightintensity1) {
		super();
		//this.fitnessvalue = fitnessValue;
		this.location = location;
                this.lightintensity = lightintensity;
                this.lightintensity1 = lightintensity1;
                this.sen=sen;
	}

    

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
       
        
        public void setSenpos(Sensor senpos){
           
            this.sen=senpos;
        
        }
        public Sensor getsensor(){
            
            return sen;
        }
       public void setLightIn(Sensor sen){
           lightintensity = Coverage.evaluate(sen);
             this.lightintensity = lightintensity;
                }
        
        
        public double getLightIn(){
            //lightintensity = Coverage.evaluate(sen);
            //lightintensity1 = (Lifetime.evaluate(sen)*100);
            //System.out.println("lifetime : " + lightintensity);
            //System.out.println("Lifetime : " + lightintensity1);
            //double light = (lightintensity+lightintensity1);
            //System.out.println("Lifetime+Coverage : " + (lightintensity+lightintensity1)/200);
            return (lightintensity);
        
        }
        public void setLightInli(Sensor sen){
             lightintensity1 = (Lifetime.evaluate(sen));
             this.lightintensity1 = lightintensity1;
                }
         public double getLightInli(){
            
           
            //System.out.println("lifetime : " + lightintensity);
            //System.out.println("Lifetime : " + lightintensity1);
            //double light = (lightintensity+lightintensity1)/200;
            //System.out.println("Lifetime+Coverage : " + (lightintensity+lightintensity1)/200);
            return (lightintensity1);
        
        }
        
        
        //public double getlinlif(Sensor sen){
        
        //lightintensity1 = Lifetime.evaluate(sen);
        
        //return lightintensity1;}
        
        
   
    
}

