package Logic;

/**
 * 
 * @author Steffen Pichler, Christian Vogel, Veysel Aksak, Daniel Kemper
 */
public class Pair
{
	private Object first;
	private Object second;
	private Object third;
	public Pair(Object first, Object second, Object third){
		this.first=first;
		this.second=second;
		this.third=third;
	}
	
	public Object getFirst(){return first;}
	public Object getSecond(){return second;}
	public Object getThird(){return third;}
}
