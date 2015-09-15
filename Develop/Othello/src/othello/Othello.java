package othello;

import java.util.Collections;
import java.util.List;

/**
 * �I�Z����1������S�ĊǗ����� Othello controls all of a othello game.
 * �I�������^�[���̐���,�{�[�h�̊Ǘ��Ȃǂ����ׂčs��.<br>
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
	 * �I�Z�������������� Initializes othello.<br>
	 */
	public Othello() {
		initialize();
	}

	/**
	 * �I�Z�������������� Initializes othello.<br>
	 */
	public void initialize() {
		history_ = new History();
		boardManager_ = new BoardManager();
		hasFinished_ = false;
		turnColor_ = Color.black();
	}

	/**
	 * �{�[�h��̎w�肳�ꂽ�F�̐΂̐��𐔂��� Counts the number of discs which has the color c.<br>
	 * @param c ������΂̐F color of disc to count
	 * @return �w�肳�ꂽ�F�̐΂̐� number of discs which has the color c
	 */
	public int count(Color c){
		return (int) boardManager_.board()
				.stream().filter((s)->{return (!s.isEmpty()) && s.color().equals(c);}).count();
	}

	/**
	 * ���݂̃{�[�h��Ԃ� Returns current board.<br>
	 * @return ���݂̃{�[�h current board
	 */
	public Board board(){
		return boardManager_.board();
	}

	/**
	 * ���݂̃^�[���v���C���̐F��Ԃ� Returns current turn player's color.<br>
	 * @return �^�[���v���C���̐F turn player's color
	 */
	public Color turnColor(){
		if(hasFinished()){
			throw new IllegalStateException("game has already finished");
		}
		return turnColor_.clone();
	}

	/**
	 * �Q�[�����I�����Ă���ΐ^,�i�s���Ȃ�U��Ԃ� Returns true if the game has finished, false if it is going.<br>
	 * @return �Q�[�����I�����Ă���ΐ^,�i�s���Ȃ�U true if game has finished; otherwise false
	 */
	public boolean hasFinished(){
		return hasFinished_;
	}

	/**
	 * �Q�[���̗�����Ԃ� Returns history of the game.<br>
	 * @return �Q�[���̗��� history of game
	 */
	public History history(){
		return history_.clone();
	}

	/**
	 * �΂�u�����Ƃ̂ł���I������Ԃ� Returns available choices.<br>
	 * @return �u����I�����̃��X�g list of available choices
	 */
	public List<Choice> getChoices(){
		return boardManager_.getChoices(turnColor());
	}

	/**
	 * choice�̐΂�u�������ɂЂ�����Ԃ����ʒu��Ԃ� Returns list of positions which will be reversed.<br>
	 * @param choice �u���΂̑I��
	 * @return �Ђ�����Ԃ����ʒu�̃��X�g list of positions
	 */
	public List<Position> getReversed(Choice choice){
		return boardManager_.getReversed(choice);
	}

	/**
	 * choice�̐΂�u���ă^�[������������ Play a turn with choice.<br>
	 * <code>choice.position()</code>�ɐ΂�u����,����̐΂��Ђ�����Ԃ�,�^�[���v���C������シ��.<br>
	 * Puts disc at <code>choice.position()</code>, reverses opponent's discs, changes turn player.<br>
	 * @param choice �I�� choice
	 * @throws  {@link IllegalArgumentException} ���p�\�łȂ��I���������� when choice is not available
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
	 * n�^�[�������߂� Undoes n turns.
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
	 * 1�^�[�������߂� Undoes a turn.
	 */
	public void undo(){
		undo(1);
	}

	/**
	 * �p�X������ΐ^��Ԃ� Returns true if pass occurred.<br>
	 * �����O�̃^�[���ƌ��݂̃^�[���Ń^�[���v���C�₪�����Ȃ�^��Ԃ�.<br>
	 * Returns true if previous turn player is same as current turn player.
	 * @return �p�X������ΐ^ true if pass occurred
	 */
	public boolean wasPassed(){
		return history_.size() > 2 && history_.getLast().color().equals(turnColor_);
	}

}
