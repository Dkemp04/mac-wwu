package GUI;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;

public class MapManager
{
	LinkedList<Map> maps = new LinkedList<Map>();
	
	public MapManager()
	{
		addMap("Deutschland");
		addMap ("Polen");
		//maps.add(new Map("Deutschland", Toolkit.getDefaultToolkit().getImage(MainFrame.WORKSPACE + "/Deutschland.jpg")));
		//maps.add(new Map("Polen", Toolkit.getDefaultToolkit().getImage(MainFrame.WORKSPACE + "/Polen.jpg")));
	}
	
	public void addMap (String name)
	{
		maps.add(new Map (name, Toolkit.getDefaultToolkit().getImage(MainFrame.WORKSPACE + "/images/" + name + ".jpg")));
	}
	
	public LinkedList<Map> getMaps ()
	{
		return maps;
	}
	
	public class Map
	{
		private String name;
		private Image img;
		
		private Map (String name, Image img)
		{
			this.name = name;
			this.img = img;
		}
		
		public String getName ()
		{			return name;}
		
		public Image getImage ()
		{			return img;}
	}
}