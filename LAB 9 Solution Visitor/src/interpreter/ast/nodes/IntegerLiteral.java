package interpreter.ast.nodes;

import interpreter.ast.visitors.Visitor;

public class IntegerLiteral implements Expression {

	public String value;

	public IntegerLiteral(String value) {
		this.value = value;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
