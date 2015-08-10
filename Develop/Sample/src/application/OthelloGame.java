package application;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

import othello.Board;
import othello.Choice;
import othello.Othello;
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
		Board board = othello_.board();
		for(Square s : board){
			if(s.hasDisc()){
				Color color = s.color().equals(othello.Color.black()) ?
						Color.BLACK : Color.WHITE;
				boardPane_.add(new Circle(22.0, color),	s.i() - 1, s.j() - 1);
			}
		}
	}
}
