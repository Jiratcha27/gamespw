package f2.spw;
//classแม่
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public abstract class Sprite {
	int x;
	int y;
	int width;
	int height;
	
	public Sprite(int x, int y, int width, int height) {
		this.x = x;//ตำแหน่ง
		this.y = y;//ตำแหน่ง
                
		this.width = width;//ขนาด
		this.height = height;
	}
        
        //abstract method วาดตัวละครผู้เล่น ให้ class อื่น extend
	abstract public void draw(Graphics2D g);
	
	public Double getRectangle() {
		return new Rectangle2D.Double(x, y, width, height);
	}
}