package scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import densan.s.game.drawing.Drawer;
import densan.s.game.input.KeyInput;
import densan.s.game.manager.GameManager;
import densan.s.game.manager.Updatable;



public class GameOverScene implements Updatable{

	int waittime =0;
	private Font flipDesignFont ;
	public GameOverScene(){
		try{
			flipDesignFont = Font.createFont(Font.TRUETYPE_FONT, 
					TitleScene.class.getClassLoader().getResourceAsStream("FLOPDesignFont.ttf"));
		}catch(FontFormatException e){
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			flipDesignFont = flipDesignFont.deriveFont(42.0f);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(KeyInput.isPress(KeyEvent.VK_Z)&&waittime>60)
			GameManager.getInstance().setUpdatable(new TitleScene());
		if(waittime<=60){
			waittime++;
		}
	}

	@Override
	public void draw(Drawer d) {
		// TODO Auto-generated method stub
		d.setFont(flipDesignFont);
		d.setColor(Color.RED);
	//	d.setFontSize(40);
		
		
	
		
		d.drawStringCenter("GAME  OVER", GameManager.getInstance().getFrameWidth()/2, GameManager.getInstance().getFrameHeight()/2-100);
		
		d.setColor(Color.BLACK);
		d.setFontSize(20);
		if(waittime>60)
		d.drawStringCenter("Zキーを押してください", GameManager.getInstance().getFrameWidth()/2,  GameManager.getInstance().getFrameHeight()/2+40);
	}


	
	

}
