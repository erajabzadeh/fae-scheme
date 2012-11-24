package interp;

import ast.*;

public interface Visitor {
    VObject visit(AdditionNode       node, Environment e);
    VObject visit(SubtractionNode    node, Environment e);
    VObject visit(MultiplicationNode node, Environment e);
    VObject visit(DivisionNode       node, Environment e);
    VObject visit(NumberNode   node, Environment e);
    VObject visit(SymbolNode   node, Environment e);
    VObject visit(LetNode      node, Environment e);
    VObject visit(LambdaNode   node, Environment e);
    VObject visit(ListNode     node, Environment e);
}

