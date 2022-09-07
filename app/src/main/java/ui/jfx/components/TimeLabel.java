package ui.jfx.components;

// Imports
import java.text.SimpleDateFormat;
import java.util.Date;

// JavaFX
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

public class TimeLabel extends Label {
    private StringProperty format = new SimpleStringProperty();
    private volatile boolean enough = false;
    private Thread timer = new Thread(() -> {
        while (!enough) {
            Platform.runLater(()-> {
                this.setText(getDateTime());
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
        }
    });

    public void start() {
        timer.setDaemon(true);
        timer.start();
    }

    public TimeLabel() {
        this.format.set("EEEE h:mm a");
        start();
    }
    public TimeLabel(String format) {
        this.format.set(format);
        start();
    }

    public String getDateTime() {
        SimpleDateFormat date = new SimpleDateFormat(getFormat());
        return date.format(new Date());
    }

    // Properties
    public StringProperty formatProperty() { return format; }
    public String getFormat(){ return format.get(); }
    public void setFormat(String fmt) { format.set(fmt); }

    public Thread getTimer() { return timer; }
}
