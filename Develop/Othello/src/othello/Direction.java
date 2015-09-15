package othello;

/**
 *�I�Z���{�[�h��ł̕������`���� Defines directions on othello board.<br>
 *<br>
 *example
 *<pre>
 *{@code
public static void main(String[] args){
	for(Direction d : Direction.values()){
		System.out.println(d.name() + " : (" + d.movei() + "," + d.movej() + ")");
	}
	// output :
	//N : (-1,0)
	//NE : (-1,1)
	//E : (0,1)
	//SE : (1,1)
	//S : (1,0)
	//SW : (1,-1)
	//W : (0,-1)
	//NW : (-1,-1)
}
 *}
 *</pre>
 */
public enum Direction {

	/**
	 * �� North.
	 */
	N(-1, 0),

	/**
	 * �E�� Northeast.
	 */
	NE(-1, 1),

	/**
	 * �E East.
	 */
	E(0, 1),

	/**
	 * �E�� Southeast.
	 */
	SE(1, 1),

	/**
	 * �� South.
	 */
	S(1, 0),

	/**
	 * ���� Southwest.
	 */
	SW(1, -1),

	/**
	 * �� West.
	 */
	W(0, -1),

	/**
	 * ���� Northwest
	 */
	NW(-1, -1),
	;

	/*public static void main(String[] args){
		for(Direction d : Direction.values()){
			System.out.println(d.name() + " : (" + d.movei() + "," + d.movej() + ")");
		}
		// output :
		//N : (-1,0)
		//NE : (-1,1)
		//E : (0,1)
		//SE : (1,1)
		//S : (1,0)
		//SW : (1,-1)
		//W : (0,-1)
		//NW : (-1,-1)
	}*/

	private final int movei_;

	private final int movej_;

	private Direction(int i, int j){
		movei_ = i;
		movej_ = j;
	}

	/**
	 * �������̈ړ��ʂ�Ԃ� Returns movement value of the horizontal direction.<br>
	 * <table border=1>
	 * <caption>���� �� �Ԃ�l Direction and Return</caption>
	 * <tr> <td>{@code N}</td> <td>0</td> </tr>
	 * <tr> <td>{@code NE}</td> <td>1</td> </tr>
	 * <tr> <td>{@code E}</td> <td>1</td> </tr>
	 * <tr> <td>{@code SE}</td> <td>1</td> </tr>
	 * <tr> <td>{@code S}</td> <td>0</td> </tr>
	 * <tr> <td>{@code SW}</td> <td>-1</td> </tr>
	 * <tr> <td>{@code W}</td> <td>-1</td> </tr>
	 * <tr> <td>{@code NW}</td> <td>-1</td> </tr>
	 * </table>
	 * @return �������̈ړ��� movement value of the horizontal direction
	 */
	public int movei(){
		return movei_;
	}

	/**
	 * �c�����̈ړ��ʂ�Ԃ� Returns movement value of the vertical direction.<br>
	 * <table border=1>
	 * <caption>���� �� �Ԃ�l Direction and Return</caption>
	 * <tr> <td>{@code N}</td> <td>-1</td> </tr>
	 * <tr> <td>{@code NE}</td> <td>-1</td> </tr>
	 * <tr> <td>{@code E}</td> <td>0</td> </tr>
	 * <tr> <td>{@code SE}</td> <td>1</td> </tr>
	 * <tr> <td>{@code S}</td> <td>1</td> </tr>
	 * <tr> <td>{@code SW}</td> <td>1</td> </tr>
	 * <tr> <td>{@code W}</td> <td>0</td> </tr>
	 * <tr> <td>{@code NW}</td> <td>-1</td> </tr>
	 * </table>
	 * @return �c�����̈ړ��� movement value of the vertical direction
	 */
	public int movej(){
		return movej_;
	}
}
