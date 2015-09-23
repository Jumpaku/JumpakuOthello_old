package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import othello.BoardIterator;
import othello.Othello;
import othello.Position;
import othello.Square;

public class SimpleOthelloView implements OthelloGame.View{

	private final Pane gridPane_;

	private final Pane boardPane_;

	SimpleOthelloView(Pane gridPane, Pane boardPane){
		gridPane_ = gridPane;
		boardPane_ = boardPane;
	}

	@Override
	public void render(Othello o){
		BoardIterator<Square> itr = o.board().iterator();
		boardPane_.getChildren().clear();
		boardPane_.getChildren().add(gridPane_);

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
