package interpreter.ast.nodes;

import interpreter.ast.visitors.Visitor;

public interface Node {

	Object accept(Visitor v, Object param);
}
