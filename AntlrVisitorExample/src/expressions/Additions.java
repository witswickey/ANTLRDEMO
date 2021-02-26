package expressions;

public class Additions extends Expressions {

	Expressions left;
	Expressions right;
	
	public Additions(Expressions l,Expressions r) {
		this.left=l;
		this.right=r;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return left.toString() +" + "+ right.toString();
	}
}
