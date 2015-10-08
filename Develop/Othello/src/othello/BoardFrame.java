package othello;

import java.util.AbstractCollection;
import java.util.ArrayList;

/**
 * �I�Z���{�[�h�̃f�[�^�\����񋟂��� Provides data structure of othello board.<br>
 * �r���Ńf�[�^�\����ύX���邱��(�v�f�̒ǉ���폜)�͂ł��Ȃ�.<br>
 * �I�Z���{�[�h�̃f�[�^�\���̓{�[�h�̊O�̃}�X���܂߂�10x10��2�����z��ł���.<br>
 * �C�e���[�^�̓{�[�h��((1,1)����(8,8)�܂�)��8x8=64�̃}�X�𑖍�����.<br>
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
	 * �{�[�h��2�����z�� grid of board.
	 */
	protected ArrayList<ArrayList<E>> frame_ = new ArrayList<ArrayList<E>>();

	/**
	 * �{�[�h���\�z���� Constructs board.<br>
	 * �S�Ă̗v�f��<code>null</code>�ɂ���<br>
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
	 * �C�e���[�^��Ԃ� Returns iterator.<br>
	 * �ʒu(1,1)�̃C�e���[�^��Ԃ�.<br>
	 * �C�e���[�^��(1,1)����(8,8)�܂ł𔽕�����<br>
	 * Iterator of position (1,1) on the board is returned.<br>
	 * Iterator iterates from (1,1) to (8,8).<br>
	 * @return �C�e���[�^ iterator
	 */
	@Override
	public BoardIterator<E> iterator() {
		return new BoardIterator<E>(Position.begin(), this);
	}

	/**
	 * �ʒu(1,1)�̃C�e���[�^��Ԃ�.<br>
	 * �C�e���[�^��(1,1)����(8,8)�܂ł𔽕�����<br>
	 * Iterator of position (1,1) on the board is returned.<br>
	 * Iterator iterates from (1,1) to (8,8).<br>
	 * @param p �擾����C�e���[�^�̈ʒu position of iterator to get
	 * @return �C�e���[�^ iterator
	 */
	public BoardIterator<E> iterator(Position p){
		return new BoardIterator<E>(p.clone(), this);
	}

	/**
	 * �ʒup�̗v�f��Ԃ� Returns element of position p.
	 * @param p �ʒu position
	 * @return �ʒup�̗v�f element of position p
	 */
	public E get(Position p) {
		return get(p.getI(), p.getJ());
	}

	/**
	 * �ʒu(i,j)�̗v�f��Ԃ� Returns element of position (i,j).
	 * @param i �s row [0,9]
	 * @param j �� column [0,9]
	 * @return �ʒu(i,j)�̗v�f element of position (i,j)
	 */
	public E get(int i, int j) {
		return frame_.get(i).get(j);
	}

	/**
	 * �ʒup�ɗv�fe��ݒ肷�� Sets element to position p.
	 * @param p �ʒu position
	 * @param e �v�f element to set
	 */
	public void set(Position p, E e){
		set(p.getI(), p.getJ(), e);
	}

	/**
	 * �ʒu(i,j)�ɗv�fe��ݒ肷�� Sets element to position (i,j).
	 * @param i �s row [0,9]
	 * @param j �� column [0,9]
	 * @param e �v�f element to set
	 */
	public void set(int i, int j, E e){
		frame_.get(i).set(j, e);
	}

	/**
	 * �I�Z���{�[�h�̃}�X�̐���Ԃ� Returns number of squares on the board.<br>
	 * �I�Z���{�[�h�̃}�X�̐���64<br>
	 * Number of squares is 64.<br>
	 * @return 64
	 */
	@Override
	public int size() {
		return 64;
	}

}
