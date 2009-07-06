package Logik;

public abstract class Methode extends Thread{
	protected double length;
	protected Point[] problemElement;
	protected Point[] solution;
	protected Point startingPoint;
	protected int unused;
	protected int positionFront;
	protected int positionEnd;
	protected History history;
	public Methode(Problem problem){
		this.startingPoint = problem.getStartingPoint();
		unused = problem.getPoints().size()-1;
		positionEnd = unused;
		positionFront = 0;
		//erzeugt einen Lsungsvektor zum speichern der Lsung
		problemElement = new Point[unused+1];
		
		//Ld die Punkte in ein Array zur besseren Verarbeitbarkeit
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
	 * Gibt die Lnge der Lsung zurck
	 * @return Gibt die Lnge zurck
	 */
	public double getLength(){
		return length;
	}
	
	
	/**
	 * Fgt den Punkt in die Lsung ein. Dabei werden die Punkte am Anfangsende der Lsung eingefgt 
	 * @param addedPoint Der einzufgende Punkt
	 */
	protected void addPointFront(int addedPoint){
		//Die neue Lsung wird eingefgt. 		
		solution[positionFront] = problemElement[addedPoint];
// 		Element in problemElement rechts von unused muss gerettet werden
		problemElement[addedPoint] = problemElement[unused];
		if(positionFront == 0)
			length += startingPoint.Distance(solution[positionFront]);
		//Fall: Mittlere Punkte
	    else
	       	length += solution[solution.length-unused-2].Distance(solution[solution.length-unused-1]);
		//Zustzliche Distanz Endpunkte
        if(unused == 0)
        {
        	if(positionFront != solution.length-1)
        		length += solution[positionFront].Distance(solution[positionFront+1]);
        	else
        		length += solution[solution.length-1].Distance(startingPoint);
        	double tempDistance = (positionFront == 0)?(startingPoint.Distance(solution[positionFront])):(solution[solution.length-unused-2].Distance(solution[solution.length-unused-1]));
        	addHistory(null, "Der letzte Punkt ist erreicht, die Route wird geschlossen. Ein zustzlicher Weg von "+tempDistance+" muss zurckgelegt werden.");
        	addHistory(null, "Die gesamte zurckgelegte Strecke betrgt "+length+".");
        }
        //Korrigiert die Zeiger
        positionFront++;
        unused--;
	}
	

	/**
	 * Fgt den Punkt in die Lsung ein. Dabei werden die Punkte am Ende der Lsung eingefgt 
	 * @param addedPoint Index des einzufügenden Punkts in ProblemElements
	 */
	protected void addPointEnd(int addedPoint){
		//Die neue Lsung wird eingefgt.
		solution[positionEnd] = problemElement[addedPoint];
		//Das Problem wird überschrieben
		problemElement[addedPoint] = problemElement[unused];
		//Erster Punkt von hinten: Daher Distanzberechnung ber Startpunkt
		if(positionEnd == solution.length-1)
			length += startingPoint.Distance(solution[positionEnd]);
		else
			length += solution[positionEnd].Distance(solution[positionEnd+1]);
		//Zustzliche Distanz Endpunkte
		if(unused == 0)
        {
			//Ist der Punkt mittig, wird der letzte Punkt im Array gesucht
        	if(positionEnd != 0)
        		length += solution[positionEnd].Distance(solution[positionEnd-1]);
        	//Andernfalls ist es der Startpunkt
        	else
        		length += solution[0].Distance(startingPoint);
        	double tempDistance = (positionEnd == solution.length-1)?(startingPoint.Distance(solution[positionEnd])):solution[positionEnd].Distance(solution[positionEnd-1]);
        	addHistory(null, "Der letzte Punkt ist erreicht, die Route wird geschlossen. Ein zustzlicher Weg von "+tempDistance+" muss zurckgelegt werden.");
        	addHistory(null, "Die gesamte zurckgelegte Strecke betrgt "+length+".");
        }
        //Korrigiert die Zeiger
        positionEnd--;
        unused--;
	}
	
	/**
	 * Fgt einen neuen History-Eintrag ein
	 * @param newPoint Der neue Punkt des History-Eintrages. Im Falle dass ein History-Eintrag ohne Punkt erzeugt wird muss der Parameter null bergeben werden
	 * @param newText Der Text des History-Eintrages.
	 */
	protected void addHistory(Point newPoint, String newText){
		history.add(newPoint, newText);
	}
	
	public String toString(){
		return history.toString();
	}
}
