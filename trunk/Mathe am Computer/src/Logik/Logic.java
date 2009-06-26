package Logik;

import java.util.*;

/**
 * Klasse zur Kapselung der Gesch�ftslogik
 * @author s_pich02
 *
 */
public class Logic {
	private Problem problem;
	private LinkedList<Methode> methodes;
	public Logic(){
		methodes = new LinkedList<Methode>();
	}
	
	public void SetProblem(Problem problem){
		this.problem = problem;
	}
	
	/**
	 * F�gt eine Methode zur Bearbeitung hinzu.
	 * @param methode
	 */
	public void AddMethode(Methode methode){
		if(!methodes.contains(methode))
			methodes.add(methode);
	}
	
	/**
	 * Entfernt eine Bearbeitungsmethode
	 * @param methode
	 */
	public void RemoveMethode(Methode methode){
		if(methodes.contains(methode))
			methodes.remove(methode);
	}
	
	/**
	 * Entfernt die verwendeten Methoden
	 */
	public void ClearMethodes(){
		methodes = new LinkedList<Methode>();
	}
	
	
	/**
	 * L�st das Problem mit allen eingetragenen L�sungsverfahren
	 */
	public void Solve(){
		for(Methode methode : methodes)
			methode.start();
	}
	
	public Problem getProblem(){
		return problem;
	}
}
