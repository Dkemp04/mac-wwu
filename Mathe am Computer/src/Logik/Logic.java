package Logik;

import java.util.*;
import GUI.*;

/**
 * Klasse zur Kapselung der Gesch�ftslogik
 * @author s_pich02
 *
 */
public class Logic extends Thread{
	private ChildFrame display;
	private Problem problem;
	private LinkedList<Methode> methodes;
	private LinkedList<Methode> solutions;
	public Logic(){
		methodes = new LinkedList<Methode>();
		solutions = new LinkedList<Methode>();
	}
	
	public void setProblem(Problem problem){
		this.problem = problem;
	}
	
	public void setCallback(ChildFrame display){
		this.display=display;
	}
	
	/**
	 * F�gt eine Methode zur Bearbeitung hinzu.
	 * @param methode
	 */
	public void addMethode(Methode methode){
		if(!methodes.contains(methode))
			methodes.add(methode);
	}	
	
	/**
	 * L�st das Problem mit allen eingetragenen L�sungsverfahren
	 */
	public void run(){
		for(Methode methode : methodes)
			methode.start();
		while(!methodes.isEmpty())
		{
			for (int i = 0; i < methodes.size(); i++)
			{
				Methode m = methodes.get(i);
				if (m != null)
				{
					if(!m.isAlive() && !m.isInterrupted())
					{
						solutions.add(m);
						if(display != null)
						{
							display.logicCallback(m);
							display.repaint();
						}
						methodes.remove(m);
					}
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public Problem getProblem(){
		return problem;
	}
}