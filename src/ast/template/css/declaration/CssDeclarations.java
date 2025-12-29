package ast.template.css.declaration;

import java.util.List;

public final class CssDeclarations {
    private final List<CssDeclaration> declarations;

    public CssDeclarations(List<CssDeclaration> declarations) {
        this.declarations =
                /*cssDeclarations;*/List.copyOf(declarations);
    }

    public List<CssDeclaration> getDeclarations() {
        return declarations;
    }
}
