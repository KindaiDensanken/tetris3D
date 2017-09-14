package object;

import system.PointOf3D;

public class Tetris_L extends Tetris{

	public Tetris_L(PointOf3D controllPoint) {
		super(controllPoint);
		// TODO Auto-generated constructor stub
		pointList.add(new PointOf3D(0,0,0));
		pointList.add(new PointOf3D(0,-1,0));
		pointList.add(new PointOf3D(0,-1,1));
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return TetrisList.L.getTetrisNumber();
	}

}
