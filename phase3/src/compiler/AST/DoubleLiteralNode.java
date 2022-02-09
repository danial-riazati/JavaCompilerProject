package compiler.AST;

public class DoubleLiteralNode extends Literal {
    private double value;


    public DoubleLiteralNode(Double value) {
        super(PrimitiveType.DOUBLE);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
