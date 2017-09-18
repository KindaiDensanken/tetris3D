package object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

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
	/**
	 * 配列（map)の大きさ
	 */
	public static final int MAPSIZE=Map.SIZE;
	
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
	 * xz平面でforwardに１移動するメソッド
	 * @return 動けた場合true,動けなかった場合false
	 */
	public boolean moveForward(){
		int temp = controllPoint.getZ();
		controllPoint.setZ(temp+1);
		
		if(!checkMap()) {
			controllPoint.setZ(temp);
			return false;
		}
		assignMap();
		return true;
	}
	/**
	 * xz平面でz-1に移動するメソッド
	 * @return 動けた場合true,動けなかった場合false
	 */
	public boolean moveBackward(){
		int temp = controllPoint.getZ();
		controllPoint.setZ(temp-1);
		
		if(!checkMap()) {
			controllPoint.setZ(temp);
			return false;
		}
		assignMap();
		return true;
	}
	
	/**
	 * xz平面でx+1に移動するメソッド
	 * @return 動けた場合true,動けなかった場合false
	 */
	public boolean moveRight(){
		int temp = controllPoint.getX();
		controllPoint.setX(temp+1);
		
		if(!checkMap()) {
			controllPoint.setX(temp);
			return false;
		}
		assignMap();
		return true;
	}
	
	
	/**
	 * xz平面でx+1に移動するメソッド
	 * @return 動けた場合true,動けなかった場合false
	 */
	public boolean moveLeft(){
		int temp = controllPoint.getX();
		controllPoint.setX(temp-1);
		
		if(!checkMap()) {
			controllPoint.setX(temp);
			return false;
		}
		assignMap();
		return true;
	}
	
	
	/**
	 * y軸方向にテトリスブロックが90°回転するメソッド
	 * @return boolean　 回転できるかどうか
	 */
	public boolean Rotate_1(){
		ArrayList<PointOf3D> copy = new ArrayList<PointOf3D>();
		for(PointOf3D p : pointList){
			copy.add(p.clone());
		}
		Iterator<PointOf3D> itr = pointList.iterator();
		while(itr.hasNext()){
			
			PointOf3D p = itr.next();
			System.out.println(p.getX()+","+p.getY());
			p.rotateY();
			System.out.println(p.getX()+","+p.getY());
			System.out.println("----");
		}
		//回転後が正しい座標形態にあるか
		if(checkMap()){
			System.out.println("rotate_1 succeed");
			//
			assignMap();
			System.out.println("b"+copy.get(1).getX()+"a"+pointList.get(1).getX());
			return true;
		}else{
			System.out.println("rotate_1　failed");
			pointList = new ArrayList<>(copy);
			return false;
		}
	}
	
	/**
	 * y軸方向にテトリスブロックが270°回転するメソッド
	 * @return boolean　回転できるかどうか
	 */
	public boolean Rotate_2(){
		ArrayList<PointOf3D> clone = new ArrayList<PointOf3D>();
		for(PointOf3D p : pointList){
			clone.add(p.clone());
		}
		Iterator<PointOf3D> itr = pointList.iterator();
		while(itr.hasNext()){
			PointOf3D p = itr.next();
			p.rotateY();
			p.rotateY();
			p.rotateY();
		}
		//回転後が正しい座標形態にあるか
		if(checkMap()){
			assignMap();
			return true;
		}else{
			pointList = clone;
			return false;
		}
	}
	
	public boolean Rotate_3(){
		ArrayList<PointOf3D> clone = new ArrayList<PointOf3D>();
		for(PointOf3D p : pointList){
			clone.add(p.clone());
		}
		Iterator<PointOf3D> itr = pointList.iterator();
		while(itr.hasNext()){
			PointOf3D p = itr.next();
			p.rotateX();
			p.rotateX();
			p.rotateX();
		}
		//回転後が正しい座標形態にあるか
		if(checkMap()){
			assignMap();
			return true;
		}else{
			pointList = new ArrayList<PointOf3D>(clone);
			return false;
		}
	}
	public boolean Rotate_4(){
		ArrayList<PointOf3D> clone = new ArrayList<PointOf3D>(pointList); //回転できなかった場合の為クローン
		Iterator<PointOf3D> itr = pointList.iterator();
		while(itr.hasNext()){
			PointOf3D p = itr.next();
			p.rotateX();
		}
		//回転後が正しい座標形態にあるか
		if(checkMap()){
			assignMap();
			return true;
		}else{
			pointList = new ArrayList<PointOf3D>(clone);
			return false;
		}
	}
	/**
	 * pointListの座標をmapに直した時に問題ないかチェック
	 * @return
	 */
	public boolean checkMap(){
		//pointList
		Iterator<PointOf3D> itr = pointList.iterator();
		while(itr.hasNext()){
			PointOf3D p = itr.next();
			
			int x = controllPoint.getX()+p.getX();
			int y = controllPoint.getY()+p.getY();
			int z = controllPoint.getZ()+p.getZ();
			System.out.println(y);
			if(x<0||MAPSIZE<=x||y<0||z<0||MAPSIZE<=z){
				System.out.println("checkmap failed 1 x="+x+" y="+y+" z:"+z);
				return false;
			}
			
			try{//例外処理
			if(Map.getInstance().getUncontrolTetris(x, y, z)!=0) {
				System.out.println("checkmap failed 2");
				Map.getInstance().putoutuncout();
				return false;
			}
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
	public void assignMap(){
		
			int[][][] newmap = new int[MAPSIZE][MAPSIZE][MAPSIZE]; 
			IntStream.range(0, newmap.length).forEach(s->IntStream.range(0, newmap[s].length).
					forEach(t->IntStream.range(0, newmap[s][t].length).forEach(u->newmap[s][t][u]=0)));
			Iterator<PointOf3D> itr = pointList.iterator();
			while(itr.hasNext()){
				PointOf3D p = itr.next();
				int x = controllPoint.getX()+p.getX();
				int y = controllPoint.getY()+p.getY();
				int z = controllPoint.getZ()+p.getZ();
				
				try{
					newmap[x][y][z]=getType();
				
				}catch(ArrayIndexOutOfBoundsException e){
					//init();
					e.printStackTrace();
				}
			}
			int[][][] uncout = new int[MAPSIZE][MAPSIZE][MAPSIZE]; 
			uncout = Map.getInstance().getUncotrolMap();

			//newmapにuncounｔmapを合わせる処理を記述
			for(int s = 0 ; s<MAPSIZE;s++)
				for(int t = 0 ; t<MAPSIZE ; t++)
					for(int u = 0 ;u <MAPSIZE;u++) {
						if(uncout[s][t][u]>0) {
							newmap[s][t][u] = uncout[s][t][u];
						}
					}
			Map.getInstance().setMap(newmap);
	}
	
	public void adduncoutMap() {
		Iterator<PointOf3D> itr = pointList.iterator();
		while(itr.hasNext()){
			PointOf3D p = itr.next();
			int x = controllPoint.getX()+p.getX();
			int y = controllPoint.getY()+p.getY();
			int z = controllPoint.getZ()+p.getZ();
			
			try{
				Map.getInstance().getUncotrolMap()[x][y][z]=getType();
			
			}catch(ArrayIndexOutOfBoundsException e){
				//init();
				e.printStackTrace();
			}
		}
	}
}
