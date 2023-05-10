import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private List<String> actions;
    private List<LocalDateTime> timestamps;

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