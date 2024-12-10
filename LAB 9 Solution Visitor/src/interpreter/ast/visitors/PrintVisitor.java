package interpreter.ast.visitors;

import interpreter.ast.nodes.Assignment;
import interpreter.ast.nodes.Division;
import interpreter.ast.nodes.IntegerLiteral;
import interpreter.ast.nodes.Print;
import interpreter.ast.nodes.Product;
import interpreter.ast.nodes.Program;
import interpreter.ast.nodes.Read;
import interpreter.ast.nodes.Sum;
import interpreter.ast.nodes.Variable;

public class PrintVisitor implements Visitor {

	@Override
	public Object visit(Program program, Object param) {
		program.statements.forEach(s -> s.accept(this, null));
		
		return null;
	}

	@Override
	public Object visit(Sum sum, Object param) {
		sum.left.accept(this, null);
		System.out.print(" + ");
		sum.right.accept(this, null);

		return null;
	}

	@Override
	public Object visit(Product product, Object param) {
		product.left.accept(this, null);
		System.out.print(" * ");
		product.right.accept(this, null);

		return null;
	}

	@Override
	public Object visit(Division division, Object param) {
		division.left.accept(this, null);
		System.out.print(" / ");
		division.right.accept(this, null);

		return null;
	}

	@Override
	public Object visit(Assignment assignment, Object param) {
		assignment.variable.accept(this, null);
		System.out.print(" = ");
		assignment.expression.accept(this, null);
		System.out.println(";");

		return null;
	}

	@Override
	public Object visit(Print print, Object param) {
		System.out.print("print ");
		print.expression.accept(this, null);
		System.out.println(";");

		return null;
	}

	@Override
	public Object visit(Read read, Object param) {
		System.out.print("read ");
		read.variable.accept(this, null);
		System.out.println(";");

		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {
		System.out.print(variable.name);

		return null;
	}

	@Override
	public Object visit(IntegerLiteral literal, Object param) {
		System.out.print(literal.value);

		return null;
	}
}
