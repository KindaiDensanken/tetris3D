package scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import densan.s.game.drawing.Drawer;
import densan.s.game.manager.GameManager;
import densan.s.game.manager.Updatable;
import object.TetrisList;
import system.Map;

public class InGameScene implements Updatable{
/**
 * 描画するMapのy値
 */
	private int drawLine = 4;
private Font flipDesignFont;
/**
 * 
 */
	private static final Map map = Map.getInstance();
	
	public static int LEVEL = 1;
	
	public  InGameScene() {
		// TODO 自動生成されたコンストラクター・スタブ
		LEVEL=1;
		try{
			flipDesignFont = Font.createFont(Font.TRUETYPE_FONT, 
					TitleScene.class.getClassLoader().getResourceAsStream("FLOPDesignFont.ttf"));
		}catch(FontFormatException e){
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			flipDesignFont = flipDesignFont.deriveFont(40.0f);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
	
				map.update();
				/*
		if(KeyInput.isPress(KeyEvent.VK_UP)) {
			drawLine++;
		}
		if(KeyInput.isPress(KeyEvent.VK_DOWN)){
			drawLine--;
		}
		*/
	}

	int[][][] list = new int[5][5][5];
	
	public void draw(Drawer d) {
		// TODO Auto-generated method stub
		d.setColor(Color.BLACK);
		list = map.getMap();
		
		for(int y=0;y<Map.SIZE;y++)
		for(int x = 0; x<Map.SIZE;x++){
			for(int z = 0; z < Map.SIZE;z++){
				try {
				int tetrisID = map.getTetris(x, y, z);
				
				switch (TetrisList.getType(tetrisID)) {
				case Empty:
					d.setColor(Color.BLACK);
					d.fillRect(30 + x*35, 600-200*y+200-z*35, 25, 25);
					break;

				default:
					d.setColor(Color.BLUE);
					d.fillRect(30 + x*35, 600-200*y+200-z*35, 25, 25);

					break;
				}
				}catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
						// TODO: handle exception
					drawLine=4;
					}
			}
			
		}
		drawScore(d);
		drawLevel(d);
		//draw
		d.drawString("WASDキーで移動", GameManager.getInstance().getFrameWidth()/2-40, GameManager.getInstance().getFrameHeight()-400);
		d.drawString("→↓←↑キーで回転", GameManager.getInstance().getFrameWidth()/2-40, GameManager.getInstance().getFrameHeight()-450);
	}

	private void drawLevel(Drawer d) {
		// TODO 自動生成されたメソッド・スタブ
		d.setColor(Color.BLACK);
		d.drawString("LEVEL:", GameManager.getInstance().getFrameWidth()/2, 200);
		switch (LEVEL) {
		case 1:
			d.setColor(Color.CYAN);
			break;
		case 2:
			d.setColor(Color.YELLOW);
			break;
		case 3:
			d.setColor(Color.RED);
			break;
		default:
			break;
		}
		d.drawString("  "+LEVEL, GameManager.getInstance().getFrameWidth()/2+100, 200);
		d.setColor(Color.BLACK);
	}

	private void drawScore(Drawer d) {
		// TODO 自動生成されたメソッド・スタブ
		d.setFont(d.getFont().deriveFont(40));
		d.setColor(Color.RED);
		d.drawString("Score:", GameManager.getInstance().getFrameWidth()/2, 250);
		d.setColor(Color.BLACK);
		d.drawString("  "+Map.getInstance().getScore(), GameManager.getInstance().getFrameWidth()/2+100, 250);
		
	}

	public static void levelUp(){
		LEVEL++;
		switch (LEVEL) {
		case 2:
			Map.getInstance().setFalllimit(300);
			break;
		case 3:
			Map.getInstance().setFalllimit(200);
			break;
		default:
			System.err.println("LEVEL error");
		}
	}
}
