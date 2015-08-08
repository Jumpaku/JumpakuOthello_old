package othello;

import java.util.ArrayList;

public class Board extends BoardFrame<Square> implements Cloneable{

	public static void main(String[] args) {
		Board b1 = new Board();
		Board b2 = b1.clone();
		b2.get(4, 3).put(Color.random());
		for(int i = 1; i <= 8; ++i){
			System.out.print("|");
			for(int j = 1; j <= 8; ++j){
				Square s = b2.frame_.get(i).get(j);
				System.out.print((s.hasDisc() ? s.disc().color().toString().charAt(0) : " ") + "|");
			}
			System.out.println();
		}
	}

	public Board() {
		for(int i = 0; i < 10; ++i){
			for(int j = 0; j < 10; ++j){
				frame_.get(i).set(j, new Square(i, j));
			}
		}
		get(4, 4).put(Color.dark());
		get(5, 5).put(Color.dark());
		get(4, 5).put(Color.light());
		get(5, 4).put(Color.light());
	}

	@Override
	public Board clone() {
		try {
			Board b = (Board)super.clone();
			b.frame_ = new ArrayList<ArrayList<Square>>();
			for(int i = 0; i < 10; ++i){
				b.frame_.add(new ArrayList<>());
				for(int j = 0; j < 10; ++j){
					b.frame_.get(i).add(frame_.get(i).get(j).clone());
				}
			}
			return b;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
