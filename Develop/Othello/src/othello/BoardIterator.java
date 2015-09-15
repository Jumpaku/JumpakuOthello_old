package othello;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * �I�Z���{�[�h��𑖍�����C�e���[�^ Iterator traverses on othello board.<br>
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
	 * �C�e���[�^���w���v�f�̈ʒu position of iterator.
	 */
	protected Position position_;

	/**
	 * �C�e���[�^���\�z���� Constructs iterator.<br>
	 * �w�肳�ꂽ�{�[�h�𑖍�����ʒup�̃C�e���[�^���\�z����.<br>
	 * Constructs iterator of position p traverses on the board board.
	 * @param p �C�e���[�^�̈ʒu position of iterator
	 * @param board ��������{�[�h board on which iterator traverses
	 */
	public BoardIterator(Position p, BoardFrame<E> board) {
		position_ = p.clone();
		board_ = board;
	}

	/**
	 * ���������ł���ɗv�f������ꍇ��true��Ԃ��܂� Returns true if the iteration has more elements.
	 * <code>next()</code>����O���X���[����̂ł͂Ȃ��v�f��Ԃ��ꍇ�́A<code>true</code>��Ԃ��܂��B
	 * Returns <code>true</code> if <code>next()</code> would return an element rather than throwing an exception.
	 * @return �v�f������ꍇ��<code>true</code> <code>true</code> if the iteration has more elements
	 */
	@Override
	public boolean hasNext() {
		return !position_.isEnd();
	}

	/**
	 * ���̗v�f�Ɉړ����� Goes to next element.<br>
	 * ���݂̗v�f��Ԃ�, �w���v�f��1�i�߂�.<br>
	 * Returns current element and goes to next element.
	 * @return ���݂̗v�f current element
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
	 * �w�肳�ꂽ������1�i�߂� Goes to the direction d.<br>
	 * ���݂̗v�f��Ԃ�,�w���v�f���w�肳�ꂽ������1�i�߂�.
	 * Returns current element and goes to specified direction.
	 * @param d �i�ޕ��� direction to go
	 * @return ���݂̗v�f current element
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
	 * ���݂̈ʒu��Ԃ� Returns current position.
	 * @return ���݂̈ʒu current position
	 */
	public Position position(){
		return position_.clone();
	}

	/**
	 * ���݂̗v�f��Ԃ� Returns current element.<br>
	 * @return ���݂̗v�f current element
	 */
	public E element(){
		return board_.get(position_);
	}
}
