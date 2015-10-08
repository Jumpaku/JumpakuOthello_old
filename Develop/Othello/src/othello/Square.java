package othello;

/**
 * �I�Z���Ղ̃}�X Square on othello board.<br>
 * �}�X�͂��̃}�X�ɒu����Ă���΂̊Ǘ�������<br>
 * �΂��u����Ă��邩���Ȃ����̊m�F,�u����Ă���΂̐F�̊m�F,�΂�u������,��菜������,���Ԃ����ƂȂǂ��ł���<br>
 * You can check color of disc on the square, check whether square is empty or not, put or remove or reverse a disc on the square.
 *<br>
 *<br>
 *sample<br>
 *source :
 * <pre>
 * {@code
	Color white = Color.white();

	Square s1 = new Square();

	System.out.println(s1.isEmpty() ? "empty" : s1.color());
	s1.put(white);
	System.out.println(s1.isEmpty() ? "empty" : s1.color());
	s1.reverse();
	System.out.println(s1.isEmpty() ? "empty" : s1.color());
	s1.remove();
	System.out.println(s1.isEmpty() ? "empty" : s1.color());
 * }
 * </pre>
 * output : <br>
 * empty<br>
 * W<br>
 * B<br>
 * empty<br>
 */
public class Square implements Cloneable {

	/*public static void main(String[] args) {
		Color white = Color.white();

		Square s1 = new Square();

		System.out.println(s1.isEmpty() ? "empty" : s1.color());
		s1.put(white);
		System.out.println(s1.isEmpty() ? "empty" : s1.color());
		s1.reverse();
		System.out.println(s1.isEmpty() ? "empty" : s1.color());
		s1.remove();
		System.out.println(s1.isEmpty() ? "empty" : s1.color());
	}*/

	private Disc disc_ = null;

	/**
	 * d�̐΂�u������Ԃ̃}�X���\�z���� Constructs square with a disc d.<br>
	 * {@code d == null}�̎��͐΂��u����Ă��Ȃ��}�X���\�z�����<br>
	 * When {@code d == null}, square is constructed without disc.<br>
	 * @param d �u���� disc to put
	 */
	public Square(Disc d){
		if(d != null){
			disc_ = d.clone();
		}
	}

	/**
	 * c�̐F�̐΂�u������Ԃ̃}�X���\�z���� Constructs square with a disc which color is c.<br>
	 * {@code c == null}�̎��͐΂��u����Ă��Ȃ��}�X���\�z�����<br>
	 * When {@code c == null}, square is constructed without disc.<br>
	 * @param c �u���΂̐F color of disc to put
	 */
	public Square(Color c){
		this(c == null ? (Disc)null : new Disc(c));
	}

	/**
	 * �΂��u����Ă��Ȃ��}�X���\�z���� Constructs square without disc.<br>
	 */
	public Square(){
		this((Disc)null);
	}

	/**
	 * s���R�s�[���ă}�X���\�z���� Constructs square with copying.<br>
	 * {@code s == null}�̎�{@link NullPointerException}����������<br>
	 * When {@code s == null}, {@link NullPointerException} is thrown.
	 * @param s �R�s�[���̃}�X source square
	 * @throws NullPointerException {@code s == null}�̎� when {@code s == null}
	 */
	public Square(Square s){
		this(s.disc_);
	}

	/**
	 * �}�X�I�u�W�F�N�g�̃N���[���𐶐����� Clones square object.<br>
	 * @return ���̃}�X�I�u�W�F�N�g�̃N���[�� clone of this square
	 */
	@Override
	public Square clone(){
		Square square;
		try {
			square =  (Square)(super.clone());
			square.disc_ = disc_ == null ? null : disc_.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			square = null;
		}
		return square;
	}

	/**
	 * ���̃}�X�ɐ΂�u�� Puts a disc on this square.<br>
	 * {@code d == null}�̎�{@link NullPointerException}����������<br>
	 * ���ɑ��̐΂��u����Ă���ꍇ�V�������ɒu�������<br>
	 * When {@code d == null}, {@link NullPointerException} is thrown.<br>
	 * If a old disc has already been put on this square, new disc d replaces old one.
	 * @param d �u���� disc to put
	 */
	public void put(Disc d){
		disc_ = d.clone();
	}

	/**
	 * ���̃}�X�ɐFc�̐΂�u�� Puts a disc which color is c on this square.<br>
	 * {@code c == null}�̎�{@link NullPointerException}����������<br>
	 * ���ɑ��̐΂��u����Ă���ꍇ�V�������ɒu�������<br>
	 * When {@code d == null}, {@link NullPointerException} is thrown.<br>
	 * If a old disc has already been put on this square, new disc d replaces old one.
	 * @param c �u���΂̐F color of disc to put
	 */
	public void put(Color c){
		put(new Disc(c));
	}

	/**
	 * ���̃}�X����΂���菜�� Removes a disc from this square.<br>
	 * ���ɐ΂��u����Ă��Ȃ��ꍇ�������Ȃ�<br>
	 * If no disc has already been put on this square, it does nothing.
	 */
	public void remove(){
		disc_ = null;
	}

	/**
	 * �}�X����̎�{@code true}��Ԃ� Returns {@code true} if square is empty.<br>
	 * �}�X�ɐ΂��u����Ă��Ȃ�����{@code true}, �u����Ă��鎞��{@code false}��Ԃ�<br>
	 * If square doesn't have any disc, it returns {@code true}. Otherwise, it returns {@code false}.<br>
	 * @return �}�X����Ȃ�{@code true},�����łȂ��Ȃ�{@code false} {@code true} if square is empty; {@code false} otherwise
	 */
	public boolean isEmpty(){
		return disc_ == null;
	}

	/**
	 * �}�X�����΂𗠕Ԃ� Reverses a disc on this square.<br>
	 * �}�X��̐΂����Ȃ甒��,���Ȃ獕�ɂ���<br>
	 * �}�X��ɐ΂��Ȃ���{@link IllegalStateException}����������<br>
	 * It changes color of disc on this square.<br>
	 * When square is empty, {@link IllegalStateException} is thrown.
	 * @throws IllegalStateException �}�X����̎� when square is empty
	 */
	public void reverse(){
		if(isEmpty()){
			throw new IllegalStateException("cannot reverse because has no disc");
		}
		disc_.reverse();
	}

	/**
	 * �}�X�ɐ΂��u���Ă���ꍇ�΂�Ԃ� If square has a disc, returns it.<br>
	 * �}�X�ɐ΂��u���Ă���ꍇ,���̐΂̃R�s�[���Ԃ����.<br>
	 * �}�X�ɐ΂��u���ĂȂ��ꍇ,{@link IllegalStateException}����������.<br>
	 * If a disc is on the square, it returns a copy of the disc.<br>
	 * Otherwise,{@link IllegalStateException} is thrown.<br>
	 * @return �΂̃R�s�[ copy of disc
	 * @throws IllegalStateException �΂������� when square is empty
	 */
	public Disc disc(){
		if(isEmpty()){
			throw new IllegalStateException("cannot return disc because has no disc");
		}
		return disc_.clone();
	}

	/**
	 * �}�X�ɐ΂��u���Ă���ꍇ�΂̐F��Ԃ� If square has a disc, returns its color.<br>
	 * �}�X�ɐ΂��u���Ă���ꍇ,���̐΂̐F���Ԃ����.<br>
	 * �}�X�ɐ΂��u���ĂȂ��ꍇ,{@link IllegalStateException}����������.<br>
	 * If a disc is on the square, it returns a color of the disc.<br>
	 * Otherwise,{@link IllegalStateException} is thrown.<br>
	 * @return �΂̐F color of disc ({@code disc().color()})
	 * @throws IllegalStateException �΂������� when square is empty
	 */
	public Color color(){
		return disc().color();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disc_ == null) ? 0 : disc_.hashCode());
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
		if (!(obj instanceof Square)) {
			return false;
		}
		Square other = (Square) obj;
		if (disc_ == null) {
			if (other.disc_ != null) {
				return false;
			}
		} else if (!disc_.equals(other.disc_)) {
			return false;
		}
		return true;
	}
}
