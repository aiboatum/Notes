import java.awt.*;
import javax.swing.*;
public class BallGames2 extends JFrame{
	
	Image ball=Toolkit.getDefaultToolkit().getImage("images/ball.png");
	Image desk=Toolkit.getDefaultToolkit().getImage("images/desk.jpg");
	
	double x=100;	//小球很坐标（左上角为坐标原点）
	double y=100;
	
	double degree=3.14/3;//弧度，60度

	//画窗口
	public void paint(Graphics g) {
		//重写的paint方法会被系统自动调用一次
		//之后的调用要使用repaint方法
		//System.out.println("窗口被画了一次");
		g.drawImage(desk,0,0,null);
		g.drawImage(ball,(int)x, (int)y, null); 
		
		System.out.println(degree);
		
		x=x+10*Math.cos(degree);
		y=y+10*Math.sin(degree);
		if(y>500-40-30||y<40+40)degree=-degree;
		if(x<40||x>856-40-30)degree=-degree;
		
	}
	
	//窗口加载
	void lanchFrame() {
		setSize(865,500);
		setLocation(50,50);
		setVisible(true);
		
		//重画
		while(true) {
			repaint();
			try {
				Thread.sleep(40);//一秒20次窗口
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("This is a game testing!");
		BallGames2 game=new BallGames2();
		game.lanchFrame();
	}
}