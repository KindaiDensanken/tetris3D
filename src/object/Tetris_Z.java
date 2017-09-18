package object;

import system.PointOf3D;
/** 
*z字のテトリスブロック
*@extends 
*/
public class Tetris_Z extends Tetris{

    public Tetris_Z(PointOf3D controllPoint){
        super(controllPoint);

        pointList.add(new PointOf3D(-1,0,0));
        pointList.add(new PointOf3D(0,0,0));
        pointList.add(new PointOf3D(-1,0,-1));
        pointList.add(new PointOf3D(-2,0,-1));
      
    }

    /**
    * @return 3
     */
     public int getType(){
         return TetrisList.Z.getTetrisNumber();
     }
}