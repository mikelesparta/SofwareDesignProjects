package interpreter.ast.nodes;

import java.util.List;

import interpreter.ast.visitors.Visitor;

public class Program implements Node {

	public List<Statement> statements;

	public Program(List<Statement> program) {
		this.statements = program;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
