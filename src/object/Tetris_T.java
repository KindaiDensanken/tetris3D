package object;
/**
*T型のテトリス
*
 */
public class Tetris_T implements Tetris{



	public class Tetris_T(PointOf3D controllPoint){

		super(controllPoint);

		PointList.add(new PointOf3D(0,0,0));
		PointList.add(new PointOf3D(0,-1,0));
		PointList.add(new PointOf3D(-1,-1,0));
		PointList.add(new PointOf3D(1,-1,0));
		if(checkMap()){
			
		}
		assignMap();
	}


	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return TetrisList.T.getTetrisNumber();

}
