package expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionsProcessor {

	List<Expressions> list;
	public Map<String,Integer> sym_table;
	
	public ExpressionsProcessor(List<Expressions> list) {
		this.list=list;
		this.sym_table=new HashMap<String, Integer>();
	}
	
	public List<String> getEvaluationResult(){
		List<String> eval=new ArrayList<String>();
		//System.out.println("List is "+list);
		for(Expressions e:list) {
			if(e instanceof VariableDeclaration) {
				VariableDeclaration vp=(VariableDeclaration)e;
				sym_table.put(vp.id, vp.value);
			}
			else {
				String input=e.toString();
				int result=getEvalResult(e);
				eval.add(input+" is "+result);
			}
		}
		
		
		return eval;
	}

	private int getEvalResult(Expressions e) {
		int result=0;
		
		if(e instanceof Number) {
			Number num=(Number) e;
			result =num.num;
		}
		else if (e instanceof Variable) {
			Variable ve=(Variable)e;
			result=sym_table.get(ve.id);
			
		}
		else if (e instanceof Additions) {
			Additions ade=(Additions)e;
			
			int left=getEvalResult(ade.left);
			int right=getEvalResult(ade.right);
			
			result= left+right;
		}
		else if (e instanceof Multiplications) {
			Multiplications ade=(Multiplications)e;
			
			int left=getEvalResult(ade.left);
			int right=getEvalResult(ade.right);
			
			result= left*right;
		}
		
		return result;
	}
}
