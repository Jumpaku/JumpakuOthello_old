package application;

import othello.Choice;
import othello.Othello;

/**
 * �I�Z�����Ǘ����ĕύX������΃����[�ɒʒm���� Manages othello model and notifies changes to view
 */
public class OthelloModel {
	/**
	 * �����[�̃C���^�[�t�F�[�X Interface of view
	 */
	public interface View{
		/**
		 * �����[���X�V���� Updates view
		 * @param o
		 */
		void update(Othello o);
	}

	/**
	 * �Q�[�����Ď����郔���[ View which observes this game
	 */
	View view_;

	/**
	 * �I�Z���{�� Main part of othello
	 */
	private Othello othello_;

	/**
	 * �����[��o�^����R���X�g���N�^ Constructor which registers view
	 * @param v
	 */
	public OthelloModel(View v){
		view_ = v;
	}

	/**
	 * �Q�[�����J�n���ă����[�ɕ񍐂��� Starts a game and notifies to view
	 */
	public void start() {
		othello_ = new Othello();
		view_.update(othello_);
	}

	/**
	 * �΂�u���ă����[�ɕ񍐂��� Selects a choice and notifies to view
	 * @param i
	 * @param j
	 */
	public void select(int i, int j) {
		if(othello_ != null){
			Choice choice = new Choice(i, j, othello_.turnColor());
			if(othello_.getChoices().contains(choice)){
				othello_.play(choice);
				view_.update(othello_);
			}
		}
	}
}
