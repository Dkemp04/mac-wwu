package Logik;

public class History {
	History nextItem;
	Point historyPoint;
	String historyText;
	/**
	 * Erzeugt ein neues Element und fügt dieses in eine bestehende History ein
	 * @param history Die bestehende History
	 * @param historyPoint Der neue Punkt der History
	 * @param historyText Der Text der History
	 */
	public void add(Point historyPoint, String historyText){
		setLastItem(new History(historyPoint, historyText));
	}
	/**
	 * Erzeugt ein neues Element als Ausgang einer History
	 * @param historyPoint Der Startpunkt der History
	 * @param historyText Der Text der History
	 */
	public History(Point historyPoint, String historyText){
		nextItem = null;
		this.historyPoint = historyPoint;
		this.historyText = historyText;
	}
	
	/**
	 * Fügt ein neues Element in eine History ein.
	 * @param newHistory Das neue Element
	 */
	private void setLastItem(History newHistory){
		if(nextItem == null)
			nextItem = newHistory;
		else
			nextItem.setLastItem(newHistory);
	}
	
	public String toString(){
		if(nextItem != null)
			return historyText+"\n"+nextItem.toString();
		else
			return historyText;
	}
}