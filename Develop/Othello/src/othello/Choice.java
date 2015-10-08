package othello;

/**
 * どのマスに何色の石を置くかという選択を表す Choice of putting disc (where and which color).<br>
 * Choiceは不変.<br>
 * Choice is immutable.<br>
 * <br>
 * <br>
 * example<br>
 * source : <br>
 * <pre>
 * {@code
 	Choice c1 = new Choice(3, 6, Color.black());
		System.out.println(c1.position());
		System.out.println(c1.i());
		System.out.println(c1.j());
		System.out.println(c1.color());
		System.out.println(c1);
 * }
 * </pre>
 * output : <br>
 * 3,6<br>
 * 3<br>
 * 6<br>
 * B<br>
 * 3,6,B<br>
 */
public final class Choice implements Cloneable {

	/*public static void main(String[] args) {
		Choice c1 = new Choice(3, 6, Color.black());
		System.out.println(c1.position());
		System.out.println(c1.i());
		System.out.println(c1.j());
		System.out.println(c1.color());
		System.out.println(c1);
	}*/

	private Position position_;

	private Color color_;

	/**
	 * 位置と色を指定して選択オブジェクトを構築する Constructs choice with position and color.<br>
	 * 位置pまたは色cが{@code null}の場合{@link NullPointerException}を投げる.<br>
	 * When {@code p == null} or {@code c == null}, {@link NullPointerException} is thrown.
	 * @param p 位置 position
	 * @param c 色 color
	 * @throws NullPointerException 位置pまたは色cが{@code null}の時 When {@code p == null} or  {@code c == null}
	 */
	public Choice(Position p, Color c) {
		position_ = p.clone();
		color_ = c;
	}

	/**
	 * 位置と色を指定して選択オブジェクトを構築する Constructs choice with position and color.<br>
	 * 色cが{@code null}の場合{@link NullPointerException}を投げる.<br>
	 * When {@code c == null}, {@link NullPointerException} is thrown.
	 * @param i 行 row
	 * @param j 列 column
	 * @param c 色 color
	 * @throws NullPointerException 色cが{@code null}の時 When {@code p == null} or  {@code c == null}
	 */
	public Choice(int i, int j, Color c) {
		this(new Position(i, j), c);
	}

	/**
	 * 選択オブジェクトのクローンを生成する Creates clone of this choice object.<br>
	 * @return 選択オブジェクトのクローン Clone of this choice
	 */
	@Override
	protected Choice clone(){
		try {
			Choice c = (Choice) super.clone();
			c.position_ = position_.clone();
			return c;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 選択が持つの位置オブジェクトのコピーを返す Returns copied position of this choice.<br>
	 * @return 位置オブジェクトのコピー copied position
	 */
	public Position position(){
		return position_.clone();
	}

	/**
	 * 選択が持つの位置の行番号を返す Returns row number of this choice's position.<br>
	 * @return 位置の行番号 row number of position
	 */
	public int i(){
		return position_.i();
	}

	/**
	 * 選択が持つの位置の列番号を返す Returns column number of this choice's position.<br>
	 * @return 位置の列番号 column number of position
	 */
	public int j(){
		return position_.j();
	}

	/**
	 * 選択が持つの色オブジェクトのコピーを返す Returns copied color of this choice.<br>
	 * @return 色オブジェクトのコピー copied color
	 */
	public Color color(){
		return color_;
	}

	/**
	 * 選択を表す文字列を返す Returns string which presents a choice.<br>
	 * 位置(3,6)に黒という選択の場合{@code "3,6,B"}という文字列が返される<br>
	 * For example a choice which position is (3,6) and color is black, string {@code "3,6,B"} is returned.<br>
	 * @return {@code position().toString() + "," + color().toString()};
	 */
	@Override
	public String toString() {
		return position_.toString() + "," + color_.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color_ == null) ? 0 : color_.hashCode());
		result = prime * result + ((position_ == null) ? 0 : position_.hashCode());
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
		if (!(obj instanceof Choice)) {
			return false;
		}
		Choice other = (Choice) obj;
		if (color_ != other.color_) {
			return false;
		}
		if (position_ == null) {
			if (other.position_ != null) {
				return false;
			}
		} else if (!position_.equals(other.position_)) {
			return false;
		}
		return true;
	}


}
