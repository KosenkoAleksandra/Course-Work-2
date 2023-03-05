package Course_work;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private static int idGenerator = 1;
    private String title;
    private Type type;
    private final int id = idGenerator;
    private LocalDateTime dateTime;
    private String description;

    public Task(String title, Type type, String description, LocalDateTime localDateTime) throws TaskNotFoundException {
        this.title = ValidateUtils.validateString(title);
        this.type = type;
        this.dateTime = localDateTime;
        this.description = ValidateUtils.validateString(description);
        idGenerator++;
    }

    //region getters-setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idGenerator == task.idGenerator && id == task.id && Objects.equals(title, task.title) && type == task.type && Objects.equals(dateTime, task.dateTime) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenerator, title, type, id, dateTime, description);
    }

    @Override
    public String toString() {
        return id + " Название задачи: " + title + ", тип -  " + type + ", время " + dateTime + ", описание: " + description;
    }
    public abstract boolean appearsIn(LocalDateTime localDateTime);

}
