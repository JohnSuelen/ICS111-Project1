

import java.awt.Color;
import java.io.*;
import java.util.*;


public class be_safe {


	public static void main(String[] args) throws java.io.IOException {

		//1400x800 window w/ white bg
		EZ.initialize(1400, 800);
		EZ.setBackgroundColor(new Color(150,69,2));

		// car image bc not included in array
		EZImage car = EZ.addImage("car.png", 700, 200);

		// sounds
		EZSound rubber = EZ.addSound("rubber.wav");
		EZSound sonic = EZ.addSound("sonic.wav");
		EZSound swallow = EZ.addSound("pillswallow.wav");
		EZSound nyan = EZ.addSound("nyan.wav");
		EZSound bruh = EZ.addSound("bruh.wav");
		
		int score = 0;
		// text
		int fontsize = 75;
		EZText text = EZ.addText(700, 50, "Be safe!", new Color(255, 0, 255), fontsize);
		EZText textScore = EZ.addText(1100, 700, "Score: " + score, new Color(255, 0, 255), fontsize);
		

		// sound array
		EZSound[] cp = new EZSound[5];
		cp[0] = bruh;
		cp[1] = rubber;
		cp[2] = sonic;
		cp[3] = swallow;
		cp[4] = nyan;

		// arrays for buldings and collectibles
		pornstar[] pornstar = new pornstar[5];
		contra[] contra = new contra[5];

		// spawns buildings w/ scanner
		FileReader fr = new FileReader("bpos.txt");
		Scanner sc = new Scanner(fr);
		if (sc.hasNextLine()) {
			for (int i = 0; i < 5; i++) {
				String filename = sc.next();
				int posx = sc.nextInt();
				int posy = sc.nextInt();
				pornstar[i] = new pornstar(filename, posx, posy); 
			}
		}
		sc.close();

		// spawns collectibles w/scanner
		fr = new FileReader("objpos.txt");
		sc = new Scanner(fr);
		if (sc.hasNextLine()) {
			for (int i = 0; i < 5; i++) {
				String file = sc.next();
				int posxx = sc.nextInt();
				int posyy = sc.nextInt();
				contra[i] = new contra(file, posxx, posyy, 1400, 800);
			}
		}
		sc.close();

		// game switch, switch on, switch off
		boolean game = true;

		// car controls
		while (game != false) {

			// gets car coordinates
			int carX = car.getXCenter();
			int carY = car.getYCenter();

			// Turn right: D
			if (EZInteraction.isKeyDown("d")) {
				car.turnRight(5);

				// Turn left: A
			} else if (EZInteraction.isKeyDown("a")) {
				car.turnLeft(5);

				// Go forward: W
			} else if (EZInteraction.isKeyDown("w")) {
				car.moveForward(5);

				// Go backward: S
			} else if (EZInteraction.isKeyDown("s")) {
				car.moveForward(-5);

			}

			// death
			for (int i = 0; i < 5; i++) {
				if (pornstar[i].isInside(carX, carY)) {
					EZImage gameover = EZ.addImage("gameover.png", 700, 400); 
					game = false; // sets the movement condition to false so the game stops
					
				}
			}

			// collectibles
			for (int i = 0; i < 5; i++) {
				contra[i].go();
				if (contra[i].isInside(carX, carY)) {
					cp[i].play();
					contra[i].translateAway(10000); // moves small object away
					score++;
					textScore.setMsg("Score: " + score);
					
				}
			}
			// so car doesnt go offscreen
			if (carX > 1400) {
				carX = 1400;
				car.translateTo(carX, carY);
			} else if (carX < 0) {
				carX = 0;
				car.translateTo(carX, carY);
			} else if (carY > 800) {
				carY = 800;
				car.translateTo(carX, carY);
			} else if (carY < 0) {
				carY = 0;
				car.translateTo(carX, carY);
			}
			// victory
			if (score == 5) {
				EZ.addImage("win.jpg", 1400/2, 800/2);
				car.hide();
				game = false;
			

					}
	
			EZ.refreshScreen();

		}
	}
}

