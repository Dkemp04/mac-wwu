package Persistenz;

import java.io.*;

public class ObjectDeserialization
{	
	public Object open (String name)
	{
		Object obj = null;
		try
		{
			FileInputStream fs = new FileInputStream (name);
			ObjectInputStream is = new ObjectInputStream (fs);
			obj = is.readObject();
			is.close();
			if (obj == null)
			{	throw new FileNotFoundException("Datei wurde nicht gefunden");}
		}
		catch (Exception e)
		{	e.printStackTrace();}
		return obj;
	}
}