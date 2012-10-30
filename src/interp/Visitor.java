package interp;

import ast.*;

public interface Visitor {
    Object visit(AdditionNode node);
    Object visit(IntegerNode  node);
    Object visit(SymbolNode   node);
    Object visit(LetNode      node);
    Object visit(LambdaNode   node);
    Object visit(ListNode     node);
}

