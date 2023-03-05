package Course_work;
import java.time.LocalDateTime;

public class MonthlyTask extends Task {
    public MonthlyTask(String title, Type type, String description, LocalDateTime date) throws TaskNotFoundException {
        super(title, type, description, date);
    }

    @Override
    public boolean appearsIn(LocalDateTime date) {
        return getDateTime().getDayOfMonth() == (date.getDayOfMonth());
    }
}
