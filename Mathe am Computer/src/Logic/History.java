package Logic;

import java.io.*;

public class History implements Serializable
{
	private static final long serialVersionUID = -5920980291138882480L;
	
	private History prevItem, nextItem;
	private Point historyPoint, historyPrevPoint;
	private String historyText;
	/**
	 * Erzeugt ein neues Element und fügt dieses in eine bestehende History ein
	 * @param history Die bestehende History
	 * @param historyPoint Der neue Punkt der History
	 * @param historyText Der Text der History
	 */
	public void add(Point historyPoint, Point historyPoint2, String historyText){
		setLastItem(new History(historyPoint, historyPoint2, historyText));
	}
	/**
	 * Erzeugt ein neues Element als Ausgang einer History
	 * @param historyPoint Der Startpunkt der History
	 * @param historyText Der Text der History
	 */
	public History(Point historyPoint, Point historyPoint2, String historyText){
		nextItem = null;
		this.historyPrevPoint = historyPoint2;
		this.historyPoint = historyPoint;
		this.historyText = historyText;
	}
	
	/**
	 * Fügt ein neues Element in eine History ein.
	 * @param newHistory Das neue Element
	 */
	private void setLastItem(History newHistory){
		if(nextItem == null)
		{
			nextItem = newHistory;
			newHistory.setPrevItem(this);
		}
		else
			nextItem.setLastItem(newHistory);
	}
	
	public String toString(){
		if(nextItem != null)
			return historyText+"\n"+nextItem.toString();
		else
			return historyText;
	}

	/**
	 * Gibt den Anfang einer Linie zurück
	 * @return Der Anfang der aktuellen Linie
	 */
	public Point getLineStart(){
		return historyPrevPoint;
	}
	
	/**
	 * Gibt das Ende einer Linie zurück
	 * @return Das Ende der aktuellen Linie
	 */
	public Point getLineEnd(){
		return historyPoint;
	}
	
	public void setPrevItem (History prevItem)
	{
		this.prevItem = prevItem;
	}
	
	public History getPrev ()
	{
		if (prevItem != null)
			return this.prevItem;
		return null;
	}
	
	/**
	 * Gibt das nächste History-Item zurück
	 * @return Gibt das nächste History-Item zurück
	 */
	public History getNext()
	{
		return nextItem;
	}
}