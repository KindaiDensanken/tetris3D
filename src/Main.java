import densan.s.game.manager.GameManager;
import scene.InGameScene;
import scene.TitleScene;


public class Main {

	public static void main(String[] args) {
		GameManager.getInstance().createFrame(480, 960);
		GameManager.getInstance().setUpdatable(new TitleScene());
	}
}
