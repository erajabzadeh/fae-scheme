package trans;

import ast.*;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class Let2LambdaTransformer implements ASTTransformer {

    @Override
    public ASTNode transform (ASTNode root) {

        if (root instanceof LetNode) {

            LetNode letNode   = (LetNode) root;
            ListNode bindings = letNode.getBindings();

            // only one binding per let (FAE)
            SimpleNode pair = (SimpleNode) bindings.getChildAt(0);
            
            final ASTNode param = pair.getChildAt(0),
                          body  = this.transform(letNode.getBody()),
                          arg   = this.transform(pair.getChildAt(1));

            root = new ListNode() {{
                addChild(new LambdaNode(param, body));
                addChild(arg);
            }};
        }
        else if (root instanceof SimpleNode) {

            ListIterator<ASTNode> it = ((SimpleNode) root).getChildren().listIterator();
            while (it.hasNext())
                it.set(this.transform(it.next()));
        }
        // ignore any other type of node

        return root;
    }

    public static void main (String[] args) throws parser.ParseException {
        parser.Parser parser = new parser.Parser(System.in);
        interp.Visitor astPrinter = new ast.ASTPrinter(System.out);
        
        ASTNode root = parser.list();

        System.out.println("==before==");
        root.accept(astPrinter, null);

        root = new Let2LambdaTransformer().transform(root);

        System.out.println("==after==");
        root.accept(astPrinter, null);
    }
}
