package othello;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * オセロボード上を走査するイテレータ Iterator traverses on othello board.<br>
 * <br>
 * <br>
 * example<br>
 * source : <br>
 * <pre>
 * {@code
 * }
 * </pre>
 * output : <br>
 *
 * @param <E> Class of element
 * @see othello.Position
 */
public class BoardIterator<E> implements Iterator<E>{

	private final BoardFrame<E> board_;

	/**
	 * イテレータが指す要素の位置 position of iterator.
	 */
	protected Position position_;

	/**
	 * イテレータを構築する Constructs iterator.<br>
	 * 指定されたボードを走査する位置pのイテレータを構築する.<br>
	 * Constructs iterator of position p traverses on the board board.
	 * @param p イテレータの位置 position of iterator
	 * @param board 走査するボード board on which iterator traverses
	 */
	public BoardIterator(Position p, BoardFrame<E> board) {
		position_ = p.clone();
		board_ = board;
	}

	/**
	 * 反復処理でさらに要素がある場合にtrueを返します Returns true if the iteration has more elements.
	 * <code>next()</code>が例外をスローするのではなく要素を返す場合は、<code>true</code>を返します。
	 * Returns <code>true</code> if <code>next()</code> would return an element rather than throwing an exception.
	 * @return 要素がある場合に<code>true</code> <code>true</code> if the iteration has more elements
	 */
	@Override
	public boolean hasNext() {
		return !position_.isEnd();
	}

	/**
	 * 次の要素に移動する Goes to next element.<br>
	 * 現在の要素を返し, 指す要素を1つ進める.<br>
	 * Returns current element and goes to next element.
	 * @return 現在の要素 current element
	 * @see othello.Position
	 */
	@Override
	public E next() {
		if(!hasNext()){
			throw new NoSuchElementException("cannot get next element because does not have next");
		}
		E e = element();
		position_ = position_.next();
		return e;
	}

	/**
	 * 指定された方向に1つ進める Goes to the direction d.<br>
	 * 現在の要素を返し,指す要素を指定された方向に1つ進める.
	 * Returns current element and goes to specified direction.
	 * @param d 進む方向 direction to go
	 * @return 現在の要素 current element
	 */
	public E move(Direction d){
		if(position_.isEnd()){
			throw new NoSuchElementException("cannot move because itetator is end");
		}
		E e = element();
		position_ = position_.moved(d);
		return e;
	}

	/**
	 * 現在の位置を返す Returns current position.
	 * @return 現在の位置 current position
	 */
	public Position position(){
		return position_.clone();
	}

	/**
	 * 現在の要素を返す Returns current element.<br>
	 * @return 現在の要素 current element
	 */
	public E element(){
		return board_.get(position_);
	}
}
