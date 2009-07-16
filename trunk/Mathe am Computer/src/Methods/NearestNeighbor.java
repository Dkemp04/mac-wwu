package Methods;

import Logic.*;

/*
 * @author Steffen Pichler, Christian Vogel, Veysel Aksak, Daniel Kemper
 */
public class NearestNeighbor extends Method
{
	private static final long serialVersionUID = 6012980783640788815L;

	/**
	 * Die Run-Methode des Lsungsverfahrens
	 */
	public void run(){
		Point lastPoint = startingPoint;
		while(this.unused >= 0)
		{
			//Gibt als minimum-Distanz "unendlich" an
			double distance = Double.MAX_VALUE;
			int position = 0;
			//Sucht zum letzten Punkt den nähsten nächsten Punkt
			for(int compare=0;compare<=unused;compare++){
				if(lastPoint.Distance(problemElement[compare]) <= distance)
				{
					position = compare;
					distance = lastPoint.Distance(problemElement[compare]);
				}
			}
			//Setzt den letzten Punkt um und fügt ihn ein	
			addHistory(lastPoint, problemElement[position], "Der Punkt "+problemElement[position]+" hat die Distanz "+distance+" zum vorherigen Punkt, damit die geringste Distanz. Hiermit wird er als naechstes in die Route aufgenommen.");
			lastPoint = problemElement[position];	
			addPointFront(position);
		}
	}
	
	public NearestNeighbor(Problem problem){
		super(problem);
	}
	
	public String getMethodName()
	{		return "Nearest Neighbor";}
	
	public String getMethodDescription()
	{	return "Die Nearest-Neighbor-Heuristik ist ein heuristisches Eröffnungsverfahren aus der Graphentheorie und wird zur Approximation einer Lösung des Problem des Handlungsreisenden verwendet.";}
}