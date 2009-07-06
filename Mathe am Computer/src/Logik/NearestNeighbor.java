package Logik;

public class NearestNeighbor extends Methode{
	
	/**
	 * Die Run-Methode des L�sungsverfahrens
	 */
	public void run(){
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
			addHistory(lastPoint, "Der Punkt "+lastPoint+" hat die Distanz "+distance+" zum vorherigen Punkt, damit die geringste Distanz. Hiermit wird er als n�chstes in die Route aufgenommen.");
			addPointFront(problem[position]);
		}
	}
	
	public NearestNeighbor(Problem problem){
		super(problem);
	}	
	
}
