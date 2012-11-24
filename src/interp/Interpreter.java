package interp;

import parser.Parser;
import parser.ParseException;
import ast.ASTNode;
import ast.ASTPrinter;
import trans.ASTTransformer;
import trans.Let2LambdaTransformer;

public class Interpreter {

    public static void main (String[] args) throws ParseException {
        Parser parser = new Parser (System.in);
        ASTNode root  = null;

        while ((root = parser.list()) != null) {

            try {
                Visitor e = new Evaluator();
                
                ASTTransformer transformer = new Let2LambdaTransformer();
                root = transformer.transform(root);
                
                VObject vo = root.accept(e, new Environment());

                System.out.println(">Answer: ");
                System.out.println(vo);
                System.out.println();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                System.out.println(">AST:");
                
                Visitor astPrinter = new ASTPrinter(System.out);
                root.accept(astPrinter, null);
                System.out.println();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        
        }
    }
}
