/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import model.Field;
import model.Square;

public class Utility {
	public static ArrayList<KeyCode[]> keyCode = new ArrayList<>();
	public static ArrayList<Color> color = new ArrayList<>();
	public static ArrayList<String> name = new ArrayList<>();
	public static Square[] squareList = null;
	public static int timeRemaining;
	public static int player;
	public static Field field = new Field();
	public static boolean isPause = false;
	public static final int screenWidth = 640;
	public static final int screenHeight = 690;
	public static final Color noSquareColor = Color.LIGHTGRAY;
	public static final int tickPerSecond = 10;
	
	public static void initializeKeyCode() {
		//create key code for 4 players.
		KeyCode[] key = new KeyCode[4];
		key[0] = KeyCode.W;
		key[1] = KeyCode.A;
		key[2] = KeyCode.S;
		key[3] = KeyCode.D;
		keyCode.add(key);
		KeyCode[] key2 = new KeyCode[4];
		key2[0] = KeyCode.UP;
		key2[1] = KeyCode.LEFT;
		key2[2] = KeyCode.DOWN;
		key2[3] = KeyCode.RIGHT;
		keyCode.add(key2);
		KeyCode[] key3 = new KeyCode[4];
		key3[0] = KeyCode.Y;
		key3[1] = KeyCode.G;
		key3[2] = KeyCode.H;
		key3[3] = KeyCode.J;
		keyCode.add(key3);
		KeyCode[] key4 = new KeyCode[4];
		key4[0] = KeyCode.O;
		key4[1] = KeyCode.K;
		key4[2] = KeyCode.L;
		key4[3] = KeyCode.SEMICOLON;
		keyCode.add(key4);
	}
	
	public static void clearUtility() {
		//clear utility for new game.
		color.clear();
		name.clear();
		keyCode.clear();
		field.clearField();
		isPause = false;
	}
	
	public static double round(double value, int places) {
		//round to 2 point of double.
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
