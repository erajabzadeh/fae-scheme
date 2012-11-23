package interp;

import ast.*;

public interface Visitor {
    VObject visit(AdditionNode node);
    VObject visit(IntegerNode  node);
    VObject visit(SymbolNode   node);
    VObject visit(LetNode      node);
    VObject visit(LambdaNode   node);
    VObject visit(ListNode     node);
}

