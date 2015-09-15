package othello;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * �I�Z���Q�[���̗��� History of choices in a othello game.<br>
 * �e�^�[���̑I�����ꂽ�ʒu�ƒu���ꂽ�΂̐F�����ԂɋL�����܂�<br>
 * �擪���疖���Ɍ������ĐV�����^�[���̑I���ƂȂ��Ă��܂�<br>
 * History contains choices of every turn in a othello game.<br>
 * First element is oldest, last element is latest.<br>
 *<br>
 * <br>
 * sample<br>
 * source :
 * <pre>
 * {@code
	History h = new History();
	h.addLast(new Choice(4, 4, Color.black()));
	h.addLast(new Choice(4, 5, Color.white()));
	h.addLast(new Choice(5, 4, Color.white()));
	h.addLast(new Choice(5, 5, Color.black()));
	Iterator<Choice> itr = h.iterator();
	while(itr.hasNext()){
		System.out.println(itr.next());
	}
	while(!h.isEmpty()){
		System.out.println(h.removeLast());
	}
 * }
 * </pre>
 * output : <br>
 * 4,4,B<br>
 * 4,5,W<br>
 * 5,4,W<br>
 * 5,5,B<br>
 * 5,5,B<br>
 * 5,4,W<br>
 * 4,5,W<br>
 * 4,4,B<br>
 */
public class History implements Cloneable {

	/*public static void main(String[] args) {
		History h = new History();
		h.addLast(new Choice(4, 4, Color.black()));
		h.addLast(new Choice(4, 5, Color.white()));
		h.addLast(new Choice(5, 4, Color.white()));
		h.addLast(new Choice(5, 5, Color.black()));
		Iterator<Choice> itr = h.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
		while(!h.isEmpty()){
			System.out.println(h.removeLast());
		}
	}*/

	private LinkedList<Choice> history_ = new LinkedList<Choice>();

	/**
	 * �擪�Ɏw�肳�ꂽ�v�f��}�����܂� Inserts the specified element at the beginning.
	 * @param choice �v�f element
	 */
	public void addFirst(Choice choice){
		history_.addFirst(choice);
	}

	/**
	 * �Ō�Ɏw�肳�ꂽ�v�f��ǉ����܂� Appends the specified element to the end.
	 * @param choice �v�f element
	 */
	public void addLast(Choice choice){
		history_.addLast(choice);
	}

	/**
	 * �v�f��K�؂ȏ����Ŕ�������C�e���[�^��Ԃ��܂� Returns an iterator over the elements.
	 * @return �C�e���[�^ iterator
	 */
	public Iterator<othello.Choice> iterator(){
		return history_.iterator();
	}

	/**
	 * �ŏ��̗v�f��Ԃ��܂� Returns the first element.
	 * @return �ŏ��̗v�f first element
	 */
	public Choice getFirst(){
		return history_.getFirst();
	}

	/**
	 * �Ō�̗v�f��Ԃ��܂� Returns the last element.
	 * @return �Ō�̗v�f last element
	 */
	public Choice getLast(){
		return history_.getLast();
	}

	/**
	 * �ŏ��̗v�f���폜���ĕԂ��܂� Removes and returns the first element
	 * @return �ŏ��̗v�f first element
	 */
	public Choice removeFirst(){
		return history_.removeFirst();
	}

	/**
	 * �Ō�̗v�f���폜���ĕԂ��܂� Removes and returns the last element.
	 * @return �Ō�̗v�f last element
	 */
	public Choice removeLast(){
		return history_.removeLast();
	}

	/**
	 * �v�f�̐���Ԃ��܂� Returns the number of elements.
	 * @return �v�f�� number of elements
	 */
	public int size(){
		return history_.size();
	}

	/**
	 * �v�f���Ȃ��ꍇ��{@code true}��Ԃ��܂� Returns {@code true} if history contains no elements
	 * @return �v�f���Ȃ��ꍇ��{@code true} {@code true} if history contains no elements
	 */
	public boolean isEmpty(){
		return history_.isEmpty();
	}

	/**
	 * �f�B�[�v�R�s�[��Ԃ��܂� Returns a deep copy of this
	 */
	@Override
	protected History clone() {
		try {
			History h =  (History) super.clone();
			for(int i = 0; i < size(); ++i){
				h.history_.set(i, history_.get(i).clone());
			}
			return h;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
