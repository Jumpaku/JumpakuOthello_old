package othello;

/**
 * オセロボード上での位置を表す Position on othello board.<br>
 * {@link Position}は不変.<br>
 * また最後の位置(8,8)の次のダミー位置(8,9)が存在する.<br>
 *
 * <table border=1>
 * <caption>position on a board</caption>
 * <tr> <td></td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td> </tr>
 * <tr> <td>1</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>2</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>3</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>4</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>5</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>6</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>7</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>8</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * </table>
 *<br>
 *<br>
 *sample<br>
 *source :
 * <pre>
 * {@code
	Position x = new Position(3, 6);
	Position y = x.clone();
	Position z = new Position(7, 4);
	System.out.println(x.compareTo(y) + " : " + x.equals(y));
	System.out.println(x.compareTo(z) + " : " + x.equals(z));
	System.out.println(z.compareTo(y) + " : " + z.equals(y));

	Position p = new Position(8, 1);
	for(Direction d : Direction.values()){
		System.out.print(d.name() + " " + p.moved(d) + " : ");
	}
	System.out.println();
	for(Position i = Position.begin(); i.compareTo(Position.end()) < 0; i = i.next()){
		System.out.print("(" + i + ")");
	}
 * }
 * </pre>
 * output : <br>
 * 0 : true<br>
 * -4 : false<br>
 * 4 : false<br>
 * N 7,1 : NE 7,2 : E 8,2 : SE 8,9 : S 8,9 : SW 8,9 : W 8,9 : NW 8,9 :<br>
 * (1,1)(1,2)(1,3)(1,4)...(8,1)(8,2)(8,3)(8,4)(8,5)(8,6)(8,7)(8,8)
 */
public final class Position implements Cloneable, Comparable<Position> {

	/*public static void main(String[] args){
		Position x = new Position(3, 6);
		Position y = x.clone();
		Position z = new Position(7, 4);
		System.out.println(x.compareTo(y) + " : " + x.equals(y));
		System.out.println(x.compareTo(z) + " : " + x.equals(z));
		System.out.println(z.compareTo(y) + " : " + z.equals(y));

		Position p = new Position(8, 1);
		for(Direction d : Direction.values()){
			System.out.print(d.name() + " " + p.moved(d) + " : ");
		}
		System.out.println();
		for(Position i = Position.begin(); i.compareTo(Position.end()) < 0; i = i.next()){
			System.out.print("(" + i + ")");
		}
	}*/

	private final int i_;

	private final int j_;

	/**
	 * 位置オブジェクトを生成し行と列を設定する Constructs position object and sets row and column.
	 * もしiまたはjが1以上8以下でない場合ダミー位置(8,9)に設定される.
	 * @param i 行 row ({@code 1 <= i && i <= 8})
	 * @param j 列 column ({@code 1 <= j && j <= 8})
	 */
	public Position(int i, int j){
		if(i <= 0 || i >= 9 || j <= 0 || j >= 9){
			i_ = 8;
			j_ = 9;
		}
		else{
			i_ = i;
			j_ = j;
		}
	}

	/**
	 * 位置オブジェクトをコピーして構築する Copies and Constructs position.
	 * 引数pと同じ位置を表す位置オブジェクトを構築するコピーコンストラクタ.<br>
	 * 引数が{@code null}の時は{@link NullPointerException}が投げられる.<br>
	 * This is a copy constructor copies argument p.<br>
	 * When p is {@code null}, {@link NullPointerException} is thrown.
	 * @param p コピー元の位置オブジェクト source position
	 * @throws NullPointerException when {@code p == null}
	 */
	public Position(Position p){
		this(p.i_, p.j_);
	}

	/**
	 * 位置オブジェクトを構築する Constructs position object.<br>
	 * デフォルトでは(1,1)に設定される.<br>
	 * Default position is (1,1)
	 */
	public Position(){
		this(begin());
	}

	/**
	 * 位置オブジェクトのクローンを生成する Creates clone of this position object.<br>
	 * @return 位置オブジェクトのクローン Clone of this position
	 */
	@Override
	public Position clone(){
		try {
			return (Position)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 他の位置と同じ位置か調べる Indicates whether some other object is equal to this one.<br>
	 * このオブジェクトと比較するobjは{@link Position}でなければならない.<br>
	 * objが{@link Position}でない時{@link java.lang.ClassCastException}が投げられる.<br>
	 * {@code obj == null}の時{@code false}を返す.<br>
	 * Argument obj must be {@link Position}.<br>
	 * When obj is not {@link Position}, {@link java.lang.ClassCastException} is thrown.<br>
	 * If {@code obj == null}, it returns false.
	 * @param obj このオブジェクトと比べる位置 the reference object with which to compare.
	 * @return 同じ位置を表すなら{@code true},違う位置なら{@code false} {@code true} if this object is the same color as the obj; {@code false} otherwise.
	 * @throws java.lang.ClassCastException 引数objが{@link Position}でない時 When obj is not {@link Position}.
	 */
	@Override
	public boolean equals(Object obj) {
		Position p = (Position) obj;
		return p == null ? false : i_ == p.i_ && j_ == p.j_;
	}

	/**
	 * 最後の次のダミー位置を返す Returns dummy.
	 * 最後の位置(8,8)の次のダミー位置(8,9)を返す.<br>
	 * Returned dummy is next position of last position.<br>
	 * Last position is (8,8), dummy is (8,9).
	 * @return {@code new Position(8, 9)}
	 */
	public static Position end(){
		return new Position(8, 9);
	}

	/**
	 * 最初の位置を返す Returns first position.
	 * 最初の位置(1,1)を返す.<br>
	 * First position is (1,1)
	 * @return {@code new Position(1, 1)}
	 */
	public static Position begin(){
		return new Position(1, 1);
	}

	/**
	 * 最後の次の位置か調べる Indicates whether this position is next of last.
	 * @return {@code equals(end())}
	 */
	public boolean isEnd(){
		return equals(end());
	}

	/**
	 * 次の位置を生成する Creates next position.<br>
	 * 左から右へ,上から下への順番で次の位置を表すオブジェクトを生成する.<br>
	 * この時,元の位置オブジェクトは変化しない.<br>
	 * もしこの位置オブジェクトが最後の次のダミー位置なら{@code Position#end()}を返す.<br>
	 * New position which is next of this is created.<br>
	 * The order is from left to right, from top to bottom.<br>
	 * This object is not changed.<br>
	 * If this is (8,9), returns {@code Position#end()}
	 * @return 次の位置 next position
	 */
	public Position next(){
		Position pos = end();

		if(!this.isEnd()){
			if(j_ < 8){
				pos = new Position(i_, j_ + 1);
			}
			else{
				pos = new Position(i_ + 1, 1);
			}
		}

		return pos;
	}

	/**
	 * 移動させた位置オブジェクト生成する Creates moved position.<br>
	 * 行方向にmovei,列方向にmovej移動させた位置オブジェクトを生成する.<br>
	 * この時,元の位置オブジェクトは変化しない.<br>
	 * 移動させた結果,行または列が0以下または9以上になる場合{@code Position#end()}が返される.<br>
	 * Created position is moved horizontally by movei, vertically by movej from this position.<br>
	 * This object is not changed.<br>
	 * At the result of movement, if row or column are grater than 8 or less than 1, returned position is {@code Position#end()}.
	 * @param movei 行方向の移動量 amount of horizontal movement
	 * @param movej 列方向の移動量 amount of vertical movement
	 * @return 移動させた位置オブジェクト moved position
	 */
	public Position moved(int movei, int movej){
		return new Position(i_ + movei, j_ + movej);
	}

	/**
	 * 移動させた位置オブジェクト生成する Creates moved position.<br>
	 * dirの方向に1マスだけ移動させた位置オブジェクト生成する.<br>
	 * この時,元の位置オブジェクトは変化しない.<br>
	 * 移動させた結果,行または列が0以下または9以上になる場合{@code Position#end()}が返される.<br>
	 * Created position is moved by 1 square from this position on the direction dir.
	 * This object is not changed.<br>
	 * At the result of movement, if row or column are grater than 8 or less than 1, returned position is {@code Position#end()}.
	 * @param dir 移動させる方向 direction to move
	 * @return 移動させた位置オブジェクト moved position
	 */
	public Position moved(Direction dir){
		return moved(dir.movei(), dir.movej());
	}

	/**
	 * 行数であるi成分を返す Returns row number i.
	 * @return 行数 row number
	 */
	public int i(){
		return i_;
	}

	/**
	 * 列数であるj成分を返す Returns column number j.
	 * @return 列数 column number
	 */
	public int j(){
		return j_;
	}

	/**
	 * 位置の文字列を返す Returns string of position
	 * 例えば位置(4,5)の文字列は{@code "4,5"}となる.<br>
	 * For example position (4,5), returned string is {@code "4,5"}.
	 * @return 位置の文字列 string of position<br>
	 * (i,j) {@literal -->} {@code "i,j"}
	 */
	@Override
	public String toString() {
		return i_ + "," + j_;
	}

	/**
	 * 位置オブジェクトの大小を比較する Compares this object with the specified object for order.
	 * 左から右へ,上から下への順で大きくなる.<br>
	 * 例えばx,yが(3,6),zが(7,4)の時{@code x.compareTo(y) == 0},{@code x.compareTo(z) < 0},{@code z.compareTo(y) > 0}となる.<br>
	 * 引数pが{@code null}の時NullPointerExceptionが投げられる.<br>
	 * The order is from left to right, from top to bottom.<br>
	 * For example x,y are (3,6),z is (7,4), the results are {@code x.compareTo(y) == 0},{@code x.compareTo(z) < 0},{@code z.compareTo(y) > 0}
	 * When argument p is {@code null}, NullPointerException is thrown.
	 * @return i() == p.i() ? j() - p.j() : i() - p.i()
	 * @throws NullPointerException {@code p == null}の時 when {@code p == null}
	 */
	@Override
	public int compareTo(Position p) {
		if(p == null){
			throw new NullPointerException("cannot compare because position is null");
		}

		return i_ == p.i_ ? j_ - p.j_ : i_ - p.i_;
	}

}
