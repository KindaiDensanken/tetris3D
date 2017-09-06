


import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.LightBase;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import javafx.util.Duration;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.crypto.Data;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.TransformException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.*;
import scene.InGameScene;
import scene.TestScene;
import scene.Updatable;
import system.Map;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;


public class Main  extends Application implements Updatable{
	
	public static Scene scene;
	
	public static Stage primaryStage;
	
	public static Group root;
	
	public static void main(String args[]){
		launch(args);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
    	this.primaryStage=primaryStage;
        // ルートノードの作成
    //	 final ExecutorService service = Executors.newFixedThreadPool(5);
    	
    	root = create3DGroup();
    	 
    	//一秒ごとに更新する
    	Timeline timer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>(){
    		@Override
    		public void handle(ActionEvent event) {
    			// TODO Auto-generated method stub
    			root =create3DGroup();
    		}
    	}));
    	 timer.setCycleCount(Timeline.INDEFINITE);
         timer.play();
        Camera      camera      = new PerspectiveCamera( true );
     
        
        //カメラをz軸方向に-20移動
        camera.getTransforms().addAll(new Translate(0,0,-80));
        camera.setRotate(-30);
        camera.setRotationAxis(Rotate.X_AXIS);
     //   camera.setRotate(-45);
     //   camera.setRotationAxis(Rotate.Y_AXIS);
   //     camera.setRotate(30);
     //   camera.setRotationAxis(Rotate.Y_AXIS);
    //    camera.getTransforms().add(new Rotate(10,Rotate.X_AXIS));
//    ?    camera.getTransforms().add( new Translate( 0.0 , 0.0 , -30 ) );
  //      root.getChildren().add( camera );
         
        // 照明を設定
     
         
        // 3D用のーンを作成
        scene          = new Scene( root , 500 , 500 , true );
        scene.setFill( Color.BLACK );
        scene.setCamera( camera );
         
        // ウィンドウ表示
        primaryStage.setScene( scene );
        primaryStage.show();
       // InGameScene.getInstance().UpdateByFps(this);
        
	}
	/**
     * 3Dオブジェクトのグループを作成する
     * @return
     */
	int count = 0;
    public Group create3DGroup()
    {
        // シーングラフのルートを作成
    	
      root        = new Group();
      int[][][] list = Map.getInstance().getMap();
      ArrayList<Box> boxList = new ArrayList<Box>();
      if(count>5){
    	  list[0][0][0]= 3;
      }
      System.out.println(list[0][0][0]);
        // BOXを作成
      IntStream.range(0, list.length).forEach(s->IntStream.range(0, list[s].length).
				forEach(t->IntStream.range(0,list[s][t].length).filter(u -> list[s][t][u]==0)
					.forEach(i->boxList.add(createBox(s,t,i)))));
   
      boxList.stream().forEach(s->root.getChildren().add(s));
      
      
      System.out.println(boxList.size());
      count++;
      primaryStage.setScene( scene );
      primaryStage.show();
        return root;
    }
	
    /**
     * 
     * @param x Mapクラスの配列の1つ目のインデックス
     * @param y Mapクラスの配列の2つ目のインデックス
     * @param z Mapクラスの配列の3つ目のインデックス
     * @return
     */
    private Box createBox(int x ,int y,int z){
    	 Box         box         = new Box();
         box.setWidth( 3);
         box.setHeight(3);
         box.setDepth( 3);
         box.getTransforms().add(new Translate(-7.5+3*x,-y*3,-7.5+3*z));
         return box;
    }

}
