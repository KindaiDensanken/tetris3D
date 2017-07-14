package gui;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.LightBase;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
 
public class FxApp extends Application
{
	public static Scene scene;
	
	public static Stage primaryStage;
	
    public static void call(String[] args)
    { launch( args );}
     
    @Override
    public void start(Stage primaryStage) throws Exception
    {
    	this.primaryStage=primaryStage;
        // ルートノードの作成
        Group       root        = create3DGroup();
        // カメラを設定
        Camera      camera      = new PerspectiveCamera( true );
        camera.getTransforms().add( new Rotate( 30 , 0 , 0 , 0 , new Point3D( -1 , -1 , 0 ) ) );
        camera.getTransforms().add( new Translate( 0.0 , 0.0 , -30 ) );
        root.getChildren().add( camera );
         
        // 照明を設定
        LightBase   light       = new PointLight();
        light.setTranslateX( 0.0 );
        light.setTranslateY( -20.0 );
        light.setTranslateZ( -30.0 );
        root.getChildren().add( light );
         
        // 3D用のーンを作成
        scene          = new Scene( root , 500 , 500 , true );
        scene.setFill( Color.BLACK );
        scene.setCamera( camera );
         
        // ウィンドウ表示
        primaryStage.setScene( scene );
        primaryStage.show();
         
    }
     
    /**
     * 3Dオブジェクトのグループを作成する
     * @return
     */
    public Group create3DGroup()
    {
        // シーングラフのルートを作成
        Group       root        = new Group();
         
        // BOXを作成
        Box         box         = new Box();
        box.setWidth( 2 );
        box.setHeight( 2 );
        box.setDepth( 2 );
        box.setTranslateX( -5 );
        box.setTranslateY( -3 );
        root.getChildren().add( box );
         /*
        // 球の作成
        Sphere      sphere      = new Sphere();
        sphere.setRadius( 2 );
        sphere.setTranslateX( 0 );
        sphere.setTranslateY( -3 );
        root.getChildren().add( sphere );
         
        // 円柱の作成
        Cylinder    cylinder    = new Cylinder();
        cylinder.setRadius( 2 );
        cylinder.setHeight( 4 );
        cylinder.setTranslateX( 5 );
        cylinder.setTranslateY( -3 );
        root.getChildren().add( cylinder );
         
        // メッシュの作成
        // 【頂点座標】
        // p0┏━┓p3
        //   ┃＼┃
        // p1┗━┛p2
        TriangleMesh    mesh        = new TriangleMesh();
        float[]         points      = { -2      ,-2     ,0      ,   // p0
                                        -2      ,2      ,0      ,   // p1
                                        2       ,2      ,0      ,   // p2
                                        2       ,-2     ,0      };  // p3
        float[]         texCoords   = { 0 , 0 };
        int[]           faces       = { 0 , 0 , 1 , 0 , 2 , 0,
                                        2 , 0 , 3 , 0 , 0 , 0 };
        mesh.getPoints().addAll( points );
        mesh.getTexCoords().addAll( texCoords );
        mesh.getFaces().addAll( faces );
        
         
        // メッシュビュー1(左)を作成
        MeshView        meshView1    = new MeshView();
        meshView1.setMesh( mesh );
        meshView1.setTranslateX( -5 );
        meshView1.setTranslateY( 4 );
        meshView1.setDrawMode( DrawMode.FILL );
        root.getChildren().add( meshView1 );
         
        // メッシュビュー2(中央)を作成
        MeshView        meshView2    = new MeshView();
        meshView2.setMesh( mesh );
        meshView2.setTranslateY( 4 );
        meshView2.setDrawMode( DrawMode.LINE );
        root.getChildren().add( meshView2 );
 
        // メッシュビュー3(右)を作成
        MeshView        meshView3    = new MeshView();
        meshView3.setMesh( mesh );
        meshView3.setTranslateX( 5 );
        meshView3.setTranslateY( 4 );
        meshView3.setDrawMode( DrawMode.FILL );
        meshView3.setCullFace( CullFace.FRONT );
        root.getChildren().add( meshView3 );
         
         */
        return root;
    }
 
}