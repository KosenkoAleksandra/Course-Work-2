package Course_task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст");
        String text = scanner.nextLine();
        String[] words = text.split(" ");
        System.out.println("Вы ввели " + words.length + " слов");
        Arrays.stream(text.split(" "))
                .collect(Collectors.toMap(k -> k, v -> 1, Integer::sum))
                .entrySet().stream()
                .sorted((e1, e2) -> {
                    int value = Objects.compare(e1.getValue(), e2.getValue(),
                            Comparator.reverseOrder());
                    if (value == 0)
                        value = Objects.compare(e1.getKey(), e2.getKey(),
                                String.CASE_INSENSITIVE_ORDER);
                    return value;
                })
                .forEach(System.out::println);
    }
    }





