package othello;

import java.util.Random;

public class Color implements Cloneable{

	public static void main(String[] args){
		Color c1 = new Color(Color.light());
		Color c2;
		c2 = c1.clone().reversed();
		System.out.println(c2.equals(c1));
		for(int i = 0; i < 10; ++i){
			System.out.println(Color.random());
		}
	}

	private enum C{
		DARK,
		LIGHT,
	}

	private final C color_;

	private Color(C color) {
		color_ = color;
	}

	public Color(Color color) {
		this(color.color_);
	}

	public Color() {
		this(dark());
	}

	@Override
	public Color clone(){
		try{
			return (Color) super.clone();
		}
		catch(CloneNotSupportedException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean equals(Object obj) {
		return obj == null ? false : ((Color)obj).color_ == color_;
	}

	public Color reversed(){
		return equals(dark()) ? light() : dark();
	}

	public static Color dark(){
		return new Color(C.DARK);
	}

	public static Color light(){
		return new Color(C.LIGHT);
	}

	public static Color random(){
		return new Random().nextBoolean() ? light() : dark();
	}

	@Override
	public String toString() {
		return equals(dark()) ? "DARK" : "LIGHT";
	}

}
