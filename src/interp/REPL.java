package interp;

import parser.Parser;
import parser.ParseException;
import ast.ASTNode;
import ast.ASTPrinter;
import trans.ASTTransformer;
import trans.Let2LambdaTransformer;
import util.*;

public class REPL {

    private static void printLogo() {
        System.out.print(
            "   _______   ____  ____    __                    \n" + 
            "  / __/ _ | / __/ / __/___/ /  ___ __ _  ___     \n" + 
            " / _// __ |/ _/  _\\ \\/ __/ _ \\/ -_)  ' \\/ -_)\n" + 
            "/_/ /_/ |_/___/ /___/\\__/_//_/\\__/_/_/_/\\__/  \n" + 
            "                                                 \n");

        System.out.println("FAE Scheme, 2012");
    }

    public static void main (String[] args) {
        printLogo();

        JVMOptionParser optionParser= JVMOptionParser.instance();
        Parser parser               = new Parser (System.in);
        ASTTransformer transformer  = new Let2LambdaTransformer();
        Visitor astPrinter          = new ASTPrinter(System.out);
        Visitor repl                = new Evaluator();
        ASTNode root                = null;
        
        boolean transformLets = !optionParser.isDefined("no-let2lambda")
                             || "false".equals(optionParser.value("no-let2lambda"));
        // TODO: log transformLets

        for (;;) {
            System.out.print("> ");
            try {
                if ((root = parser.begin()) == null)
                    break;

                if (transformLets) {
                    // turn lets to lambda applications
                    root = transformer.transform(root);
                }

                // print the AST
                root.accept(astPrinter, null);
                System.out.println();

                // evaluate the expression
                VObject vo = root.accept(repl, new Environment());
                System.out.println(vo);
                System.out.println();

            }
            catch (Exception e) {
                e.printStackTrace();
                parser.ReInit(System.in);
            }
            finally {
            }
        }
    }

}
