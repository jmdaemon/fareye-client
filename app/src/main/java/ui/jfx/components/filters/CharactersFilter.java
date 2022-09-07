package ui.jfx.components.filters;

// Imports
import ui.jfx.components.mixins.ITextFieldFilter;

// JavaFX
import javafx.scene.control.TextFormatter;

public class CharactersFilter implements ITextFieldFilter {
    private static String FILTER;
    private TextFormatter<String> formatter;

    public CharactersFilter() {
        FILTER = "[A-Za-z]";
        formatter = createTextFormatter();
    }

    public CharactersFilter(String filter) {
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
