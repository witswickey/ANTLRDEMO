package expressions;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import antlr.ExprBaseVisitor;
import antlr.ExprParser.AdditionContext;
import antlr.ExprParser.DeclarationContext;
import antlr.ExprParser.MultiplicationContext;
import antlr.ExprParser.NumberContext;
import antlr.ExprParser.VariableContext;

public class AntlrToExpression extends ExprBaseVisitor<Expressions> {

	List<String> vars;
	List<String> sematicErrors;

	public AntlrToExpression(List<String> sematicerr) {
		this.vars = new ArrayList<String>();
		this.sematicErrors = sematicerr;
	}

	@Override
	public Expressions visitDeclaration(DeclarationContext ctx) {

		Token idToken = ctx.ID().getSymbol(); // equivalen to ctx.getChild(0).getSymbol()
		int linenum = idToken.getLine();
		int column = idToken.getCharPositionInLine() + 1;

		String id = ctx.getChild(0).getText();
		if (vars.contains(id)) {
			sematicErrors.add("Error : variable " + id + " already declared at " + linenum + " ," + column);
		} else {
			vars.add(id);
		}

		String type = ctx.getChild(2).getText();
		String value = ctx.NUM().getText();
		int val = Integer.parseInt(value);

		return new VariableDeclaration(id, type, val);
		// return super.visitDeclaration(ctx);
	}

	@Override
	public Expressions visitMultiplication(MultiplicationContext ctx) {
		Expressions left = visit(ctx.getChild(0));
		Expressions right = visit(ctx.getChild(2));

		return new Multiplications(left, right);
	}

	@Override
	public Expressions visitAddition(AdditionContext ctx) {
		Expressions left = visit(ctx.getChild(0));
		Expressions right = visit(ctx.getChild(2));

		return new Additions(left, right);
	}

	@Override
	public Expressions visitVariable(VariableContext ctx) {
		// TODO Auto-generated method stub
		Token idToken = ctx.ID().getSymbol(); // equivalen to ctx.getChild(0).getSymbol()
		int linenum = idToken.getLine();
		int column = idToken.getCharPositionInLine() + 1;

		String id = ctx.getChild(0).getText();
		if (!vars.contains(id)) {
			sematicErrors.add("Error : variable " + id + " not declared at "+linenum+" "+column);
		}
		return new Variable(id);
	}

	@Override
	public Expressions visitNumber(NumberContext ctx) {
		// TODO Auto-generated method stub
		String num = ctx.getChild(0).getText();
		int number = Integer.parseInt(num);
		return new Number(number);
	}

}
