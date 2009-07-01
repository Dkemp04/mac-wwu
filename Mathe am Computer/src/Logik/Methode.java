package Logik;

public abstract class Methode extends Thread{
	protected double length;
	protected Point[] problemElement;
	protected Point[] solution;
	protected Point startingPoint;
	protected int unused;
	protected History history;
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
		//Erzeugt das History-Objekt
		history = new History(startingPoint, "Startpunkt "+startingPoint+".");
		
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
	protected void addPoint(int position){
		solution[solution.length-unused-1] = problemElement[position];
		problemElement[position] = problemElement[unused];
		//Fall: Erster Punkt
        if(unused == solution.length-1)
        	length += startingPoint.Distance(solution[solution.length-unused-1]);
        //Fall: Mittlere Punkte
        else
        	length += solution[solution.length-unused-2].Distance(solution[solution.length-unused-1]);
        //Zusätzliche Distanz Endpunkte
        if(unused == 0)
        {
        	length += solution[solution.length-1].Distance(startingPoint);
        	addHistory(null, "Der letzte Punkt ist erreicht, die Route wird geschlossen. Ein zusätzlicher Weg von "+solution[solution.length-1].Distance(startingPoint)+" muss zurückgelegt werden.");
        	addHistory(null, "Die gesamte zurückgelegte Strecke beträgt "+length+".");
        }
        unused--;
	}
	
	/**
	 * Fügt den Punkt in die Lösung ein. Verwendet dabei statt dem Problemindex wirkliche Punkte
	 * Diese Methode dient dazu, Punkte bei Verfahren einzufügen, bei denen eine Vielzahl von Lösungen
	 * erzeugt wird und damit die eigentliche Position verloren geht
	 * @param addedPoint Der einzufügende Punkt
	 */
	protected void addPoint(Point addedPoint){
		solution[solution.length-unused-1] = addedPoint;
		//Fall: Erster Punkt
        if(unused == solution.length-1)
        	length += startingPoint.Distance(solution[solution.length-unused-1]);
        //Fall: Mittlere Punkte
        else
        	length += solution[solution.length-unused-2].Distance(solution[solution.length-unused-1]);
        //Zusätzliche Distanz Endpunkte
        if(unused == 0)
        {
        	length += solution[solution.length-1].Distance(startingPoint);
        	addHistory(null, "Der letzte Punkt ist erreicht, die Route wird geschlossen. Ein zusätzlicher Weg von "+solution[solution.length-1].Distance(startingPoint)+" muss zurückgelegt werden.");
        	addHistory(null, "Die gesamte zurückgelegte Strecke beträgt "+length+".");
        }
        unused--;
	}
	
	
	/**
	 * Fügt einen neuen History-Eintrag ein
	 * @param newPoint Der neue Punkt des History-Eintrages. Im Falle dass ein History-Eintrag ohne Punkt erzeugt wird muss der Parameter null übergeben werden
	 * @param newText Der Text des History-Eintrages.
	 */
	protected void addHistory(Point newPoint, String newText){
		history.add(newPoint, newText);
	}
	
	public String toString(){
		return history.toString();
	}
}
