package compiler.codegen;

import compiler.AST.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Argument {
    private Type type;
    private String name;

    Argument(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Argument argument = (Argument) o;
        return Objects.equals(name, argument.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }
}

public class Signature {
    private Type returnType;
    private String name;
    private List<Argument> args = new ArrayList<>();

    Signature(Type returnType, String name) {
        this.returnType = returnType;
        this.name = name;
    }

    void addArgs(List<Argument> arguments) {
        args.addAll(arguments);
    }

    Type getReturnType() {
        return returnType;
    }

    public String getName() {
        return name;
    }

    List<Argument> getArgs() {
        return args;
    }


    @Override
    public int hashCode() {
        return Objects.hash(returnType, name, args.size());
    }
}