package Methods;

import Logic.Point;
import Logic.Problem;
import junit.framework.TestCase;

/*
 * @author Steffen Pichler, Christian Vogel, Veysel Aksak, Daniel Kemper
 */
public class NearestTest extends TestCase
{
	public void testNearest(){
		Problem problem = new Problem();
		try{
			problem.addPoint(new Point(2.0, 3.0));
			problem.addPoint(new Point(31.0, 4.0));
			problem.addPoint(new Point(12.0, 7.0));
			problem.addPoint(new Point(8.0, 12.0));
			problem.addPoint(new Point(11.0, 1.0));
			problem.addPoint(new Point(56.0, 5.0));
			problem.addPoint(new Point(3.0, 3.0));
			problem.addPoint(new Point(6.0, 4.0));
			problem.addPoint(new Point(8.0, 9.0));
			problem.addStartingPoint(new Point(11.0, 11.0));
			NearestNeighbor neighbor = new NearestNeighbor(problem);
			BranchAndBound branch = new BranchAndBound(problem);
			branch.start();
			neighbor.start();
			while(neighbor.isAlive() || branch.isAlive()){
				Thread.sleep(100);
			}
			System.out.println(neighbor);
			System.out.println(branch);
		}
		catch(Exception e){}
	}
}
