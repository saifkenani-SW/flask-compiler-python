package symbolTable.JinjaSymbolTable;

import java.util.ArrayList;
import java.util.List;

// ===== رمز الفلتر =====
public class FilterSymbol extends JinjaSymbol {
    private List<String> parameterTypes;

    public FilterSymbol(String name, int line, int column) {
        super(name, JinjaSymbolType.FILTER, line, column);
        this.parameterTypes = new ArrayList<>();
    }

    public void addParameterType(String type) {
        parameterTypes.add(type);
    }

    public List<String> getParameterTypes() {
        return parameterTypes;
    }
}
