package othello;

public enum Direction {

	N(-1, 0),
	NE(-1, 1),
	E(0, 1),
	SE(1, 1),
	S(1, 0),
	SW(1, -1),
	W(0, -1),
	NW(-1, -1),
	NO_DIRECTION(0, 0),
	;

	public static void main(String[] args){
		for(Direction d1 = begin(); d1 != end(); d1 = next(d1)){
			System.out.println(d1.name() + "\t: (" + d1.movei() + "," + d1.movej() + ")");
		}
	}

	private final int movei_;

	private final int movej_;

	private Direction(int i, int j){
		movei_ = i;
		movej_ = j;
	}

	public static Direction next(Direction dir) {
		return values()[dir.ordinal() + 1];
	}

	public static Direction begin(){
		return N;
	}

	public static Direction end(){
		return NO_DIRECTION;
	}

	public int movei(){
		return movei_;
	}

	public int movej(){
		return movej_;
	}
}
