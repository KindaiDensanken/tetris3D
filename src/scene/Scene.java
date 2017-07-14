package scene;
import gui.FxApp;

public  class Scene {
	// TODO Auto-generated method stub
	//javafxの起動
	private static Scene instance = new Scene();
	private  Scene(){
		
	}
	
	public static Scene getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

	public  void UpdateByFps(String[] args,Updatable updatable){
	FxApp.call(args);
	long error = 0;  
	int fps = 60;  
	long idealSleep = (1000 << 16) / fps;  
	long oldTime;  
	long newTime = System.currentTimeMillis() << 16;  
	while (true) {  
	  oldTime = newTime;  
	  updatable.update(); // メイン処理  
	  newTime = System.currentTimeMillis() << 16;  
	  long sleepTime = idealSleep - (newTime - oldTime) - error; // 休止できる時間  
	  if (sleepTime < 0x20000) sleepTime = 0x20000; // 最低でも2msは休止  
	  oldTime = newTime;  
	  try{
	  Thread.sleep(sleepTime >> 16); // 休止  
	  }catch(InterruptedException e){
		  System.err.println("システムの割り込み");
	  }
	  newTime = System.currentTimeMillis() << 16;  
	  error = newTime - oldTime - sleepTime; // 休止時間の誤差  
	}  
}

}
