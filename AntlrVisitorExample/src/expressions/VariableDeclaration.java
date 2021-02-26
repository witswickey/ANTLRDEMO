package expressions;

public class VariableDeclaration extends Expressions {
	public String id;
	public String type;
	public int value;

	public VariableDeclaration(String id, String type, int val) {
		this.id = id;
		this.type = type;
		this.value = val;
	}
}
