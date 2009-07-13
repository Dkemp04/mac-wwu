package Logic;

import java.io.*;
import java.util.*;
import GUI.Main.ChildFrame;
import Methods.Method;

/**
 * Klasse zur Kapselung der Gesch�ftslogik
 * @author s_pich02
 *
 */
public class Logic extends Thread implements Serializable
{
	private static final long serialVersionUID = -3213427962110288382L;
	
	transient private ChildFrame display;
	private Problem problem;
	private LinkedList<Method> methodes;
	private LinkedList<Method> solutions;
	public Logic(){
		methodes = new LinkedList<Method>();
		solutions = new LinkedList<Method>();
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
	public void addMethode(Method methode){
		if(!methodes.contains(methode))
			methodes.add(methode);
	}	
	
	/**
	 * L�st das Problem mit allen eingetragenen L�sungsverfahren
	 */
	public void run(){
		for(Method methode : methodes)
			methode.start();
		while(!methodes.isEmpty())
		{
			for (int i = 0; i < methodes.size(); i++)
			{
				Method m = methodes.get(i);
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