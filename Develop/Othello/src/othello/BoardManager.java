package othello;

import java.util.LinkedList;
import java.util.List;

public class BoardManager implements Cloneable {

	public static void main(String[] args) {
		BoardManager bm = new BoardManager();
		Choice c = bm.getChoices(Color.black()).get(0);
		System.out.println(c);
		List<Position> r = bm.getReversed(c);
		bm.putDisc(c);
		bm.reverseDiscs(r);
		Board b = bm.board();
		for(int i = 1; i <= 8; ++i){
			System.out.print("|");
			for(int j = 1; j <= 8; ++j){
				Square s = b.get(i, j);
				System.out.print((!s.isEmpty() ? s.disc().color().toString().charAt(0) : " ") + "|");
			}
			System.out.println();
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
		/*for(Square s : board_){
			Choice choice = new Choice(s.position(), color);
			if(!getReversed(choice).isEmpty()){
				choices.add(choice);
			}
		}*/
		for(BoardIterator<Square> itr = board_.iterator(); itr.hasNext(); itr.next()){
			Choice choice = new Choice(itr.position(), color);
			if(!getReversed(choice).isEmpty()){
				choices.add(choice);
			}
		}
		return choices;

	}

	public List<Position> getReversed(Choice choice){
		List<Position> reversed = new LinkedList<Position>();
		if(!board_.get(choice.position()).isEmpty()){
			return reversed;
		}
		for(Direction d : Direction.values()){
			List<Position> tmp = new LinkedList<Position>();
			BoardIterator<Square> itr = board_.iterator(choice.position());
			itr.move(d);
			while(!itr.element().isEmpty()){
				if(itr.element().color().equals(choice.color().reversed())){
					tmp.add(itr.position());
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
