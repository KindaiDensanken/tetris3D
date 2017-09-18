package scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.event.KeyEvent;
import java.io.IOException;

import system.Map;
import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;
import densan.s.game.input.KeyInput;
import densan.s.game.manager.GameManager;
import densan.s.game.manager.Updatable;
/**
 * タイトルシーン
 * @author tachibana
 *
 */
public class TitleScene implements Updatable {

	private static long HIGHSCORE =0;
	
	private int flashcount = 0;
	
	private boolean hide =false;
	private static final int FLASHSPEED = 40;
	private static final Image BRONZE = ImageLoader.load("picture/bronze.png");
	private static final Image SILVER = ImageLoader.load("picture/silver.png");
	private static final Image GOLD = ImageLoader.load("picture/gold.png");
	
	public TitleScene(){
		try{
			flipDesignFont = Font.createFont(Font.TRUETYPE_FONT, 
					TitleScene.class.getClassLoader().getResourceAsStream("FLOPDesignFont.ttf"));
		}catch(FontFormatException e){
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			flipDesignFont = flipDesignFont.deriveFont(30.0f);
	}
	public static Font getFont(float size){
		if(flipDesignFont ==null){
			try{
				flipDesignFont = Font.createFont(Font.TRUETYPE_FONT, 
						TitleScene.class.getClassLoader().getResourceAsStream("FLOPDesignFont.ttf"));
			}catch(FontFormatException e){
				e.printStackTrace();
			}catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
				flipDesignFont = flipDesignFont.deriveFont(size);
		}
		return flipDesignFont;
		
	}
	
	@Override
	public void update() {
		// TODO 自動生成されたメソッド・スタブ

		if(KeyInput.isPress(KeyEvent.VK_Z)){
			Map.getInstance().init();
			GameManager.getInstance().setUpdatable(new InGameScene());
		}
		flash();
	}
	/**
	 * 点滅関連の数値の処理
	 */
	private void flash(){
		if(flashcount>FLASHSPEED){
			hide = !hide;//hideフラグの反転
			flashcount=0;
		}
		flashcount++;
		
	}

	private static Font flipDesignFont;
	
	@Override
	public void draw(Drawer d) {
		// TODO 自動生成されたメソッド・スタブ
		
		/*
		d.setFont(new Font("Arial", Font.BOLD , 24));
		*/
		d.setFont(flipDesignFont);
		
		d.setColor(Color.RED);
		d.drawStringCenter("3D-TETRIS", GameManager.getInstance().getFrameWidth()/2, 50);
		d.setColor(Color.BLACK);
		if(!hide)
		d.drawStringCenter("Zキーでゲームスタート", GameManager.getInstance().getFrameWidth()/2, GameManager.getInstance().getFrameHeight()/2+140);
		d.drawStringCenter("1st 5000", GameManager.getInstance().getFrameWidth()/2, 320);
		d.drawStringCenter("2nd 3000", GameManager.getInstance().getFrameWidth()/2, 350);
		d.drawStringCenter("3rd 1000", GameManager.getInstance().getFrameWidth()/2, 380);
		d.drawStringCenter(""+HIGHSCORE, GameManager.getInstance().getFrameWidth()/2, 480);
		d.setFont(getFont(50.0f));
		d.setColor(Color.ORANGE);
	//	d.setFontSize(40);
		d.drawStringCenter("目標スコア", GameManager.getInstance().getFrameWidth()/2, 220);
		d.drawStringCenter("HIGH SCORE", GameManager.getInstance().getFrameWidth()/2, 440);
		d.setColor(Color.RED);
		
		drawMedal(d);
	}
	/**
	 * 
	 * @param d
	 */
	private void drawMedal(Drawer d) {
		// TODO 自動生成されたメソッド・スタブ
		if(5000<=HIGHSCORE)
			d.drawImage(GOLD, 100, 310);
		if(3000<=HIGHSCORE)
			d.drawImage(SILVER, 100, 340);
		if(1000<=HIGHSCORE)
			d.drawImage(BRONZE, 100, 370);
		
	}

	/**
	 * スコアの更新
	 * @param score
	 */
	public static void compHIGHSCORE(long score){
		if(HIGHSCORE<score)
			HIGHSCORE=score;
	}

}
