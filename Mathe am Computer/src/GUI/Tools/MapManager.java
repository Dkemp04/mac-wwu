package GUI.Tools;

import java.awt.*;
import java.io.*;
import java.util.*;

import GUI.Main.MainFrame;

/**
 * 
 * @author Steffen Pichler, Christian Vogel, Veysel Aksak, Daniel Kemper
 */
public class MapManager implements Serializable
{
	private static final long serialVersionUID = 3053489811089182476L;
	
	private LinkedList<Map> maps = new LinkedList<Map>();
	
	public MapManager()
	{
		addMap("Deutschland", MainFrame.WORKSPACE + "/images/Deutschland.jpg");
		addMap ("Polen", MainFrame.WORKSPACE + "/images/Polen.jpg");
	}
	
	public void addMap (String name, String path)
	{
		maps.add(new Map(name, path));
	}
	
	public String getMapPath (String name)
	{
		for (int i = 0; i < maps.size() ;i++)
		{
			if (maps.get(i).getName() == name)
			{
				return maps.get(i).getPath();
			}
		}
		return null;
	}
	
	public LinkedList<Map> getMaps ()
	{
		return maps;
	}
	
	public class Map implements Serializable
	{
		private static final long serialVersionUID = 1499513765199554978L;
		
		private String name;
		private String path;
		private Image img;
		
		public Map (String name, String path)
		{
			this.name = name;
			this.path = path;
			this.img = Toolkit.getDefaultToolkit().getImage(path);
		}
		
		public String getName ()
		{			return this.name;}
		
		public String getPath ()
		{			return this.path;}
		
		public Image getImage ()
		{			return this.img;}
	}
}