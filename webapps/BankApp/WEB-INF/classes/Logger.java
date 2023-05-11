import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Logger implements Serializable{
    private List<String> actions;
    private List<LocalDateTime> timestamps;
    private static final long serialVersionUID = 1L;

    public Logger() {
        actions = new ArrayList<>();
        timestamps = new ArrayList<>();
    }

    public void logAction(String action) {
        LocalDateTime timestamp = LocalDateTime.now();
        actions.add(action);
        timestamps.add(timestamp);
    }

    public List<String> getActions() {
        return actions;
    }

    public List<LocalDateTime> getTimestamps() {
        return timestamps;
    }

    public void clearLog() {
        actions.clear();
        timestamps.clear();
    }
}