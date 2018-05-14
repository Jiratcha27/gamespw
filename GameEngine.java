package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;

//คลาสใช้ควบคุมตัวละคร player1,player2,enemy
public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        private ArrayList<Gift> gf = new ArrayList<Gift>();
	private SpaceShip v1;//player1
        private SpaceShip2 v2;//player2
	private Gift gift;
	private Timer timer;
	
	private long score1 = 0;
        private long score2 = 0
        private int level1 = 1;
        private int level2 = 1;
	private double difficulty = 0.1;//ความยากของกระสุน
	private boolean v1A=true;
        private boolean v2A=true;
        
	public GameEngine(GamePanel gp, SpaceShip v1 ,SpaceShip2 v2,ArrayList<Gift> gf) {
		this.gp = gp;
		this.v1 = v1;
                this.v2 = v2;
		this.gf = gf;
		gp.sprites.add(v1);
                gp.sprites.add(v2);
	
                ArrayList <BasicBlocks> blk = new ArrayList <BasicBlocks>();
                
		timer = new Timer(40, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();                              
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();//สั่ง start จะทำการเรียกใช้ method override3อันข้างล่าง
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*490), 30);
		gp.sprites.add(e);
		enemies.add(e);
      
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
                    Enemy e = e_iter.next();
                    e.proceed();
			
                    if(!e.isAlive()){
			e_iter.remove();
			gp.sprites.remove(e);
                        if(v1A && v2A){
                            score1 += 5;
                            score2 +=5;
                        }
                        else if(v1A){
                            score1 += 5;
                            if(score1>=200 ){
                                if((score1%200==0)){//ทุกscore 200                                  
                                    level1 += 1;                                      
                                    difficulty += 0.08;//เพิ่มกระสุน   
                                } 
                            }                                
			}
                        else if(v2A){
                            score2 += 5;
                            if(score2>=200 ){
                                if((score2%200==0)){//ทุกscore 200
                                    level2 += 1;                                      
                                    difficulty += 0.08;//เพิ่มกระสุน
                                    
                                } 
                            }                                
			}
                    }   
		}
		
                Iterator<Gift> g_iter = gf.iterator();
		while(g_iter.hasNext()){
			Gift g = g_iter.next();
			g.move();
			
			if(!g.isAlive()){
				g_iter.remove();
				gf.remove(g);
				if(v1A && v2A){
                                    score1 += 20;
                                    score2 += 20;
                                }
                                else if(v1A){
                                    score1 += 20;
                                }
                                else if(v2A){
                                    score2 += 20;
                                }
			}
		}
                
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v1.getRectangle();//player1
                Rectangle2D.Double vr2 = v2.getRectangle();//player2
		Rectangle2D.Double er;//ของenemy
                Rectangle2D.Double gg;
                
		for(Enemy e : enemies){
                    er = e.getRectangle();
                    if(er.intersects(vr)){//player1
                                v1A=false;
				die();                      
				return;
                    }
                    else if(er.intersects(vr2)){
                                v2A=false;
				die();

				return;
                    }
		}
                for(Gift f : gf){
                    gg = f.getRectangle();
                    if(gg.intersects(vr)){
			f.adie();
			return;
                    }
                    else if(gg.intersects(vr2)){
                        f.adie();
			return;
                    }
                }	
        }
              
	public void die(){
                if(!v1A && !v2A){  
                    Over o = new Over();
                    timer.stop();
                }
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
                        if(v2A)
                            v2.move_x(-1);
			break;
		case KeyEvent.VK_RIGHT:
                        if(v2A)
                            v2.move_x(1);
			break;
                case KeyEvent.VK_UP:
                        if(v2A)
                            v2.move_y(-1);
			break;
		case KeyEvent.VK_DOWN:
                        if(v2A)
                            v2.move_y(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.06;
			break;
                case KeyEvent.VK_Z:
                        if(v1A)
                            v1.move_x(-1);
			break;
		case KeyEvent.VK_C:
                        if(v1A)
                            v1.move_x(1);
			break;
                case KeyEvent.VK_S:
                        if(v1A)
                            v1.move_y(-1);
			break;
		case KeyEvent.VK_X:
                        if(v1A)
                            v1.move_y(1);
			break;
                
                }
	}

	public long getScore1(){
		return score1;
	}
        public long getScore2(){
		return score2;
	}
        public int getLevel1(){
		return level1;
	}
        public int getLevel2(){
		return level2;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {//การกดปุ่มค้างไว้
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {//การปล่อยปุ่ม
		//do nothing
                
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}