package othello;

import java.util.Random;

/**
*オセロの色(白か黒) Color of othello.<br>
*白か黒のどちらかを表す.<br>
*<br>
*<br>
*sample<br>
*source :
*<pre>
*{@code
	Color white = Color.white;
	Color black = Color.black;
	System.out.println(white + " : " + black); // W : B
	System.out.println(white.reversed().equals(black)); // true
	System.out.println(white.equals(black)); // false
	for(int i = 0; i < 10; ++i){ // WWBWBWWWBB
		System.out.print(Color.random());
	}
*}
*</pre>
*output :
*W : B
*true
*WWBWBWWWBB
*/
public enum Color{
	/**
	 * 白色 white.
	 */
	white,
	/**
	 * 黒色 black
	 */
	black,
	;

	/*
	public static void main(String[] args){
		Color white = Color.white;
		Color black = Color.black;
		System.out.println(white + " : " + black); // W : B
		System.out.println(white.reversed().equals(black)); // true
		System.out.println(white.equals(black)); // false
		for(int i = 0; i < 10; ++i){ // BBWBBWWBBB
			System.out.print(Color.random());
		}
	}
	*/

	/**
	 * このオブジェクトと別の色を返す Returns another color.<br>
	 * このオブジェクトが黒を表しているなら白,白を表しているなら黒を返す.<br>
	 * このオブジェクト自体は変化しない<br>
	 * If this object is white, it returns black object.<br>
	 * If this object is black, it returns white object.<br>
	 * This objects is immutable.<br>
	 * @return {@code this == black ? white : black}
	 * @see Color#black
	 * @see Color#white
	 */
	public Color reversed(){
		return this == black ? white : black;
	}

	/**
	 * ランダムにカラーオブジェクトを生成する Create random color.<br>
	 * 確率は黒が50%, 白が50%.<br>
	 * 乱数生成アルゴリズムは線形合同法.<br>
	 * Probability : black - 50%, white - 50%.<br>
	 * Algorithm : LCG.<br>
	 * @return ランダムに黒か白. random color black or white.{@code new Random().nextBoolean() ? white() : black();}
	 */
	public static Color random(){
		return new Random().nextBoolean() ? white : black;
	}

	/**
	 * 色を表す文字列を返す Returns string which presents a color.<br>
	 * もしこのオブジェクトが黒なら{@code "B"},白なら{@code "W"}が返される.<br>
	 * If this object is black, the returned string is {@code "B"}.<br>
	 * If this object is white, the returned string is {@code "W"}.<br>
	 * @return equals(black()) ? "B" : "W";
	 */
	//@Override
	public String toString() {
		return this == black ? "B" : "W";
	}
}
