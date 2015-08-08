package othello;

public class Choice implements Cloneable {

	private Position position_;

	private Color color_;

	public Choice(Position p, Color c) {
		position_ = p.clone();
		color_ = c.clone();
	}

	public Choice(int i, int j, Color c) {
		this(new Position(i, j), c);
	}

	@Override
	protected Choice clone(){
		try {
			Choice c = (Choice) super.clone();
			c.position_ = position_.clone();
			c.color_ = color_.clone();
			return c;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Position position(){
		return position_.clone();
	}

	public int i(){
		return position_.i();
	}

	public int j(){
		return position_.j();
	}

	public Color color(){
		return color_.clone();
	}
	
	@Override
	public String toString() {
		return position_.toString() + "," + color_.toString();
	}
}
