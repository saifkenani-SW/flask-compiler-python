package symbolTableJinja;

import java.util.ArrayList;
import java.util.List;

public class MacroSymbol extends JinjaSymbol {
    private List<ParameterSymbol> parameters;
    private List<JinjaSymbol> bodySymbols;

    public MacroSymbol(String name, int line, int column) {
        super(name, JinjaSymbolType.MACRO, line, column);
        this.parameters = new ArrayList<>();
        this.bodySymbols = new ArrayList<>();
    }

    public void addParameter(ParameterSymbol param) {
        parameters.add(param);
    }

    public List<ParameterSymbol> getParameters() {
        return parameters;
    }

    public void addBodySymbol(JinjaSymbol symbol) {
        bodySymbols.add(symbol);
    }

    public List<JinjaSymbol> getBodySymbols() {
        return bodySymbols;
    }
}
