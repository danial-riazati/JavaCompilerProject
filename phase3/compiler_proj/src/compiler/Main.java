package compiler;

import compiler.AST.ASTNode;
import compiler.AST.Program;
import compiler.codegen.*;
import compiler.Vtable.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        PrintStream stream;
        try {
            scanner = new Scanner(new File(args[0]));
            stream = new PrintStream(new FileOutputStream(args[1]));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Program prog;
        try {
            parser p = new parser(scanner);
            p.parse();
            prog = p.getRoot();
        } catch (Exception e) {
            stream.print("Syntax Error");
            stream.close();
            return;
        }
        // printTree(prog);
        try {
            vtableAnalysis(prog);
            generateCode(prog, stream);
        } catch (Exception e) {
            stream.print("Semantic Error");
            stream.close();
        }


    }

    private static void generateCode(Program prog, PrintStream stream) throws Exception {
        prog.accept(new CodeGenVisitor(stream));
        stream.close();
    }

    private static void vtableAnalysis(Program prog) throws Exception {
        prog.accept(new VtableGenerator());
    }

    private static void printTree(ASTNode node) {
        for (ASTNode child : node.getChildren()) {
            System.out.println(child + " ---------> " + node);
        }
        for (ASTNode child : node.getChildren()) {
            printTree(child);
        }

    }
}
