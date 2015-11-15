
import java.awt.Graphics;


public class Oval extends Shape {

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		int x1=getPosition().x;int y1=getPosition().y;
		int x2=getWidth();int y2=getHeight();
		g.drawOval(x1,y1,x2,y2);
		
	}
	
}
