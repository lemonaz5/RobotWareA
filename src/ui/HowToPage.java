/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import logic.Utility;
import main.Main;
import model.RenderableHolder;

public class HowToPage extends StackPane {

	private Canvas canvas;
	private Button back;

	public HowToPage() {
		
		this.setPrefSize(Utility.screenWidth, Utility.screenHeight);
		this.canvas = new Canvas(Utility.screenWidth, Utility.screenHeight);
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.howto1, 0, 0);
		
		BorderPane all = new BorderPane();
		all.setPrefSize(Utility.screenWidth, Utility.screenHeight);
		all.setPadding(new Insets(10));
		this.getChildren().addAll(canvas,all);
		
		back = new Button();
		back.setGraphic(new ImageView(RenderableHolder.back));
		back.setStyle("-fx-background-color : transparent;");
		all.setTop(back);

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

		HBox h2 = new HBox();
		HBox h3 = new HBox();
		Button btn1 = new Button();
		btn1.setGraphic(new ImageView(RenderableHolder.arrow));
		btn1.setStyle("-fx-background-color : transparent;");
		Button btn2 = new Button();
		btn2.setGraphic(new ImageView(RenderableHolder.arrowf));
		btn2.setStyle("-fx-background-color : transparent;");
		btn2.setDisable(true);
		h2.setPadding(new Insets(300, 10, 50, 10));
		h3.setPadding(new Insets(300, 10, 50, 10));
		h2.getChildren().add(btn1);
		h3.getChildren().add(btn2);
		all.setLeft(h3);
		all.setRight(h2);

		btn1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.select.play();
				gc.clearRect(0, 0, Utility.screenWidth, Utility.screenHeight);
				gc.drawImage(RenderableHolder.howto2, 0, 0);
				btn2.setDisable(false);
				btn1.setDisable(true);
			}

		});
		
		btn1.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				RenderableHolder.canClick.play();
			}
		});

		btn2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.select.play();
				gc.clearRect(0, 0, Utility.screenWidth, Utility.screenHeight);
				gc.drawImage(RenderableHolder.howto1, 0, 0);
				btn1.setDisable(false);
				btn2.setDisable(true);
			}
		});
		
		btn2.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				RenderableHolder.canClick.play();
			}
		});

	}
}