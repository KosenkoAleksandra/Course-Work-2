package Course_work;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;

public class WeeklyTask extends Task {
    public WeeklyTask(String title, Type type, String description, LocalDateTime date) throws TaskNotFoundException {
        super(title, type, description, date);
    }
    @Override
    public boolean appearsIn(LocalDateTime date) {
        return getDateTime().getDayOfWeek().equals(date.getDayOfWeek());
    }
}
