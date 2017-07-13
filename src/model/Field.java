/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Utility;

public class Field implements IRenderable {
	private Area[][] field;
	
	public Field(){
		initializeField();
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return -9999;
	}
	
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void initializeField() {
		//create field with empty area.
		field = new Area[64][64];
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				field[i][j] = new Area(i, j);
			}
		}
	}
	
	public Area getAreaAt(int x,int y) {
		//get area at (x,y)
		if(x < 0) x = 0;
		else if ( x > 63) x = 63;
		
		if(y < 0) y = 0;
		else if (y > 63) y = 63;
		
		return field[x][y];
	}
	
	public int getSquareArea(Square square) {
		//get number of area own by square to update score.
		int areaCheck = 0;
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				if(field[i][j].getColor().equals(square.getColor())) {
					areaCheck+=1;
				}
			}
		}
		return areaCheck;
	}
	
	public void clearField() {
		//clear field.
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				field[i][j].setColor(Utility.noSquareColor);
			}
		}
	}
	
	public void setColorArea(int x , int y , Color color) {
		//set color of area at (x,y) with color.
		getAreaAt(x,y).setColor(color);
	}
	
	public void draw(GraphicsContext gc) {
		for(Area[] i : field) {
			for(Area j : i) {
				j.draw(gc);
			}
		}
	}
	
	public void floodFill(int x, int y, Square square) {
		//fill area at (x,y) with square's color,then call this method with area around.
		
		if (x < 0 || y < 0 || x > 63 || y>63)
			//do nothing if it out of field.
			return;

		Area area = getAreaAt(x, y);
		Color oldColor = area.getColor();
		Color newColor = square.getColor();

		if (newColor.equals(oldColor) || !checkAround(area,newColor)) {
			//do nothing if its color equal to square's color, or this area not in closed area.
			return;
		} else {
			area.setColor(newColor);

			//recursive method at area around this.
			floodFill(x - 1, y, square);
			floodFill(x + 1, y, square);
			floodFill(x, y - 1, square);
			floodFill(x, y + 1, square);
		}
	}
	
	public boolean checkAround(Area area,Color color) {
		//check that this area is in closed area with color.
		return checkRight(area,color) && checkLeft(area,color) && checkTop(area,color) && checkBottom(area,color);
	}
	private boolean checkRight(Area area,Color color) {
		//check right side of this area.
		Area areaCheck;
		for(int i = area.getX();i<=63;i++) {
			areaCheck = getAreaAt(i,area.getY());
			if(areaCheck.getColor().equals(color)) return true;
		}
		return false;
	}
	
	private boolean checkLeft(Area area,Color color) {
		//check left side of this area.
		Area areaCheck;
		for(int i = area.getX();i>=0;i--) {
			areaCheck = getAreaAt(i,area.getY());
			if(areaCheck.getColor().equals(color)) return true;
		}
		return false;
	}
	
	private boolean checkTop(Area area,Color color) {
		//check top side of this area.
		Area areaCheck;
		for(int i = area.getY();i>=0;i--) {
			areaCheck = getAreaAt(area.getX(),i);
			if(areaCheck.getColor().equals(color)) return true;
		}
		return false;
	}
	
	private boolean checkBottom(Area area,Color color) {
		//check bottom side of this area.
		Area areaCheck;
		for(int i = area.getY();i<=63;i++) {
			areaCheck = getAreaAt(area.getX(),i);
			if(areaCheck.getColor().equals(color)) return true;
		}
		return false;
	}
	
	
}
