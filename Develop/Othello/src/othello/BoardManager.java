package othello;

import java.util.LinkedList;
import java.util.List;

/**
 * �{�[�h��̐΂��Ǘ�,���삷�� Manages discs on the board.<br>
 * �{�[�h��̐΂�u����ꏊ�𒲂ׂ���,�΂�u������,�Ђ�����Ԃ����肷��<br>
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
	 * �I�u�W�F�N�g�̃N���[���𐶐����� Creates clone of this board manager object.<br>
	 * @return �I�u�W�F�N�g�̃N���[�� Clone of this board manager
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
	 * �{�[�h��Ԃ� Returns board.<br>
	 * �{�[�h�̃R�s�[��Ԃ��܂�.<br>
	 * Returns copy of board.
	 * @return �{�[�h board
	 */
	public Board getBoard(){
		return board_.clone();
	}

	/**
	 * �{�[�h���Z�b�g���� Sets board.<br>
	 * �{�[�h���R�s�[���Đݒ肷��.<br>
	 * Sets copy of board.<br>
	 * @param board �Z�b�g����{�[�h board to set
	 */
	public void setBoard(Board board){
		board_ = board.clone();
	}

	/**
	 * �{�[�h���choice�Ŏw�肳�ꂽ�΂�u�� Puts a disc of the choice on the board.<br>
	 * ���񂾐΂��Ђ�����Ԃ����͂��Ȃ�.<br>
	 * �΂��Ђ�����Ԃ��ɂ�{@link BoardManager#reverseDiscs(List)}���g��.<br>
	 * Doesn't reverse any disc.<br>
	 * Use {@link BoardManager#reverseDiscs(List)} to reverse discs.
	 * @param choice �u���΂̑I�� choice of disc to put
	 */
	public void putDisc(Choice choice){
		board_.get(choice.getPosition()).put(choice.getColor());
	}

	/**
	 * �w�肳�ꂽ�ʒu�̐΂�S�ĂЂ�����Ԃ� Reverses all discs which is at position of reversed.<br>
	 * @param reversed �΂��Ђ�����Ԃ��}�X�̈ʒu�̃��X�g list of positions of disc to reverse
	 */
	public void reverseDiscs(List<Position> reversed){
		for(Position p : reversed){
			board_.get(p).reverse();
		}
	}

	/**
	 * �΂�u�����Ƃ��ł���I������Ԃ� Returns available choices.<br>
	 * @param color �u�����Ƃ���΂̐F color of disc to put
	 * @return ���p�\�ȑI���̃��X�g list of available choices
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
	 * �Ђ�����Ԃ��΂̃��X�g��Ԃ� Returns list of position to reverse disc.<br>
	 * @param choice �u�����Ƃ���΂̑I�� choice to put a disc
	 * @return �Ђ�����Ԃ��΂̃��X�g list of position to reverse disc
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
