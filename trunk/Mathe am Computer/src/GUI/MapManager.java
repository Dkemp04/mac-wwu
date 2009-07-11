package GUI;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.util.LinkedList;

public class MapManager implements Serializable
{
	private static final long serialVersionUID = 3053489811089182476L;
	
	private LinkedList<Map> maps = new LinkedList<Map>();
	
	public MapManager()
	{
		addMap("Deutschland", "C:\\Users\\Daniel Kemper\\Desktop\\Mathe am Computer\\Workspace\\Mathe am Computer\\src\\GUI\\Deutschland.jpg");
		addMap ("Polen", "C:\\Users\\Daniel Kemper\\Desktop\\Mathe am Computer\\Workspace\\Mathe am Computer\\src\\GUI\\Polen.jpg");
	}
	
	public void addMap (String name, String path)
	{
		maps.add(new Map(name, path));
	}
	
	public LinkedList<Map> getMaps ()
	{
		return maps;
	}
	
	public class Map
	{
		private String name;
		private Image img;
		
		public Map (String name, String path)
		{
			this.name = name;
			this.img = Toolkit.getDefaultToolkit().getImage(path);
		}
		
		public String getName ()
		{			return this.name;}
		
		public Image getImage ()
		{			return this.img;}
	}
}