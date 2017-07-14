
import scene.Scene;
import scene.TestScene;
import scene.Updatable;

public class Main implements Updatable{
	
	public static void main(String args[]){
		Scene.getInstance().UpdateByFps(args, new TestScene());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	


}
