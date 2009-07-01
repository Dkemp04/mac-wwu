package Logik;

public class BranchAndBound extends Methode{
	
	public BranchAndBound(Problem problem){
		super(problem);
	}
	
	public void run(){
		Point[] protoSolution = FirstBranch(problemElement.clone(), new Point[solution.length], Double.MAX_VALUE, 0.0, 0);
		for(int i=0; i< protoSolution.length;i++)
		{
			addHistory(protoSolution[i], "Der Punkt "+protoSolution[i]+" wird nach dem Branch-And-Bound Algorithmus aufgenommen. Er ist garantiert Teil der kürzesten Route.");
			addPoint(protoSolution[i]);
		}
	}
	public Point[] FirstBranch(Point[] problem, Point[] route, double maxDistance, double distance, int elements){
		//Wenn die Route voll ist, wird abgebrochen
		if(elements == problem.length)
			if(distance+route[elements-1].Distance(startingPoint) >= maxDistance)
				return null;
			else
				return route;
		
		Point[] solution = null;
		for(int i=0; i<problem.length-elements;i++){			
			Point[] tempSolution = null;
			Point[] workingProblem = problem.clone();
			Point[] workingRoute = route.clone();
			double workingDistance;
			workingRoute[elements] = problem[i];
			workingProblem[i] = workingProblem[workingProblem.length-elements-1];
			if(elements >= 1)
				workingDistance = distance+workingRoute[elements].Distance(workingRoute[elements-1]);
			else
				workingDistance = workingRoute[elements].Distance(startingPoint);
			if(workingDistance < maxDistance)
				tempSolution = Branch(workingProblem, workingRoute, maxDistance, workingDistance, elements+1);
			if(tempSolution != null){
				solution = tempSolution;
				maxDistance = solution[0].Distance(startingPoint);
				for(int j=1;j<problem.length;j++)
					maxDistance += solution[j].Distance(solution[j-1]);
				maxDistance+=solution[solution.length-1].Distance(startingPoint);
			}
		}
		return solution;
	}
	
	public Point[] Branch(Point[] problem, Point[] route, double maxDistance, double distance, int elements){
		//Wenn die Route voll ist, wird abgebrochen
		if(elements == problem.length)
			if(distance+route[elements-1].Distance(startingPoint) >= maxDistance)
				return null;
			else
				return route;
		
		Point[] solution = null;
		for(int i=0; i<problem.length-elements;i++){			
			Point[] tempSolution = null;
			Point[] workingProblem = problem.clone();
			Point[] workingRoute = route.clone();
			double workingDistance;
			workingRoute[elements] = problem[i];
			workingProblem[i] = workingProblem[workingProblem.length-elements-1];
			if(elements >= 1)
				workingDistance = distance+workingRoute[elements].Distance(workingRoute[elements-1]);
			else
				workingDistance = workingRoute[elements].Distance(startingPoint);
			if(workingDistance < maxDistance)
				tempSolution = Branch(workingProblem, workingRoute, maxDistance, workingDistance, elements+1);
			if(tempSolution != null){
				solution = tempSolution;
				maxDistance = solution[0].Distance(startingPoint);
				for(int j=1;j<problem.length;j++)
					maxDistance += solution[j].Distance(solution[j-1]);
				maxDistance+=solution[solution.length-1].Distance(startingPoint);
			}
		}
		return solution;
	}
}
