// 変数の定義
#define LED_PIN 13
 
// 初期化
void setup(){
  pinMode(LED_PIN, OUTPUT);
 
  // シリアルポートを9600 bps[ビット/秒]で初期化 
  Serial.begin(9600);
}
 
// 繰り返し処理
void loop(){
  char inputchar;
 
  // シリアルポートより1文字読み込む
  inputchar = Serial.read();
 
  if(inputchar != -1 ){
    // 読み込んだデータが -1 以外の場合　以下の処理を行う
        Serial.println(inputchar);
        digitalWrite(LED_PIN, HIGH);
  } else {
    // 読み込むデータが無い場合は何もしない
    //Serial.println("No_Signal");
    digitalWrite(LED_PIN, LOW);
  }
}
