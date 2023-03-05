package Course_work;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class TaskService {
    private static final Map<Integer, Task> taskMap = new HashMap<>();
    public static void addTask(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println("Введите название задачи: ");
            String title = ValidateUtils.validateString(scanner.nextLine());
            System.out.println("Введите описание задачи: ");
            String description = ValidateUtils.validateString(scanner.nextLine());
            System.out.println("Введите тип задачи: 0 - рабочая или 1 - личная");
            Type type = Type.values()[scanner.nextInt()];
            System.out.println("Введите повторяемость задачи: 0 - однократная, 1 - ежедневная, 2 - еженедельная, 3 - ежемесячная, 4 - ежегодная");
            int entryDate = scanner.nextInt();
            System.out.println("Введите дату dd.MM.yyyy HH:mm");
            scanner.nextLine();
            createEvent(scanner, title, type, description, entryDate);
            System.out.println("Для выхода нажмите Enter\n");
            scanner.nextLine();
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void createEvent(Scanner scanner, String title, Type type, String description, int entryDate){
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Task task = null;
            try {
                task = createTask(entryDate, title, type, description, eventDate);
                System.out.println("Задача создана " + task);
            } catch (TaskNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат ввода dd.MM.yyyy HH:mm и попробуйте еще раз");
            createEvent(scanner, title, type, description, entryDate);
        }
    }
    private static List<Task> findTasksByDate(LocalDate date){
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskMap.values()) {
            if (task.appearsIn(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }
    private static Task createTask (int entryDate, String title, Type type, String description, LocalDateTime localDateTime) throws TaskNotFoundException {
      return switch (entryDate) {
          case 0:
                OneTimeTask oneTimeTask = new OneTimeTask(title, type, description, localDateTime);
                taskMap.put(oneTimeTask.getId(), oneTimeTask);
                break;
          case 1:
                DailyTask dailyTask = new DailyTask(title, type, description, localDateTime);
                taskMap.put(dailyTask.getId(), dailyTask);
                break;

          case 2:
                WeeklyTask weeklyTask = new WeeklyTask(title, type, description, localDateTime);
                taskMap.put(weeklyTask.getId(), weeklyTask);
                break;

          case 3:
                MonthlyTask monthlyTask = new MonthlyTask(title, type, description, localDateTime);
                taskMap.put(monthlyTask.getId(), monthlyTask);
                break;

          case 4:
                YearlyTask yearlyTask = new YearlyTask(title, type, description, localDateTime);
                taskMap.put(yearlyTask.getId(), yearlyTask);
                break;

        default: System.out.println("Введено неверное значение");
        };
    }
    public static void removeTask(Scanner scanner) {
        System.out.println("Активные задачи\n");
        printActiveTasks();
        System.out.println("Для удаления введите id задачи\n");
        int id = scanner.nextInt();
        if (taskMap.containsKey(id)) {
            Task deleteTask = taskMap.get(id);
            System.out.println("Задача " + id + " удалена\n");
        } else {
            System.out.println("Задача по id не найдена\n");
        }
    }
    public static void getAllByDate(Scanner scanner) {
        System.out.println("Введите дату в формате dd.MM.yyyy");
        try {
            String date = scanner.next();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate requestedDate = LocalDate.parse(date, dateTimeFormatter);
            List<Task> foundTask = findTasksByDate(requestedDate);
            System.out.println("Задачи на " + requestedDate + ": ");
            for (Task task : foundTask) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат ввода dd.MM.yyyy и попробуйте еще раз");
        }
        scanner.nextLine();
        System.out.println("Для выхода нажмите Enter\n");
    }
    private static void printActiveTasks () {
        for (Task task : taskMap.values()) {
            System.out.println(task);
        }
    }
}
