package application;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

import othello.BoardIterator;
import othello.Choice;
import othello.Othello;
import othello.Position;
import othello.Square;

public class OthelloGame {

	private final GridPane boardPane_;

	private Othello othello_;

	public OthelloGame(GridPane boardPane) {
		boardPane_ = boardPane;
	}

	public void start() {
		othello_ = new Othello();
		clearBoard();
		renderBoard();
	}

	public void select(int i, int j) {
		if(othello_ != null){
			Choice choice = new Choice(i, j, othello_.turnColor());
			if(othello_.getChoices().contains(choice)){
				othello_.play(choice);
				clearBoard();
				renderBoard();
			}
		}
	}

	private void clearBoard(){
		Node tmp = boardPane_.getChildren().get(0);
		boardPane_.getChildren().clear();
		boardPane_.getChildren().add(tmp);
	}

	private void renderBoard(){
		BoardIterator<Square> itr = othello_.board().iterator();
		while(itr.hasNext()){
			Position p = itr.position();
			Square s = itr.next();
			if(!s.isEmpty()){
				Color color = s.color().equals(othello.Color.black()) ?
						Color.BLACK : Color.WHITE;
				boardPane_.add(new Circle(22.0, color),	p.i() - 1, p.j() - 1);
			}
		}
	}
}
