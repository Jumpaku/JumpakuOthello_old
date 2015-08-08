package othello;

public class Square implements Cloneable {

	public static void main(String[] args) {
		Square[][] squares = new Square[10][10];
		for(int i = 0; i < 10; ++i){
			for(int j = 0; j < 10; ++j){
				squares[i][j] = new Square(new Position(i, j));
			}
		}
		for(int i = 1; i <= 8; ++i){
			for(int j = 1; j <= 8; ++j){
				squares[i][j].put(new Disc(Color.random()));
			}
		}
		squares[5][4].unput();
		squares[4][5].put(new Disc(Color.dark()));
		squares[4][5].reverse();
		squares[5][5] = squares[0][0].clone();
		for(int i = 0; i < 10; ++i){
			for(int j = 0; j < 10; ++j){
				System.out.println(squares[i][j] + " : "
						+ squares[1][1].equals(squares[i][j])
						+ " : " + squares[8][9].equals(squares[i][j])
						+ " : " + squares[i][j].hasDisc());
			}
		}
	}

	private Position position_;

	private Disc disc_ = null;

	public Square(Position p, Disc d){
		position_ = p.clone();
		if(d != null){
			disc_ = d.clone();
		}
	}

	public Square(Position p, Color c){
		this(p, new Disc(c));
	}

	public Square(Position p){
		this(p, (Disc)null);
	}

	public Square(int i, int j){
		this(new Position(i, j), (Disc)null);
	}

	public Square(Square s){
		this(s.position_, s.disc_);
	}

	@Override
	public Square clone(){
		Square square;
		try {
			square =  (Square)(super.clone());
			square.position_ = position_.clone();
			square.disc_ = disc_ == null ? null : disc_.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			square = null;
		}
		return square;
	}

	@Override
	public boolean equals(Object obj) {
		Square s = (Square)obj;
		return position_.equals(s.position_) &&
				((disc_ == null || s.disc_ == null)
						? disc_ == s.disc_ : disc_.equals(s.disc_));
	}

	public void put(Disc d){
		disc_ = d.clone();
	}

	public void put(Color c){
		put(new Disc(c));
	}

	public void unput(){
		disc_ = null;
	}

	public boolean hasDisc(){
		return disc_ != null;
	}

	public void reverse(){
		if(!hasDisc()){
			throw new IllegalStateException("cannot reverse because has no disc");
		}
		disc_.reverse();
	}

	public Position position(){
		return position_.clone();
	}

	public Disc disc(){
		if(!hasDisc()){
			throw new IllegalStateException("cannot return disc because has no disc");
		}
		return disc_.clone();
	}

	public Color color(){
		return disc().color();
	}

	@Override
	public String toString() {
		String p =  position_.toString();
		String c = disc_ == null ? "null" : disc_.color().toString();
		return p + "," + c;
	}
}
