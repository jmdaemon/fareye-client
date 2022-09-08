package ui.jfx.components.filters;

public class NumericFilter extends Filter {
    public static String DEFAULT_FILTER = "[0-9]*(\\.[0-9]*)?";
    public NumericFilter() {
        super(DEFAULT_FILTER);
    }
}
