package trans;

import ast.ASTNode;

public interface ASTTransformer {
    ASTNode transform (final ASTNode root);
}
