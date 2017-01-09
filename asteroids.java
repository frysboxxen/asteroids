import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.FontMetrics;

public class asteroids{
	
	public static void main(String[] args){
		int Xscale = 2500;
		int Yscale = 1800;
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(Xscale,Yscale);
		StdDraw.setXscale(0,Xscale);
		StdDraw.setYscale(0,Yscale);
		
		spaceship Spaceship = new spaceship();
		spaceship Kalle = new spaceship();
		spaceship[] stone = new spaceship[5];
		
		Font font = new Font("Arial", Font.BOLD, Xscale/8);
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.WHITE);
		
		for(int i=0; i<5;i++){
			stone[i] = new spaceship();
		}
		stone[0].stonePNG = "stones//stone1.png";
		stone[0].radie = 900/2;
		stone[1].stonePNG = "stones//stone2.png";
		stone[1].radie = 150;
		stone[2].stonePNG = "stones//meteor.png";
		stone[2].radie = 75;
		stone[3].stonePNG = "stones//meteor.png";
		stone[3].radie = 75;
		stone[4].stonePNG = "stones//meteor.png";
		stone[4].radie = 75;
		
		Spaceship.shipX = Xscale/2;
		Spaceship.shipY = Yscale/2;
		Spaceship.boostKey = 38;
		Spaceship.Right = 39;
		Spaceship.Left = 37;
		Spaceship.shipPNG = "spaceship.png";
		Spaceship.shipNoFirePNG = "spaceshipNoFire.png";
		Spaceship.laserPNG = "laser.png";
		Spaceship.Shoot = 32;
		
		Kalle.shipX = (Xscale/3);
		Kalle.shipY = (Yscale/3);
		Kalle.boostKey = 87;
		Kalle.Right = 68;
		Kalle.Left = 65;
		Kalle.shipPNG = "spaceshipRed.png";
		Kalle.shipNoFirePNG = "spaceshipRedNoFire.png";
		Kalle.laserPNG = "laserGreen.png";
		Kalle.Shoot = 81;
		
		boolean Win = false;
		boolean C = true;
		
		while(true){
		
			StdDraw.clear();
			StdDraw.picture(Xscale/2, Yscale/2,"SpaceB.png");
			
			
			for(int i=0;i<5;i++){
				stone[i].sideStone();
				stone[i].moveStones();
			}
			
			Spaceship.degree();
			Spaceship.shipCanon();
			Spaceship.boost();
			Spaceship.moveShip();
			Spaceship.moveLaser();
			Spaceship.side();
			Spaceship.printShip();
			
			
			Kalle.degree();
			Kalle.shipCanon();
			Kalle.boost();
			Kalle.moveLaser();
			Kalle.moveShip();
			Kalle.side();
			Kalle.printShip();
			
			for(int i = Kalle.laserX.size()-1; i>0;i--){
				if(spaceSupport.hitOrMiss(Spaceship.shipX,Kalle.laserX.get(i),Spaceship.shipY,Kalle.laserY.get(i),100,150)){
					Spaceship.hit = true;
					Kalle.laserX.remove(i);
					Kalle.laserY.remove(i);
					Kalle.laserDegree.remove(i);
					if(Kalle.scoreB){
						Kalle.score++;
						Kalle.scoreB = false;
					}
				}
			}
			for(int i = Spaceship.laserX.size()-1; i>0;i--){
				if(spaceSupport.hitOrMiss(Kalle.shipX,Spaceship.laserX.get(i),Kalle.shipY,Spaceship.laserY.get(i),100,150)){
					Kalle.hit = true;
					Spaceship.laserX.remove(i);
					Spaceship.laserY.remove(i);
					Spaceship.laserDegree.remove(i);
					if(Spaceship.scoreB){
						Spaceship.score++;
						Spaceship.scoreB = false;
					}
				}
			}
			for(int i=0;i<5;i++){
				if(spaceSupport.hitOrMiss(stone[i].laserhit,Spaceship.shipX,stone[i].stoneX,Spaceship.shipY,stone[i].stoneY,stone[i].radie,stone[i].radie)){
					Spaceship.hit = true;
				}
			}
			for(int i=0;i<5;i++){
				if(spaceSupport.hitOrMiss(stone[i].laserhit,Kalle.shipX,stone[i].stoneX,Kalle.shipY,stone[i].stoneY,stone[i].radie,stone[i].radie)){
					Kalle.hit = true;
				}
			}
			for(int j=0;j<5;j++){
				for(int i = Spaceship.laserX.size()-1; i>0;i--){
					if(spaceSupport.hitOrMiss(stone[j].laserhit,stone[j].stoneX,Spaceship.laserX.get(i),stone[j].stoneY,Spaceship.laserY.get(i),stone[j].radie,stone[j].radie)){
						stone[j].laserhit++;
						Spaceship.laserX.remove(i);
						Spaceship.laserY.remove(i);
						Spaceship.laserDegree.remove(i);
						if(stone[j].laserhit==99)Spaceship.score = Spaceship.score +10;
					}
				}
			}
			for(int j=0;j<5;j++){
				for(int i = Kalle.laserX.size()-1; i>0;i--){
					if(spaceSupport.hitOrMiss(stone[j].laserhit,stone[j].stoneX,Kalle.laserX.get(i),stone[j].stoneY,Kalle.laserY.get(i),stone[j].radie,stone[j].radie)){
						stone[j].laserhit++;
						Kalle.laserX.remove(i);
						Kalle.laserY.remove(i);
						Kalle.laserDegree.remove(i);
						if(stone[j].laserhit==99)Spaceship.score = Spaceship.score +10;
					}
				}
			}
			
			StdDraw.text(Xscale - Xscale/12,Yscale/12,Integer.toString(Spaceship.score));
			StdDraw.text(Xscale/12,Yscale/12,Integer.toString(Kalle.score));
			
			
			StdDraw.show();
			StdDraw.pause(35);	
			
			if(StdDraw.isKeyPressed(KeyEvent.VK_O)){
				Kalle.scoreB = true;
				Spaceship.hit = false;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_R)){
				Spaceship.scoreB = true;
				Kalle.hit = false;
			}
			if((Spaceship.score > 30 || Kalle.score > 30))Win=true;
			
			while(Win && C){
					StdDraw.text(Xscale/2,Yscale/2,"CONTINUE?");
					StdDraw.show();
					if(StdDraw.isKeyPressed(KeyEvent.VK_N)){
						Win=false;
						C = false;
					}
			}
		}
	}
}
