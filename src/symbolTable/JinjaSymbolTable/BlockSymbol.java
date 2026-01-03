package symbolTable.JinjaSymbolTable;

import java.util.ArrayList;
import java.util.List;

// ===== رمز البلوك =====
public class BlockSymbol extends JinjaSymbol {
    private List<JinjaSymbol> contentSymbols;
    private boolean isOverridden;  // تم التجاوز في قالب ابن

    public BlockSymbol(String name, int line, int column) {
        super(name, JinjaSymbolType.BLOCK, line, column);
        this.contentSymbols = new ArrayList<>();
        this.isOverridden = false;
    }

    public void addContentSymbol(JinjaSymbol symbol) {
        contentSymbols.add(symbol);
    }

    public List<JinjaSymbol> getContentSymbols() {
        return contentSymbols;
    }

    public boolean isOverridden() {
        return isOverridden;
    }

    public void setOverridden(boolean overridden) {
        isOverridden = overridden;
    }
}
