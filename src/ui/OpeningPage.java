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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.Utility;
import main.Main;
import model.RenderableHolder;

public class OpeningPage extends StackPane {

	private Canvas canvas;
	private Button circle1, circle2, circle3, circle4, start, setting, howTo, rank;
	public OpeningPage() {
		this.setPrefSize(Utility.screenWidth, Utility.screenHeight);
		canvas = new Canvas(Utility.screenWidth, Utility.screenHeight);
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);

		gc.drawImage(RenderableHolder.home, 0, 0);

		// button
		Pane block = new Pane();
		block.setPrefSize(640, 25);
		start = new Button();
		start.setGraphic(new ImageView(RenderableHolder.start));
		start.setStyle("-fx-background-color : transparent;");
		setting = new Button();
		setting.setGraphic(new ImageView(RenderableHolder.setting));
		setting.setStyle("-fx-background-color : transparent;");
		howTo = new Button();
		howTo.setGraphic(new ImageView(RenderableHolder.howto));
		howTo.setStyle("-fx-background-color : transparent;");
		circle1 = new Button();
		circle1.setGraphic(new ImageView(RenderableHolder.circle));
		circle1.setStyle("-fx-background-color : transparent;");
		circle2 = new Button();
		circle2.setGraphic(new ImageView(RenderableHolder.circle));
		circle2.setStyle("-fx-background-color : transparent;");
		circle3 = new Button();
		circle3.setGraphic(new ImageView(RenderableHolder.circle));
		circle3.setStyle("-fx-background-color : transparent;");
		circle4 = new Button();
		circle4.setGraphic(new ImageView(RenderableHolder.circle));
		circle4.setStyle("-fx-background-color : transparent;");
		rank = new Button();
		rank.setGraphic(new ImageView(RenderableHolder.rank));
		rank.setStyle("-fx-background-color : transparent;");

		HBox h1 = new HBox();
		HBox h2 = new HBox();
		HBox h3 = new HBox();
		HBox h4 = new HBox();

		h1.getChildren().addAll(circle1, start);
		h2.getChildren().addAll(circle2, howTo);
		h3.getChildren().addAll(circle3, setting);
		h4.getChildren().addAll(circle4, rank);

		VBox onLeft = new VBox();
		onLeft.setPrefSize(640, 690);
		VBox.setMargin(h1, new Insets(10, 0, 20, 300));
		VBox.setMargin(h2, new Insets(15, 0, 0, 300));
		VBox.setMargin(h3, new Insets(0, 0, 0, 300));
		VBox.setMargin(h4, new Insets(0, 0, 0, 300));
		onLeft.getChildren().addAll(block, h1, h2, h3, h4);

		this.getChildren().add(onLeft);

		circle1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.select.play();
				Main.instance.toMain();
			}

		});

		circle1.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				RenderableHolder.canClick.play();
				circle1.setGraphic(new ImageView(RenderableHolder.circlex));
				circle1.setStyle("-fx-background-color : transparent;");
			}
		});

		circle1.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				circle1.setGraphic(new ImageView(RenderableHolder.circle));
				circle1.setStyle("-fx-background-color : transparent;");
			}
		});

		circle2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.select.play();
				Main.instance.toHowto();
			}

		});

		circle2.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				RenderableHolder.canClick.play();
				circle2.setGraphic(new ImageView(RenderableHolder.circlex));
				circle2.setStyle("-fx-background-color : transparent;");
			}
		});

		circle2.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				circle2.setGraphic(new ImageView(RenderableHolder.circle));
				circle2.setStyle("-fx-background-color : transparent;");
			}
		});

		// delete soon
		// rank.setOnAction(new EventHandler<ActionEvent>() {
		//
		// @Override
		// public void handle(ActionEvent event) {
		// // TODO Auto-generated method stub
		// Main.instance.toRanking();
		// RenderableHolder.select.play();
		// }
		// });
		//
		// rank.setOnMouseEntered(new EventHandler<Event>() {
		//
		// @Override
		// public void handle(Event event) {
		// // TODO Auto-generated method stub
		// RenderableHolder.canClick.play();
		// }
		// });

		if (Utility.squareList != null) {

			circle4.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					RenderableHolder.select.play();
					Main.instance.toRanking();
				}

			});

			circle4.setOnMouseEntered(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					RenderableHolder.canClick.play();
					circle4.setGraphic(new ImageView(RenderableHolder.circlex));
					circle4.setStyle("-fx-background-color : transparent;");
				}
			});

			circle4.setOnMouseExited(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					circle4.setGraphic(new ImageView(RenderableHolder.circle));
					circle4.setStyle("-fx-background-color : transparent;");
				}
			});

		}

		circle3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.select.play();
				Main.instance.toSetting();
			}
		});

		circle3.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				RenderableHolder.canClick.play();
				circle3.setGraphic(new ImageView(RenderableHolder.circlex));
				circle3.setStyle("-fx-background-color : transparent;");
			}
		});

		circle3.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				circle3.setGraphic(new ImageView(RenderableHolder.circle));
				circle3.setStyle("-fx-background-color : transparent;");

			}
		});
	}


}
