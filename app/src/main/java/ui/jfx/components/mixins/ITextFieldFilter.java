
package ui.jfx.components.mixins;

import javafx.scene.control.TextFormatter;

/**
  * Text Field Filter Mixin
  *
  */
public interface ITextFieldFilter {
    String getFilter();
    TextFormatter<String> getFormatter();
    default public TextFormatter<String> createTextFormatter() {
        return new TextFormatter<String>(change -> change.getText().matches(getFilter()) ? change : null);
    }
}
