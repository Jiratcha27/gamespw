package f2.spw;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Main {//สร้างหน้าต่างจอเกม
	public static void main(String[] args){
                
                ArrayList<Gift> gf = new ArrayList<Gift>();
		JFrame frame = new JFrame("Space War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 750);
		frame.getContentPane().setLayout(new BorderLayout());
		
                gf.add(new Gift(140,100));
                gf.add(new Gift(150,100));
                gf.add(new Gift(190,100));
                gf.add(new Gift(20,150));
                gf.add(new Gift(50,150));
                gf.add(new Gift(290,290));
                gf.add(new Gift(270,290));
                gf.add(new Gift(100,290));
                gf.add(new Gift(120,290));
                
		SpaceShip v1 = new SpaceShip(100, 680, 20, 20);//ตำแหน่งของผู่เล่นอยู่ส่วนไหนของหน้าต่าง
                SpaceShip2 v2 = new SpaceShip2(250, 680, 20, 20);
                BasicBlocks blk = new BasicBlocks();
		GamePanel gp = new GamePanel(gf);
		GameEngine engine = new GameEngine(gp, v1 ,v2,gf);
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		engine.start();
	}
}