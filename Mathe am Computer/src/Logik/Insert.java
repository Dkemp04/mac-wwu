package Logik;

import java.util.ArrayList;


// Füge das Element ein, durch das der Teilring am wenigsten Länge zunimmt

public class Insert extends Methode{
	
	/**
	 * Nächster Nachbar mit geringster Rundreiselänge
	 */
	
	
	public void run(){
		ArrayList<Point> tempSolution = new ArrayList<Point>();
		double currentLength = 0;
		Point lastPoint, part1 = startingPoint;
		tempSolution.add(startingPoint);
		while(this.unused >= 0)
		{
			//Gibt als minimum-Distanz "unendlich" an
			double distance = Double.MAX_VALUE;
			int position = 0;
			//Sucht zum letzten Punkt den nhsten nchsten Punkt
			for(int compare=0;compare<=unused;compare++){
				part1 = tempSolution.get(tempSolution.size()-1);
				for (int part2=0; part2 <= tempSolution.size()-1;part2++)
				{
					
					if(distance(problemElement[compare], part1, tempSolution.get(part2)) <= distance)
					{
						position = compare;
						distance = distance(problemElement[compare], part1, tempSolution.get(part2));
					}
					part1 = tempSolution.get(part2);
				}
			}
			currentLength += distance;
			//Setzt den letzten Punkt um und fgt ihn ein
			lastPoint = problemElement[position];		
			addHistory(lastPoint, "Anfügen des Punkt "+lastPoint+" ergibt den geringsten Längenzuwachs der Rundreise ("+currentLength+").");
			tempSolution.add(position, problemElement[position]);
			unused--;
		}
		solution = tempSolution.toArray(solution);
	}
	
	protected double distance(Point compare, Point part1, Point part2) {
		return (compare.Distance(part1)+compare.Distance(part2)-part1.Distance(part2));
	}
	
	public Insert(Problem problem){
		super(problem);
	}	
	
}
