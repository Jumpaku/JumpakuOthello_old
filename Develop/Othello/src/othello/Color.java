package othello;

import java.util.Random;

/**
*�I�Z���̐F(������) Color of othello.<br>
*�������̂ǂ��炩��\��.<br>
*<br>
*<br>
*sample<br>
*source :
*<pre>
*{@code
	Color white = Color.white;
	Color black = Color.black;
	System.out.println(white + " : " + black); // W : B
	System.out.println(white.reversed().equals(black)); // true
	System.out.println(white.equals(black)); // false
	for(int i = 0; i < 10; ++i){ // WWBWBWWWBB
		System.out.print(Color.random());
	}
*}
*</pre>
*output :
*W : B
*true
*WWBWBWWWBB
*/
public enum Color{
	/**
	 * ���F white.
	 */
	white,
	/**
	 * ���F black
	 */
	black,
	;

	/*
	public static void main(String[] args){
		Color white = Color.white;
		Color black = Color.black;
		System.out.println(white + " : " + black); // W : B
		System.out.println(white.reversed().equals(black)); // true
		System.out.println(white.equals(black)); // false
		for(int i = 0; i < 10; ++i){ // BBWBBWWBBB
			System.out.print(Color.random());
		}
	}
	*/

	/**
	 * ���̃I�u�W�F�N�g�ƕʂ̐F��Ԃ� Returns another color.<br>
	 * ���̃I�u�W�F�N�g������\���Ă���Ȃ甒,����\���Ă���Ȃ獕��Ԃ�.<br>
	 * ���̃I�u�W�F�N�g���͕̂ω����Ȃ�<br>
	 * If this object is white, it returns black object.<br>
	 * If this object is black, it returns white object.<br>
	 * This objects is immutable.<br>
	 * @return {@code this == black ? white : black}
	 * @see Color#black
	 * @see Color#white
	 */
	public Color reversed(){
		return this == black ? white : black;
	}

	/**
	 * �����_���ɃJ���[�I�u�W�F�N�g�𐶐����� Create random color.<br>
	 * �m���͍���50%, ����50%.<br>
	 * ���������A���S���Y���͐��`�����@.<br>
	 * Probability : black - 50%, white - 50%.<br>
	 * Algorithm : LCG.<br>
	 * @return �����_���ɍ�����. random color black or white.{@code new Random().nextBoolean() ? white() : black();}
	 */
	public static Color random(){
		return new Random().nextBoolean() ? white : black;
	}

	/**
	 * �F��\���������Ԃ� Returns string which presents a color.<br>
	 * �������̃I�u�W�F�N�g�����Ȃ�{@code "B"},���Ȃ�{@code "W"}���Ԃ����.<br>
	 * If this object is black, the returned string is {@code "B"}.<br>
	 * If this object is white, the returned string is {@code "W"}.<br>
	 * @return equals(black()) ? "B" : "W";
	 */
	//@Override
	public String toString() {
		return this == black ? "B" : "W";
	}
}
