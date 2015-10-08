package othello;

/**
 * �ǂ̃}�X�ɉ��F�̐΂�u�����Ƃ����I����\�� Choice of putting disc (where and which color).<br>
 * Choice�͕s��.<br>
 * Choice is immutable.<br>
 * <br>
 * <br>
 * example<br>
 * source : <br>
 * <pre>
 * {@code
 	Choice c1 = new Choice(3, 6, Color.black());
		System.out.println(c1.position());
		System.out.println(c1.i());
		System.out.println(c1.j());
		System.out.println(c1.color());
		System.out.println(c1);
 * }
 * </pre>
 * output : <br>
 * 3,6<br>
 * 3<br>
 * 6<br>
 * B<br>
 * 3,6,B<br>
 */
public final class Choice implements Cloneable {

	/*public static void main(String[] args) {
		Choice c1 = new Choice(3, 6, Color.black());
		System.out.println(c1.position());
		System.out.println(c1.i());
		System.out.println(c1.j());
		System.out.println(c1.color());
		System.out.println(c1);
	}*/

	private Position position_;

	private Color color_;

	/**
	 * �ʒu�ƐF���w�肵�đI���I�u�W�F�N�g���\�z���� Constructs choice with position and color.<br>
	 * �ʒup�܂��͐Fc��{@code null}�̏ꍇ{@link NullPointerException}�𓊂���.<br>
	 * When {@code p == null} or {@code c == null}, {@link NullPointerException} is thrown.
	 * @param p �ʒu position
	 * @param c �F color
	 * @throws NullPointerException �ʒup�܂��͐Fc��{@code null}�̎� When {@code p == null} or  {@code c == null}
	 */
	public Choice(Position p, Color c) {
		position_ = p.clone();
		color_ = c;
	}

	/**
	 * �ʒu�ƐF���w�肵�đI���I�u�W�F�N�g���\�z���� Constructs choice with position and color.<br>
	 * �Fc��{@code null}�̏ꍇ{@link NullPointerException}�𓊂���.<br>
	 * When {@code c == null}, {@link NullPointerException} is thrown.
	 * @param i �s row
	 * @param j �� column
	 * @param c �F color
	 * @throws NullPointerException �Fc��{@code null}�̎� When {@code p == null} or  {@code c == null}
	 */
	public Choice(int i, int j, Color c) {
		this(new Position(i, j), c);
	}

	/**
	 * �I���I�u�W�F�N�g�̃N���[���𐶐����� Creates clone of this choice object.<br>
	 * @return �I���I�u�W�F�N�g�̃N���[�� Clone of this choice
	 */
	@Override
	protected Choice clone(){
		try {
			Choice c = (Choice) super.clone();
			c.position_ = position_.clone();
			return c;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * �I�������̈ʒu�I�u�W�F�N�g�̃R�s�[��Ԃ� Returns copied position of this choice.<br>
	 * @return �ʒu�I�u�W�F�N�g�̃R�s�[ copied position
	 */
	public Position position(){
		return position_.clone();
	}

	/**
	 * �I�������̈ʒu�̍s�ԍ���Ԃ� Returns row number of this choice's position.<br>
	 * @return �ʒu�̍s�ԍ� row number of position
	 */
	public int i(){
		return position_.i();
	}

	/**
	 * �I�������̈ʒu�̗�ԍ���Ԃ� Returns column number of this choice's position.<br>
	 * @return �ʒu�̗�ԍ� column number of position
	 */
	public int j(){
		return position_.j();
	}

	/**
	 * �I�������̐F�I�u�W�F�N�g�̃R�s�[��Ԃ� Returns copied color of this choice.<br>
	 * @return �F�I�u�W�F�N�g�̃R�s�[ copied color
	 */
	public Color color(){
		return color_;
	}

	/**
	 * �I����\���������Ԃ� Returns string which presents a choice.<br>
	 * �ʒu(3,6)�ɍ��Ƃ����I���̏ꍇ{@code "3,6,B"}�Ƃ��������񂪕Ԃ����<br>
	 * For example a choice which position is (3,6) and color is black, string {@code "3,6,B"} is returned.<br>
	 * @return {@code position().toString() + "," + color().toString()};
	 */
	@Override
	public String toString() {
		return position_.toString() + "," + color_.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color_ == null) ? 0 : color_.hashCode());
		result = prime * result + ((position_ == null) ? 0 : position_.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Choice)) {
			return false;
		}
		Choice other = (Choice) obj;
		if (color_ != other.color_) {
			return false;
		}
		if (position_ == null) {
			if (other.position_ != null) {
				return false;
			}
		} else if (!position_.equals(other.position_)) {
			return false;
		}
		return true;
	}


}
