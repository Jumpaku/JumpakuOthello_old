package application;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

import othello.BoardIterator;
import othello.Choice;
import othello.Othello;
import othello.Position;
import othello.Square;

public class OthelloGame {

	private final Pane boardPane_;

	private Othello othello_;

	public OthelloGame(Pane boardPane) {
		boardPane_ = boardPane;
		start();
	}

	public void start() {
		othello_ = new Othello();
		renderBoard();
	}

	public void select(double i, double j) {
		if(othello_ != null){
			Choice choice = new Choice((int)i, (int)j, othello_.turnColor());
			if(othello_.getChoices().contains(choice)){
				othello_.play(choice);
				renderBoard();
			}
		}
	}

	private void renderBoard(){
		BoardIterator<Square> itr = othello_.board().iterator();
		boardPane_.getChildren().clear();
		while(itr.hasNext()){
			Position p = itr.position();
			Square s = itr.next();
			if(!s.isEmpty()){
				Color color = s.color().equals(othello.Color.black()) ?
						Color.BLACK : Color.WHITE;
				double x = (double)p.j()*50.0 - 25.0;
				double y = (double)p.i()*50.0 - 25.0;
				boardPane_.getChildren().add(new Circle(x, y, 23, color));
			}
		}
	}
}
