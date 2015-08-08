package othello;

import java.util.LinkedList;
import java.util.List;

public class BoardManager implements Cloneable {

	public static void main(String[] args) {
		BoardManager bm = new BoardManager();
		List<Position> r = bm.getReversed(new Choice(4,3,Color.light()));
		bm.putDisc(new Choice(4,3,Color.light()));
		bm.reverseDiscs(r);
		for(Position p : r){
			System.out.println(p);
		}

	}

	private Board board_ = new Board();

	public Board board(){
		return board_.clone();
	}

	public void board(Board board){
		board_ = board.clone();
	}

	public void putDisc(Choice choice){
		board_.get(choice.position()).put(choice.color());
	}

	public void reverseDiscs(List<Position> reversed){
		for(Position p : reversed){
			board_.get(p).reverse();
		}
	}

	public List<Choice> getChoices(Color color){
		List<Choice> choices = new LinkedList<Choice>();
		for(Square s : board_){
			Choice choice = new Choice(s.position(), color);
			if(!getReversed(choice).isEmpty()){
				choices.add(choice);
			}
		}
		return choices;

	}

	public List<Position> getReversed(Choice choice){
		List<Position> reversed = new LinkedList<Position>();
		if(board_.get(choice.position()).hasDisc()){
			return reversed;
		}
		for(Direction d = Direction.begin(); d != Direction.end(); d = Direction.next(d)){
			List<Position> tmp = new LinkedList<Position>();
			BoardIterator<Square> itr = board_.iterator(choice.position());
			itr.move(d);
			while(itr.element().hasDisc()){
				if(itr.element().color().equals(choice.color().reversed())){
					tmp.add(itr.element().position());
				}
				else {
					reversed.addAll(tmp);
					break;
				}
				itr.move(d);
			}
		}
		return reversed;
	}

}
