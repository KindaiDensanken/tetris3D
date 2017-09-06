package system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import object.Tetris;
import object.Tetris_Empty;

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
	
	
	/**
	 * 捜査中のテトリスブロック
	 */
	private Tetris cotrorling;
	/**
	 * 
	 */
	private PointOf3D controllPoint;
	/**
	 * コンストラクタ
	 * 配列mapの初期化
	 */
	private Map(){
		//map配列のすべてにTetriseeEmptyを代入
		IntStream.range(0, map.length).forEach(s->IntStream.range(0, map[s].length).
				forEach(t->IntStream.range(0, map[s][t].length).forEach(u->map[s][t][u]=0)));
		//
		IntStream.range(0, uncontMap.length).forEach(s->IntStream.range(0, uncontMap[s].length).
				forEach(t->IntStream.range(0, uncontMap[s][t].length).forEach(u->uncontMap[s][t][u]=0)));
		
		//
		controllPoint = new PointOf3D(2, 4, 2);
		
	}
	
	
	
	
	int count = 0;
	/**
	 * mapの時間経過に置ける処理
	 */
	public void update(){
		
		//if()
		fallDown();
		
		count++;
	}
	
	
	public void fallDown(){
		cotrorling.fallDown();
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

}
