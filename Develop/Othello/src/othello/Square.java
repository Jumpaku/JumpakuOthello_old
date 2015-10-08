package othello;

/**
 * オセロ盤のマス Square on othello board.<br>
 * マスはそのマスに置かれている石の管理をする<br>
 * 石が置かれているかいないかの確認,置かれている石の色の確認,石を置くこと,取り除くこと,裏返すことなどができる<br>
 * You can check color of disc on the square, check whether square is empty or not, put or remove or reverse a disc on the square.
 *<br>
 *<br>
 *sample<br>
 *source :
 * <pre>
 * {@code
	Color white = Color.white();

	Square s1 = new Square();

	System.out.println(s1.isEmpty() ? "empty" : s1.color());
	s1.put(white);
	System.out.println(s1.isEmpty() ? "empty" : s1.color());
	s1.reverse();
	System.out.println(s1.isEmpty() ? "empty" : s1.color());
	s1.remove();
	System.out.println(s1.isEmpty() ? "empty" : s1.color());
 * }
 * </pre>
 * output : <br>
 * empty<br>
 * W<br>
 * B<br>
 * empty<br>
 */
public class Square implements Cloneable {

	/*public static void main(String[] args) {
		Color white = Color.white();

		Square s1 = new Square();

		System.out.println(s1.isEmpty() ? "empty" : s1.color());
		s1.put(white);
		System.out.println(s1.isEmpty() ? "empty" : s1.color());
		s1.reverse();
		System.out.println(s1.isEmpty() ? "empty" : s1.color());
		s1.remove();
		System.out.println(s1.isEmpty() ? "empty" : s1.color());
	}*/

	private Disc disc_ = null;

	/**
	 * dの石を置いた状態のマスを構築する Constructs square with a disc d.<br>
	 * {@code d == null}の時は石が置かれていないマスが構築される<br>
	 * When {@code d == null}, square is constructed without disc.<br>
	 * @param d 置く石 disc to put
	 */
	public Square(Disc d){
		if(d != null){
			disc_ = d.clone();
		}
	}

	/**
	 * cの色の石を置いた状態のマスを構築する Constructs square with a disc which color is c.<br>
	 * {@code c == null}の時は石が置かれていないマスが構築される<br>
	 * When {@code c == null}, square is constructed without disc.<br>
	 * @param c 置く石の色 color of disc to put
	 */
	public Square(Color c){
		this(c == null ? (Disc)null : new Disc(c));
	}

	/**
	 * 石が置かれていないマスを構築する Constructs square without disc.<br>
	 */
	public Square(){
		this((Disc)null);
	}

	/**
	 * sをコピーしてマスを構築する Constructs square with copying.<br>
	 * {@code s == null}の時{@link NullPointerException}が投げられる<br>
	 * When {@code s == null}, {@link NullPointerException} is thrown.
	 * @param s コピー元のマス source square
	 * @throws NullPointerException {@code s == null}の時 when {@code s == null}
	 */
	public Square(Square s){
		this(s.disc_);
	}

	/**
	 * マスオブジェクトのクローンを生成する Clones square object.<br>
	 * @return このマスオブジェクトのクローン clone of this square
	 */
	@Override
	public Square clone(){
		Square square;
		try {
			square =  (Square)(super.clone());
			square.disc_ = disc_ == null ? null : disc_.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			square = null;
		}
		return square;
	}

	/**
	 * このマスに石を置く Puts a disc on this square.<br>
	 * {@code d == null}の時{@link NullPointerException}が投げられる<br>
	 * 既に他の石が置かれている場合新しい方に置き換わる<br>
	 * When {@code d == null}, {@link NullPointerException} is thrown.<br>
	 * If a old disc has already been put on this square, new disc d replaces old one.
	 * @param d 置く石 disc to put
	 */
	public void put(Disc d){
		disc_ = d.clone();
	}

	/**
	 * このマスに色cの石を置く Puts a disc which color is c on this square.<br>
	 * {@code c == null}の時{@link NullPointerException}が投げられる<br>
	 * 既に他の石が置かれている場合新しい方に置き換わる<br>
	 * When {@code d == null}, {@link NullPointerException} is thrown.<br>
	 * If a old disc has already been put on this square, new disc d replaces old one.
	 * @param c 置く石の色 color of disc to put
	 */
	public void put(Color c){
		put(new Disc(c));
	}

	/**
	 * このマスから石を取り除く Removes a disc from this square.<br>
	 * 既に石が置かれていない場合何もしない<br>
	 * If no disc has already been put on this square, it does nothing.
	 */
	public void remove(){
		disc_ = null;
	}

	/**
	 * マスが空の時{@code true}を返す Returns {@code true} if square is empty.<br>
	 * マスに石が置かれていない時は{@code true}, 置かれている時は{@code false}を返す<br>
	 * If square doesn't have any disc, it returns {@code true}. Otherwise, it returns {@code false}.<br>
	 * @return マスが空なら{@code true},そうでないなら{@code false} {@code true} if square is empty; {@code false} otherwise
	 */
	public boolean isEmpty(){
		return disc_ == null;
	}

	/**
	 * マスが持つ石を裏返す Reverses a disc on this square.<br>
	 * マス上の石が黒なら白に,白なら黒にする<br>
	 * マス上に石がない時{@link IllegalStateException}が投げられる<br>
	 * It changes color of disc on this square.<br>
	 * When square is empty, {@link IllegalStateException} is thrown.
	 * @throws IllegalStateException マスが空の時 when square is empty
	 */
	public void reverse(){
		if(isEmpty()){
			throw new IllegalStateException("cannot reverse because has no disc");
		}
		disc_.reverse();
	}

	/**
	 * マスに石が置いてある場合石を返す If square has a disc, returns it.<br>
	 * マスに石が置いてある場合,その石のコピーが返される.<br>
	 * マスに石が置いてない場合,{@link IllegalStateException}が投げられる.<br>
	 * If a disc is on the square, it returns a copy of the disc.<br>
	 * Otherwise,{@link IllegalStateException} is thrown.<br>
	 * @return 石のコピー copy of disc
	 * @throws IllegalStateException 石が無い時 when square is empty
	 */
	public Disc disc(){
		if(isEmpty()){
			throw new IllegalStateException("cannot return disc because has no disc");
		}
		return disc_.clone();
	}

	/**
	 * マスに石が置いてある場合石の色を返す If square has a disc, returns its color.<br>
	 * マスに石が置いてある場合,その石の色が返される.<br>
	 * マスに石が置いてない場合,{@link IllegalStateException}が投げられる.<br>
	 * If a disc is on the square, it returns a color of the disc.<br>
	 * Otherwise,{@link IllegalStateException} is thrown.<br>
	 * @return 石の色 color of disc ({@code disc().color()})
	 * @throws IllegalStateException 石が無い時 when square is empty
	 */
	public Color color(){
		return disc().color();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disc_ == null) ? 0 : disc_.hashCode());
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
		if (!(obj instanceof Square)) {
			return false;
		}
		Square other = (Square) obj;
		if (disc_ == null) {
			if (other.disc_ != null) {
				return false;
			}
		} else if (!disc_.equals(other.disc_)) {
			return false;
		}
		return true;
	}
}
