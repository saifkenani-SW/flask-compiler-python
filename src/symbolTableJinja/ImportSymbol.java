package symbolTableJinja;

import java.util.ArrayList;
import java.util.List;

public class ImportSymbol extends JinjaSymbol {
    private String templatePath;
    private String alias;
    private List<String> importedNames;

    public ImportSymbol(String templatePath, int line, int column) {
        super(templatePath, JinjaSymbolType.IMPORT, line, column);
        this.templatePath = templatePath;
        this.importedNames = new ArrayList<>();
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<String> getImportedNames() {
        return importedNames;
    }

    public void addImportedName(String name) {
        importedNames.add(name);
    }

    public boolean isFromImport() {
        return !importedNames.isEmpty();
    }
}
