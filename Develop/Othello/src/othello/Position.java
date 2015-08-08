package othello;

public class Position implements Cloneable, Comparable<Position> {

	public static void main(String[] args){
		Position p1 = new Position(-34, 6990);

		Position p2 = end();
		System.out.println(p1.compareTo(p2) + " : " + p1.equals(p2));
		for(Direction d = Direction.begin(); d != Direction.end(); d = Direction.next(d)){
			System.out.println(d.name() + "\t : " + p1.moved(d));
		}
		for(Position p = begin(); p.compareTo(end()) < 0; p = p.next()){
			System.out.println(p);
		}
	}

	private final int i_;

	private final int j_;

	public Position(int i, int j){
		if(i <= 0 || i >= 9 || j <= 0 || j >= 9){
			i_ = 8;
			j_ = 9;
		}
		else{
			i_ = i;
			j_ = j;
		}
	}

	public Position(Position p){
		this(p.i_, p.j_);
	}

	public Position(){
		this(begin());
	}

	@Override
	public Position clone(){
		try {
			return (Position)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean equals(Object obj) {
		Position p = (Position) obj;
		return p == null ? false : i_ == p.i_ && j_ == p.j_;
	}

	public static Position end(){
		return new Position(8, 9);
	}

	public static Position begin(){
		return new Position(1, 1);
	}

	public boolean isEnd(){
		return equals(end());
	}

	public Position next(){
		Position pos = end();

		if(!this.isEnd()){
			if(j_ < 8){
				pos = new Position(i_, j_ + 1);
			}
			else{
				pos = new Position(i_ + 1, 1);
			}
		}

		return pos;
	}

	public Position moved(int movei, int movej){
		return new Position(i_ + movei, j_ + movej);
	}

	public Position moved(Direction dir){
		return moved(dir.movei(), dir.movej());
	}

	public int i(){
		return i_;
	}

	public int j(){
		return j_;
	}

	@Override
	public String toString() {
		return i_ + "," + j_;
	}

	@Override
	public int compareTo(Position p) {
		if(p == null){
			throw new NullPointerException("cannot compare because position is null");
		}
		if(i_ == p.i_){
			return j_ - p.j_;
		}
		else{
			return i_ - p.i_;
		}
	}

}
