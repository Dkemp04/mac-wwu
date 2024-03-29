package Methods;

import java.util.*;
import Logic.*;

/**F�ge das Element ein, durch das der Teilring am wenigsten L�nge zunimmt
 * @author Steffen Pichler, Christian Vogel, Veysel Aksak, Daniel Kemper
 */
public class Insert extends Method
{
	private static final long serialVersionUID = -7999385348898024575L;

	/**
	 * Nächster Nachbar mit geringster Rundreiselänge
	 */
	
	
	public void run(){
		ArrayList<Point> tempSolution = new ArrayList<Point>();
		double currentLength = 0;
		Point lastPoint = startingPoint, part1 = startingPoint;
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
			addHistory(lastPoint, problemElement[position], "Anfuegen des Punkt "+problemElement[position]+" ergibt den geringsten Laengenzuwachs der Rundreise ("+currentLength+").");
			//Setzt den letzten Punkt um und fgt ihn ein
			lastPoint = problemElement[position];		
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
	
	public String getMethodName()
	{	return "Nearest Insert";}

	public String getMethodDescription()
	{	return "Die Nearest-Insertion-Heuristik (NEARIN), ist eine Einf�ge-Heuristik und damit ein heuristisches Er�ffnungsverfahren aus der Graphentheorie. Es dient zur Approximation einer guten L�sung des Problem des Handlungsreisenden; Ziel ist es also, eine m�glichst kurze Rundreise durch alle Knoten des Graphen zu finden.";}
}
