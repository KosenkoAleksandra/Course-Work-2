package Course_work;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {

    public OneTimeTask(String title, Type type, String description, LocalDateTime date) throws TaskNotFoundException {
        super(title, type, description, date);
    }

    @Override
    public boolean appearsIn(LocalDateTime date) {
        return getDateTime().toLocalDate().equals(date.toLocalDate());
    }
}
