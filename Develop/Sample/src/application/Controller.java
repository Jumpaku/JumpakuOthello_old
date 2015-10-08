package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * �v���C���̑�����I�Z���֓`���� Tells operation of player to othello
 */
public class Controller implements Initializable{
	/**
	 * �΂��u�����y�C�� Pane where discs are put
	 */
	@FXML
	private Pane boardPane_;

	/**
	 * �i�q��̒��������y�C�� Pane which has grid lines
	 */
	@FXML
	private Pane gridPane_;

	/**
	 * �I�Z���Q�[�����Ǘ����郂�f�� Model of othello game
	 */
	private OthelloModel game_;

	/**
	 * �I�Z���{�[�h��`�悷�郔���[ View of othello game
	 */
	private OthelloModel.View view_;

	/**
	 * �A�v�����I������ Quits application
	 * @param e
	 */
	@FXML
	private void quit(MouseEvent e){
		Platform.exit();
	}

	/**
	 * �V�����Q�[�����J�n���� Starts a new game
	 * @param e
	 */
	@FXML
	private void start(MouseEvent e){
		game_.start();
	}

	/**
	 * �΂�u���ꏊ��I������ Selects a choice
	 * @param e
	 */
	@FXML
	private void select(MouseEvent e){
		int j = (int)Math.floor(e.getX()/50.0) + 1;
		int i = (int)Math.floor(e.getY()/50.0) + 1;
		game_.select(i, j);
	}

	/**
	 * �A�v�������������� Initializes application
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		view_ = new SimpleView(gridPane_, boardPane_);
		game_ = new OthelloModel(view_);
		game_.start();
	}
}
