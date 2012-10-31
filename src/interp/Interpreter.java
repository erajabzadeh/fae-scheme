package interp;

import parser.Parser;
import parser.ParseException;
import ast.ASTNode;
import ast.ASTPrinter;

public class Interpreter {

    public static void main (String[] args) throws ParseException {
        Parser parser = new Parser (System.in);
        ASTNode root  = null;

        while ((root = parser.list()) != null) {

            try {
                Visitor e = new Evaluator();
                VObject vo = (VObject) root.accept(e); 
                System.out.println(vo.evaluate(null));
                System.out.println();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                System.out.println("AST:");
                Visitor astPrinter = new ASTPrinter(System.out);
                root.accept(astPrinter);
                System.out.println();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
