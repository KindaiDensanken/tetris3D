package system;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.OutputStream;

public class SerialThrow {
	private static CommPortIdentifier comID;
	private static SerialPort commPort;
	OutputStream outStream;

	/**
	 * シリアルポートを開く処理
	 */
	public SerialThrow() {
		// シリアルポートを開き、通信速度等を設定する
		try {
			// ポート取得
			comID = CommPortIdentifier
					.getPortIdentifier("/dev/tty.usbmodem1441");
			// /dev/tty.usbmodem1411"
			//win"com6"
			// ポート開く
			//dummy_app_name
			commPort = (SerialPort) comID.open("dummy_app_name", 2000);
			// Serial通信設定
			commPort.setSerialPortParams(19200, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			// フロー設定
			commPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
			// 送信ストリームを開く
			outStream = commPort.getOutputStream();
			// しばらく待機
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("\ncomm port open error\n");
			if (commPort != null)
				commPort.close();
			return;
		}
	}

	/**
	 * シリアル送信
	 */
	public void toMessage(String str) {
		try {
			String tmpStr = str;
			outStream.write(tmpStr.getBytes());
			System.out.println(tmpStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * シリアルポートとアウトプットストリームを閉じて終了する
	 */
	public void exit() {
		try {
			outStream.close();
			System.out.print("commPort OutputStream is closed\n");
			commPort.close();
			System.out.print("commPort is closed\n");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/*
	 * main
	 */
	public static void main(String[] args) {

		// クラスを初期化（シリアルポートを開く）
		SerialThrow serial = new SerialThrow();
		// バイナリを送信
		
		serial.toMessage("A1111111111111111f");
		serial.toMessage("B1111111111111111f");
		serial.toMessage("C1111111111111111f");
		serial.toMessage("D1111111111111111f");
		/*
		serial.toMessage("A0000000000000000f");
		serial.toMessage("B0000000000000000f");
		serial.toMessage("C0000000000000000f");
		serial.toMessage("D0000000000000000f");
		*/
		// ストリームとポートを閉じる
		serial.exit();
	}

}
