package interp;

import parser.Parser;
import parser.ParseException;
import ast.ASTNode;
import ast.ASTPrinter;
import trans.ASTTransformer;
import trans.Let2LambdaTransformer;

public class Interpreter {

    public static void main (String[] args) throws ParseException {
        Parser parser               = new Parser (System.in);
        ASTTransformer transformer  = new Let2LambdaTransformer();
        Visitor astPrinter          = new ASTPrinter(System.out);
        Visitor repl                = new Evaluator();

        ASTNode root  = null;

        while ((root = parser.list()) != null) {

            try {
                // turn lets to lambda applications
                root = transformer.transform(root);

                // print the AST
                //System.out.println(">AST:");
                root.accept(astPrinter, null);
                System.out.println();

                // evaluate the expression
                VObject vo = root.accept(repl, new Environment());
                //System.out.println(">Answer: ");
                System.out.println(vo);
                System.out.println();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
