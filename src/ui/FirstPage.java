package ui;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import main.Main;
import model.RenderableHolder;

public class FirstPage extends StackPane {

	private Canvas canvas;
	private boolean stop = false;

	public FirstPage() {
		this.setPrefSize(640, 690);
		canvas = new Canvas(640, 690);
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);

		RenderableHolder.song1.setCycleCount(AudioClip.INDEFINITE);
		RenderableHolder.song1.play();
		drawName(gc);

		this.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				stop = true;
				Main.instance.toOpening();
			}
		});
	}

	private void drawWannita(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.nameBg, 0, 0);
		gc.drawImage(RenderableHolder.wannita, 110, 260);
	}

	private void drawTechin(GraphicsContext gc) {
		// gc.clearRect(0, 0, 640, 690);
		gc.drawImage(RenderableHolder.nameBg, 0, 0);
		gc.drawImage(RenderableHolder.techin, 150, 260);
	}

	private void drawName(GraphicsContext gc) {
		new AnimationTimer() {
			Long start = 0l;

			@Override
			public void handle(long now) {
				if (start == 0l) {
					start = now;
				}
				long diff = now - start;
				if (diff <= 4200000000l) { // 100000000l = 100ms.
					drawWannita(gc);
				} else if (diff > 4200000000l && diff <= 7700000000l) {
					drawTechin(gc);
				} else {
					drawOpen(gc);
					stop();
				}
			}
		}.start();
	}

	private void drawOpen(GraphicsContext gc) {
		new AnimationTimer() {
			Long start = 0l;

			@Override
			public void handle(long now) {

				if (start == 0l) {
					start = now;
				}
				long diff = now - start;
				if (diff <= 400000000l) { // 100000000l = 100ms.
					gc.drawImage(RenderableHolder.title1, 0, 0);
				} else if (diff > 400000000l && diff <= 700000000l) {
					gc.drawImage(RenderableHolder.title2, 0, 0);
					gc.drawImage(RenderableHolder.clickpic, 205, 530);
				} else if (diff > 700000000l && diff <= 1100000000l) {
					gc.drawImage(RenderableHolder.title3, 0, 0);
					gc.drawImage(RenderableHolder.clickpic, 205, 530);
				} else if (diff > 1100000000l && diff <= 1500000000l) {
					gc.drawImage(RenderableHolder.title2, 0, 0);
					gc.drawImage(RenderableHolder.clickpic, 205, 530);
					start = now;
				}

				if (stop) {
					stop();
				}

			}

		}.start();
	}
}
