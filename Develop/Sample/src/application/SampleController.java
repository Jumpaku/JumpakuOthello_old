package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class SampleController implements Initializable{
	@FXML
	private Button quitButton_;

	@FXML
	private Button startButton_;

	@FXML
	private GridPane boardPane_;

	private OthelloGame game_;

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
		int i = (int) (Math.floor(e.getX()/48.0) + 1.0);
		int j = (int) (Math.floor(e.getY()/48.0) + 1.0);
		game_.select(i, j);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		game_ = new OthelloGame(boardPane_);
	}
}
