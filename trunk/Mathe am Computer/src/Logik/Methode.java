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
		//erzeugt einen L�sungsvektor zum speichern der L�sung
		problemElement = new Point[unused+1];
		
		//L�d die Punkte in ein Array zur besseren Verarbeitbarkeit
		int position = 0;
		for(Point point : problem.getPoints())
		{
			problemElement[position] = point;
			position++;
		}
		this.solution = new Point[unused+1];	
		//Erzeugt das History-Objekt
		history = new History(startingPoint, null, "Startpunkt "+startingPoint+".");
		
	}
	
	/**
	 * Gibt die L�nge der L�sung zurck
	 * @return Gibt die L�nge zurck
	 */
	public double getLength(){
		return length;
	}
	
	
	/**
	 * F�gt den Punkt in die L�sung ein. Dabei werden die Punkte am Anfangsende der L�sung eingef�gt 
	 * @param addedPoint Die Position des einzuf�genden Punktes in das Array
	 */
	protected void addPointFront(int addedPoint){
		//Die neue L�sung wird eingefgt. 		
		solution[positionFront] = problemElement[addedPoint];
		//Das Problem wird �berschrieben
		problemElement[addedPoint] = problemElement[unused];
		//Fall: Erster Punkt von vorne
		if(positionFront == 0)
			length += startingPoint.Distance(solution[positionFront]);
		//Fall: Mittlere Punkte
	    else
	       	length += solution[solution.length-unused-2].Distance(solution[solution.length-unused-1]);
		//Zus�tzliche Distanz Endpunkte
        if(unused == 0)
        {
        	if(positionFront != solution.length-1)
        		length += solution[positionFront].Distance(solution[positionFront+1]);
        	else
        		length += solution[solution.length-1].Distance(startingPoint);
        	double tempDistance = (positionFront == 0)?(startingPoint.Distance(solution[positionFront])):(solution[solution.length-unused-2].Distance(solution[solution.length-unused-1]));
        	addHistory(null, null, "Der letzte Punkt ist erreicht, die Route wird geschlossen. Ein zustzlicher Weg von "+tempDistance+" muss zurckgelegt werden.");
        	addHistory(null, null, "Die gesamte zurckgelegte Strecke betrgt "+length+".");
        }
        //Korrigiert die Zeiger
        positionFront++;
        unused--;
	}
	

	/**
	 * F�gt den Punkt in die L�sung ein. Dabei werden die Punkte am Ende der L�sung eingef�gt 
	 * @param addedPoint Index des einzuf�genden Punkts in ProblemElements
	 */
	protected void addPointEnd(int addedPoint){
		//Die neue L�sung wird eingef�gt.
		solution[positionEnd] = problemElement[addedPoint];
		//Das Problem wird �berschrieben
		problemElement[addedPoint] = problemElement[unused];
		//Erster Punkt von hinten: Daher Distanzberechnung �ber Startpunkt
		if(positionEnd == solution.length-1)
			length += startingPoint.Distance(solution[positionEnd]);
		else
			length += solution[positionEnd].Distance(solution[positionEnd+1]);
		//Zus�tzliche Distanz Endpunkte
		if(unused == 0)
        {
			//Ist der Punkt mittig, wird der letzte Punkt im Array gesucht
        	if(positionEnd != 0)
        		length += solution[positionEnd].Distance(solution[positionEnd-1]);
        	//Andernfalls ist es der Startpunkt
        	else
        		length += solution[0].Distance(startingPoint);
        	double tempDistance = (positionEnd == solution.length-1)?(startingPoint.Distance(solution[positionEnd])):solution[positionEnd].Distance(solution[positionEnd-1]);
        	addHistory(null, null, "Der letzte Punkt ist erreicht, die Route wird geschlossen. Ein zustzlicher Weg von "+tempDistance+" muss zurckgelegt werden.");
        	addHistory(null, null, "Die gesamte zurckgelegte Strecke betrgt "+length+".");
        }
        //Korrigiert die Zeiger
        positionEnd--;
        unused--;
	}


	/**
	 * F�gt den Punkt in die L�sung ein. Dabei werden die Punkte am Anfangsende der L�sung eingef�gt.
	 * Im Gegensatz zu addedPointFront/End wird das Problem nicht umsortiert. Diese Methode sollte daher nur verwendet werden,
	 * Wenn auf eine andere Weise ein L�sungsarray eingespeichert werden soll 
	 * @param addedPoint Der einzuf�gende Punkt
	 */
	protected void addPoint(Point addedPoint){
		//Die neue L�sung wird eingef�gt. 		
		solution[positionFront] = addedPoint;
		if(positionFront == 0)
			length += startingPoint.Distance(solution[positionFront]);
		//Fall: Mittlere Punkte
	    else
	       	length += solution[solution.length-unused-2].Distance(solution[solution.length-unused-1]);
		//Zus�tzliche Distanz Endpunkte
        if(unused == 0)
        {
        	if(positionFront != solution.length-1)
        		length += solution[positionFront].Distance(solution[positionFront+1]);
        	else
        		length += solution[solution.length-1].Distance(startingPoint);
        	double tempDistance = (positionFront == 0)?(startingPoint.Distance(solution[positionFront])):(solution[solution.length-unused-2].Distance(solution[solution.length-unused-1]));
        	addHistory(null, null, "Der letzte Punkt ist erreicht, die Route wird geschlossen. Ein zustzlicher Weg von "+tempDistance+" muss zurckgelegt werden.");
        	addHistory(null, null, "Die gesamte zurckgelegte Strecke betrgt "+length+".");
        }
        //Korrigiert die Zeiger
        positionFront++;
        unused--;
	}
	
	/**
	 * F�gt einen neuen History-Eintrag ein
	 * @param oldPoint Der alte Punkt des History-Eintrages. Dient gemeinsam mit newPoint zur Bestimmung einer Strecke
	 * @param newPoint Der neue Punkt des History-Eintrages. 
	 * @param newText Der Text des History-Eintrages.
	 */
	protected void addHistory(Point oldPoint, Point newPoint, String newText){
		history.add(oldPoint, newPoint, newText);
	}
	
	public String toString(){
		return history.toString();
	}
}
