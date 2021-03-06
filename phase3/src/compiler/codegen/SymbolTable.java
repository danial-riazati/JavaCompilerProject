package compiler.codegen;
import java.util.ArrayList;

public class SymbolTable implements Symbol {
    private final ArrayList<Scope> scopes = new ArrayList<>();
    private Scope currentScope;

    public Scope getCurrentScope() {
        return currentScope;
    }

    public void enterScope(String id) {
        Scope newScope = new Scope(id);
        scopes.add(newScope);
        currentScope = newScope;
    }

    public void leaveCurrentScope() {
        if (currentScope != null)
            scopes.remove(currentScope);
        currentScope = scopes.get(scopes.size() - 1);
    }

    void put(String id, SymbolInfo si) throws Exception {
        if (currentScope.getVariables().containsKey(id)) {
            throw new Exception("current scope already contains an entry for " + id);
        }

        currentScope.getVariables().put(id, si);
    }

    public Symbol get(String id) throws Exception {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).getVariables().containsKey(id))
                return scopes.get(i).getVariables().get(id);
        }
        throw new Exception("variable " + id + " did'nt declared ");
    }

    String getScopeNameOfIdentifier(String id) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).getVariables().containsKey(id))
                return scopes.get(i).getName();
        }
        return currentScope.getName();
    }

    public String getCurrentScopeName() {
        return currentScope.getName();
    }

    public ArrayList<Scope> getScopes() {
        return scopes;
    }

}