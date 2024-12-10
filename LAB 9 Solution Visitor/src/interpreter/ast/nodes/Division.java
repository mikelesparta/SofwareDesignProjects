package interpreter.ast.nodes;

import interpreter.ast.visitors.Visitor;

public class Division implements Expression {

	public Expression left, right;

	public Division(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
