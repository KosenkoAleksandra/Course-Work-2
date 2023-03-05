package Course_work;

public class ValidateUtils {
    public static String validateString(String value) {
        if (value == null || value.isBlank() || value.isEmpty()) {
            throw new TaskNotFoundException("Некорректный ввод!");
        } else {
            return value;
        }
    }
}
