package othello;

/**
 *オセロボード上での方向を定義する Defines directions on othello board.<br>
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
	 * 上 North.
	 */
	N(-1, 0),

	/**
	 * 右上 Northeast.
	 */
	NE(-1, 1),

	/**
	 * 右 East.
	 */
	E(0, 1),

	/**
	 * 右下 Southeast.
	 */
	SE(1, 1),

	/**
	 * 下 South.
	 */
	S(1, 0),

	/**
	 * 左下 Southwest.
	 */
	SW(1, -1),

	/**
	 * 左 West.
	 */
	W(0, -1),

	/**
	 * 左上 Northwest
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
	 * 横方向の移動量を返す Returns movement value of the horizontal direction.<br>
	 * <table border=1>
	 * <caption>方向 と 返り値 Direction and Return</caption>
	 * <tr> <td>{@code N}</td> <td>0</td> </tr>
	 * <tr> <td>{@code NE}</td> <td>1</td> </tr>
	 * <tr> <td>{@code E}</td> <td>1</td> </tr>
	 * <tr> <td>{@code SE}</td> <td>1</td> </tr>
	 * <tr> <td>{@code S}</td> <td>0</td> </tr>
	 * <tr> <td>{@code SW}</td> <td>-1</td> </tr>
	 * <tr> <td>{@code W}</td> <td>-1</td> </tr>
	 * <tr> <td>{@code NW}</td> <td>-1</td> </tr>
	 * </table>
	 * @return 横方向の移動量 movement value of the horizontal direction
	 */
	public int movei(){
		return movei_;
	}

	/**
	 * 縦方向の移動量を返す Returns movement value of the vertical direction.<br>
	 * <table border=1>
	 * <caption>方向 と 返り値 Direction and Return</caption>
	 * <tr> <td>{@code N}</td> <td>-1</td> </tr>
	 * <tr> <td>{@code NE}</td> <td>-1</td> </tr>
	 * <tr> <td>{@code E}</td> <td>0</td> </tr>
	 * <tr> <td>{@code SE}</td> <td>1</td> </tr>
	 * <tr> <td>{@code S}</td> <td>1</td> </tr>
	 * <tr> <td>{@code SW}</td> <td>1</td> </tr>
	 * <tr> <td>{@code W}</td> <td>0</td> </tr>
	 * <tr> <td>{@code NW}</td> <td>-1</td> </tr>
	 * </table>
	 * @return 縦方向の移動量 movement value of the vertical direction
	 */
	public int movej(){
		return movej_;
	}
}
