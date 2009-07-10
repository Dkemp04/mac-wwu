package Logik;

import java.util.*;
public class MST extends Methode{
	protected History historyMST = new History(null, null,"Erzeugung des Problems.");
	/**
	 * Erzeugt einen MST
	 * @return Gibt den ersten Punkt eines MST-Baums aus
	 */
	public void run(){
		LinkedList<Cluster> clusterList = new LinkedList<Cluster>();
		for(Point problemPoint : problemElement)
			clusterList.add(new Cluster(problemPoint));
		clusterList.add(new Cluster(startingPoint));
		//Verbindet Cluster, bis nur noch einer existiert
		while(clusterList.size() != 1){
			//Die Distanz
			double distance = Double.MAX_VALUE;
			//Die Elemente zum Verknüpfen der Cluster
			MSTPoint bridge = null;
			MSTPoint bridgeEnd = null;
			//Die aktiven Cluster
			Cluster cluster1 = null;
			Cluster cluster2 = null;

			Iterator<Cluster> clusterItr = clusterList.iterator();
			while(clusterItr.hasNext()){
				Cluster itr1Object = clusterItr.next();
				Iterator<Cluster> clusterItr2 = clusterList.iterator();
				Cluster itr2Object = clusterItr2.next();
				//Wechselt bis zum Cluster nach dem Originalcluster
				while(itr1Object != itr2Object){
					itr2Object = clusterItr2.next();
				}
				//Überprüft für die Folgecluster die Distanz zum Originalcluster
				while(clusterItr2.hasNext()){
					itr2Object = clusterItr2.next(); 
					Pair temp = itr1Object.distance(itr2Object);
					if(((Double)temp.getThird()).doubleValue() < distance){
						bridge = (MSTPoint)temp.getFirst();
						bridgeEnd = (MSTPoint)temp.getSecond();
						distance = (Double)temp.getThird();
						cluster1 = itr1Object;
						cluster2 = itr2Object;
					}
				}
			}
			//Verbindet die beiden Cluster mit der kürzesten Distanz
			cluster1.combine(cluster2, bridge, bridgeEnd);
			//Entfernt den alten Cluster
			clusterList.remove(cluster2);
		}
		//Gibt den ersten Punkt des MST zurück
		history = historyMST;
//		return clusterList.getFirst().getMST();
	}
	
	public String getMethodName()
	{			return "Minimal Spanning Tree";}
	
	public MST(Problem problem){
		super(problem);
	}
	
	private class MSTPoint{
		Point point;
		LinkedList<MSTPoint> connected = new LinkedList<MSTPoint>();
		Point lastPoint;
		public MSTPoint(Point point){
			this.point = point;
		}
		/**
		 * Berechnet die Distanz zweier MST Punkte
		 * @param point
		 * @return
		 */
		public double distance(MSTPoint point){
			return this.point.Distance(point.point);
		}
		
		public void addConnect(MSTPoint point){
			connected.add(point);
		}
	}
	/**
	 * 
	 * Cluster. Ein Cluster ist eine verbundene Punktemenge.
	 * @author steffen
	 *
	 */
	private class Cluster{
		LinkedList<MSTPoint> clusterPoints = new LinkedList<MSTPoint>();
		public Cluster(Point initialPoint){
			clusterPoints.add(new MSTPoint(initialPoint));
		}
		
		/**
		 * Kombiniert zwei Cluster
		 * @param combine Der zweite Cluster
		 * @param bridge Das erste Brückenelement
		 * @param bridge Das zweite Brückenelement
		 */
		public void combine(Cluster combine, MSTPoint bridge, MSTPoint bridgeEnd){
			addBridge(bridge, bridgeEnd);
			for(MSTPoint point : combine.clusterPoints)
				clusterPoints.add(point);
		}
		
		/**
		 * Bindet das Element bridge als Brückenelement an das Cluster
		 * @param bridge Das erste Brückelement.
		 * @param bridgeEnd Das zweite Brückelement.
		 */
		public void addBridge(MSTPoint bridge, MSTPoint bridgeEnd){
			
			bridgeEnd.addConnect(bridge);
			bridge.addConnect(bridgeEnd);
			historyMST.add(bridge.point, bridgeEnd.point, "Verbindet zwei Cluster über die Punkte "+bridge+" und "+bridgeEnd+". Diese haben die minimale Distanz. Diese beträgt "+bridge.distance(bridgeEnd)+".");
		}
		
		/**
		 * Berechnet die Distanz zwischen zwei Clustern
		 * @param cluster Der zweite Cluster
		 * @return Die Distanz zwischen den Clustern und die Brückenelemente
		 */
		public Pair distance(Cluster cluster){
			double distance = Double.MAX_VALUE;
			MSTPoint bridge = null;
			MSTPoint bridgeEnd = null;
			for(MSTPoint point : clusterPoints){
				for(MSTPoint otherPoint : cluster.clusterPoints){
					if(point.distance(otherPoint) < distance){
						distance = point.distance(otherPoint);
						bridge = otherPoint;	
						bridgeEnd = point;
					}
				}
			}				
			return new Pair(bridge, bridgeEnd, distance);
		}
		
		/**
		 * Gibt den ersten Punkt eines Clusters zurück (aus dem sich der MST ableitet)
		 * @return Der erste Punkt eines MST-Algorithmus
		 */
		public MSTPoint getMST(){return clusterPoints.getFirst();}
	}
}
