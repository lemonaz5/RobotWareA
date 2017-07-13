/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import logic.DrawingUtility;
import logic.Utility;

public class Square extends Entity {
	private boolean isDestroyed,isInArea,isJustSpawn,isToggleArea;
	private int moveSpeed;
	private int directionX , directionY; //0 = up , 1 = down , 0 = left ,1 =right
	private Color color;
	private String name;
	private double score;
	private Field field;
	private List<Line> lineList;
	private KeyCode[] keyCode;
	private int deadCounter;
	private boolean isFirstRank;
	
	public Square(Field field,int x, int y, Color color, String name,KeyCode[] keyCode){
		super(x,y);
		this.isDestroyed = false;
		this.moveSpeed = 1;
		this.color = color;
		this.name = name;
		this.isInArea = true;
		this.isJustSpawn = true;
		this.score = 0;
		this.isToggleArea = false;
		this.field = field;
		this.lineList = new ArrayList<Line>();
		this.keyCode = keyCode;
		this.deadCounter = 5;
		this.isFirstRank = false;
		
		//random direction for new square.
		Random rand = new Random();
		this.directionX = rand.nextInt(3)-1;
		if (directionX == 0){
			this.directionY = rand.nextInt(3)-1;
			while (directionY == 0){
				this.directionY = rand.nextInt(3)-1;
			}
		}else {
			this.directionY = 0;
		}
	}
	
	public void setFirstRank(boolean isFirstRank) {
		this.isFirstRank = isFirstRank;
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public int getDirectionX() {
		return directionX;
	}

	public void setDirectionX(int directionX) {
		this.directionX = directionX;
	}

	public int getDirectionY() {
		return directionY;
	}

	public void setDirectionY(int directionY) {
		this.directionY = directionY;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return isDestroyed;
	}
	
	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public KeyCode[] getKeyCode() {
		return keyCode;
	}
	
	public boolean isToggleArea() {
		return isToggleArea;
	}
		
	public List<Line> getLineList() {
		return lineList;
	}
	
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public void update() {
		if(!isDestroyed) {
		startArea();
		move();
		outOfBound();
		inArea();
		collectLine();
		}
		else {
			updateDead();
		}
		
	}
	
	public int compareScore(Square square) {
		//compare score with other square.
		if(score > square.score) return 1;
		else if (score == square.score) return 0;
		else return -1;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
			DrawingUtility.drawBlock(gc, x, y, color);
		
		if(this.isFirstRank) {
			DrawingUtility.drawCrown(gc, x, y, color);
		}
		
	}
	
	private void move() {
		if(!isDestroyed && !isInArea) newLine();

		x += moveSpeed * directionX;
		y += moveSpeed * directionY;
	}

	private void newLine() {
		Line line = new Line(x, y, this);
		addEntity(line);
	}
	
	private void startArea() {
		//create start area when square is just spawn.
		if (this.isJustSpawn) {
			for(int i = x - 1 ; i <= x + 1 ; i++) {
				for(int j = y - 1 ; j <= y + 1 ;j++) {
					field.setColorArea(i, j, color);
				}
			}
			isJustSpawn = false;
		}
	}
	
	private void outOfBound() {
		//set dead when square collide with boundary.
		if(x < 0 || y < 0 || x > 63 || y > 63) {
			x=0;
			y=0;
			isDestroyed = true;
		}
	}
	
	private void inArea() {
		//set toggle when square is in area.
		Area thisArea = field.getAreaAt(x, y);
		if(thisArea.getColor().equals(color) && !isInArea) {
			isInArea = true;
			isToggleArea = true;
		}
		else if(thisArea.getColor().equals(color)) {
			isToggleArea = false;
		}
		else  {
			isInArea = false;
			isToggleArea = false;
		}
	}
	
	private void clearField() {
		//clear field when square dead.
		for (int i = 0 ; i < 64 ; i++) {
			for (int j = 0 ; j < 64 ; j++) {
				if(field.getAreaAt(i,j).getColor().equals(color)) {
					field.getAreaAt(i,j).setColor(Utility.noSquareColor);
				}
			}
		}
	}
	
	private void clearLine() {
		//clear line when square dead.
		for(IRenderable i : RenderableHolder.getInstance().getEntities()) {
			if(i instanceof Line && ((Line) i).getSquare().equals(this)) {
						((Line) i).setDestroyed(true);
			}
		}
	}
	public void checkDestroy() {
		//clear field and line when square dead.
		if(isDestroyed) {
			if (!RenderableHolder.dead.isPlaying()){
				RenderableHolder.dead.play();
			}
			clearField();
			clearLine();
		}
	}
	
	private void collectLine() {
		//collect line that will be area when square is toggle area.
		if (isToggleArea) {
		for(IRenderable i : RenderableHolder.getInstance().getEntities()) {
			if(i instanceof Line && ((Line) i).getSquare().equals(this)) {
				lineList.add((Line) i);
			}
		}
		}
		else lineList.clear();
	}
	
	private void updateDead() {
		//update dead of square.
		if(deadCounter==0) {
			deadCounter = 5;
			addEntity(this);
			isJustSpawn = true;
			isDestroyed = false;
			x = 1 + new Random().nextInt(62);
			y = 1 + new Random().nextInt(62);
		}
		else deadCounter -= 1;
	}
	
}
