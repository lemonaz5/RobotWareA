/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package ui;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import logic.NameAndColorException;
import logic.Utility;
import main.Main;
import model.RenderableHolder;
import model.Square;

public class MainPage extends StackPane {
	
	private PlayerBlock[] playerBlock;
	private Button playGame;
	private int numberOfPlayer = 4;
	private int gameTime = 3;
	private Spinner sptime,spplayer;
	private Canvas canvas;
	
	public MainPage() {
		
		playerBlock = new PlayerBlock[4];
		BorderPane all = new BorderPane();
		all.setPrefSize(Utility.screenWidth, Utility.screenHeight);
		canvas = new Canvas(Utility.screenWidth,Utility.screenHeight);
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.main, 0, 0);
		
		this.setPrefSize(Utility.screenWidth, Utility.screenHeight);
		this.getChildren().addAll(canvas ,all);
		
		FlowPane topOfPage = new FlowPane();
		topOfPage.setPrefSize(640,100);
		topOfPage.setAlignment(Pos.CENTER);
		topOfPage.setHgap(30);
		topOfPage.setVgap(10);
//		Image player = RenderableHolder.player;
		Label player = new Label("Player");
		player.setFont(Font.font("Tohoma",FontWeight.BLACK,FontPosture.REGULAR,15));
		spplayer = new Spinner(2,4,numberOfPlayer,1);
		spplayer.setPrefWidth(100);
		Label time = new Label("Time(min)");
		time.setFont(Font.font("Tohoma",FontWeight.BLACK,FontPosture.REGULAR,15));
		sptime = new Spinner(1,8,gameTime,1);
		sptime.setPrefWidth(100);
		topOfPage.getChildren().addAll(player,spplayer,time,sptime);
		
		all.setTop(topOfPage);
		
		GridPane middleOfPage = new GridPane();
		middleOfPage.setPrefSize(640,490);
		middleOfPage.setAlignment(Pos.TOP_CENTER);
		middleOfPage.setHgap(10);
		middleOfPage.setVgap(10);
		middleOfPage.setPadding(new Insets(10,10,10,10));
		PlayerBlock pBox1 = new PlayerBlock();
		playerBlock[0] = pBox1;
		PlayerBlock pBox2 = new PlayerBlock();
		playerBlock[1] = pBox2;
		PlayerBlock pBox3 = new PlayerBlock();
		playerBlock[2] = pBox3;
		PlayerBlock pBox4 = new PlayerBlock();
		playerBlock[3] = pBox4;
		middleOfPage.add(pBox1, 0, 0);
		middleOfPage.add(pBox2, 1, 0);
		middleOfPage.add(pBox3, 0, 1);
		middleOfPage.add(pBox4, 1, 1);
		all.setCenter(middleOfPage);
		
		VBox bottomOfPage = new VBox();
		bottomOfPage.setPrefSize(960, 140);
		playGame = new Button();
		playGame.setGraphic(new ImageView(RenderableHolder.play));
		playGame.setStyle("-fx-background-color : transparent;");
		Button back = new Button();
		back.setGraphic(new ImageView(RenderableHolder.back));
		back.setStyle("-fx-background-color : transparent;");
		VBox.setMargin(back, new Insets(0,0,10,0));
		bottomOfPage.setAlignment(Pos.TOP_CENTER);
		bottomOfPage.getChildren().addAll(playGame,back);
		
		all.setBottom(bottomOfPage);
		
		back.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				RenderableHolder.canClick.play();
			}
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.select.play();
				Main.instance.toOpening();
			}
		});
		
		playGame.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				RenderableHolder.canClick.play();
				playGame.setGraphic(new ImageView(RenderableHolder.playh));
			}
		});
		
		playGame.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				playGame.setGraphic(new ImageView(RenderableHolder.play));
			}
		});
		
		playGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.select.play();
				try {
				Utility.clearUtility();
				Utility.initializeKeyCode();
				Utility.player = (int) spplayer.getValue();
				Utility.timeRemaining = (int) sptime.getValue() * 60;
				Utility.squareList = new Square[Utility.player];
				for(int i = 0;i<Utility.player;i++) {
					Utility.color.add(playerBlock[i].getColor());
					Utility.name.add(playerBlock[i].getName());
				}
				checkNameAndColor(Utility.name,Utility.color);
				for(int i = 0;i<Utility.player;i++) {
					Color color = Utility.color.get(i);
					String name = Utility.name.get(i);
					Square square = new Square(Utility.field,1 + new Random().nextInt(62),1 + new Random().nextInt(62),color,name,Utility.keyCode.get(i));
					Utility.squareList[i] = square;
				}
				Main.instance.toGame();
				
				} catch (Exception e) {
					System.out.println("Hello");
				}
			}
			
			
		});

		Thread check = new Thread(() -> {
			while(true) {
					// TODO Auto-generated method stub
					disablePlayerBlock();
					try {
						Thread.sleep(10);
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								for(PlayerBlock i : playerBlock) {
									i.DrawExample(i.getGc());
								}
							}
						
						});
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		check.start();
	}
	
	private void disablePlayerBlock() {
		for(int i = playerBlock.length-1;i>= (int) spplayer.getValue();i--) {
			playerBlock[i].blackButton.setDisable(true);
			playerBlock[i].blueButton.setDisable(true);
			playerBlock[i].greenButton.setDisable(true);
			playerBlock[i].lightBlueButton.setDisable(true);
			playerBlock[i].orangeButton.setDisable(true);
			playerBlock[i].pinkButton.setDisable(true);
			playerBlock[i].redButton.setDisable(true);
			playerBlock[i].yellowButton.setDisable(true);
			playerBlock[i].textName.setDisable(true);
			playerBlock[i].isEditable = false;;
		}
		
		for(int i = 0;i< (int) spplayer.getValue();i++) {
			playerBlock[i].blackButton.setDisable(false);
			playerBlock[i].blueButton.setDisable(false);
			playerBlock[i].greenButton.setDisable(false);
			playerBlock[i].lightBlueButton.setDisable(false);
			playerBlock[i].orangeButton.setDisable(false);
			playerBlock[i].yellowButton.setDisable(false);
			playerBlock[i].pinkButton.setDisable(false);
			playerBlock[i].redButton.setDisable(false);
			playerBlock[i].textName.setDisable(false);
			playerBlock[i].isEditable = true;
		}
	}

	
	private void checkNameAndColor(ArrayList<String> nameList,ArrayList<Color> colorList) throws NameAndColorException {
		for(int i = 0;i<nameList.size();i++) {
			String name = nameList.get(i);
			if(name.trim().equals("") || name.trim().length()>4) {
				throw new NameAndColorException(nameList,colorList);
			}
		}
		for(int i=0;i<colorList.size()-1;i++) {
			for(int j=i+1;j<colorList.size();j++) {
				if(colorList.get(i).equals(colorList.get(j))) {
					throw new NameAndColorException(nameList,colorList);
				}
			}
		}
		boolean notEmpty;
		for(int i=0;i<nameList.size()-1;i++) {
			for(int j=i+1;j<nameList.size();j++) {
				notEmpty = !nameList.get(i).trim().equals("");
				if(nameList.get(i).equals(nameList.get(j)) && notEmpty) {
					throw new NameAndColorException(nameList,colorList);
				}
			}
		}
	}
}
