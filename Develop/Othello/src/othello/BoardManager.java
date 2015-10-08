package othello;

import java.util.LinkedList;
import java.util.List;

/**
 * ボード上の石を管理,操作する Manages discs on the board.<br>
 * ボード上の石を置ける場所を調べたり,石を置いたり,ひっくり返したりする<br>
 * Checks whether square is available or not. Puts a disc. Reverses discs.<br>
 * <br>
 * example<br>
 * source : <br>
 * <pre>
 * {@code
 * }
 * </pre>
 * output : <br>
 */
public class BoardManager implements Cloneable {

	/*public static void main(String[] args) {
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
	}*/

	private Board board_ = new Board();

	/**
	 * オブジェクトのクローンを生成する Creates clone of this board manager object.<br>
	 * @return オブジェクトのクローン Clone of this board manager
	 */
	@Override
	protected BoardManager clone() {
		try{
			BoardManager bm = (BoardManager) super.clone();
			bm.board_ = board_.clone();
			return bm;
		}
		catch(CloneNotSupportedException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ボードを返す Returns board.<br>
	 * ボードのコピーを返します.<br>
	 * Returns copy of board.
	 * @return ボード board
	 */
	public Board getBoard(){
		return board_.clone();
	}

	/**
	 * ボードをセットする Sets board.<br>
	 * ボードをコピーして設定する.<br>
	 * Sets copy of board.<br>
	 * @param board セットするボード board to set
	 */
	public void setBoard(Board board){
		board_ = board.clone();
	}

	/**
	 * ボード上にchoiceで指定された石を置く Puts a disc of the choice on the board.<br>
	 * 挟んだ石をひっくり返す事はしない.<br>
	 * 石をひっくり返すには{@link BoardManager#reverseDiscs(List)}を使う.<br>
	 * Doesn't reverse any disc.<br>
	 * Use {@link BoardManager#reverseDiscs(List)} to reverse discs.
	 * @param choice 置く石の選択 choice of disc to put
	 */
	public void putDisc(Choice choice){
		board_.get(choice.getPosition()).put(choice.getColor());
	}

	/**
	 * 指定された位置の石を全てひっくり返す Reverses all discs which is at position of reversed.<br>
	 * @param reversed 石をひっくり返すマスの位置のリスト list of positions of disc to reverse
	 */
	public void reverseDiscs(List<Position> reversed){
		for(Position p : reversed){
			board_.get(p).reverse();
		}
	}

	/**
	 * 石を置くことができる選択肢を返す Returns available choices.<br>
	 * @param color 置こうとする石の色 color of disc to put
	 * @return 利用可能な選択のリスト list of available choices
	 */
	public List<Choice> createChoiceList(Color color){
		List<Choice> choices = new LinkedList<Choice>();
		for(BoardIterator<Square> itr = board_.iterator(); itr.hasNext(); itr.next()){
			Choice choice = new Choice(itr.getPosition(), color);
			if(!createPositionListToReverse(choice).isEmpty()){
				choices.add(choice);
			}
		}
		return choices;
	}

	/**
	 * ひっくり返す石のリストを返す Returns list of position to reverse disc.<br>
	 * @param choice 置こうとする石の選択 choice to put a disc
	 * @return ひっくり返す石のリスト list of position to reverse disc
	 */
	public List<Position> createPositionListToReverse(Choice choice){
		List<Position> reversed = new LinkedList<Position>();
		if(!board_.get(choice.getPosition()).isEmpty()){
			return reversed;
		}
		for(Direction d : Direction.values()){
			List<Position> tmp = new LinkedList<Position>();
			BoardIterator<Square> itr = board_.iterator(choice.getPosition());
			itr.move(d);
			while(!itr.getElement().isEmpty()){
				if(itr.getElement().color().equals(choice.getColor().reversed())){
					tmp.add(itr.getPosition());
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
