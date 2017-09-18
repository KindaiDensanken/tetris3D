package system;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import scene.GameOverScene;
import scene.InGameScene;
import scene.TitleScene;
import densan.s.game.input.KeyInput;
import densan.s.game.manager.GameManager;
import object.Tetris;
import object.TetrisList;
import object.Tetris_Bo;
import object.Tetris_Empty;
import object.Tetris_L;
import object.Tetris_Rect;
import object.Tetris_Z;
import object.Tetris_one;

/**
 * singletoneパターン<br>
 * テトリスの配置を保持する<br>
 * Tetrisオブジェクトの三次元配列
 * @author isato
 *
 */
public class Map {
/**
 * instance
 */
	private static Map instance = new Map();
	/**
	 * Tetrisの三次元配列
	 */
	private int[][][] map= new int[5][5][5];  
	//使うか未定
	private int[][][] uncontMap = new int[5][5][5];
	
	private long score = 0;
	
/**
 * 大きさ
 */
	public static final int SIZE = 4;
	
	/**
	 * 捜査中のテトリスブロック
	 */
	private Tetris cotrorling;
	/**
	 * 
	 */
	private PointOf3D firstPoint = new PointOf3D(SIZE/2, SIZE-1, SIZE/2);
	private static final int initX=SIZE/2;
	private  static final int initY=SIZE-1;
	private static final int initZ=SIZE/2;	
	private SerialThrow serial;
	private boolean tetris_init = true;
	private int[][][] uncoutMap;
	private int chain = 0;
	
	/**
	 * コンストラクタ
	 * 配列mapの初期化
	 */
	private Map(){
		//map配列のすべてにTetriseeEmptyを代入
		/*
		IntStream.range(0, map.length).forEach(s->IntStream.range(0, map[s].length).
				forEach(t->IntStream.range(0, map[s][t].length).forEach(u->map[s][t][u]=0)));
		//
		IntStream.range(0, uncontMap.length).forEach(s->IntStream.range(0, uncontMap[s].length).
				forEach(t->IntStream.range(0, uncontMap[s][t].length).forEach(u->uncontMap[s][t][u]=0)));
	//	cotrorling = new Tetris_Rect(new PointOf3D(initX, initY, initZ));
		generateTetris();
		sendSerial();
		*/
		serial = new SerialThrow();
		init();
	}
	
/**
 * 
 */
	private void generateTetris() {
		tetris_init=true;;
		Random r = new Random();
		//1~(Tetrisの種類数)の範囲で生成する
	//一時テスト
		switch (TetrisList.getType(r.nextInt(TetrisList.values().length-1)+1)) {
		case Rect:
			cotrorling = new Tetris_Rect(new PointOf3D(initX,initY,initZ));
			break;
/*現状の4*4*4ではクソゲーになるのでT字型はなし
		case T:
			cotrorling = new Tetris_T(new PointOf3D(initX,initY,initZ));
			break;
	*/		
		case Z:
			cotrorling = new Tetris_Z(new PointOf3D(initX,initY,initZ));
			break;
		case L:
			cotrorling = new Tetris_L(new PointOf3D(initX,initY,initZ));
			break;
		case Bo:
			cotrorling = new  Tetris_Bo(new PointOf3D(initX,initY,initZ));
			break;
		default:
			System.err.println("配列生成の失敗");
			break;
		}
		
		
			
	}
	
	
	int count = 0;
	private int removecount = 0;
	private int fallLimit = 500;
	/**
	 * mapの時間経過に置ける処理
	 */
	public void update(){
		//テトリスブロックを生成した時の最初の初期化
		if(tetris_init) {
		if(cotrorling.checkMap()) {
			cotrorling.assignMap();
			sendSerial();
		}
		else 
			gameOver();
		tetris_init = false;
		}
		
		
	if(KeyInput.isPress(KeyEvent.VK_X)) {
			System.out.println("x");
			if(cotrorling.checkMap()) {
				cotrorling.assignMap();
	}
		}
	if(KeyInput.isPress(KeyEvent.VK_Z)){
		System.out.println("z");
			fallDown();
			count = 0;
	}
	if(KeyInput.isPress(KeyEvent.VK_W)){
		System.out.println("w");
			cotrorling.moveForward();
			sendSerial();
	}
	if(KeyInput.isPress(KeyEvent.VK_A)){
		System.out.println("a");
			cotrorling.moveLeft();
			sendSerial();
			sendSerial();
	}
	if(KeyInput.isPress(KeyEvent.VK_S)){
		System.out.println("s");
			cotrorling.moveBackward();
			sendSerial();
	}
	if(KeyInput.isPress(KeyEvent.VK_D)){
		System.out.println("a");
			cotrorling.moveRight();
			sendSerial();
	}
	
	
	if(KeyInput.isPress(KeyEvent.VK_RIGHT)){
		System.out.println("RIGHT");
		cotrorling.Rotate_1();	
		sendSerial();
	}
	if(KeyInput.isPress(KeyEvent.VK_LEFT)){
		cotrorling.Rotate_2();
		sendSerial();
	}
	
	if(KeyInput.isPress(KeyEvent.VK_UP)){
		System.out.println("UP");
		cotrorling.Rotate_3();
		sendSerial();
	}
	
	if(KeyInput.isPress(KeyEvent.VK_DOWN)){
		System.out.println("Down");
		cotrorling.Rotate_4();
		sendSerial();
	}
	
	timeLimit();
	}
	
	/**
	 * 時間経過ごとにテトリスの落下及びレベルアップ
	 */
	private void timeLimit(){
		if(count>=fallLimit){
			fallDown();
			sendSerial();
			count=0;
		}
		count++;
			
	}
	/**
	 * controlingに登録されているテトリスの落下処理
	 */
	public void fallDown(){
		if(!cotrorling.fallDown()) {
			cotrorling.adduncoutMap();
			remove();
			generateTetris();
		}
		count=0;
		sendSerial();
			
	}
	/**
	 * テトリスの消えるかどうかを判定しその後の処理を行う
	 */
	private void remove() {
		for(int y = 0; y<SIZE;y++) {
			boolean removeflag = true;;
			for(int x = 0;x<SIZE;x++) {
				for(int z = 0;z<SIZE;z++) {
					if(map[x][y][z]==0)
						removeflag=false;
				}
			}
			if(removeflag){
				for(int n_y = y;n_y<SIZE-1;n_y++) {
					for(int x = 0;x<SIZE;x++)
						for(int z = 0;z<SIZE;z++) {
							map[x][n_y][z] = map[x][n_y+1][z];
							uncontMap[x][n_y][z] = map[x][n_y+1][z];
							
						}
				}
				for(int x = 0;x<SIZE;x++)
					for(int z = 0;z<SIZE;z++) {
						map[x][SIZE-1][z] = 0;
						uncontMap[x][SIZE-1][z] = 0;
					}
				removecount++;
				score += 500+chain*200;
				if(removecount==3)
					InGameScene.levelUp();
				if(removecount==8)
					InGameScene.levelUp();
			}
		}
					
	}
	
	/**
	 * 3dテトリスにMapから情報を読み取り信号を送る
	 */
	private void sendSerial(){
		String tmp = "";
		for(int y = 0; y<SIZE;y++){
			switch (y) {
			case 0:
				tmp+="D";
				break;
			case 1:
				tmp+="C";
				break;
			case 2:
				tmp+="B";
				break;
			case 3:
				tmp+="A";
			default:
				break;
			}
			for(int x = 0; x < SIZE;x++){
				for(int z = SIZE-1; z >=0; z--){
					if(map[x][y][z]==0)
					tmp+=0;
					else tmp += 1;
				}
			}
			tmp += "f";
		}
		System.out.println(tmp);
		serial.toMessage(tmp);
	}
	
	/**
	 * 唯一であるインスタンスの取得
	 * @return
	 */
	public static Map getInstance(){
		return instance;
	}
	
	
	
	/**
	 * 
	 * 指定したインデックスにTetrisをsetする
	 * @param Tetris
	 * @param x
	 * @param y
	 * @param z
	 * @return テトリスをsetできたかどうか　基本的に配列の数字のエラーだけなはず
	 */
	public boolean setTetris(Tetris t, int x,int y,int z){
		try{
			map[x][y][z]=t.getType();
		}catch(ArrayIndexOutOfBoundsException e){
			return false;
		}
		return true;
	}
	
	/**
	 * 指定したインデックスに指定したint型のデータに対応するテトリスを代入する
	 * @param tetrisNumber
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public boolean setTetris(int tetrisNumber,int x,int y,int z){
		try{	
			map[x][y][z]=tetrisNumber;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	
	/**
	 * Mapの指定したindexのint型の整数を取得<br>
	 * とりあえず今のところはOutOfExceptionをthrowしてる
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public int getTetris(int x,int y,int z) throws ArrayIndexOutOfBoundsException{
//		return map[x][y][z];
		//try catchでnullを返すパターン
		
		try{
			return map[x][y][z];
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/**
	 * 
	 *  Mapの指定したindexのint型の整数を取得<br>
	 * とりあえず今のところはOutOfExceptionをthrowしてる
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public int getUncontrolTetris(int x,int y,int z) throws ArrayIndexOutOfBoundsException{
		try{
			return uncontMap[x][y][z];
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * コンソールにテトリスの出力
	 */
	public void putout(){
		IntStream.range(0, map.length).forEach(s->IntStream.range(0, map[s].length).
				forEach(t->IntStream.range(0, map[s][t].length).forEach(u->System.out.print(map[s][t][u]))));
	}
	
	public void putoutuncout(){
		IntStream.range(0, map.length).forEach(s->IntStream.range(0, map[s].length).
				forEach(t->IntStream.range(0, map[s][t].length).forEach(u->System.out.print(uncontMap[s][t][u]))));
	}
	/**
	 * mapのゲッター
	 * @return
	 */
	public int[][][] getMap(){
		return map;
	}
	/**
	 * uncountmapの
	 * @return
	 */
	public int[][][] getUncotrolMap(){
		return uncontMap;
	}
	/**
	 * map セッター
	 * @param a
	 */
	public void setMap(int[][][]  a) {
		map=a;
	}
	
	/**
	 * テトリスの座標やmap,score 初期化
	 */
	public void init(){
	
		IntStream.range(0, map.length).forEach(s->IntStream.range(0, map[s].length).
				forEach(t->IntStream.range(0, map[s][t].length).forEach(u->map[s][t][u]=0)));
		//
		IntStream.range(0, uncontMap.length).forEach(s->IntStream.range(0, uncontMap[s].length).
				forEach(t->IntStream.range(0, uncontMap[s][t].length).forEach(u->uncontMap[s][t][u]=0)));
	//	cotrorling = new Tetris_Rect(new PointOf3D(initX, initY, initZ));;
		generateTetris();
		
		sendSerial();
		tetris_init = true;
		score = 0	;
	}
	/**
	 * ゲームオーバーの処理
	 */
	public void gameOver(){
		TitleScene.compHIGHSCORE(score);
		GameManager.getInstance().setUpdatable(new GameOverScene());
		
	}
	
	
	/*
	/**
	 * Tetrisオブジェクトの３次元配列をintの三次元配列にgetTypeメソッドを実行して変換して返す
	 * @return
	 */
	/*
	public int[][][] getTypeList(){
		int[][][] intArray = new int[5][5][5];
		IntStream.range(0, intArray.length).forEach(s->IntStream.range(0, intArray[s].length).
				forEach(t->IntStream.range(0, intArray[s][t].length).forEach(u->intArray[s][t][u]=0)));
		IntStream.range(0, map.length).forEach(s->IntStream.range(0, map[s].length).
				forEach(t->IntStream.range(0, map[s][t].length).forEach(u->intArray[s][t][u]=map[s][t][u].getType())));
		return intArray;
	}
	*/
	
	public long getScore(){
		return score;
	}
	
	public void setFalllimit(int n){
		fallLimit =  n;
	}

}
