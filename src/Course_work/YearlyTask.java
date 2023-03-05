package Course_work;
import java.time.LocalDateTime;

public class YearlyTask extends Task {
    public YearlyTask(String title, Type type, String description, LocalDateTime date) throws TaskNotFoundException {
        super(title, type, description, date);
    }

    @Override
    public boolean appearsIn(LocalDateTime date) {
        return getDateTime().getDayOfYear() == (date.getDayOfYear());
    }
}
