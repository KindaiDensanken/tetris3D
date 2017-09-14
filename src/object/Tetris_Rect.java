package object;

import system.PointOf3D;
/**
*/
public class Tetris_Rect extends Tetris{

	public Tetris_Rect(PointOf3D controllPoint) {
		// TODO 自動生成されたコンストラクター・スタブ
		super(controllPoint);
		//正方形型のテトリスを表す配列
		pointList.add(new PointOf3D(0, 0, 0));
		pointList.add(new PointOf3D(0, 0, 1));
		pointList.add(new PointOf3D(0, -1, 0));
		pointList.add(new PointOf3D(0, -1, 1));
		
		pointList.add(new PointOf3D(1, 0, 0));
		pointList.add(new PointOf3D(1, 0, 1));
		pointList.add(new PointOf3D(1, -1, 0));
		pointList.add(new PointOf3D(1, -1, 1));
	/*以下のコードはコンストラクタを呼び出した側で行うこと

		if(checkMap()){
			
	}
		assignMap();
		*/
	}
	

	@Override
	/**
	 * @return 1
	 */
	public int getType() {
		// TODO Auto-generated method stub
		return TetrisList.Rect.getTetrisNumber();
	}



	

}
