/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package main;

import ui.FirstPage;
import ui.GameScreen;
import ui.HowToPage;
import ui.MainPage;
import ui.OpeningPage;
import ui.RankingPage;
import ui.SettingPage;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import logic.GameManager;
import logic.InputUtility;
import logic.Utility;
import model.RenderableHolder;

public class Main extends Application {

	public static Main instance;
	private Stage primaryStage;
	private MainPage mainPage;
	private HowToPage howToPage;
	private GameScreen gameScreen;
	private RankingPage rankingPage;
	private SettingPage settingPage;
	private OpeningPage openingPage;
	private FirstPage firstPage;
	private Scene firstScene, settingScene, mainScene, howToScene, gameScene, rankingScene, openScene;
	private GameManager gameManager;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setResizable(false);
		this.primaryStage.setTitle("Robot Warea");
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		mainPage = new MainPage();
		mainScene = new Scene(mainPage);
		
		firstPage = new FirstPage();
		FadeTransition ft = new FadeTransition(Duration.millis(3500), firstPage);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		firstScene = new Scene(firstPage);
		this.primaryStage.setScene(firstScene);
		this.primaryStage.show();

	}

	public synchronized void toHowto() {
		howToPage = new HowToPage();
		howToScene = new Scene(howToPage);
		this.primaryStage.setScene(howToScene);
	}

	public synchronized void toMain() {
		this.primaryStage.setScene(mainScene);
	}

	public synchronized void toRanking() {
		rankingPage = new RankingPage();
		rankingScene = new Scene(rankingPage);
		this.primaryStage.setScene(rankingScene);
		gameScreen = null;
		gameScene = null;
	}

	public synchronized void toSetting() {
		settingPage = new SettingPage();
		settingScene = new Scene(settingPage);
		this.primaryStage.setScene(settingScene);
	}

	public synchronized void toOpening() {
		openingPage = new OpeningPage();
		FadeTransition ft = new FadeTransition(Duration.millis(2000), openingPage);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		openScene = new Scene(openingPage);
		this.primaryStage.setScene(openScene);
	}

	public synchronized void toGame() {
		gameManager = new GameManager();
		gameScreen = new GameScreen(Utility.screenWidth, Utility.screenHeight, gameManager);
		gameScene = new Scene(gameScreen);
		gameScreen.requestFocus();
		this.primaryStage.setScene(gameScene);

		gameScene.setOnKeyPressed((KeyEvent event) -> {
			gameManager.receiveKey(event.getCode());
			InputUtility.setKeyPressed(event.getCode(), true);
			InputUtility.typeEnter();
			if (InputUtility.isExit()) {
				GameScreen.threadTimer.interrupt();
				Utility.clearUtility();
				RenderableHolder.getInstance().getEntities().clear();
				InputUtility.clearKey();
				Main.instance.toRanking();
			}
		});

		gameScene.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), false);
		});
	}

}
