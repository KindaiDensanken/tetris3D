package object;




/**
 * 列挙型<br>
 * テトリスのオブジェクトとint型の数字を結びつける<br>
 * テトリスの種類を増やした場合(objectパッケージ)要変更
 * @author tachibana
 *
 */
public enum TetrisList {
	Empty(0),
	Rect(1),
	T(2);
	Z(3);
	//要追加
	private final int tetrisNum;
//	private final Tetris tetris;
	
	 TetrisList(final int tetrisNum) {
		// TODO 自動生成されたコンストラクター・スタブ
		 this.tetrisNum= tetrisNum;
	}
	 
	 /**
	  * 対応するテトリスの番号を返す
	  * @return
	  */
	 public int getTetrisNumber(){
		 return tetrisNum;
	 }
	/**
	 *  
	 * @param id
	 * @return
	 */
	 public static TetrisList getType(final int id){
		 TetrisList[] types = TetrisList.values();
	        for (TetrisList type : types) {
	            if (type.getTetrisNumber() == id) {
	                return type;
	            }
	        }
	      return null;
	 }
	 /**
	  * テトリスブロックのidをTetrisオブジェクトに変換<br>
	  * 要nullチェック
	  * @param id
	  * @return Tetrisオブジェクト 一致しない場合null
	  */
	 /*
	 public static Tetris taranlateID(int id){
		 switch (getType(id)) {
		case Empty:
			return new Tetris_Empty();
		case Rect:
			return new Tetris_Rect();
		case T:
			return new Tetris_T();
			//要追加

		default:
			return null;
		}
	 }
	  */
}
