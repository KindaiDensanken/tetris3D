package object;

import system.PointOf3D;

/**
*T型のテトリス
*
 */
public class Tetris_T extends Tetris{



	public Tetris_T(PointOf3D controllPoint){

		super(controllPoint);

		pointList.add(new PointOf3D(0,0,0));
		pointList.add(new PointOf3D(0,-1,0));
		pointList.add(new PointOf3D(-1,-1,0));
		pointList.add(new PointOf3D(1,-1,0));
	}


	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return TetrisList.T.getTetrisNumber();
	}

}
