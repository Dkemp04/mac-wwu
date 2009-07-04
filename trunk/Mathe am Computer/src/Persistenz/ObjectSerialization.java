package Persistenz;

import java.io.*;

public class ObjectSerialization
{
	public ObjectSerialization (String name, Object obj)
	{
		save (name, obj);
	}
	
	public void save (String name, Object obj)
	{
		try
		{
			FileOutputStream fs = new FileOutputStream (name + ".tsp");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(obj);
			os.close();
		}
		catch(IOException ioe)
		{
			System.err.println(ioe.toString());
		}
	}
}