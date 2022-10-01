/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiobjectfa;

/**
 *
 * @author SOFEEM
 */
public class NonDominateSolution {
    
    private int rank;
    Firefly Fobj;
    
    
    
public void setindex(int rank) {
		this.rank = rank;
	}
       public int getindex() {
		return rank;
	}
    

	public Firefly getLocation() {
		return Fobj;
	}

	public void setff(Firefly Fobj) {
		this.Fobj = Fobj;
	}
       
        
    
    }
    

