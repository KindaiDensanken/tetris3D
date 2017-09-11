import Scene.InGameScene;
import densan.s.game.manager.GameManager;

public class Main {

	public static void main(String[] args) {
		GameManager.getInstance().createFrame(640, 480);
		GameManager.getInstance().setUpdatable(new InGameScene());
	}
}
