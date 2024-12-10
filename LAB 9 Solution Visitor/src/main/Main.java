package main;

import java.util.ArrayList;
import java.util.List;

import interpreter.RecursiveTreeTraversal;
import interpreter.ast.nodes.Assignment;
import interpreter.ast.nodes.Division;
import interpreter.ast.nodes.IntegerLiteral;
import interpreter.ast.nodes.Print;
import interpreter.ast.nodes.Product;
import interpreter.ast.nodes.Program;
import interpreter.ast.nodes.Read;
import interpreter.ast.nodes.Statement;
import interpreter.ast.nodes.Sum;
import interpreter.ast.nodes.Variable;
import interpreter.ast.visitors.ExecutionVisitor;
import interpreter.ast.visitors.PrintVisitor;

public class Main {

	public static void main(String[] args) {
		// read ancho; read alto; area = alto * ancho / 2; print area + 10;

		List<Statement> statements = new ArrayList<Statement>();

		// read ancho;
		statements.add(new Read(new Variable("ancho")));

		// read alto;
		statements.add(new Read(new Variable("alto")));

		// area = alto * ancho / 2;
		Product prod = new Product(new Variable("alto"), new Variable("ancho"));
		statements.add(new Assignment(new Variable("area"), new Division(prod, new IntegerLiteral("2"))));

		// print area + 10;
		statements.add(new Print(new Sum(new Variable("area"), new IntegerLiteral("10"))));

		Program program = new Program(statements);

		System.out.println("\n--- Recorrido Recursivo: todo en un método");
		RecursiveTreeTraversal recursiveTraversal = new RecursiveTreeTraversal();
		recursiveTraversal.visit(program);

		// No compila
		// System.out.println("\n--- Recorrido ideal: no compila");
		// IdealVersion ideal = new IdealVersion();
		// ideal.visit(program);

		// 1. Lo mismo pero con un visitor
		System.out.println("\n--- Recorrido con el patrón Visitor");
		PrintVisitor printVisitor = new PrintVisitor();
		program.accept(printVisitor, null);

		// 2. Después, otro visitor que interpreta el programa
		System.out.println("\n--- Ejecución con el patrón Visitor");
		ExecutionVisitor executionVisitor = new ExecutionVisitor();
		program.accept(executionVisitor, null);
	}
}
