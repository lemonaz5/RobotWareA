/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.DrawingUtility;
import logic.Utility;

public class Area extends Entity {

	private boolean isDestroyed;
	private Color color;

	public Area(int x, int y){
		super(x, y);
		this.color = Utility.noSquareColor;
		this.isDestroyed = false;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return isDestroyed;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(color.equals(Utility.noSquareColor)) {
			//Draw table if it no own square.
			DrawingUtility.drawTable(gc, x, y, color);
		} else {
			DrawingUtility.drawBlock(gc, x, y, color);
		}
	}
}
