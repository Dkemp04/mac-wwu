package Logik;

import java.awt.Point;

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
		history = new History(startingPoint, "Startpunkt "+startingPoint+".");
		
	}
	
	/**
	 * Gibt die L�nge der L�sung zur�ck
	 * @return Gibt die L�nge zur�ck
	 */
	public double getLength(){
		return length;
	}
	
	
	/**
	 * F�gt den Punkt in die L�sung ein. Dabei werden die Punkte am Anfangsende der L�sung eingef�gt 
	 * @param addedPoint Der einzuf�gende Punkt
	 */
	protected void addPointFront(Point addedPoint){
		//Die neue L�sung wird eingef�gt. 
		solution[positionFront] = addedPoint;
		if(positionFront == 0)
			length += startingPoint.distance(solution[positionFront])
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
        	addHistory(null, "Der letzte Punkt ist erreicht, die Route wird geschlossen. Ein zus�tzlicher Weg von "+(solution[positionFront].Distance(solution[positionFront+1]))?solution[positionFront].Distance(solution[positionFront+1]):solution[solution.length-1].Distance(startingPoint)+" muss zur�ckgelegt werden.");
        	addHistory(null, "Die gesamte zur�ckgelegte Strecke betr�gt "+length+".");
        }
        //Korrigiert die Zeiger
        positionFront++;
        unused--;
	}
	

	/**
	 * F�gt den Punkt in die L�sung ein. Dabei werden die Punkte am Ende der L�sung eingef�gt 
	 * @param addedPoint Der einzuf�gende Punkt
	 */
	protected void addPointEnd(Point addedPoint){
		//Die neue L�sung wird eingef�gt.
		solution[positionEnd] = addedPoint;
		//Erster Punkt von hinten: Daher Distanzberechnung �ber Startpunkt
		if(positionEnd == solution.length-1)
			length += startingPoint.distance(solution[positionEnd])
		else
			length += solution[positionEnd].distance(positionEnd+1);
		//Zus�tzliche Distanz Endpunkte
		if(unused == 0)
        {
			//Ist der Punkt mittig, wird der letzte Punkt im Array gesucht
        	if(positionEnd != 0)
        		length += solution[positionEnd].Distance(solution[positionEnd-1]);
        	//Andernfalls ist es der Startpunkt
        	else
        		length += solution[0].Distance(startingPoint);
        	addHistory(null, "Der letzte Punkt ist erreicht, die Route wird geschlossen. Ein zus�tzlicher Weg von "+(positionEnd != 0)?solution[positionEnd].Distance(solution[positionEnd-1]):solution[0].Distance(startingPoint)+" muss zur�ckgelegt werden.");
        	addHistory(null, "Die gesamte zur�ckgelegte Strecke betr�gt "+length+".");
        }
        //Korrigiert die Zeiger
        positionEnd--;
        unused--;
	}
	
	/**
	 * F�gt einen neuen History-Eintrag ein
	 * @param newPoint Der neue Punkt des History-Eintrages. Im Falle dass ein History-Eintrag ohne Punkt erzeugt wird muss der Parameter null �bergeben werden
	 * @param newText Der Text des History-Eintrages.
	 */
	protected void addHistory(Point newPoint, String newText){
		history.add(newPoint, newText);
	}
	
	public String toString(){
		return history.toString();
	}
}
