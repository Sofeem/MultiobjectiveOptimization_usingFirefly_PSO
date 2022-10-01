/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiobjectfa;

/**
 *
 * @author SOFEEM
 */
class Sensor {
    private double[][] senpos;
public Sensor() {
		super();
		
	}
	public Sensor(double[][] senpos) {
		super();
		this.senpos = senpos;
	}

	public double[][] getSenPos() {
		return senpos;
	}

	public void setSenPos(double[][] senpos) {
		this.senpos = senpos;
	}
    
}