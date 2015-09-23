package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import othello.BoardIterator;
import othello.Othello;
import othello.Position;
import othello.Square;

/**
 * オセロのモデルを監視してシンプルなUIを描画する Observes othello model and draws simple UI
 */
public class SimpleView implements OthelloModel.View{
	/**
	 * 石が置かれるペイン Pane where discs are put
	 */
	private final Pane boardPane_;

	/**
	 * 格子状の直線を持つペイン Pane which has grid lines
	 */
	private final Pane gridPane_;

	/**
	 * ペインを登録するコンストラクタ Constructs with registering panes
	 * @param gridPane
	 * @param boardPane
	 */
	SimpleView(Pane gridPane, Pane boardPane){
		gridPane_ = gridPane;
		boardPane_ = boardPane;
	}

	/**
	 * ヴューを更新する Updates view
	 */
	@Override
	public void update(Othello o){
		draw(o);
	}

	/**
	 * オセロボードを描画する Draws othello board
	 * @param o
	 */
	private void draw(Othello o){
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
