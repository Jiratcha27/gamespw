package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 500;
	public static final int Y_TO_DIE = 750;
	
	private int step = 12;
	private boolean alive = true;
	
	public Enemy(int x, int y) {
		super(x, y, 5, 10);
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		
	}
       //เช็คการชนของบังเกอร์กับenemy
	public void proceed(){
		y += step;
                if(y >= 210 && x >= 65 && x <= 130){
                        alive = false;
                }
                else if(y >= 210 && x >= 345 && x <= 415){
                        alive = false;
                }
                else if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
}
