package othello;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoardIterator<E> implements Iterator<E>{

	private final BoardFrame<E> board_;

	private Position position_;

	public BoardIterator(Position p, BoardFrame<E> board) {
		position_ = p.clone();
		board_ = board;
	}

	@Override
	public boolean hasNext() {
		return !position_.isEnd();
	}

	@Override
	public E next() {
		if(!hasNext()){
			throw new NoSuchElementException("cannot get next element because does not have next");
		}
		E e = element();
		position_ = position_.next();
		return e;
	}

	public E move(Direction d){
		if(position_.isEnd()){
			throw new NoSuchElementException("cannot move because itetator is end");
		}
		E e = element();
		position_ = position_.moved(d);
		return e;
	}

	public E element(){
		return board_.get(position_);
	}
}