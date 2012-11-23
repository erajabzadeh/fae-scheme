package ast;

import interp.VObject;
import interp.Visitor;

import java.io.PrintStream;

public class ASTPrinter implements Visitor {

    private PrintStream printer;

    public ASTPrinter(final PrintStream out) {
        this.printer = out;
    }

    @Override
        public VObject visit(AdditionNode node) {
            this.printer.println(this.dent + "[add");
            this.indent(); 

            node.getLHS().accept(this);
            node.getRHS().accept(this);

            this.outdent();
            this.printer.println(this.dent + "]");

            return null;
        }

    @Override
        public VObject visit(IntegerNode node) {
            this.printer.println(
                    this.dent + 
                    "[num " + node.getValue() + "]"
                    );

            return null;
        }

    @Override
        public VObject visit(SymbolNode node) {
            this.printer.println(this.dent + "[id " + node.getId() + "]");

            return null;
        }

    @Override
        public VObject visit(LetNode node) {
            this.printer.println(this.dent + "[let"); 

            this.indent();
            for (ASTNode child : node.getChildren())
                child.accept(this);
            this.outdent();

            this.printer.println(this.dent + "]");

            return null;
        }

    @Override
        public VObject visit(LambdaNode node) {
            this.printer.println(this.dent + "[lambda");

            this.indent();
            for (ASTNode child : node.getChildren())
                child.accept(this);
            this.outdent();

            this.printer.println(this.dent + "]");

            return null;
        }

    @Override
        public VObject visit(ListNode node) {
            this.printer.println(this.dent + "[list");

            this.indent();
            for (ASTNode child : node.getChildren())
                child.accept(this);
            this.outdent();

            this.printer.println(this.dent + "]");

            return null;
        }

    private void indent() {
        this.dent += this.indentString;
    }

    private void outdent() {
        this.dent = this.dent.substring(this.indentString.length());
    }

    private String indentString = "   ";
    private String dent         = "";
}
