package Logik;

public class NearestNeighbor extends Methode{
	
	/**
	 * Die Run-Methode des L�sungsverfahrens
	 */
	public void start(){
		Point lastPoint = startingPoint;
		while(this.unused >= 0)
		{
			//Gibt als minimum-Distanz "unendlich" an
			double distance = Double.MAX_VALUE;
			int position = 0;
			//Sucht zum letzten Punkt den n�hsten n�chsten Punkt
			for(int compare=0;compare<=unused;compare++){
				if(lastPoint.Distance(problemElement[compare]) <= distance)
				{
					position = compare;
					distance = lastPoint.Distance(problemElement[compare]);
				}
			}
			//Setzt den letzten Punkt um und f�gt ihn ein
			lastPoint = problemElement[position];
			AddPoint(position);
		}
	}
	
	public NearestNeighbor(Problem problem){
		super(problem);
	}
}
