package interp;

import ast.*;

public interface Visitor {
    VObject visit(AdditionNode node, Environment e);
    VObject visit(IntegerNode  node, Environment e);
    VObject visit(SymbolNode   node, Environment e);
    VObject visit(LetNode      node, Environment e);
    VObject visit(LambdaNode   node, Environment e);
    VObject visit(ListNode     node, Environment e);
}

