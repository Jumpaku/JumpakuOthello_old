package othello;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class BoardFrame<E> extends AbstractCollection<E>{

	public static void main(String[] args) {
		BoardFrame<Square> squares = new BoardFrame<Square>();
		for(int i = 0; i < 10; ++i){
			for(int j = 0; j < 10; ++j){
				squares.set(i, j, new Square(new Position(i, j)));
			}
		}
		for(Square s : squares){
			s.put(new Disc(Color.random()));
		}
		squares.get(5, 4).unput();
		squares.get(4, 5).put(new Disc(Color.dark()));
		squares.get(4, 5).reverse();
		squares.set(5, 5, squares.get(0, 0).clone());
		for(Square s : squares){
			System.out.println(s + " : "
					+ s.equals(squares.get(1, 1))
					+ " : " + squares.get(8, 9).equals(s)
					+ " : " + s.hasDisc());
			}
	}

	protected ArrayList<ArrayList<E>> frame_ = new ArrayList<ArrayList<E>>();

	public BoardFrame() {
		for(int i = 0; i < 10; ++i){
			frame_.add(new ArrayList<>());
			for(int j = 0; j < 10; ++j){
				frame_.get(i).add((E)null);
			}
		}
	}

	@Override
	public BoardIterator<E> iterator() {
		return new BoardIterator<E>(Position.begin(), this);
	}

	public E get(Position p) {
		return get(p.i(), p.j());
	}

	public E get(int i, int j) {
		return frame_.get(i).get(j);
	}

	public void set(Position p, E e){
		set(p.i(), p.j(), e);
	}

	public void set(int i, int j, E e){
		frame_.get(i).set(j, e);
	}

	@Override
	public int size() {
		return 64;
	}

}
