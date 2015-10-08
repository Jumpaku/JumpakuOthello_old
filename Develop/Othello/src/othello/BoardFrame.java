package othello;

import java.util.AbstractCollection;
import java.util.ArrayList;

/**
 * オセロボードのデータ構造を提供する Provides data structure of othello board.<br>
 * 途中でデータ構造を変更すること(要素の追加や削除)はできない.<br>
 * オセロボードのデータ構造はボードの外のマスを含めて10x10の2次元配列である.<br>
 * イテレータはボード内((1,1)から(8,8)まで)の8x8=64個のマスを走査する.<br>
 * It is not able to change data structure (remove or add some elements).<br>
 * Data structure of othello board is 10x10 grid which contains square at out of board.<br>
 * However, iterator traverses on the board (from (1,1) to (8,8)).Number of element is 8x8=64.<br>
 * <br>
 * <br>
 * example<br>
 * source : <br>
 * <pre>
 * {@code
	BoardFrame<Square> squares = new BoardFrame<Square>();
	for(int i = 0; i < 10; ++i){
		for(int j = 0; j < 10; ++j){
			squares.set(i, j, new Square());
		}
	}
	for(Square s : squares){
		s.put(new Disc(Color.random()));
	}
	for(int i = 0; i < 10; ++i){
		for(int j = 0; j < 10; ++j){
			Square s = squares.get(i,j);
			System.out.print((s.isEmpty() ? "N" : s.color()) + " ");
		}
		System.out.println();
	}
 * }
 * </pre>
 * output : <br>
 *
 * @param <E> Class of element
 */
public class BoardFrame<E> extends AbstractCollection<E>{

	/*public static void main(String[] args) {
		BoardFrame<Square> squares = new BoardFrame<Square>();
		for(int i = 0; i < 10; ++i){
			for(int j = 0; j < 10; ++j){
				squares.set(i, j, new Square());
			}
		}
		for(Square s : squares){
			s.put(new Disc(Color.random()));
		}
		for(int i = 0; i < 10; ++i){
			for(int j = 0; j < 10; ++j){
				Square s = squares.get(i,j);
				System.out.print((s.isEmpty() ? "N" : s.color()) + " ");
			}
			System.out.println();
		}
	}*/

	/**
	 * ボードの2次元配列 grid of board.
	 */
	protected ArrayList<ArrayList<E>> frame_ = new ArrayList<ArrayList<E>>();

	/**
	 * ボードを構築する Constructs board.<br>
	 * 全ての要素が<code>null</code>にする<br>
	 * It initializes all element as <code>null</code>
	 */
	public BoardFrame() {
		for(int i = 0; i < 10; ++i){
			frame_.add(new ArrayList<>());
			for(int j = 0; j < 10; ++j){
				frame_.get(i).add((E)null);
			}
		}
	}

	/**
	 * イテレータを返す Returns iterator.<br>
	 * 位置(1,1)のイテレータを返す.<br>
	 * イテレータは(1,1)から(8,8)までを反復する<br>
	 * Iterator of position (1,1) on the board is returned.<br>
	 * Iterator iterates from (1,1) to (8,8).<br>
	 * @return イテレータ iterator
	 */
	@Override
	public BoardIterator<E> iterator() {
		return new BoardIterator<E>(Position.begin(), this);
	}

	/**
	 * 位置(1,1)のイテレータを返す.<br>
	 * イテレータは(1,1)から(8,8)までを反復する<br>
	 * Iterator of position (1,1) on the board is returned.<br>
	 * Iterator iterates from (1,1) to (8,8).<br>
	 * @param p 取得するイテレータの位置 position of iterator to get
	 * @return イテレータ iterator
	 */
	public BoardIterator<E> iterator(Position p){
		return new BoardIterator<E>(p.clone(), this);
	}

	/**
	 * 位置pの要素を返す Returns element of position p.
	 * @param p 位置 position
	 * @return 位置pの要素 element of position p
	 */
	public E get(Position p) {
		return get(p.getI(), p.getJ());
	}

	/**
	 * 位置(i,j)の要素を返す Returns element of position (i,j).
	 * @param i 行 row [0,9]
	 * @param j 列 column [0,9]
	 * @return 位置(i,j)の要素 element of position (i,j)
	 */
	public E get(int i, int j) {
		return frame_.get(i).get(j);
	}

	/**
	 * 位置pに要素eを設定する Sets element to position p.
	 * @param p 位置 position
	 * @param e 要素 element to set
	 */
	public void set(Position p, E e){
		set(p.getI(), p.getJ(), e);
	}

	/**
	 * 位置(i,j)に要素eを設定する Sets element to position (i,j).
	 * @param i 行 row [0,9]
	 * @param j 列 column [0,9]
	 * @param e 要素 element to set
	 */
	public void set(int i, int j, E e){
		frame_.get(i).set(j, e);
	}

	/**
	 * オセロボードのマスの数を返す Returns number of squares on the board.<br>
	 * オセロボードのマスの数は64<br>
	 * Number of squares is 64.<br>
	 * @return 64
	 */
	@Override
	public int size() {
		return 64;
	}

}
