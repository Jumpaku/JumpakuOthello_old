package othello;

import java.util.Random;

/**
*�I�Z���̐F(������) Color of othello.<br>
*�I�Z���̐F,�K����������2�F�����̂ǂ��炩�ƂȂ�.<br>
*{@link Color#black()}�܂���{@link Color#white()}���g���ĐF�𓾂鎖���ł���.<br>
*�R���X�g���N�^�ŐF���w�肵�Ȃ��ꍇ�͍��ƂȂ�.
*Color in othello must be black or white.<br>
*You can get color using {@link Color#black()} or {@link Color#white()}.<br>
*{@link Color#Color()} constructs black color.<br>
*<br>
*
*example
*<pre>
*{@code
public static void main(String[] args){
	Color white = Color.white();
	Color black = Color.black();
	System.out.println(white + " : " + black); // W : B
	System.out.println(white.reversed().equals(black)); // true
	System.out.println(white.equals(black)); // false
	for(int i = 0; i < 10; ++i){ // BBWBBWWBBB
		System.out.print(Color.random());
	}
}
*}
*</pre>
*/
public class Color implements Cloneable{

	/*public static void main(String[] args){
		Color white = Color.white();
		Color black = Color.black();
		System.out.println(white + " : " + black); // W : B
		System.out.println(white.reversed().equals(black)); // true
		System.out.println(white.equals(black)); // false
		for(int i = 0; i < 10; ++i){ // BBWBBWWBBB
			System.out.print(Color.random());
		}
	}*/

	private enum C{
		BLACK,
		WHITE,
	}

	private final C color_;

	private Color(C color) {
		color_ = color;
	}

	/**
	 * �J���[�I�u�W�F�N�g���\�z���� Constructs color.<br>
	 * �f�t�H���g�ł͍��ɂȂ�.<br>
	 * Default color is black.<br>
	 */
	public Color() {
		this(black().color_);
	}

	/**
	 * �J���[�I�u�W�F�N�g�̃N���[���𐶐����� Creates clone of this color object.<br>
	 * @return �J���[�I�u�W�F�N�g�̃N���[�� Clone of this color
	 */
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

	/**
	 * ���̐F�Ɠ����F�����ׂ� Indicates whether some other object is equal to this one.<br>
	 * ���̃I�u�W�F�N�g�Ɣ�r����obj��{@link Color}�łȂ���΂Ȃ�Ȃ�.<br>
	 * obj��{@link Color}�łȂ���{@link java.lang.ClassCastException}����������.<br>
	 * {@code obj == null}�̎�{@code false}��Ԃ�.<br>
	 * Argument obj must be {@link Color}.<br>
	 * When obj is not {@link Color}, {@link java.lang.ClassCastException} is thrown.<br>
	 * If {@code obj == null}, it returns false.
	 * @param obj ���̃I�u�W�F�N�g�Ɣ�ׂ�F.
	 * the reference object with which to compare.
	 * @return �����F��\���Ȃ�{@code true},�Ⴄ�F�Ȃ�{@code false}.
	 * {@code true} if this object is the same color as the obj; {@code false} otherwise.
	 * @throws java.lang.ClassCastException ����obj��{@link Color}�łȂ���.
	 * When obj is not {@link Color}.
	 */
	@Override
	public boolean equals(Object obj) {
		return obj == null ? false : ((Color)obj).color_ == color_;
	}

	/**
	 * ���΂̐F��Ԃ� Returns reversed color.
	 * ���̃I�u�W�F�N�g������\���Ă���Ȃ甒,����\���Ă���Ȃ獕��Ԃ�.<br>
	 * If this object is white, it returns black object.<br>
	 * If this object is black, it returns white object.<br>
	 * @return {@code equals(black()) ? white() : black()}
	 * @see Color#black()
	 * @see Color#white()
	 */
	public Color reversed(){
		return equals(black()) ? white() : black();
	}

	/**
	 * ���̃J���[�I�u�W�F�N�g��Ԃ� Returns black.
	 * @return �� black color
	 */
	public static Color black(){
		return new Color(C.BLACK);
	}

	/**
	 * ���̃J���[�I�u�W�F�N�g��Ԃ� Returns white.
	 * @return �� white color
	 */
	public static Color white(){
		return new Color(C.WHITE);
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
		return new Random().nextBoolean() ? white() : black();
	}

	/**
	 * �F��\���������Ԃ� Returns string which presents a color.<br>
	 * �������̃I�u�W�F�N�g�����Ȃ�{@code "B"},���Ȃ�{@code "W"}���Ԃ����.<br>
	 * If this object is black, the returned string is {@code "B"}.<br>
	 * If this object is white, the returned string is {@code "W"}.<br>
	 * @return equals(black()) ? "B" : "W";
	 */
	@Override
	public String toString() {
		return equals(black()) ? "B" : "W";
	}

}
