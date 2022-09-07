package ui.jfx.components.filters;

// Imports
import ui.jfx.components.mixins.ITextFieldFilter;

// JavaFX
import javafx.scene.control.TextFormatter;

public class NaturalsFilter implements ITextFieldFilter {
    private static String FILTER;
    private TextFormatter<String> formatter;

    public NaturalsFilter() {
        FILTER = "[0-9]";
        formatter = createTextFormatter();
    }

    public NaturalsFilter(String filter) {
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
