package object;

import system.PointOf3D;
/** 
*z字のテトリスブロック
*@extends 
*/
public class Tetris_Z extends Tetris{

    public Tetris_Z(PointOf3D controllPoint){
        super(controllPoint);

        pointlist.add(new PointOf3D(0,0,0));
        pointlist.add(new PointOf3D(1,0,0));
        pointlist.add(new PointOf3D(0,-1,0));
        pointlist.add(new PointOf3D(0,-1,-1));
        if(checkMap()){
            //
		}
		assignMap();
    }

    @override
    /**
    * @return 3
     */

     public int getType(){
         return Tetris.Z.getTetrisNumber;
     }
}