package othello;

import java.util.ArrayList;

/**
 * �I�Z���{�[�h othello board.<br>
 * <br>
 * example<br>
 * source : <br>
 * <pre>
 * {@code
	Board board = new Board();
	for(int i = 1; i <= 8; ++i){
		System.out.print("|");
		for(int j = 1; j <= 8; ++j){
			Square s = board.get(i, j);
			System.out.print((!s.isEmpty() ? s.color() : " ") + "|");
		}
		System.out.println();
	}
 * }
 * </pre>
 * output : <br>
 */
public class Board extends BoardFrame<Square> implements Cloneable{

	/*public static void main(String[] args) {
		Board board = new Board();
		for(int i = 1; i <= 8; ++i){
			System.out.print("|");
			for(int j = 1; j <= 8; ++j){
				Square s = board.get(i, j);
				System.out.print((!s.isEmpty() ? s.color() : " ") + "|");
			}
			System.out.println();
		}
	}*/

	/**
	 * �I�Z���{�[�h������������ Initializes othello board.<br>
	 * �S�Ă̗v�f�ɋ�̃}�X���Z�b�g��,������4�}�X�ɐ΂�u��.<br>
	 * ���̐΂�(4,4),(5,5)����,(4,5),(5,4)�����ł���.<br>
	 * It sets empty squares to all element, and puts 4 discs on center squares.<br>
	 * Discs at (4,4) and (5,5) is black, at (4,5),(5,4) is white.
	 */
	public Board() {
		for(int i = 0; i < 10; ++i){
			for(int j = 0; j < 10; ++j){
				frame_.get(i).set(j, new Square());
			}
		}
		get(4, 4).put(Color.black);
		get(5, 5).put(Color.black);
		get(4, 5).put(Color.white);
		get(5, 4).put(Color.white);
	}

	/**
	 * �I���I�u�W�F�N�g�̃N���[���𐶐����� Creates clone of this board object.<br>
	 * @return �I���I�u�W�F�N�g�̃N���[�� Clone of this board
	 */
	@Override
	public Board clone() {
		try {
			Board b = (Board)super.clone();
			b.frame_ = new ArrayList<ArrayList<Square>>();
			for(int i = 0; i < 10; ++i){
				b.frame_.add(new ArrayList<>());
				for(int j = 0; j < 10; ++j){
					b.frame_.get(i).add(frame_.get(i).get(j).clone());
				}
			}
			return b;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
