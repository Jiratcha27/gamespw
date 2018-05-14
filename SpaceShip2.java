package f2.spw;
///////////////เป็นส่วนของผู่เล่น///// ตั้งค่าต่างๆ///ship x
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class SpaceShip2 extends Sprite{

	int step = 10;
        private boolean alive = true;
	public SpaceShip2(int x, int y, int width, int height) {
		super(x, y, width, height);
               
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, width, height);
		
	}

	public void move_x(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 485 - width)
			x = 485 - width;       
	}
        public void move_y(int direction){
		y += (step * direction);
		if(y < 0)
			x = 0;
		if(y > 700 - height)
			y = 700 - height;       
	}
        

}