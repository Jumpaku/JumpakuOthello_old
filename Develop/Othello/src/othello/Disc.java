package othello;

/**
 * �I�Z���̐� Disc of othello.<br>
 * �I�Z���̐΂�\��.<br>
 * �I�Z���̐΂͍������ǂ��炩�̐F��K������.<br>
 * {@link Disc#reverse()}�Ŏ����Ă���F�𔒂��獕,�܂��͍����甒�ɐ؂�ւ��鎖���ł���.<br>
 * {@link Disc#color}�Ŏ����Ă���F���擾�ł���.<br>
 * Disc expresses disc of othello.<br>
 * Disc has a color which is black or white.<br>
 * The method {@link Disc#reverse()} changes the color of the disc from white into black, or from black into white.
 * The method {@link Disc#color()} returns the color of the disc.<br>
 *<br>
 * example
 * <pre>
 * {@code
public static void main(String[] args){
	Disc d = new Disc(Color.black());
	Disc l = new Disc(Color.white());
	Disc x = d.clone();
	x.reverse();
	System.out.println(d.color() + " : " + l.color());// B : W
	System.out.println(x.equals(l));// true
}
 * }
 * </pre>
 */
public class Disc implements Cloneable{

	/*public static void main(String[] args){
		Disc d = new Disc(Color.black());
		Disc l = new Disc(Color.white());
		Disc x = d.clone();
		x.reverse();
		System.out.println(d.color() + " : " + l.color());// B : W
		System.out.println(x.equals(l));// true
	}*/

	private Color color_ = Color.black();

	/**
	 * �΃I�u�W�F�N�g���\�z���� Constructs disc.<br>
	 * �΂̐F�̓f�t�H���g�ł͍��ɐݒ肳���.<br>
	 * Default color of this disc is set into black.
	 */
	public Disc(){
		this(Color.black());
	}

	/**
	 * �΃I�u�W�F�N�g���\�z��,�F��ݒ肷�� Constructs disc and sets color.<br>
	 * ����color�Ŏw�肳�ꂽ�F���ݒ肳���.<br>
	 * {@code null}���n���ꂽ����{@code NullPointerException}����������.<br>
	 * Color of this object is set to argument color.<br>
	 * When {@code color == null}, {@code NullPointerException} is thrown.
	 * @param color �ݒ肷��F color to set
	 * @throws NullPointerException {@code color == null}�̎� when {@code color == null}
	 */
	public Disc(Color color){
		color_ = new Color(color);
	}

	/**
	 * �΃I�u�W�F�N�g�̃N���[���𐶐����� Creates clone of this disc object.<br>
	 * @return �΃I�u�W�F�N�g�̃N���[�� Clone of this disc
	 */
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

	/**
	 * ���̐΂ƐF�����������ׂ� Indicates whether some other object is same color as this one.<br>
	 * ���̃I�u�W�F�N�g�Ɣ�r����obj��{@link Disc}�łȂ���΂Ȃ�Ȃ�.<br>
	 * obj��{@link Disc}�łȂ���{@link java.lang.ClassCastException}����������.<br>
	 * {@code obj == null}�̎�{@code false}��Ԃ�.<br>
	 * Argument obj must be {@link Disc}.<br>
	 * When obj is not {@link Disc}, {@link java.lang.ClassCastException} is thrown.<br>
	 * If {@code obj == null}, it returns false.
	 * @param obj ���̃I�u�W�F�N�g�Ɣ�ׂ��.
	 * the reference object with which to compare.
	 * @return �F�������Ȃ�{@code true},�Ⴄ�F�Ȃ�{@code false}.
	 * {@code true} if this object has the same color as the obj has; {@code false} otherwise.
	 * @throws java.lang.ClassCastException ����obj��{@link Color}�łȂ���.
	 * When obj is not {@link Disc }.
	 */
	@Override
	public boolean equals(Object obj) {
		return obj == null ? false : color_.equals(((Disc)obj).color());
	}

	/**
	 * �΂𗠕Ԃ� Reverses this disc.<br>
	 * ���̐΂����F�����Ȃ獕��,���Ȃ甒�֊�����.<br>
	 * If this disc has white color, {@link Disc#reverse()} changes into black.<br>
	 * If this disc has black color, {@link Disc#reverse()} changes into white.<br>
	 */
	public void reverse(){
		color_ = color_.reversed();
	}

	/**
	 * ���̐΂������Ă���F��Ԃ� Returns a color of this disc.
	 * @return �΂����F�̃R�s�[ Copied color of this disc.
	 */
	public Color color(){
		return color_.clone();
	}

}
