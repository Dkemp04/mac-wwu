package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**Klasse, welche die graphische Repräsentation von Punkten bzw. Ellipsen des Problems darstellt
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class SingleEllipse
{
	//Deklarierung der Attribute
	private Ellipse2D singleEllipse;		//Graphische Repräsentation der Ellipse
	private double x, y, w, h;				//X -und Y-Koordinaten und Höhe und Breite der Ellipse
	
	/**Klasse, welche die graphische Repräsentation von Punkten des Problems darstellt
	 * @param x X-Koordinate des Kreises 
	 * @param y Y-Koordinate des Kreises
	 */
	public SingleEllipse(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.w = 1;
		this.h = 1;
		
		//Erzeugung der neuen Ellipse
		singleEllipse = new Ellipse2D.Double(x,y,w,h);
    }
	
	/**Methode, die mit Hilfe des anzugebenen Graphics-Objektes die Ellipse zeichnet
	 * @param g2D Graphics-Objekt, das zur Darstellung der Ellipse dient
	 */
	public void draw(Graphics2D g2D)
	{
		g2D.draw(singleEllipse);
	}
	
	/**Aktualisiert die x -und y-Koordinaten der Ellipse mit den übergebenen Werten
	 * @param x Neue x-Koordinate der Ellipse
	 * @param y Neue y-Koordinate der Ellipse
	 */
	public void updateData(double x, double y)
	{
		this.x = x;
		this.y = y;
		singleEllipse = new Ellipse2D.Double(x,y,w,h);
    }
	
	/**Methode, die überprüft, ob die Maus sich innerhalb der Ellipse befindet
	 * @param e Maus-Ereignis, welches zur Ermittlung der Maus-Koordinaten verwandt wird
	 * @return Gibt die Ellipse zurück, wenn sich die Maus innerhalb der Ellipse befindet, ansonsten eine null-Referenz
	 */
	public SingleEllipse select(MouseEvent e)
	{
		if (singleEllipse.contains(e.getX(), e.getY()))
			return this;
		return null;
	}
	
	//get-Methoden, welche die graphische Repräsentation (Ellipse2D), die X -oder Y-Koordinaten, Höhe oder Breite zurückgeben
	public Ellipse2D getEllipse(){return singleEllipse;}
	public double getX(){return x;}
	public double getY(){return y;}
	public double getHeight(){return h;}
	public double getWidth(){return w;}
}