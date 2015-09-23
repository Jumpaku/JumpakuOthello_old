package application;

import othello.Choice;
import othello.Othello;

/**
 * オセロを管理して変更があればヴューに通知する Manages othello model and notifies changes to view
 */
public class OthelloModel {
	/**
	 * ヴューのインターフェース Interface of view
	 */
	public interface View{
		/**
		 * ヴューを更新する Updates view
		 * @param o
		 */
		void update(Othello o);
	}

	/**
	 * ゲームを監視するヴュー View which observes this game
	 */
	View view_;

	/**
	 * オセロ本体 Main part of othello
	 */
	private Othello othello_;

	/**
	 * ヴューを登録するコンストラクタ Constructor which registers view
	 * @param v
	 */
	public OthelloModel(View v){
		view_ = v;
	}

	/**
	 * ゲームを開始してヴューに報告する Starts a game and notifies to view
	 */
	public void start() {
		othello_ = new Othello();
		view_.update(othello_);
	}

	/**
	 * 石を置いてヴューに報告する Selects a choice and notifies to view
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
