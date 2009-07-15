package Methods;

import Logic.Point;
import Logic.Problem;

public class BranchAndBound extends Method
{
	private static final long serialVersionUID = -8304703391224488672L;

	public BranchAndBound(Problem problem){
		super(problem);
	}
	
	public void run(){
		
		//Erzeugt eine Lösung mit dem Branch&Bound-Alorithmus
		Point[] protoSolution = Branch(problemElement.clone(), new Point[solution.length], Double.MAX_VALUE, 0.0, 0);
		//Überträgt die Lösung in das Array
		for(int i=0; i< protoSolution.length;i++)
		{
			if(i==0)
				addHistory(startingPoint, protoSolution[i], "Der Punkt "+protoSolution[i]+" wird nach dem Branch-And-Bound Algorithmus aufgenommen. Er ist garantiert Teil der kuerzesten Route.");
			else
				addHistory(protoSolution[i-1], protoSolution[i], "Der Punkt "+protoSolution[i]+" wird nach dem Branch-And-Bound Algorithmus aufgenommen. Er ist garantiert Teil der kuerzesten Route.");
			addPoint(protoSolution[i]);
		}
	}
	
	public Point[] Branch(Point[] problem, Point[] route, double maxDistance, double distance, int elements){
		//Wenn die Route voll ist, wird abgebrochen
		if(elements == problem.length)
			if(distance+route[elements-1].Distance(startingPoint) >= maxDistance)
				return null;
			else
				return route;
		
		//Speichert eine vorläufige Lösung
		Point[] solution = null;
		//Geht die möglichen Pfade durch und überprüft deren Länge
		for(int i=0; i<problem.length-elements;i++){			
			//Speichert die Lösung dieses Pfades
			Point[] tempSolution = null;
			//Kopiert Problem und bisherige Route, um später damit arbeiten zu können
			Point[] workingProblem = problem.clone();
			Point[] workingRoute = route.clone();
			//Speichert die Länge des aktuellen Problems
			double workingDistance;
			//Fügt der Route einen Punkt hinzu (durch die Schleife insgesamt alle Punkte in jeder Reihenfolge)			
			workingRoute[elements] = problem[i];
			//Sortiert das Problem um
			workingProblem[i] = workingProblem[workingProblem.length-elements-1];
			//Bei mittigen Elementen wird die Distanz durch das Vorelement berechnet
			if(elements >= 1)
				workingDistance = distance+workingRoute[elements].Distance(workingRoute[elements-1]);
			//Bei anfangselementen wird die Distanz durch den Startpunkt berechnet
			else
				workingDistance = workingRoute[elements].Distance(startingPoint);
			//Wenn die Länge des bisherigen Problems kürzer als die bisher höchste Länge ist, wird weitergearbeitet
			if(workingDistance < maxDistance)
				tempSolution = Branch(workingProblem, workingRoute, maxDistance, workingDistance, elements+1);
			//Gibt es in diesem Pfad kürzere Lösungen, wird diese als neue Lösung angenommen.
			if(tempSolution != null){
				solution = tempSolution;
				maxDistance = solution[0].Distance(startingPoint);
				for(int j=1;j<problem.length;j++)
					maxDistance += solution[j].Distance(solution[j-1]);
				maxDistance+=solution[solution.length-1].Distance(startingPoint);
			}
		}
		//Gibt die Lösung zurück
		return solution;
	}
	
	public String getMethodName()
	{		return "Branch-and-Bound";}
	
	public String getMethodDescription()
	{		return "Branch-and-Bound (Verzweigung und Schranke) ist eine im Bereich Operations Research häufig verwendete mathematische Methode, deren Ziel darin besteht, für ein gegebenes ganzzahliges Optimierungsproblem eine beste Lösung zu finden. Branch-and-Bound führt auf einen Entscheidungsbaum, ist selbst aber kein spezielles Verfahren, sondern eine Behandlungsmethode, ein Meta-Verfahren. Für konkrete kombinatorische Optimierungsprobleme ergeben sich dementsprechend angepasste Branch-and-Bound-Algorithmen.";}
}