package Scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import densan.s.game.drawing.Drawer;
import densan.s.game.input.KeyInput;
import densan.s.game.manager.Updatable;
import object.TetrisList;
import system.Map;

public class InGameScene implements Updatable{
/**
 * 描画するMapのy値
 */
	private int drawLine = 4;
/**
 * 
 */
	private static final Map map = Map.getInstance();
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
	
				map.update();
		if(KeyInput.isPress(KeyEvent.VK_UP)) {
			drawLine++;
		}
		if(KeyInput.isPress(KeyEvent.VK_DOWN)){
			drawLine--;
		}
	}

	int[][][] list = new int[5][5][5];
	
	@Override
	public void draw(Drawer d) {
		// TODO Auto-generated method stub
		d.setColor(Color.BLACK);
		d.setFont(new Font("Arial", Font.BOLD , 24));
		d.drawString(drawLine+":", 20, 20);
		list = map.getMap();
		for(int x = 0; x<map.SIZE;x++){
			for(int z = 0; z < map.SIZE;z++){
				try {
				int tetrisID = map.getTetris(x, drawLine, z);
				
				switch (TetrisList.getType(tetrisID)) {
				case Empty:
					d.setColor(Color.BLACK);
					d.fillRect(30 + x*35, 450-z*35, 25, 25);
					break;

				default:
					d.setColor(Color.BLUE);
					d.fillRect(30 + x*35, 450-z*35, 25, 25);

					break;
				}
				}catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
						// TODO: handle exception
					drawLine=4;
					}
			}
		}
		
	}

}
