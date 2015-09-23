package application;

import othello.Choice;
import othello.Othello;

public class OthelloGame {

	public interface View{
		void render(Othello o);
	}

	View view_;

	private Othello othello_;

	public OthelloGame(View v){
		view_ = v;
	}

	public void start() {
		othello_ = new Othello();
		view_.render(othello_);
	}

	public void select(int i, int j) {
		if(othello_ != null){
			Choice choice = new Choice(i, j, othello_.turnColor());
			if(othello_.getChoices().contains(choice)){
				othello_.play(choice);
				view_.render(othello_);
			}
		}
	}
}
