package Logic;
import java.io.*;
import java.util.*;

/**
 * @author s_pich02
 *
 */
public class Problem implements Serializable {
	private static final long serialVersionUID = 3162051159131130553L;
	
	private Point startingPoint = null;
	private LinkedList<Point> points = new LinkedList<Point>();
	
	/**
	 * Fügt den Startpunkt hinzu. Der Startpunkt darf nicht in der Punktliste sein
	 * @param point Der Startpunkt
	 * @throws Exception Exception für den Fall eines Punktes, der bereits in der Zielliste liegt
	 */
	public void addStartingPoint(Point point) throws Exception{
		if(points.contains(point))
			throw new Exception("Punkt bereits in der Menge");
		startingPoint = point;
	}
	
	/**
	 * Fügt einen Punkt zur Liste hinzu. Dieser muss vorher bei den übrigen Punkten initialisiert worden sein
	 * @param point
	 */
	public void addPoint(Point point) throws Exception{
		if(point.equals(startingPoint))
			throw new Exception("Punkt bereits vorhanden.");
		points.add(point);
	}
	
	/**
	 * Gibt den Startpunkt zurück
	 * @return
	 */
	public Point getStartingPoint(){
		return startingPoint;
	}
	

	public LinkedList<Point> getPoints(){
		return points;
	}
}