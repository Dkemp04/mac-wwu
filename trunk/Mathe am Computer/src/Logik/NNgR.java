package Logik;


// Nächster Nachbar (zum aktuellen Zweigende mit kürzestem Teilring)

public class NNgR extends Methode{
	
	/**
	 * Nächster Nachbar mit geringster Rundreiselänge
	 */
	
	
	public void run(){
		double currentLength = 0;
		Point lastPoint = startingPoint;
		while(this.unused >= 0)
		{
			//Gibt als minimum-Distanz "unendlich" an
			double distance = Double.MAX_VALUE;
			int position = 0;
			//Sucht zum letzten Punkt den nhsten nchsten Punkt
			for(int compare=0;compare<=unused;compare++){
				if(distance(compare) <= distance)
				{
					position = compare;
					distance = distance(compare);
				}
			}
			currentLength += distance;
			//Setzt den letzten Punkt um und fgt ihn ein
			addHistory(lastPoint, problemElement[position], "Anf�gen des Punkt "+problemElement[position]+" ergibt den geringsten L�ngenzuwachs der Rundreise ("+currentLength+").");
			lastPoint = problemElement[position];		
			addPointFront(position);
		}
	}
	
	protected double distance(int compare) {
		return solution[positionFront].Distance(problemElement[compare])+solution[positionEnd].Distance(problemElement[compare]);
	}
	
	public NNgR(Problem problem){
		super(problem);
	}
	
	public String getMethodName()
	{		return "Nearest Neighbor";}
}