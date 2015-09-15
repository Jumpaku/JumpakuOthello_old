package othello;

import java.util.Collections;
import java.util.List;

/**
 * オセロの1試合を全て管理する Othello controls all of a othello game.
 * 終了判定やターンの制御,ボードの管理などをすべて行う.<br>
 * It controls closing detection, controls turn transition, manages board.<br>
 */
public class Othello implements Cloneable {

	/*public static void main(String[] args) {
		Othello o = new Othello();
		while(!o.hasFinished()){
			List<Choice> choices = o.getChoices();
			Collections.shuffle(choices);
			o.play(choices.get(0));
			for(int i = 1; i <= 8; ++i){
				System.out.print("|");
				for(int j = 1; j <= 8; ++j){
					Square s = o.board().get(i,j);
					System.out.print((s.isEmpty() ? " " : s.color()) + "|");
				}
				System.out.println();
			}
		}
	}*/

	private History history_ = new History();

	private BoardManager boardManager_ = new BoardManager();

	private boolean hasFinished_ = false;

	private Color turnColor_ = Color.black();

	/**
	 * オセロを初期化する Initializes othello.<br>
	 */
	public Othello() {
		initialize();
	}

	/**
	 * オセロを初期化する Initializes othello.<br>
	 */
	public void initialize() {
		history_ = new History();
		boardManager_ = new BoardManager();
		hasFinished_ = false;
		turnColor_ = Color.black();
	}

	/**
	 * ボード上の指定された色の石の数を数える Counts the number of discs which has the color c.<br>
	 * @param c 数える石の色 color of disc to count
	 * @return 指定された色の石の数 number of discs which has the color c
	 */
	public int count(Color c){
		return (int) boardManager_.board()
				.stream().filter((s)->{return (!s.isEmpty()) && s.color().equals(c);}).count();
	}

	/**
	 * 現在のボードを返す Returns current board.<br>
	 * @return 現在のボード current board
	 */
	public Board board(){
		return boardManager_.board();
	}

	/**
	 * 現在のターンプレイヤの色を返す Returns current turn player's color.<br>
	 * @return ターンプレイヤの色 turn player's color
	 */
	public Color turnColor(){
		if(hasFinished()){
			throw new IllegalStateException("game has already finished");
		}
		return turnColor_.clone();
	}

	/**
	 * ゲームが終了していれば真,進行中なら偽を返す Returns true if the game has finished, false if it is going.<br>
	 * @return ゲームが終了していれば真,進行中なら偽 true if game has finished; otherwise false
	 */
	public boolean hasFinished(){
		return hasFinished_;
	}

	/**
	 * ゲームの履歴を返す Returns history of the game.<br>
	 * @return ゲームの履歴 history of game
	 */
	public History history(){
		return history_.clone();
	}

	/**
	 * 石を置くことのできる選択肢を返す Returns available choices.<br>
	 * @return 置ける選択肢のリスト list of available choices
	 */
	public List<Choice> getChoices(){
		return boardManager_.getChoices(turnColor());
	}

	/**
	 * choiceの石を置いた時にひっくり返される位置を返す Returns list of positions which will be reversed.<br>
	 * @param choice 置く石の選択
	 * @return ひっくり返される位置のリスト list of positions
	 */
	public List<Position> getReversed(Choice choice){
		return boardManager_.getReversed(choice);
	}

	/**
	 * choiceの石を置いてターンを完了する Play a turn with choice.<br>
	 * <code>choice.position()</code>に石を置いて,相手の石をひっくり返し,ターンプレイヤを交代する.<br>
	 * Puts disc at <code>choice.position()</code>, reverses opponent's discs, changes turn player.<br>
	 * @param choice 選択 choice
	 * @throws  {@link IllegalArgumentException} 利用可能でない選択をした時 when choice is not available
	 */
	public void play(Choice choice){
		if(!getChoices().contains(choice)){
			throw new IllegalArgumentException("cannot put because choice is not available");
		}

		List<Position> reversed = getReversed(choice);
		boardManager_.putDisc(choice);
		boardManager_.reverseDiscs(reversed);
		history_.addLast(choice.clone());

		turnColor_ = turnColor_.reversed();
		if(getChoices().isEmpty()){
			turnColor_ = turnColor_.reversed();
			if(getChoices().isEmpty()){
				hasFinished_ = true;
			}
		}
	}

	/**
	 * nターン巻き戻す Undoes n turns.
	 * @param n
	 */
	public void undo(int n){
		for(int i = 0; i < n; ++i){
			history_.removeLast();
		}
		History tmp = history_;
		initialize();
		while(!tmp.isEmpty()){
			play(tmp.removeFirst());
		}
	}

	/**
	 * 1ターン巻き戻す Undoes a turn.
	 */
	public void undo(){
		undo(1);
	}

	/**
	 * パスがあれば真を返す Returns true if pass occurred.<br>
	 * もし前のターンと現在のターンでターンプレイやが同じなら真を返す.<br>
	 * Returns true if previous turn player is same as current turn player.
	 * @return パスがあれば真 true if pass occurred
	 */
	public boolean wasPassed(){
		return history_.size() > 2 && history_.getLast().color().equals(turnColor_);
	}

}
