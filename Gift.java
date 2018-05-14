
package f2.spw;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Gift {
    private int x,y;//กำหนดพิกัด
    private int speedx = 3;
    private int speedy = 3;
    ArrayList<Gift> gf = new ArrayList<Gift>();
    Gift gift;

    private boolean alive = true;
    
    public Gift(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g) {
        move();
        g.setColor(Color.GRAY);
	g.fillOval(x, y, 10, 10);//(ตำแหน่ง,ขนาด)
    }
    
    public void move(){//player เคลื่อนที่แกนx
	x += speedx;
        if(x > 350){
            speedx -= 2;
        }
        if(x < 0){
            speedx += 2;
        }
        y += speedy;
        if(y > 300){
            speedy -= 4;
        }
        if(y < 100){
            speedy += 4;
        }
    }
    public Rectangle2D.Double getRectangle() {
		return new Rectangle2D.Double(x, y, 10, 10);
	}
    
    public boolean isAlive(){
		return alive;
	}
    public void adie(){
		alive = false;
	}

}