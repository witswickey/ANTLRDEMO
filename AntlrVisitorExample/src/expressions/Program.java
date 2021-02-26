package expressions;

import java.util.ArrayList;
import java.util.List;

public class Program {
	public List<Expressions> expression;
	
	public Program() {
		this.expression=new ArrayList<>();
	}
	
	public void addExpressions(Expressions e) {
		expression.add(e);
	}
}
