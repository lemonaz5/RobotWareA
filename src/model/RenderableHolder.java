/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image home, main, settingPage1, settingPage2, howto1, howto2, nameBg, gameBackground, title1, title2,
			title3;
	public static Image back, circle, circlex, howto, play, playh, setting, arrow, arrowf, start, pausepic, rank;
	public static Image salmon, red, blue, green, purple, gold, black, white;
	public static Image wannita, techin, clickpic;
	public static AudioClip song1, song2, song3, song4;
	public static AudioClip winner, pause, canClick, dead, select;

	public static final RenderableHolder instance = new RenderableHolder();;

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {

			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	static {
		loadResource();
	}

	private static void loadResource() {
		title1 = new Image(ClassLoader.getSystemResource("title1.png").toString());
		title2 = new Image(ClassLoader.getSystemResource("title2.png").toString());
		title3 = new Image(ClassLoader.getSystemResource("title3.png").toString());
		home = new Image(ClassLoader.getSystemResource("homepage.png").toString());
		main = new Image(ClassLoader.getSystemResource("bg.png").toString());
		wannita = new Image(ClassLoader.getSystemResource("word_namewannita.png").toString());
		techin = new Image(ClassLoader.getSystemResource("word_nametechin.png").toString());
		clickpic = new Image(ClassLoader.getSystemResource("word_click.png").toString());
		song1 = new AudioClip(ClassLoader.getSystemResource("Title.wav").toString());
		play = new Image(ClassLoader.getSystemResource("word_play.png").toString());
		playh = new Image(ClassLoader.getSystemResource("word_playheading.png").toString());
		back = new Image(ClassLoader.getSystemResource("word_back.png").toString());
		canClick = new AudioClip(ClassLoader.getSystemResource("WORMSELECT.WAV").toString());
		select = new AudioClip(ClassLoader.getSystemResource("button-30.mp3").toString());
		Thread t = new Thread(() -> {
			settingPage1 = new Image(ClassLoader.getSystemResource("settingpage1.png").toString());
			settingPage2 = new Image(ClassLoader.getSystemResource("settingpage2.png").toString());
			gameBackground = new Image(ClassLoader.getSystemResource("paper.png").toString());
			rank = new Image(ClassLoader.getSystemResource("word_rank.png").toString());
			nameBg = new Image(ClassLoader.getSystemResource("name_bg.png").toString());
			start = new Image(ClassLoader.getSystemResource("word_startgame.png").toString());
			pausepic = new Image(ClassLoader.getSystemResource("pauseW.png").toString());
			howto1 = new Image(ClassLoader.getSystemResource("howtopage1.png").toString());
			howto2 = new Image(ClassLoader.getSystemResource("howto2new.png").toString());
			arrow = new Image(ClassLoader.getSystemResource("word_arrow.png").toString());
			arrowf = new Image(ClassLoader.getSystemResource("word_arrowflip.png").toString());
			circle = new Image(ClassLoader.getSystemResource("word_circle.png").toString());
			circlex = new Image(ClassLoader.getSystemResource("word_circlex.png").toString());
			howto = new Image(ClassLoader.getSystemResource("word_howto.png").toString());
			setting = new Image(ClassLoader.getSystemResource("word_setting.png").toString());
			salmon = new Image(ClassLoader.getSystemResource("avatar1.png").toString());
			red = new Image(ClassLoader.getSystemResource("avatar2.png").toString());
			blue = new Image(ClassLoader.getSystemResource("avatar3.png").toString());
			white = new Image(ClassLoader.getSystemResource("avatar4.png").toString());
			green = new Image(ClassLoader.getSystemResource("avatar5.png").toString());
			purple = new Image(ClassLoader.getSystemResource("avatar6.png").toString());
			black = new Image(ClassLoader.getSystemResource("avatar7.png").toString());
			gold = new Image(ClassLoader.getSystemResource("avatar8.png").toString());
			dead = new AudioClip(ClassLoader.getSystemResource("Worm Sound Bye Bye.mp3").toString());
			pause = new AudioClip(ClassLoader.getSystemResource("PAUSETICK.WAV").toString());
			winner = new AudioClip(ClassLoader.getSystemResource("CrowdPart2.wav").toString());
		});
		Thread r = new Thread(() -> {
			song2 = new AudioClip(ClassLoader.getSystemResource("08.wav").toString());
		});
		Thread s = new Thread(()-> {
			song3 = new AudioClip(ClassLoader.getSystemResource("Crystal Waver.wav").toString());
		});
		Thread g = new Thread(()-> {
			song4 = new AudioClip(ClassLoader.getSystemResource("Yiruma - River Flows in You.wav").toString());
		});
		s.start();
		g.start();
		r.start();
		t.start();
	}

	public synchronized void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public synchronized void remove(int index) {
		entities.remove(index);
	}

	public synchronized static RenderableHolder getInstance() {
		return instance;
	}

	public synchronized List<IRenderable> getEntities() {
		return entities;
	}
}
