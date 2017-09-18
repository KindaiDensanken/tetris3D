package object;

import system.PointOf3D;

public class Tetris_Bo extends Tetris {

	public Tetris_Bo(PointOf3D controllPoint) {
		super(controllPoint);
		// TODO 自動生成されたコンストラクター・スタブ
		pointList.add(new PointOf3D(0,0,0));
		pointList.add(new PointOf3D(-1,0,0));
	}

	@Override
	public int getType() {
		// TODO 自動生成されたメソッド・スタブ
		return TetrisList.Bo.getTetrisNumber();
	}

}
