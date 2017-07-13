/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package ui;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameManager;
import logic.Utility;
import main.Main;
import model.IRenderable;
import model.RenderableHolder;

public class GameScreen extends StackPane {
	private Canvas canvas;
	public static Thread threadTimer;

	public GameScreen(int width, int height, GameManager gameManager) {
		super();
		this.canvas = new Canvas(width, height);
		this.getChildren().add(canvas);
		threadTimer = new Thread(() -> {
			Utility.timeRemaining *= Utility.tickPerSecond;
			while(true) {
			try {
				Thread.sleep(100);
				gameManager.update();
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						paintComponents();
						if(!Utility.isPause) {
							Utility.timeRemaining--;
						}
						if (Utility.timeRemaining == 0) {
							Utility.clearUtility();
							RenderableHolder.getInstance().getEntities().clear();
							Main.instance.toRanking();
							threadTimer.interrupt();
						}
					}
					
				});
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
			}
			}
		});
		
		threadTimer.start();
	}

	private void paintComponents() {
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Utility.screenWidth, Utility.screenHeight);
		gc.drawImage(RenderableHolder.gameBackground, 0, 0);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (!entity.isDestroyed()) {
				entity.draw(gc);
			}
		}
		drawScore(gc);
		drawTime(gc);
		if(Utility.isPause) drawPause(gc);
	}

	private void drawScore(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("Angsana new", FontWeight.LIGHT, 32));
		String name1 = Utility.squareList[0].getName() +" "+ Utility.round(Utility.squareList[0].getScore(),2)+"%";
		String name2 = Utility.squareList[1].getName() +" "+ Utility.round(Utility.squareList[1].getScore(),2)+"%";
		String name3 = "";
		String name4 = "";
		if (Utility.squareList.length>2){
			name3 = Utility.squareList[2].getName() +" "+ Utility.round(Utility.squareList[2].getScore(),2)+"%";
		}if (Utility.squareList.length>3){
			name4 = Utility.squareList[3].getName() +" "+ Utility.round(Utility.squareList[3].getScore(),2)+"%";
		}
		gc.fillText("Rank : " + name1 +"  " + name2 +"  "+ name3 +"  " + name4, 10 , 670);
	}

	private void drawTime(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("Angsana new", FontWeight.LIGHT, 32));
		gc.fillText("Time : " + Utility.timeRemaining/10 , 540, 670);
	}
	
	private void drawPause(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.setGlobalAlpha(0.6);
		gc.fillRect(0, 0, 640, 640);
		gc.setFill(Color.WHITE);
		gc.setGlobalAlpha(1);
		gc.drawImage(RenderableHolder.pausepic, 170, 260);

	}
	
}
