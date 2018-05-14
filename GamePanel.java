package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//วาดภาพกราฟิกลงบนจอภาพแสดงในclassMain
public class GamePanel extends JPanel {
        ArrayList<Gift> gf = new ArrayList<Gift>();
	private BufferedImage bi;	
	Graphics2D big;
        Gift gift;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
        private BasicBlocks blk = new BasicBlocks(); //แสดงบังเกอรN
	public GamePanel(ArrayList<Gift> gf) {
		bi = new BufferedImage(500, 750, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
                blk = new BasicBlocks(); 
                gift = new Gift(200,400);
                this.gf = gf;
	}

	public void updateGameUI(GameReporter reporter){
                
		big.clearRect(0, 0, 500, 750);
		big.setColor(Color.WHITE);		
		big.drawString(String.format("Score1: %08d", reporter.getScore1()), 350, 20);  
                big.drawString(String.format("Score2: %08d", reporter.getScore2()), 350, 40); 
                big.drawString(String.format("Level1: %d", reporter.getLevel1()), 280, 20);
                big.drawString(String.format("Level2: %d", reporter.getLevel2()), 280, 40);
		for(Sprite s : sprites){
			s.draw(big);      
		}
                
		repaint();
	}

	@Override
	public void paint(Graphics g) {//เอาไว้วาดรูปทรงใส่สีลงในจอ
            Graphics2D g2d;//เป็นการcatching เปลี่ยนtype ของobjectที่ส่งมาเป็นแบบ2D
            g2d = (Graphics2D) g;
            g2d.drawImage(bi, null, 0, 0);
            blk.draw(g2d);
                       
            for(int  i=0; i<gf.size();i++){
                gift = gf.get(i);
                gift.draw(g2d);
            }
            
                
        }        

}