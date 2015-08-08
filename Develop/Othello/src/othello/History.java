package othello;

import java.util.LinkedList;

public class History implements Cloneable {

	private LinkedList<Choice> history_ = new LinkedList<Choice>();

	public void addFirst(Choice choice){
		history_.addFirst(choice);
	}

	public void addLast(Choice choice){
		history_.addLast(choice);
	}

	public Choice get(int i){
		return history_.get(i);
	}

	public Choice getFirst(){
		return history_.getFirst();
	}

	public Choice getLast(){
		return history_.getLast();
	}

	public Choice removeFirst(){
		return history_.removeFirst();
	}

	public Choice removeLast(){
		return history_.removeLast();
	}

	public int size(){
		return history_.size();
	}

	public boolean isEmpty(){
		return history_.isEmpty();
	}

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
