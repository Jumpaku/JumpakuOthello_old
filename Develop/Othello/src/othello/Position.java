package othello;

/**
 * �I�Z���{�[�h��ł̈ʒu��\�� Position on othello board.<br>
 * {@link Position}�͕s��.<br>
 * �܂��Ō�̈ʒu(8,8)�̎��̃_�~�[�ʒu(8,9)�����݂���.<br>
 *
 * <table border=1>
 * <caption>position on a board</caption>
 * <tr> <td></td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td> </tr>
 * <tr> <td>1</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>2</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>3</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>4</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>5</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>6</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>7</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * <tr> <td>8</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td> </tr>
 * </table>
 *<br>
 *<br>
 *sample<br>
 *source :
 * <pre>
 * {@code
	Position x = new Position(3, 6);
	Position y = x.clone();
	Position z = new Position(7, 4);
	System.out.println(x.compareTo(y) + " : " + x.equals(y));
	System.out.println(x.compareTo(z) + " : " + x.equals(z));
	System.out.println(z.compareTo(y) + " : " + z.equals(y));

	Position p = new Position(8, 1);
	for(Direction d : Direction.values()){
		System.out.print(d.name() + " " + p.moved(d) + " : ");
	}
	System.out.println();
	for(Position i = Position.begin(); i.compareTo(Position.end()) < 0; i = i.next()){
		System.out.print("(" + i + ")");
	}
 * }
 * </pre>
 * output : <br>
 * 0 : true<br>
 * -4 : false<br>
 * 4 : false<br>
 * N 7,1 : NE 7,2 : E 8,2 : SE 8,9 : S 8,9 : SW 8,9 : W 8,9 : NW 8,9 :<br>
 * (1,1)(1,2)(1,3)(1,4)...(8,1)(8,2)(8,3)(8,4)(8,5)(8,6)(8,7)(8,8)
 */
public final class Position implements Cloneable, Comparable<Position> {

	/*public static void main(String[] args){
		Position x = new Position(3, 6);
		Position y = x.clone();
		Position z = new Position(7, 4);
		System.out.println(x.compareTo(y) + " : " + x.equals(y));
		System.out.println(x.compareTo(z) + " : " + x.equals(z));
		System.out.println(z.compareTo(y) + " : " + z.equals(y));

		Position p = new Position(8, 1);
		for(Direction d : Direction.values()){
			System.out.print(d.name() + " " + p.moved(d) + " : ");
		}
		System.out.println();
		for(Position i = Position.begin(); i.compareTo(Position.end()) < 0; i = i.next()){
			System.out.print("(" + i + ")");
		}
	}*/

	private final int i_;

	private final int j_;

	/**
	 * �ʒu�I�u�W�F�N�g�𐶐����s�Ɨ��ݒ肷�� Constructs position object and sets row and column.
	 * ����i�܂���j��1�ȏ�8�ȉ��łȂ��ꍇ�_�~�[�ʒu(8,9)�ɐݒ肳���.
	 * @param i �s row ({@code 1 <= i && i <= 8})
	 * @param j �� column ({@code 1 <= j && j <= 8})
	 */
	public Position(int i, int j){
		if(i <= 0 || i >= 9 || j <= 0 || j >= 9){
			i_ = 8;
			j_ = 9;
		}
		else{
			i_ = i;
			j_ = j;
		}
	}

	/**
	 * �ʒu�I�u�W�F�N�g���R�s�[���č\�z���� Copies and Constructs position.
	 * ����p�Ɠ����ʒu��\���ʒu�I�u�W�F�N�g���\�z����R�s�[�R���X�g���N�^.<br>
	 * ������{@code null}�̎���{@link NullPointerException}����������.<br>
	 * This is a copy constructor copies argument p.<br>
	 * When p is {@code null}, {@link NullPointerException} is thrown.
	 * @param p �R�s�[���̈ʒu�I�u�W�F�N�g source position
	 * @throws NullPointerException when {@code p == null}
	 */
	public Position(Position p){
		this(p.i_, p.j_);
	}

	/**
	 * �ʒu�I�u�W�F�N�g���\�z���� Constructs position object.<br>
	 * �f�t�H���g�ł�(1,1)�ɐݒ肳���.<br>
	 * Default position is (1,1)
	 */
	public Position(){
		this(begin());
	}

	/**
	 * �ʒu�I�u�W�F�N�g�̃N���[���𐶐����� Creates clone of this position object.<br>
	 * @return �ʒu�I�u�W�F�N�g�̃N���[�� Clone of this position
	 */
	@Override
	public Position clone(){
		try {
			return (Position)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ���̈ʒu�Ɠ����ʒu�����ׂ� Indicates whether some other object is equal to this one.<br>
	 * ���̃I�u�W�F�N�g�Ɣ�r����obj��{@link Position}�łȂ���΂Ȃ�Ȃ�.<br>
	 * obj��{@link Position}�łȂ���{@link java.lang.ClassCastException}����������.<br>
	 * {@code obj == null}�̎�{@code false}��Ԃ�.<br>
	 * Argument obj must be {@link Position}.<br>
	 * When obj is not {@link Position}, {@link java.lang.ClassCastException} is thrown.<br>
	 * If {@code obj == null}, it returns false.
	 * @param obj ���̃I�u�W�F�N�g�Ɣ�ׂ�ʒu the reference object with which to compare.
	 * @return �����ʒu��\���Ȃ�{@code true},�Ⴄ�ʒu�Ȃ�{@code false} {@code true} if this object is the same color as the obj; {@code false} otherwise.
	 * @throws java.lang.ClassCastException ����obj��{@link Position}�łȂ��� When obj is not {@link Position}.
	 */
	@Override
	public boolean equals(Object obj) {
		Position p = (Position) obj;
		return p == null ? false : i_ == p.i_ && j_ == p.j_;
	}

	/**
	 * �Ō�̎��̃_�~�[�ʒu��Ԃ� Returns dummy.
	 * �Ō�̈ʒu(8,8)�̎��̃_�~�[�ʒu(8,9)��Ԃ�.<br>
	 * Returned dummy is next position of last position.<br>
	 * Last position is (8,8), dummy is (8,9).
	 * @return {@code new Position(8, 9)}
	 */
	public static Position end(){
		return new Position(8, 9);
	}

	/**
	 * �ŏ��̈ʒu��Ԃ� Returns first position.
	 * �ŏ��̈ʒu(1,1)��Ԃ�.<br>
	 * First position is (1,1)
	 * @return {@code new Position(1, 1)}
	 */
	public static Position begin(){
		return new Position(1, 1);
	}

	/**
	 * �Ō�̎��̈ʒu�����ׂ� Indicates whether this position is next of last.
	 * @return {@code equals(end())}
	 */
	public boolean isEnd(){
		return equals(end());
	}

	/**
	 * ���̈ʒu�𐶐����� Creates next position.<br>
	 * ������E��,�ォ�牺�ւ̏��ԂŎ��̈ʒu��\���I�u�W�F�N�g�𐶐�����.<br>
	 * ���̎�,���̈ʒu�I�u�W�F�N�g�͕ω����Ȃ�.<br>
	 * �������̈ʒu�I�u�W�F�N�g���Ō�̎��̃_�~�[�ʒu�Ȃ�{@code Position#end()}��Ԃ�.<br>
	 * New position which is next of this is created.<br>
	 * The order is from left to right, from top to bottom.<br>
	 * This object is not changed.<br>
	 * If this is (8,9), returns {@code Position#end()}
	 * @return ���̈ʒu next position
	 */
	public Position next(){
		Position pos = end();

		if(!this.isEnd()){
			if(j_ < 8){
				pos = new Position(i_, j_ + 1);
			}
			else{
				pos = new Position(i_ + 1, 1);
			}
		}

		return pos;
	}

	/**
	 * �ړ��������ʒu�I�u�W�F�N�g�������� Creates moved position.<br>
	 * �s������movei,�������movej�ړ��������ʒu�I�u�W�F�N�g�𐶐�����.<br>
	 * ���̎�,���̈ʒu�I�u�W�F�N�g�͕ω����Ȃ�.<br>
	 * �ړ�����������,�s�܂��͗�0�ȉ��܂���9�ȏ�ɂȂ�ꍇ{@code Position#end()}���Ԃ����.<br>
	 * Created position is moved horizontally by movei, vertically by movej from this position.<br>
	 * This object is not changed.<br>
	 * At the result of movement, if row or column are grater than 8 or less than 1, returned position is {@code Position#end()}.
	 * @param movei �s�����̈ړ��� amount of horizontal movement
	 * @param movej ������̈ړ��� amount of vertical movement
	 * @return �ړ��������ʒu�I�u�W�F�N�g moved position
	 */
	public Position moved(int movei, int movej){
		return new Position(i_ + movei, j_ + movej);
	}

	/**
	 * �ړ��������ʒu�I�u�W�F�N�g�������� Creates moved position.<br>
	 * dir�̕�����1�}�X�����ړ��������ʒu�I�u�W�F�N�g��������.<br>
	 * ���̎�,���̈ʒu�I�u�W�F�N�g�͕ω����Ȃ�.<br>
	 * �ړ�����������,�s�܂��͗�0�ȉ��܂���9�ȏ�ɂȂ�ꍇ{@code Position#end()}���Ԃ����.<br>
	 * Created position is moved by 1 square from this position on the direction dir.
	 * This object is not changed.<br>
	 * At the result of movement, if row or column are grater than 8 or less than 1, returned position is {@code Position#end()}.
	 * @param dir �ړ���������� direction to move
	 * @return �ړ��������ʒu�I�u�W�F�N�g moved position
	 */
	public Position moved(Direction dir){
		return moved(dir.movei(), dir.movej());
	}

	/**
	 * �s���ł���i������Ԃ� Returns row number i.
	 * @return �s�� row number
	 */
	public int i(){
		return i_;
	}

	/**
	 * �񐔂ł���j������Ԃ� Returns column number j.
	 * @return �� column number
	 */
	public int j(){
		return j_;
	}

	/**
	 * �ʒu�̕������Ԃ� Returns string of position
	 * �Ⴆ�Έʒu(4,5)�̕������{@code "4,5"}�ƂȂ�.<br>
	 * For example position (4,5), returned string is {@code "4,5"}.
	 * @return �ʒu�̕����� string of position<br>
	 * (i,j) {@literal -->} {@code "i,j"}
	 */
	@Override
	public String toString() {
		return i_ + "," + j_;
	}

	/**
	 * �ʒu�I�u�W�F�N�g�̑召���r���� Compares this object with the specified object for order.
	 * ������E��,�ォ�牺�ւ̏��ő傫���Ȃ�.<br>
	 * �Ⴆ��x,y��(3,6),z��(7,4)�̎�{@code x.compareTo(y) == 0},{@code x.compareTo(z) < 0},{@code z.compareTo(y) > 0}�ƂȂ�.<br>
	 * ����p��{@code null}�̎�NullPointerException����������.<br>
	 * The order is from left to right, from top to bottom.<br>
	 * For example x,y are (3,6),z is (7,4), the results are {@code x.compareTo(y) == 0},{@code x.compareTo(z) < 0},{@code z.compareTo(y) > 0}
	 * When argument p is {@code null}, NullPointerException is thrown.
	 * @return i() == p.i() ? j() - p.j() : i() - p.i()
	 * @throws NullPointerException {@code p == null}�̎� when {@code p == null}
	 */
	@Override
	public int compareTo(Position p) {
		if(p == null){
			throw new NullPointerException("cannot compare because position is null");
		}

		return i_ == p.i_ ? j_ - p.j_ : i_ - p.i_;
	}

}
