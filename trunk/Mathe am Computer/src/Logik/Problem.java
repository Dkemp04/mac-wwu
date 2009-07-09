package Logik;
import java.io.*;
import java.util.*;
import GUI.Graph;

/**
 * @author s_pich02
 *
 */
public class Problem implements Serializable {
	private static final long serialVersionUID = 3162051159131130553L;
	
	private Point startingPoint = null;
	private LinkedList<Point> points = new LinkedList<Point>();
	private Graph representation;
	
	/**
	 * Konstruktor, der die graphische Repr�sentation des Problems initialisiert
	 */
	public Problem ()
	{		representation = new Graph();}
	
	/**
	 * F�gt den Startpunkt hinzu. Der Startpunkt darf nicht in der Punktliste sein
	 * @param point Der Startpunkt
	 * @throws Exception Exception f�r den Fall eines Punktes, der bereits in der Zielliste liegt
	 */
	public void AddStartingPoint(Point point) throws Exception{
		if(points.contains(point))
			throw new Exception("Punkt bereits in der Menge");
		startingPoint = point;
	}
	
	/**
	 * F�gt einen Punkt zur Liste hinzu. Dieser muss vorher bei den �brigen Punkten initialisiert worden sein
	 * @param point
	 */
	public void AddPoint(Point point) throws Exception{
		if(point.equals(startingPoint))
			throw new Exception("Punkt bereits vorhanden.");
		points.add(point);
	}
	
	/**
	 * Gibt den Startpunkt zur�ck
	 * @return
	 */
	public Point getStartingPoint(){
		return startingPoint;
	}
	

	public LinkedList<Point> getPoints(){
		return points;
	}
	
	public Graph getGraph ()
	{
		return this.representation;}
}