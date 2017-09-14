package object;

import system.PointOf3D;

public class Tetris_one extends Tetris{

	public Tetris_one(PointOf3D controllPoint) {
		super(controllPoint);
		// TODO Auto-generated constructor stub
		pointList.add(new PointOf3D(0, 0, 0));
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 1;
	}

}
