
public class pornstar {

	public int x, y;
	public String filename;
	public EZImage building;
	

	pornstar(String filename, int posx, int posy) {
		x = posx;
		y = posy;
		building = EZ.addImage(filename, posx, posy);
	
	}

	public boolean isInside(int posx, int posy) {
			return building.isPointInElement(posx, posy);
		}
	}
