package symbolTableJinja;

public class ParameterSymbol extends VariableSymbol {
    private int position;
    private boolean hasDefault;

    public ParameterSymbol(String name, int line, int column,
                           String valueType, int position) {
        super(name, line, column, valueType);
        this.position = position;
        this.hasDefault = false;
    }

    public int getPosition() {
        return position;
    }

    public boolean hasDefault() {
        return hasDefault;
    }

    public void setHasDefault(boolean hasDefault) {
        this.hasDefault = hasDefault;
    }
}
