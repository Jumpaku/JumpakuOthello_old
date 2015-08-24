package othello;

import java.util.List;

public class Othello implements Cloneable {

	public static void main(String[] args) {
		Othello o = new Othello();
		System.out.println(o.count(Color.black()));

	}

	private History history_ = new History();

	private BoardManager boardManager_ = new BoardManager();

	private boolean hasFinished_ = false;

	private Color turnColor_ = Color.black();

	public Othello() {
		initialize();
	}

	public void initialize() {
		history_ = new History();
		boardManager_ = new BoardManager();
		hasFinished_ = false;
		turnColor_ = Color.black();
	}

	public int count(Color c){
		return (int) boardManager_.board()
				.stream().filter((s)->{return (!s.isEmpty()) && s.color().equals(c);}).count();
	}

	public Board board(){
		return boardManager_.board();
	}

	public Color turnColor(){
		return turnColor_.clone();
	}

	public boolean hasFinished(){
		return hasFinished_;
	}

	public History history(){
		return history_.clone();
	}

	public List<Choice> getChoices(){
		return boardManager_.getChoices(turnColor());
	}

	public List<Position> getReversed(Choice choice){
		return boardManager_.getReversed(choice);
	}

	public void play(Choice choice){
		if(!getChoices().contains(choice)){
			throw new IllegalArgumentException("cannot put because choice is not available");
		}

		List<Position> reversed = getReversed(choice);
		boardManager_.putDisc(choice);
		boardManager_.reverseDiscs(reversed);
		history_.addLast(choice.clone());

		turnColor_ = turnColor_.reversed();
		if(getChoices().isEmpty()){
			turnColor_ = turnColor_.reversed();
			if(getChoices().isEmpty()){
				hasFinished_ = true;
			}
		}
	}

	public void undo(int n){
		for(int i = 0; i < n; ++i){
			history_.removeLast();
		}
		History tmp = history_;
		initialize();
		while(!tmp.isEmpty()){
			play(tmp.removeFirst());
		}
	}

	public void undo(){
		undo(1);
	}

	public boolean wasPassed(){
		return history_.getLast().color().equals(turnColor_);
	}

}
