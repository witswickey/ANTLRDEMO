package expressions;

public class Variable extends Expressions {

	String id;
	
	public Variable(String id) {
		this.id=id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id;
	}
}
