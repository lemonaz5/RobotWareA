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
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import logic.Utility;
import main.Main;
import model.RenderableHolder;

public class SettingPage extends StackPane {

	private Canvas canvas;
	private GraphicsContext gc;
	private Button back;
	private Thread settingThread;

	public SettingPage() {
		this.setPrefSize(Utility.screenWidth, Utility.screenHeight);
		this.setAlignment(Pos.CENTER);
		canvas = new Canvas(Utility.screenWidth, Utility.screenHeight);
		gc = this.canvas.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.settingPage1, 0, 0);

		VBox all = new VBox();
		this.getChildren().addAll(canvas, all);

		back = new Button();
		back.setGraphic(new ImageView(RenderableHolder.back));
		back.setStyle("-fx-background-color : transparent;");
		VBox.setMargin(back, new Insets(15, 15, 72, 515));

		Button song1 = new Button("Maple");
		song1.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: \"Arial Narrow\"; -fx-background-color: darkgreen;");
		Button song2 = new Button("Ragnarok");
		song2.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: \"Arial Narrow\"; -fx-background-color: darkred;");
		Button song3 = new Button("Pangya");
		song3.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: \"Arial Narrow\"; -fx-background-color: darkblue;");
		Button song4 = new Button("Yiruma");
		song4.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: \"Arial Narrow\"; -fx-background-color: darkkhaki;");

		VBox.setMargin(song1, new Insets(15));
		VBox.setMargin(song2, new Insets(15));
		VBox.setMargin(song3, new Insets(15));
		VBox.setMargin(song4, new Insets(15));

		VBox v = new VBox();
		v.setPrefSize(640, 590);
		v.setAlignment(Pos.CENTER);
		v.getChildren().addAll(song1, song2, song3, song4);
		all.getChildren().addAll(v, back);

		song1.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				RenderableHolder.canClick.play();
			}
		});

		song1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.select.play();
				RenderableHolder.song2.stop();
				RenderableHolder.song3.stop();
				RenderableHolder.song4.stop();
				if (!RenderableHolder.song1.isPlaying()) {
					RenderableHolder.song1.setCycleCount(AudioClip.INDEFINITE);
					RenderableHolder.song1.play();
				}
			}
		});

		song2.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				RenderableHolder.canClick.play();
			}
		});

		song2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.select.play();
				RenderableHolder.song1.stop();
				RenderableHolder.song3.stop();
				RenderableHolder.song4.stop();
				if (!RenderableHolder.song2.isPlaying()) {
					RenderableHolder.song2.setCycleCount(AudioClip.INDEFINITE);
					RenderableHolder.song2.play();
				}
			}
		});

		song3.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				RenderableHolder.canClick.play();
			}
		});

		song3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.select.play();
				RenderableHolder.song1.stop();
				RenderableHolder.song2.stop();
				RenderableHolder.song4.stop();
				if (!RenderableHolder.song3.isPlaying()) {
					RenderableHolder.song3.setCycleCount(AudioClip.INDEFINITE);
					RenderableHolder.song3.play();
				}
			}
		});

		song4.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				RenderableHolder.canClick.play();
			}
		});

		song4.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.select.play();
				RenderableHolder.song1.stop();
				RenderableHolder.song2.stop();
				RenderableHolder.song3.stop();
				if (!RenderableHolder.song4.isPlaying()) {
					RenderableHolder.song4.setCycleCount(AudioClip.INDEFINITE);
					RenderableHolder.song4.play();
				}
			}
		});

		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Main.instance.toOpening();
				RenderableHolder.select.play();
				settingThread.interrupt();
			}
		});

		back.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				RenderableHolder.canClick.play();
			}
		});
		
		settingThread = new Thread(() -> {
			try {
				Thread.sleep(3000);
				gc.drawImage(RenderableHolder.settingPage2, 0, 0);
				VBox.setMargin(back, new Insets(15, 300, 85, 230));

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
			}

		});
		settingThread.start();

	}

}
