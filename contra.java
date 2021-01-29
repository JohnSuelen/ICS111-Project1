
import java.util.Random;

public class contra {

	private int xx, yy;
	private int destx, desty;
	private int rangex, rangey;
	private EZImage collectible;
	//constructor
	contra(String file, int posxx, int posyy, int rx, int ry) {
		xx = posxx;	yy = posyy;
		rangex = rx;rangey = ry;
		collectible = EZ.addImage(file, posxx, posyy);
		setRandomDirection();
	}
	public void setDestination(int xx, int yy) {
		destx = xx; desty = yy;
	}
	public void setRandomDirection() {

		// Make a random number generator
		Random randomGenerator = new Random();

		// Get a random number from 0 to rangex and use that as the destination of the
		// bug in X
		int randomX = randomGenerator.nextInt(rangex);

		// Do the same for the y destination of the bug
		int randomY = randomGenerator.nextInt(rangey);

		// Actually set the destination for the bug
		setDestination(randomX, randomY);
	}
	
	public boolean isInside(int posxx, int posyy) {
		return collectible.isPointInElement(posxx, posyy);
	}

	static boolean isPointInElement(int posxx, int posyy) {
		// TODO Auto-generated method stub
		return true;
	}

	public void translateAway(int distance) {
		xx = xx + distance;
		yy = yy + distance;
		collectible.translateTo(xx, yy);
	}

	public void moveLeft(int step) {
		xx = xx - step;
		collectible.translateTo(xx, yy);
	}

	public void moveRight(int step) {
		xx = xx + step;
		collectible.translateTo(xx, yy);
	}

	public void moveUp(int step) {
		yy = yy - step;
		collectible.translateTo(xx, yy);
	}

	public void moveDown(int step) {
		yy = yy + step;
		collectible.translateTo(xx, yy);
	}

	public void go() {
		if (xx > destx)
			moveLeft(1); // If the x position of bug is greater than dest x then move Left
		if (xx < destx)
			moveRight(1);
		if (yy > desty)
			moveUp(1); // If the y position of bug is greater than dest y then move up.
		if (yy < desty)
			moveDown(1);
		if ((xx == destx) && (yy == desty)) {
			setRandomDirection();
		}

	}
}
