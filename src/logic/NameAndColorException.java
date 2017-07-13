/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package logic;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

public class NameAndColorException extends Exception{

	private ArrayList<String> nameList;
	private ArrayList<Color> colorList;
	
	public NameAndColorException(ArrayList<String> nameList,ArrayList<Color> colorList) {
		this.nameList = nameList;
		this.colorList = colorList;
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		String text = "";
		text+=checkErrorName();
		text+=checkSameName();
		text+=checkSameColor();
		
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.show();
	}
	
	
	private String checkErrorName() {
		//add text when it has error name.
		String text = "";
		for(int i = 0;i<nameList.size();i++) {
			String name = nameList.get(i);
			if(name.trim().equals("")) {
				text+="- Player " + (i + 1) + " don't have a name.\n";
			}
			else if(name.trim().length()>4) {
				text+="- Player " + (i + 1) + " have a name more than 4 characters.\n";
			}
		}
		return text;
	}
	
	private String checkSameName() {
		//add text when it has same name.
		String text = "";
		boolean notEmpty;
		for(int i=0;i<nameList.size()-1;i++) {
			for(int j=i+1;j<nameList.size();j++) {
				notEmpty = !nameList.get(i).trim().equals("");
				if(nameList.get(i).equals(nameList.get(j)) && notEmpty) {
					text+="- You use the same name.\n";
					return text;
				}
			}
		}
		return text;
	}
	
	private String checkSameColor() {
		//add text when it has same color.
		String text = "";
		for(int i=0;i<colorList.size()-1;i++) {
			for(int j=i+1;j<colorList.size();j++) {
				if(colorList.get(i).equals(colorList.get(j)) ) {
					text+="- You use the same color.";
					return text;
				}
			}
		}
		return text;
	}
	

}
