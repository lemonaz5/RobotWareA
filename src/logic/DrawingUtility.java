/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawingUtility {
	
	private static final double transcluentWhite = 0.7;
	private static final double shadow = 0.4;
	private static final double opaque = 1;
	private static final int blockSize = 10;
	
	public static void drawBlock(GraphicsContext gc,int x,int y,Color color) {
		gc.setGlobalAlpha(shadow);
		gc.setFill(Color.BLACK);
		gc.fillRect(x * blockSize, (y * blockSize), blockSize, blockSize);
		gc.setGlobalAlpha(opaque);
		gc.setFill(color);
		gc.fillRect((x * blockSize) - 2, (y * blockSize) - 2, blockSize , blockSize);
	}
	
	public static void drawTable(GraphicsContext gc,int x,int y,Color color) {
		gc.setGlobalAlpha(opaque);
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(0.10);
		gc.strokeRect(x * blockSize, (y * blockSize), blockSize, blockSize);
	}
	
	public static void drawLine(GraphicsContext gc,int x,int y,Color color) {
		gc.setGlobalAlpha(transcluentWhite);
		gc.setFill(color);
		gc.fillRect((x * blockSize) - 2, (y * blockSize) - 2, blockSize , blockSize);
		
		gc.setFill(Color.WHITE);
		gc.fillRect((x * blockSize) - 2, (y * blockSize) - 2, blockSize , blockSize);
		
		gc.setGlobalAlpha(opaque);
	}
	
	public static void drawCrown(GraphicsContext gc,int x,int y,Color color) {
		gc.setFill(Color.YELLOW);
		gc.fillRect((x * blockSize), (y * blockSize) - 3, 6, 5);
	}
}
