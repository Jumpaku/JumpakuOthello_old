package othello;

import java.util.Random;

/**
*オセロの色(白か黒) Color of othello.<br>
*オセロの色,必ず白か黒の2色うちのどちらかとなる.<br>
*{@link Color#black()}または{@link Color#white()}を使って色を得る事ができる.<br>
*コンストラクタで色を指定しない場合は黒となる.
*Color in othello must be black or white.<br>
*You can get color using {@link Color#black()} or {@link Color#white()}.<br>
*{@link Color#Color()} constructs black color.<br>
*<br>
*
*example
*<pre>
*{@code
public static void main(String[] args){
	Color white = Color.white();
	Color black = Color.black();
	System.out.println(white + " : " + black); // W : B
	System.out.println(white.reversed().equals(black)); // true
	System.out.println(white.equals(black)); // false
	for(int i = 0; i < 10; ++i){ // BBWBBWWBBB
		System.out.print(Color.random());
	}
}
*}
*</pre>
*/
public class Color implements Cloneable{

	/*public static void main(String[] args){
		Color white = Color.white();
		Color black = Color.black();
		System.out.println(white + " : " + black); // W : B
		System.out.println(white.reversed().equals(black)); // true
		System.out.println(white.equals(black)); // false
		for(int i = 0; i < 10; ++i){ // BBWBBWWBBB
			System.out.print(Color.random());
		}
	}*/

	private enum C{
		BLACK,
		WHITE,
	}

	private final C color_;

	private Color(C color) {
		color_ = color;
	}

	/**
	 * カラーオブジェクトを構築する Constructs color.<br>
	 * デフォルトでは黒になる.<br>
	 * Default color is black.<br>
	 */
	public Color() {
		this(black().color_);
	}

	/**
	 * カラーオブジェクトのクローンを生成する Creates clone of this color object.<br>
	 * @return カラーオブジェクトのクローン Clone of this color
	 */
	@Override
	public Color clone(){
		try{
			return (Color) super.clone();
		}
		catch(CloneNotSupportedException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 他の色と同じ色か調べる Indicates whether some other object is equal to this one.<br>
	 * このオブジェクトと比較するobjは{@link Color}でなければならない.<br>
	 * objが{@link Color}でない時{@link java.lang.ClassCastException}が投げられる.<br>
	 * {@code obj == null}の時{@code false}を返す.<br>
	 * Argument obj must be {@link Color}.<br>
	 * When obj is not {@link Color}, {@link java.lang.ClassCastException} is thrown.<br>
	 * If {@code obj == null}, it returns false.
	 * @param obj このオブジェクトと比べる色.
	 * the reference object with which to compare.
	 * @return 同じ色を表すなら{@code true},違う色なら{@code false}.
	 * {@code true} if this object is the same color as the obj; {@code false} otherwise.
	 * @throws java.lang.ClassCastException 引数objが{@link Color}でない時.
	 * When obj is not {@link Color}.
	 */
	@Override
	public boolean equals(Object obj) {
		return obj == null ? false : ((Color)obj).color_ == color_;
	}

	/**
	 * 反対の色を返す Returns reversed color.
	 * このオブジェクトが黒を表しているなら白,白を表しているなら黒を返す.<br>
	 * If this object is white, it returns black object.<br>
	 * If this object is black, it returns white object.<br>
	 * @return {@code equals(black()) ? white() : black()}
	 * @see Color#black()
	 * @see Color#white()
	 */
	public Color reversed(){
		return equals(black()) ? white() : black();
	}

	/**
	 * 黒のカラーオブジェクトを返す Returns black.
	 * @return 黒 black color
	 */
	public static Color black(){
		return new Color(C.BLACK);
	}

	/**
	 * 白のカラーオブジェクトを返す Returns white.
	 * @return 白 white color
	 */
	public static Color white(){
		return new Color(C.WHITE);
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
		return new Random().nextBoolean() ? white() : black();
	}

	/**
	 * 色を表す文字列を返す Returns string which presents a color.<br>
	 * もしこのオブジェクトが黒なら{@code "B"},白なら{@code "W"}が返される.<br>
	 * If this object is black, the returned string is {@code "B"}.<br>
	 * If this object is white, the returned string is {@code "W"}.<br>
	 * @return equals(black()) ? "B" : "W";
	 */
	@Override
	public String toString() {
		return equals(black()) ? "B" : "W";
	}

}
