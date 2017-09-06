package object;

import java.util.ArrayList;
import java.util.Iterator;

import system.Map;
import system.PointOf3D;
/**
 * テトリスのブロックを表す抽象
 * @author tachibana
 *
 */
public abstract class Tetris {
	
	/**
	 * テトリスのある座標の起点
	 */
	protected PointOf3D controllPoint;
	
	public static final int MAPSIZE=5;
	
	/**
	 * 機転からの座標のリスト
	 */
	protected ArrayList<PointOf3D> pointList = new ArrayList<PointOf3D>();
	
	public Tetris(PointOf3D controllPoint){
		this.controllPoint= controllPoint;
	}

	public abstract int getType();
	
/**
 * 落下処理
 * cntrollpointがy<=0の場合 false
 * @return boolean
 */
	public  boolean fallDown(){
		if(controllPoint.getY()>0){
			controllPoint.setY(controllPoint.getY()-1);
				//移動先が正しくmap内に治るか　治らない場合コントロールポイントを戻す
				if(!checkMap()){
					controllPoint.setY(controllPoint.getY()+1);
					return false;
				}else{
					assignMap();//mapの更新
				}
		}else return false;//y<=の場合falseとなる
		return true;
	}
	
	/**
	 * y軸方向にテトリスブロックが90°回転するメソッド
	 * @return boolean　 回転できるかどうか
	 */
	public boolean Rotate_1(){
		ArrayList<PointOf3D> clone = new ArrayList<PointOf3D>(pointList); //回転できなかった場合の為クローン
		Iterator<PointOf3D> itr = pointList.iterator();
		while(itr.hasNext()){
			PointOf3D p = itr.next();
			p.rotateY();
		}
		//回転後が正しい座標形態にあるか
		if(checkMap()){
			return true;
		}else{
			pointList = clone;
			return false;
		}
	}
	
	/**
	 * y軸方向にテトリスブロックが270°回転するメソッド
	 * @return boolean　回転できるかどうか
	 */
	public boolean Rotate_2(){
		ArrayList<PointOf3D> clone = new ArrayList<PointOf3D>(pointList); //回転できなかった場合の為クローン
		Iterator<PointOf3D> itr = pointList.iterator();
		while(itr.hasNext()){
			PointOf3D p = itr.next();
			p.rotateY();
			p.rotateY();
			p.rotateY();
		}
		
		//回転後が正しい座標形態にあるか
		if(checkMap()){
			return true;
		}else{
			pointList = clone;
			return false;
		}
	}
	/**
	 * pointListの座標をmapに直した時に問題ないかチェック
	 * @return
	 */
	protected boolean checkMap(){
		//pointList
		Iterator<PointOf3D> itr = pointList.iterator();
		while(itr.hasNext()){
			PointOf3D p = itr.next();
			int x = controllPoint.getX()+p.getX();
			int y = controllPoint.getX()+p.getX();
			int z = controllPoint.getX()+p.getX();
			if(x<0||4<x||y<0||4<y||z<0||4<z){
				return false;
			}
			
			try{//例外処理
			if(Map.getInstance().getTetris(x, y, z)!=0)
				return false;
			}catch(ArrayIndexOutOfBoundsException e){
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	/**
	 * mapに代入
	 */
	protected void assignMap(){
		
			int[][][] map = Map.getInstance().getMap();//integerかint[]であるので変更がそのまま反映される
			Iterator<PointOf3D> itr = pointList.iterator();
			while(itr.hasNext()){
				PointOf3D p = itr.next();
				int x = controllPoint.getX()+p.getX();
				int y = controllPoint.getX()+p.getX();
				int z = controllPoint.getX()+p.getX();
				
				try{
					map[x][y][z]=getType();
				}catch(ArrayIndexOutOfBoundsException e){
					e.printStackTrace();
				}
			}
	}
}
