package system;

import java.util.stream.IntStream;

import object.Tetris;
import object.Tetris_Empty;

/**
 * singletoneパターン
 * テトリスの配置を保持
 * @author isato
 *
 */
public class Map {
/**
 * instance
 */
	private static Map instance = new Map();
	/**
	 * 
	 */
	public Tetris[][][] map= new Tetris[5][5][5];  
	/**
	 * コンストラクタ
	 * 配列mapの初期化
	 */
	private Map(){
		//map配列のすべてにTetrisemptを代入
		IntStream.range(0, map.length).forEach(s->IntStream.range(0, map[s].length).
				forEach(t->IntStream.range(0, map[s][t].length).forEach(u->map[s][t][u]=new Tetris_Empty())));
	}
	/**
	 * 唯一であるインスタンスの取得
	 * @return
	 */
	public static Map getInstance(){
		return instance;
	}
	
	public void putout(){
		IntStream.range(0, map.length).forEach(s->IntStream.range(0, map[s].length).
				forEach(t->IntStream.range(0, map[s][t].length).forEach(u->System.out.print(map[s][t][u].getType()))));
	}
	

}
