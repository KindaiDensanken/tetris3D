package system;

import java.util.ArrayList;

/**
 * スコアを管理するクラス
 * @author tachibana
 *
 */
public class Score {
	/**
	 * 
	 */
	private static ArrayList<Integer> scores = new ArrayList<Integer>();
	/**
	 * 
	 */
	private Score  instance  = new Score();
	
	
	private Score(){
		scores.add(1000);	
		scores.add(3000);
		scores.add(5000);
		
		
		}
	
	public static void addScore(int score){
		for(int i=0; i<scores.size(); i++){
			if(scores.get(i)<=score){
				scores.set(i, score);
				break;
			}
		}
	}
	
}
