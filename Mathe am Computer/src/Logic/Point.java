package Logic;

import java.io.Serializable;

/**
 * Eine Datenhaltungsklasse für einen Punkt
 * @author s_pich02
 *
 */
public class Point implements Serializable {

	private static final long serialVersionUID = -708337457805310089L;
	
	private double x;
	private double y;
	
	/**
	 * Erzeugt einen Punkt
	 * @param x X-Koordinate des Punktes
	 * @param y Y-Koordinate des Punktes
	 * @param problem Problem, in dem der Punkt benötigt wird
	 */
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gibt die Distanz zwischen zwei Punkten aus
	 * @param point Der Punkt zu dem die Distanz gemessen werden soll
	 * @return Die Distanz als Gleitkommazahl
	 */
	public double Distance(Point point){
		return Math.sqrt((point.x-this.x)*(point.x-this.x)+(point.y-this.y)*(point.y-this.y));
	}
	
	public String toString(){
		return "("+x+";"+y+")";
	}
	
	public double getX()
	{	return this.x;	}
	public double getY()
	{	return this.y;	}
}