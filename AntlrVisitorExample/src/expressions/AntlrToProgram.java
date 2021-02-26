package expressions;

import java.util.ArrayList;
import java.util.List;

import antlr.ExprBaseVisitor;
import antlr.ExprParser.ProgramContext;

public class AntlrToProgram extends ExprBaseVisitor<Program> {

	public List<String> semanticErrors;

	@Override
	public Program visitProgram(ProgramContext ctx) {

		Program prog = new Program();
		semanticErrors = new ArrayList<String>();
		AntlrToExpression exprvisitor = new AntlrToExpression(semanticErrors);// helper visitor for transforming each subtree
		//System.out.println("Ctx is "+ctx);														// to an expression object

		for (int i = 0; i < ctx.getChildCount(); i++) {

			if (i == ctx.getChildCount() - 1) {
				// EOF object

			} else {
				prog.addExpressions(exprvisitor.visit(ctx.getChild(i)));
			}
		}
		return prog;
	}

}
