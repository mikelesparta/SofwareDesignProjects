package interpreter.ast.nodes;

import interpreter.ast.visitors.Visitor;

public class Variable implements Expression {

	public String name;

	public Variable(String name) {
		this.name = name;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
