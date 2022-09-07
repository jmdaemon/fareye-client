
package ui.jfx.components.filters;

// Imports
import ui.jfx.components.mixins.ITextFieldFilter;

// JavaFX
import javafx.scene.control.TextFormatter;

public class NumericFilter implements ITextFieldFilter {
    private static String FILTER;
    private TextFormatter<String> formatter;

    public NumericFilter() {
        FILTER = "[0-9]*(\\.[0-9]*)?";
        formatter = createTextFormatter();
    }

    public NumericFilter(String filter) {
        FILTER = filter;
        formatter = createTextFormatter();
    }

    // Mixin
    @Override
    public String getFilter() { return FILTER; }

    @Override
    public TextFormatter<String> getFormatter() {
        return formatter;
    }
}
