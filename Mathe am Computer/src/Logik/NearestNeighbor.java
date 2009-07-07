package Logik;

public class NearestNeighbor extends Methode{
	
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
			addHistory(lastPoint, problemElement[position], "Der Punkt "+problemElement[position]+" hat die Distanz "+distance+" zum vorherigen Punkt, damit die geringste Distanz. Hiermit wird er als nchstes in die Route aufgenommen.");
			lastPoint = problemElement[position];	
			addPointFront(position);
		}
	}
	
	public NearestNeighbor(Problem problem){
		super(problem);
	}	
	
}