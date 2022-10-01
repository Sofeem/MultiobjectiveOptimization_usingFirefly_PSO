/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiobjectfa;

/**
 *
 * @author SOFEEM
 */
public class Edge {
     public final Vertex target;
    public final double weight;
    
    public Edge(Vertex argTarget, double argWeight)
    { 
        target = argTarget; 
      weight = argWeight; }
}
