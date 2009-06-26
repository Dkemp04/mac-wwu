package Logik;

public abstract class Methode extends Thread{
	protected double length;
	protected Point[] problemElement;
	protected Point[] solution;
	protected Point startingPoint;
	protected int unused;
	public Methode(Problem problem){
		this.startingPoint = problem.getStartingPoint();
		unused = problem.getPoints().size()-1;
		//erzeugt einen Lösungsvektor zum speichern der Lösung
		problemElement = new Point[unused+1];
		
		//Läd die Punkte in ein Array zur besseren Verarbeitbarkeit
		int position = 0;
		for(Point point : problem.getPoints())
		{
			problemElement[position] = point;
			position++;
		}
		this.solution = new Point[unused+1];
	}
	
	/**
	 * Gibt die Länge der Lösung zurück
	 * @return Gibt die Länge zurück
	 */
	public double getLength(){
		return length;
	}
	
	/**
	 * Fügt den Punkt in die Lösung ein
	 * @param position Die Position des Punktes im Array
	 */
	protected void AddPoint(int position){
		solution[solution.length-unused-1] = problemElement[position];
		problemElement[position] = problemElement[unused];
        if(unused == solution.length-1)
        	length += startingPoint.Distance(solution[solution.length-unused-1]);
        else
        	length += solution[solution.length-unused-2].Distance(solution[solution.length-unused-1]);
        if(unused == 0)
        	length += solution[solution.length-1].Distance(startingPoint);
        unused--;
	}
}
