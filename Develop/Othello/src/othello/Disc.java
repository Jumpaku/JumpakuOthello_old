package othello;

/**
 * オセロの石 Disc of othello.<br>
 * オセロの石を表す.<br>
 * オセロの石は黒か白どちらかの色を必ず持つ.<br>
 * {@link Disc#reverse()}で持っている色を白から黒,または黒から白に切り替える事ができる.<br>
 * {@link Disc#color}で持っている色を取得できる.<br>
 * Disc expresses disc of othello.<br>
 * Disc has a color which is black or white.<br>
 * The method {@link Disc#reverse()} changes the color of the disc from white into black, or from black into white.
 * The method {@link Disc#color()} returns the color of the disc.<br>
 *<br>
 * example
 * <pre>
 * {@code
	Disc d = new Disc(Color.black());
	Disc l = new Disc(Color.white());
	Disc x = d.clone();
	x.reverse();
	System.out.println(d.color() + " : " + l.color());// B : W
	System.out.println(x.equals(l));// true
 * }
 * </pre>
 */
public class Disc implements Cloneable{

	/*public static void main(String[] args){
		Disc d = new Disc(Color.black());
		Disc l = new Disc(Color.white());
		Disc x = d.clone();
		x.reverse();
		System.out.println(d.color() + " : " + l.color());// B : W
		System.out.println(x.equals(l));// true
	}*/

	private Color color_;

	/**
	 * 石オブジェクトを構築する Constructs disc.<br>
	 * 石の色はデフォルトでは黒に設定される.<br>
	 * Default color of this disc is set into black.
	 */
	public Disc(){
		this(Color.black);
	}

	/**
	 * 石オブジェクトを構築し,色を設定する Constructs disc and sets color.<br>
	 * 引数colorで指定された色が設定される.<br>
	 * {@code null}が渡された時は{@code NullPointerException}が投げられる.<br>
	 * Color of this object is set to argument color.<br>
	 * When {@code color == null}, {@code NullPointerException} is thrown.
	 * @param color 設定する色 color to set
	 * @throws NullPointerException {@code color == null}の時 when {@code color == null}
	 */
	public Disc(Color color){
		color_ = color;
	}

	/**
	 * 石オブジェクトのクローンを生成する Creates clone of this disc object.<br>
	 * @return 石オブジェクトのクローン Clone of this disc
	 */
	@Override
	public Disc clone(){
		Disc disc;
		try {
			disc = (Disc) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			disc = null;
		}
		return disc;
	}

	/**
	 * 石を裏返す Reverses this disc.<br>
	 * この石が持つ色が白なら黒へ,黒なら白へ換える.<br>
	 * If this disc has white color, {@link Disc#reverse()} changes into black.<br>
	 * If this disc has black color, {@link Disc#reverse()} changes into white.<br>
	 */
	public void reverse(){
		color_ = color_.reversed();
	}

	/**
	 * この石が持っている色を返す Returns a color of this disc.
	 * @return 石が持つ色のコピー Copied color of this disc.
	 */
	public Color color(){
		return color_;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color_ == null) ? 0 : color_.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Disc)) {
			return false;
		}
		Disc other = (Disc) obj;
		if (color_ != other.color_) {
			return false;
		}
		return true;
	}

}
