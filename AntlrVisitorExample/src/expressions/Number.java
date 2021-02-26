package expressions;

public class Number extends Expressions {
	int num;
	
	public Number(int num) {
		this.num=num;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new Integer(num).toString();
	}
}
