package othello;

public class Disc implements Cloneable{

	public static void main(String[] args){
		Disc d = new Disc(Color.dark());
		Disc l = new Disc(Color.light());
		Disc x = d.clone();
		Disc y = new Disc(x);
		x.reverse();
		System.out.println(d.equals(l) + " : " + x.color() + " : " + y.color());
	}

	private Color color_ = Color.dark();

	public Disc(){
		this(Color.dark());
	}

	public Disc(Color c){
		color_ = c.clone();
	}

	public Disc(Disc disc){
		this(disc.color());
	}

	@Override
	public Disc clone(){
		Disc disc;
		try {
			disc = (Disc) super.clone();
			disc.color_ = color_.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			disc = null;
		}
		return disc;
	}

	@Override
	public boolean equals(Object obj) {
		return obj == null ? false : color_.equals(((Disc)obj).color());
	}

	public void reverse(){
		color_ = color_.reversed();
	}

	public Color color(){
		return color_.clone();
	}

}
