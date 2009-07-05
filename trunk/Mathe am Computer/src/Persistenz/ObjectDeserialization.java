package Persistenz;

import java.io.*;
import Logik.Problem;

public class ObjectDeserialization
{	
	public Problem open (String name)
	{
		Problem prob = null;
		try
		{
			FileInputStream fs = new FileInputStream (name);
			ObjectInputStream is = new ObjectInputStream (fs);
			prob = (Problem) is.readObject();
			is.close();
			if (prob == null)
			{	throw new FileNotFoundException("Datei wurde nicht gefunden");}
		}
		catch (Exception e)
		{}
		return prob;
	}
}