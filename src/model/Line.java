/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.DrawingUtility;

public class Line extends Entity {

	private boolean isDestroyed;
	private int x,y;
	private Color color;
	private Square square;
	
	protected static final double transcluentWhite = 0.7;
	
	public Line(int x, int y, Square square) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.square = square;
		this.isDestroyed =false;
		this.x = x;
		this.y = y;
		this.color = square.getColor();
	}

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	public Square getSquare() {
		return square;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return isDestroyed;
	}
	
	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
	
	public void isCollide() {
		//set square that own this line destroyed.
		for (IRenderable e : RenderableHolder.instance.getEntities()) {
			if(e instanceof Square && this.isSamePosition((Square) e)) {
					this.square.setDestroyed(true);
			}
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtility.drawLine(gc, x, y, color);
		
	}
	
}
