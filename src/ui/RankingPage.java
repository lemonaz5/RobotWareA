/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.Utility;
import main.Main;
import model.RenderableHolder;

public class RankingPage extends StackPane {

	private Text nameFirst, nameSecond, nameThird, nameLast;
	private Text scoreFirst, scoreSecond, scoreThird, scoreLast;
	private FlowPane second, third, fourth;
	private Button back;
	private Canvas canvas;
	private Image rank;

	public RankingPage() {
		// has to be sorted winning
		RenderableHolder.dead.stop();
		RenderableHolder.winner.play();
		
		this.setPrefSize(Utility.screenWidth, Utility.screenHeight);
		canvas = new Canvas(Utility.screenWidth, Utility.screenHeight);
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		if (Utility.squareList[0].getColor().equals(Color.INDIANRED)){
			rank = RenderableHolder.red;
		}else if (Utility.squareList[0].getColor().equals(Color.SALMON)){
			rank = RenderableHolder.salmon;
		}else if (Utility.squareList[0].getColor().equals(Color.SKYBLUE)){
			rank = RenderableHolder.blue;
		}else if (Utility.squareList[0].getColor().equals(Color.PLUM)){
			rank = RenderableHolder.purple;
		}else if (Utility.squareList[0].getColor().equals(Color.LAVENDERBLUSH)){
			rank = RenderableHolder.white;
		}else if (Utility.squareList[0].getColor().equals(Color.GOLD)){
			rank = RenderableHolder.gold;
		}else if (Utility.squareList[0].getColor().equals(Color.DARKSEAGREEN)){
			rank = RenderableHolder.green;
		}else if (Utility.squareList[0].getColor().equals(Color.DARKSLATEGREY)){
			rank = RenderableHolder.black;
		}
		gc.drawImage(rank, 0, 0);
		
		BorderPane all = new BorderPane();
		all.setPrefSize(640, 690);
		this.getChildren().addAll(canvas,all);
		Font fontLabel = Font.font("Tohoma", FontWeight.EXTRA_BOLD, 25);
		Font fontContent = Font.font("Tohoma", FontWeight.SEMI_BOLD, 20);
		
		//not need anymore
		FlowPane topPane = new FlowPane();
		topPane.setPrefSize(640, 90);
		all.setTop(topPane);

		VBox leftPane = new VBox();
		leftPane.setPrefSize(300, 690);
		Pane space = new Pane();
		space.setPrefSize(480, 350);
		Label firstRank = new Label("1st Rank");
		firstRank.setFont(Font.font("Tohoma", FontWeight.EXTRA_BOLD, 30));
		Label name = new Label("Name");
		name.setFont(fontLabel);
		nameFirst = new Text(Utility.squareList[0].getName());
		nameFirst.setFont(fontContent);
		Label score = new Label("Score");
		score.setFont(fontLabel);
		scoreFirst = new Text("" + Utility.round(Utility.squareList[0].getScore(), 2)+"%");
		scoreFirst.setFont(fontContent);
		leftPane.setAlignment(Pos.CENTER);
		leftPane.getChildren().add(space);
		leftPane.getChildren().add(firstRank);
		leftPane.getChildren().add(name);
		leftPane.getChildren().add(nameFirst);
		leftPane.getChildren().add(score);
		leftPane.getChildren().add(scoreFirst);

		all.setLeft(leftPane);

		VBox rightPane = new VBox();
		rightPane.setPrefSize(300, 690);
		Font fontLabel2 = Font.font("Tohoma", FontWeight.BOLD, 20);
		Font fontContent2 = Font.font("Tohoma", FontWeight.SEMI_BOLD, 18);
		
		VBox v1 = new VBox();
		v1.setPrefSize(300, 170);
		v1.setAlignment(Pos.CENTER);
		Label secondRank = new Label("2nd Rank  ");
		Label nameTwo = new Label(" Name : ");
		Label scoreTwo = new Label("   Score : ");
		secondRank.setFont(fontLabel2);
		nameTwo.setFont(fontLabel2);
		scoreTwo.setFont(fontLabel2);
		nameSecond = new Text(Utility.squareList[1].getName());
		scoreSecond = new Text("" + Utility.round(Utility.squareList[1].getScore(), 2)+"%");
		nameSecond.setFont(fontContent2);
		scoreSecond.setFont(fontContent2);
		second = new FlowPane();
		second.getChildren().addAll(nameTwo,nameSecond,scoreTwo,scoreSecond);
		second.setAlignment(Pos.CENTER);
		v1.getChildren().addAll(secondRank,second);
		rightPane.getChildren().add(v1);

		VBox v2 = new VBox();
		v2.setPrefSize(300, 170);
		v2.setAlignment(Pos.CENTER);
		Label thirdRank = new Label("3rd Rank   ");
		Label nameThree = new Label(" Name : ");
		Label scoreThree = new Label("   Score : ");
		thirdRank.setFont(fontLabel2);
		nameThree.setFont(fontLabel2);
		scoreThree.setFont(fontLabel2);
		thirdRank.setFont(fontLabel2);
		if (Utility.squareList.length > 2) {
			nameThird = new Text(Utility.squareList[2].getName());
			scoreThird = new Text("" + Utility.round(Utility.squareList[2].getScore(), 2)+"%");
		}else{
			nameThird = new Text(" - ");
			scoreThird = new Text(" - ");
		}
		nameThird.setFont(fontContent2);
		scoreThird.setFont(fontContent2);
		third = new FlowPane();
		third.getChildren().addAll(nameThree,nameThird,scoreThree,scoreThird);
		third.setAlignment(Pos.CENTER);
		v2.getChildren().addAll(thirdRank,third);
		rightPane.getChildren().add(v2);

		VBox v3 = new VBox();
		v3.setPrefSize(300, 170);
		v3.setAlignment(Pos.CENTER);
		Label fourthRank = new Label("4th Rank   ");
		Label nameFour = new Label(" Name : ");
		Label scoreFour = new Label("   Score : ");
		fourthRank.setFont(fontLabel2);
		nameFour.setFont(fontLabel2);
		scoreFour.setFont(fontLabel2);
		if (Utility.squareList.length > 3) {
			nameLast = new Text(Utility.squareList[3].getName());
			scoreLast = new Text("" + Utility.round(Utility.squareList[3].getScore(), 2)+"%");
		}else{
			nameLast = new Text(" - ");
			scoreLast = new Text(" - ");
		}
		nameLast.setFont(fontContent2);
		scoreLast.setFont(fontContent2);
		fourth = new FlowPane();
		fourth.getChildren().addAll(nameFour,nameLast,scoreFour,scoreLast);
		fourth.setAlignment(Pos.CENTER);
		v3.getChildren().addAll(fourthRank,fourth);
		rightPane.getChildren().add(v3);

		back = new Button();
		back.setGraphic(new ImageView(RenderableHolder.back));
		back.setStyle("-fx-background-color : transparent;");
		rightPane.getChildren().add(back);

		all.setRight(rightPane);

		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.select.play();
				Main.instance.toOpening();
			}

		});
		
		back.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				RenderableHolder.canClick.play();
			}
		});

	}
}
