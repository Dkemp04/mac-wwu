package Logik;

/**
 * Eine Datenhaltungsklasse f�r einen Punkt
 * @author s_pich02
 *
 */
public class Point {
	private double x;
	private double y;
	
	/**
	 * Erzeugt einen Punkt
	 * @param x X-Koordinate des Punktes
	 * @param y Y-Koordinate des Punktes
	 * @param problem Problem, in dem der Punkt ben�tigt wird
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
}
