package interp;

import parser.Parser;
import parser.ParseException;
import ast.ASTNode;

public class Interpreter {

    public static void main (String[] args) throws ParseException {
        Parser parser = new Parser (System.in);
        ASTNode root  = null;

        while ((root = parser.list()) != null) {
            Evaluator e = new Evaluator();
            System.out.println(root.accept(e));
        }
    }
}
