/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class PlayerBlock extends VBox {
	
	protected Button redButton,greenButton,blueButton,pinkButton;
	protected Button yellowButton,lightBlueButton,orangeButton,blackButton;
	protected Color color = Color.INDIANRED;
	protected TextField textName;
	protected boolean isEditable;

	private Canvas canvas;
	private GraphicsContext gc;
	
	public PlayerBlock() {
		this.setPrefSize(200,200);
//		this.setStyle("-fx-background-color : rgb(200,200,200);");
		canvas = new Canvas(200,50);
		gc = canvas.getGraphicsContext2D();
		DrawExample(gc);
		this.getChildren().add(canvas);
		GridPane colorButton = new GridPane();
		colorButton.setPrefSize(100, 100);
		colorButton.setAlignment(Pos.CENTER);
		redButton = new Button();
		redButton.setStyle("-fx-background-color: INDIANRED; -fx-background-radius: 0;");
		redButton.setPrefSize(20, 20);
		greenButton = new Button();
		greenButton.setStyle("-fx-background-color: SALMON; -fx-background-radius: 0;");
		greenButton.setPrefSize(20, 20);
		blueButton = new Button();
		blueButton.setStyle("-fx-background-color: SKYBLUE; -fx-background-radius: 0;");
		blueButton.setPrefSize(20, 20);
		pinkButton = new Button();
		pinkButton.setStyle("-fx-background-color: PLUM; -fx-background-radius: 0;");
		pinkButton.setPrefSize(20, 20);
		yellowButton = new Button();
		yellowButton.setStyle("-fx-background-color: LAVENDERBLUSH; -fx-background-radius: 0;");
		yellowButton.setPrefSize(20, 20);
		lightBlueButton = new Button();
		lightBlueButton.setStyle("-fx-background-color: GOLD; -fx-background-radius: 0;");
		lightBlueButton.setPrefSize(20, 20);
		orangeButton = new Button();
		orangeButton.setStyle("-fx-background-color: DARKSEAGREEN; -fx-background-radius: 0;");
		orangeButton.setPrefSize(20, 20);
		blackButton = new Button();
		blackButton.setStyle("-fx-background-color: DARKSLATEGREY; -fx-background-radius: 0;");
		blackButton.setPrefSize(20, 20);
		this.isEditable = true;
		
		colorButton.add(redButton, 1, 1);
		colorButton.add(greenButton, 2, 1);
		colorButton.add(blueButton, 3, 1);
		colorButton.add(pinkButton, 4, 1);
		colorButton.add(yellowButton, 1, 2);
		colorButton.add(lightBlueButton, 2, 2);
		colorButton.add(orangeButton, 3, 2);
		colorButton.add(blackButton, 4, 2);
		
		this.getChildren().add(colorButton);
		
		FlowPane nameZone = new FlowPane();
		nameZone.setPrefSize(200, 40);
		Label name = new Label("Name");
		name.setFont(Font.font("Tohoma",FontWeight.BLACK,FontPosture.REGULAR,15));
		textName = new TextField();
		textName.setPrefWidth(100);
		nameZone.getChildren().add(name);
		nameZone.getChildren().add(textName);
		nameZone.setAlignment(Pos.CENTER);
		nameZone.setHgap(10);
		
		this.getChildren().add(nameZone);
		
		redButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				setColor(Color.INDIANRED);
				DrawExample(gc);
			}
			
			
		});
		
		greenButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				setColor(Color.SALMON);
				DrawExample(gc);
			}
			
			
		});
		
		blueButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				setColor(Color.SKYBLUE);
				DrawExample(gc);
			}
			
			
		});
		
		pinkButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				setColor(Color.PLUM);
				DrawExample(gc);
			}
			
			
		});
		
		yellowButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				setColor(Color.LAVENDERBLUSH);
				DrawExample(gc);
			}
			
			
		});
		
		lightBlueButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				setColor(Color.GOLD);
				DrawExample(gc);
			}
			
			
		});
		
		orangeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				setColor(Color.DARKSEAGREEN);
				DrawExample(gc);
			}
			
			
		});
		
		blackButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				setColor(Color.DARKSLATEGREY);
				DrawExample(gc);
			}
			
			
		});
	}
	
	public GraphicsContext getGc() {
		return gc;
	}

	public synchronized void DrawExample(GraphicsContext gc) {
		 gc.setFill(color);
		gc.fillRect(80, 10, 40, 40);
		gc.setGlobalAlpha(1);
		
		if(!this.isEditable) {
			gc.setGlobalAlpha(0.4);
			gc.setFill(Color.GRAY);
			gc.fillRect(80, 10, 40, 40);
		}
	}

	
	public Color getColor() {
		return color;
	}

	private void setColor(Color color) {
		this.color = color;
	}
	
	public String getName() {
		return textName.getText();
	}
	
}
