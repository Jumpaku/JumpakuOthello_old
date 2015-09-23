package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Controller implements Initializable{
	@FXML
	private Pane boardPane_;

	@FXML
	private Pane gridPane_;

	private OthelloGame game_;

	private OthelloGame.View view_;

	@FXML
	private void quit(MouseEvent e){
		Platform.exit();
	}

	@FXML
	private void start(MouseEvent e){
		game_.start();
	}

	@FXML
	private void select(MouseEvent e){
		int j = (int)Math.floor(e.getX()/50.0) + 1;
		int i = (int)Math.floor(e.getY()/50.0) + 1;
		game_.select(i, j);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		view_ = new SimpleOthelloView(gridPane_, boardPane_);
		game_ = new OthelloGame(view_);
		game_.start();
	}
}
