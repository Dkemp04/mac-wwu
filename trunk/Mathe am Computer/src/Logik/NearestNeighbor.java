package Logik;

public class NearestNeighbor extends Methode{
	
	/**
	 * Die Run-Methode des Lösungsverfahrens
	 */
	public void start(){
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
			lastPoint = problemElement[position];
			AddPoint(position);
		}
	}
	
	public NearestNeighbor(Problem problem){
		super(problem);
	}
}
