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

public interface Visitor {

	Object visit(Program program, Object param);

	Object visit(Assignment program, Object param);

	Object visit(Sum program, Object param);

	Object visit(Product program, Object param);

	Object visit(Division program, Object param);

	Object visit(Print program, Object param);

	Object visit(Read program, Object param);

	Object visit(Variable program, Object param);

	Object visit(IntegerLiteral program, Object param);
}
