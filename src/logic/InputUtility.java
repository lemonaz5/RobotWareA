/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package logic;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import model.RenderableHolder;

public class InputUtility {

	public static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	public static ArrayList<KeyCode> keyTriggered = new ArrayList<>();
	
	public static void setKeyPressed(KeyCode keycode , boolean pressed) {
		//set keyPressed and keyTriggered
		if(pressed){
			if(!keyPressed.contains(keycode)){
				keyPressed.add(keycode);
				keyTriggered.add(keycode);
			}
			else if(keyPressed.contains(keycode) && keyTriggered.contains(keycode)) {
				keyTriggered.remove(keycode);
			}
		}else{
			keyPressed.remove(keycode);
			if(keyTriggered.contains(keycode)) keyTriggered.remove(keycode);
		}
	}
	
	public static void typeEnter() {
		//toggle pause when type enter.
		for(KeyCode k : keyTriggered) {
			if(k.getName().toString().equals(KeyCode.ENTER.getName().toString())) {
				Utility.isPause = !Utility.isPause;
				RenderableHolder.pause.play();
			}
		}
	}
	
	public static boolean isExit() {
		//check that press exit when game is paused.
		if(Utility.isPause && keyTriggered.contains(KeyCode.ESCAPE)) {
			return true;
			
		}
		return false;
	}
	
	public static void clearKey() {
		//clear key in ArrayList when out of gameScreen
		keyPressed.clear();
		keyTriggered.clear();
	}
	
}
