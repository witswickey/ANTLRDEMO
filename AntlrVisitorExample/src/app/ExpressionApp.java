package app;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import antlr.ExprLexer;
import antlr.ExprParser;
import expressions.AntlrToProgram;
import expressions.ExpressionsProcessor;
import expressions.Program;

public class ExpressionApp {

	public static void main(String[] args) {
		
		if(args.length!=1) {
			System.err.println("File name missing");
		}
		else {
			String filename=args[0];
			
			ExprParser parser=getParser(filename);
			
			ParseTree ExprAST=parser.prog();
			
			AntlrToProgram pd=new AntlrToProgram();
			
			Program prog=pd.visit(ExprAST);
			
			if(pd.semanticErrors.isEmpty()) {
				
				ExpressionsProcessor expro=new ExpressionsProcessor(prog.expression);
				
				for(String eval:expro.getEvaluationResult()) {
					System.out.println(eval);
				}
			}
			else {
				for(String e:pd.semanticErrors) {
					System.err.println(e);
				}
			}
			
		}

	}

	private static ExprParser getParser(String filename) {

		ExprParser parser = null;

		try {
			CharStream input = CharStreams.fromFileName(filename);
			ExprLexer lex = new ExprLexer(input);
			CommonTokenStream steam = new CommonTokenStream(lex);

			parser = new ExprParser(steam);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return parser;
	}

}
