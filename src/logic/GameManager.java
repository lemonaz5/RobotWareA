/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package logic;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyCode;
import model.Area;
import model.Field;
import model.IRenderable;
import model.Line;
import model.RenderableHolder;
import model.Square;

public class GameManager {

	private Square[] squareList;
	private Field field;
	private List<Area> areaFromLine = new ArrayList<Area>();

	public GameManager() {
		squareList = Utility.squareList;
		field = Utility.field;
		for(Square i : squareList) {
			RenderableHolder.getInstance().getEntities().add(i);
		}
		RenderableHolder.getInstance().getEntities().add(field);
	}
	
	public void update() {
		if(!Utility.isPause) {
			for(Square i : squareList) {
				i.update();
			}
			checkCollide();
			for(Square i : squareList) {
				createArea(i);
				i.checkDestroy();
				updateScore(i);
			}
			destroyEntities();
			sortRank();
		}
	}
	
	public void receiveKey(KeyCode keycode) {
		//change direction when receive KeyCode.
		for(Square sq : squareList) {
			if(keycode.equals(sq.getKeyCode()[0]) && sq.getDirectionY() != 1) {
				sq.setDirectionX(0);
				sq.setDirectionY(-1);
			}
			else if(keycode.equals(sq.getKeyCode()[1]) && sq.getDirectionX() != 1) {
				sq.setDirectionX(-1);
				sq.setDirectionY(0);
			}
			else if(keycode.equals(sq.getKeyCode()[2]) && sq.getDirectionY() != -1) {
				sq.setDirectionX(0);
				sq.setDirectionY(1);
			}
			else if(keycode.equals(sq.getKeyCode()[3]) && sq.getDirectionX() != -1) {
				sq.setDirectionX(1);
				sq.setDirectionY(0);
			}
		}
	}

	private void checkCollide() {
		//check collide from line.
		for (IRenderable i : RenderableHolder.getInstance().getEntities()) {
			if(i instanceof Line) {
				((Line) i).isCollide();
			}
		}
	}
	
	private void createArea(Square square) {
		//create area from line when square is come in own area.
		if(square.isToggleArea()) {
			for(Line i : square.getLineList()) {
				getAroundLine(i);
				field.getAreaAt(i.getX(), i.getY()).setColor(square.getColor());
			}
			removeLine(square); //remove line.
			addArea(square); //create area.
			areaFromLine.clear(); //clear for next square to check.
		}
	}
	
	private void getAroundLine(Line line) {
		//get area around line for checking closed area.
		for(int i = line.getX() - 1 ; i <= line.getX() + 1 ;i++) {
			for(int j = line.getY() - 1 ; j <= line.getY() + 1 ;j++) {
				if(i==line.getX() || j==line.getY() || !(i==line.getX() && j==line.getY())) {
					areaFromLine.add(field.getAreaAt(i, j));
				}
			}
		}	
	}
	
	private void removeLine(Square square) {
		//remove line when before it become area.
		for(int i = RenderableHolder.getInstance().getEntities().size()-1;i>=0;i--) {
			IRenderable entity = RenderableHolder.getInstance().getEntities().get(i);
			if(entity instanceof Line && ((Line) entity).getSquare().equals(square)) {
				RenderableHolder.getInstance().getEntities().remove(i);
			}
		}
	}
	
	private void addArea(Square square) {
		//fill area in closed area with square's color.
		for(Area i : areaFromLine) {
			if(field.checkAround(i, square.getColor())) {
				field.floodFill(i.getX(), i.getY(), square);
			}
		}
	}

	private void destroyEntities() {
		//remove destroyed entities
		for(int i = RenderableHolder.getInstance().getEntities().size()-1;i>=0;i--) {
			if(RenderableHolder.getInstance().getEntities().get(i).isDestroyed()) {
				RenderableHolder.getInstance().getEntities().remove(i);
			}
		}
	}

	private void updateScore(Square square) {
		//update square's score.
		double score;
		score = field.getSquareArea(square) * 100;
		square.setScore(score/4096);
	}

	private void sortRank() {
		//swap rank in squareList.
		for(int i = 0;i< squareList.length - 1;i++) {
			for(int j = i+1;j<squareList.length;j++) {
				Square sq1 = squareList[i];
				Square sq2 = squareList[j];
				if(sq1.compareScore(sq2)<0) {
					squareList[i] = sq2;
					squareList[j] = sq1;
				}
			}
		}
		//set squareList to first rank.
		squareList[0].setFirstRank(true);
		for(int i = 1;i<squareList.length ; i++) {
			squareList[i].setFirstRank(false);
		}
	}
}
