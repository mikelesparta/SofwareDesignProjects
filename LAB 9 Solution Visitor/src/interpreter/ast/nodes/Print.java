package interpreter.ast.nodes;

import interpreter.ast.visitors.Visitor;

public class Print implements Statement {

	public Expression expression;

	public Print(Expression expression) {
		this.expression = expression;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
