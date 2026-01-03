package symbolTable.JinjaSymbolTable;

import java.util.ArrayList;
import java.util.List;

// ===== رمز الدالة =====
public class FunctionSymbol extends JinjaSymbol {
    private List<String> parameterTypes;
    private String returnType;

    public FunctionSymbol(String name, int line, int column) {
        super(name, JinjaSymbolType.FUNCTION, line, column);
        this.parameterTypes = new ArrayList<>();
        this.returnType = "any";
    }

    public void addParameterType(String type) {
        parameterTypes.add(type);
    }

    public List<String> getParameterTypes() {
        return parameterTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
