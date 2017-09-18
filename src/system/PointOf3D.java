package system;
/**
 * @author tachibana
 * ここでは主に座標変換の為に作ったクラス
 * 注意点としてintであることは注意
 */
public class PointOf3D {
	/**
 	* x座標
 	*/
	private int x;
	/**
	 * y座標
	 */
	private int y;
	/**
	 * z座標
	 */
	private int z;
	
	/**
	 * コンストラクタ
	 * @param x
	 * @param y
	 * @param z
	 */
	public PointOf3D(int x,int y,int z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	/**
	 *X軸に剃って回転するメソッド
	 */
	public void rotateX(){
		int y_R = y;//yの保持
		y=-z;
		z=y_R;
	}
	/**
	 * Y軸に沿って回転するメソッド
	 */
	public void rotateY(){
		int x_R = x;//yの保持
		x=-z;
		z=x_R;
	}
	
	/**
	 * セッター
	 * @param x
	 */
	public void setX(int x){
		this.x =x;
	}
/**
 * セッター
 * @param y
 */
	public void setY(int y){
		this.y =y;
	}
	/**
	 * セッター
	 * @param z
	 */
	public void setZ(int z){
		this.z =z;
	}
	/**
	 * ゲッター
	 * @return　x
	 */
	public int getX(){
		return x;
	}
	/**
	 * ゲッター
	 * @return y
	 */
	public int getY(){
		return y;
	}
	/**
	 * ゲッター
	 * @return z
	 */
	public int getZ(){
		return z;
	}
	
	public void rotateX(double c){
		
	}
	/**
	 * 座標をコンソールに出力
	 * テスト用
	 */
	public void putout(){
		System.out.println(x);
		System.out.println(y);
		System.out.println(z);
	}
	/**
	 * 二つのPointOf3Dが同一点にあるかチェック
	 * @param a
	 * @param b
	 * @return 
	 */
	public static boolean checkCollision(PointOf3D a , PointOf3D b){
		if(a.getX()==b.getX()&&
		   a.getY()==b.getY()&&
		   a.getZ()==b.getZ())
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		PointOf3D p = new PointOf3D(0, 2, 1);
		
		p.rotateX(90);
		p.putout();
	}
	public PointOf3D clone(){
		return new PointOf3D(x,y ,z);
		}
	
}
