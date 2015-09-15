package othello;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * オセロゲームの履歴 History of choices in a othello game.<br>
 * 各ターンの選択された位置と置かれた石の色を順番に記憶します<br>
 * 先頭から末尾に向かって新しいターンの選択となっています<br>
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
	 * 先頭に指定された要素を挿入します Inserts the specified element at the beginning.
	 * @param choice 要素 element
	 */
	public void addFirst(Choice choice){
		history_.addFirst(choice);
	}

	/**
	 * 最後に指定された要素を追加します Appends the specified element to the end.
	 * @param choice 要素 element
	 */
	public void addLast(Choice choice){
		history_.addLast(choice);
	}

	/**
	 * 要素を適切な順序で反復するイテレータを返します Returns an iterator over the elements.
	 * @return イテレータ iterator
	 */
	public Iterator<othello.Choice> iterator(){
		return history_.iterator();
	}

	/**
	 * 最初の要素を返します Returns the first element.
	 * @return 最初の要素 first element
	 */
	public Choice getFirst(){
		return history_.getFirst();
	}

	/**
	 * 最後の要素を返します Returns the last element.
	 * @return 最後の要素 last element
	 */
	public Choice getLast(){
		return history_.getLast();
	}

	/**
	 * 最初の要素を削除して返します Removes and returns the first element
	 * @return 最初の要素 first element
	 */
	public Choice removeFirst(){
		return history_.removeFirst();
	}

	/**
	 * 最後の要素を削除して返します Removes and returns the last element.
	 * @return 最後の要素 last element
	 */
	public Choice removeLast(){
		return history_.removeLast();
	}

	/**
	 * 要素の数を返します Returns the number of elements.
	 * @return 要素数 number of elements
	 */
	public int size(){
		return history_.size();
	}

	/**
	 * 要素がない場合に{@code true}を返します Returns {@code true} if history contains no elements
	 * @return 要素がない場合に{@code true} {@code true} if history contains no elements
	 */
	public boolean isEmpty(){
		return history_.isEmpty();
	}

	/**
	 * ディープコピーを返します Returns a deep copy of this
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
