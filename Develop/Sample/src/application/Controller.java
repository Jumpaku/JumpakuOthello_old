package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * プレイヤの操作をオセロへ伝える Tells operation of player to othello
 */
public class Controller implements Initializable{
	/**
	 * 石が置かれるペイン Pane where discs are put
	 */
	@FXML
	private Pane boardPane_;

	/**
	 * 格子状の直線を持つペイン Pane which has grid lines
	 */
	@FXML
	private Pane gridPane_;

	/**
	 * オセロゲームを管理するモデル Model of othello game
	 */
	private OthelloModel game_;

	/**
	 * オセロボードを描画するヴュー View of othello game
	 */
	private OthelloModel.View view_;

	/**
	 * アプリを終了する Quits application
	 * @param e
	 */
	@FXML
	private void quit(MouseEvent e){
		Platform.exit();
	}

	/**
	 * 新しいゲームを開始する Starts a new game
	 * @param e
	 */
	@FXML
	private void start(MouseEvent e){
		game_.start();
	}

	/**
	 * 石を置く場所を選択する Selects a choice
	 * @param e
	 */
	@FXML
	private void select(MouseEvent e){
		int j = (int)Math.floor(e.getX()/50.0) + 1;
		int i = (int)Math.floor(e.getY()/50.0) + 1;
		game_.select(i, j);
	}

	/**
	 * アプリを初期化する Initializes application
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		view_ = new SimpleView(gridPane_, boardPane_);
		game_ = new OthelloModel(view_);
		game_.start();
	}
}
